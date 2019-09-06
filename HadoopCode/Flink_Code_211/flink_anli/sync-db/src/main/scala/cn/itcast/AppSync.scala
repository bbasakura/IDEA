package cn.itcast

import java.util.Properties

import cn.itcast.bean.{Canal, HbaseOperation}
import cn.itcast.config.GlobalConfig
import cn.itcast.task.ProcessData
import cn.itcast.util.HbaseUtil
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09
/**
  * 2019/9/5
  */
object AppSync {

  def main(args: Array[String]): Unit = {

    /**
      * 1.获取执行环境
      * 2.设置处理时间
      * 3.设置检查点
      * 4.整合kafka（配置类）
      * 5.数据转换，新建bean:
      *   (1)Canal
      *   (2)ColumnValuePair
      *   (3)HbaseOperation
      * 6.设置水位线（系统时间）
      * 7.数据处理
      * 8.触发执行
      */
    //1.获取执行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //2.设置处理时间-----处理时间是什么？？无延时的？？？---是的
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)

    //3.设置检查点
    env.setStateBackend(new FsStateBackend("hdfs://hadoop01:8020/tmp/sync-checkpoint"))
    env.enableCheckpointing(6000)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    env.getCheckpointConfig.setCheckpointTimeout(60000)
    env.getCheckpointConfig.setFailOnCheckpointingErrors(false)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    //任务取消时，会保留检查点，需要手动删除
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)

    //4.整合kafka（配置类）
    val properties = new Properties()
    properties.setProperty("bootstrap.servers",GlobalConfig.kafkaBroker)
    properties.setProperty("zookeeper.connect",GlobalConfig.kafkaZk)
    properties.setProperty("group.id",GlobalConfig.kafkaGroupID)

    val kafkaSource: FlinkKafkaConsumer09[String] = new FlinkKafkaConsumer09[String](GlobalConfig.kafkaTopic,new SimpleStringSchema(),properties)
    //kafkaSource.setStartFromEarliest()  //表示从头消费>>>>>造成重复消费？？？？？？
    val source: DataStream[String] = env.addSource(kafkaSource)

    //5.数据转换
    val canals: DataStream[Canal] = source.map(line => {
      val canal: Canal = Canal.parseStr(line)
      canal
    })

    //6.设置水位线（系统时间）-----注意写法，，没有延时
    val waterCanals: DataStream[Canal] = canals.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[Canal] {
      override def getCurrentWatermark: Watermark = {

        //直接返回水印
        new Watermark(System.currentTimeMillis())
      }

      override def extractTimestamp(element: Canal, previousElementTimestamp: Long): Long = {
        //当前时间
        System.currentTimeMillis()
      }
    })

    //7.数据处理
    val value: DataStream[HbaseOperation] = ProcessData.process(waterCanals)
    //执行hbase的插入操作
    value.map(line=>{

      line.eventType match {

        case "DELETE"=>
          HbaseUtil.delByRowkey(line.tableName,line.family,line.rowkey)
          //其他：增加和更新是一个
        case _ =>
          HbaseUtil.putDataByRowkey(line.tableName,line.family,line.columnName,line.columnValue,line.rowkey)
      }
    })

    //8.触发执行
    env.execute()
  }

}
