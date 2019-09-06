package cn.itcast

import java.util.Properties

import cn.itcast.bean.{Message, UserBrowse}
import cn.itcast.config.GlobalConfig
import cn.itcast.task._
import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09

/**
  * 2019/9/4
  */
object AppStream {

  /** 开发步骤：
    * 1.获取流处理执行环境
    * 2.设置事件时间（提取的是消息源本身的时间）
    * 3.设置检查点
    * 4.整合kafka
    * 5.数据转换
    * 6.设置水位线
    * 7.任务执行
    * 8.触发执行
    */
  def main(args: Array[String]): Unit = {

    //1.获取流处理执行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    //2.设置事件时间（提取的是消息源本身的时间）
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    //3.设置检查点
    //设置检查点保存目录
    env.setStateBackend(new FsStateBackend("hdfs://hadoop01:8020/stream-checkpoint"))
    //周期性触发时间
    /*env.enableCheckpointing(6000)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    env.getCheckpointConfig.setCheckpointTimeout(6000)
    env.getCheckpointConfig.setFailOnCheckpointingErrors(false)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    */
    env.enableCheckpointing(6000)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE) //强一致性
    env.getCheckpointConfig.setCheckpointTimeout(60000) //超时时间：1分钟
    env.getCheckpointConfig.setFailOnCheckpointingErrors(false) //检查点制作失败，任务继续运行
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1) //制作检查点的最大线程
    //任务取消，保留检查点，需要手动删除
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)

    //4.整合kafka
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", GlobalConfig.kafkaBroker) //broker地址
    properties.setProperty("zookeeper.connect", GlobalConfig.kafkaZk) //zookeeper的地址
    properties.setProperty("group.id", GlobalConfig.kafkaGroupID) //消费组 ---不指定会随机生成？？

    val kafkaSource: FlinkKafkaConsumer09[String] = new FlinkKafkaConsumer09[String](GlobalConfig.kafkaTopic, new SimpleStringSchema(), properties)

    //添加source
    val source: DataStream[String] = env.addSource(kafkaSource)

    //5.数据转换------只是为了得到一个结构化的数据，不一定非要是JSON
    val messages: DataStream[Message] = source.map(line => {
      //字符串转json
      val json: JSONObject = JSON.parseObject(line)
      val count: Int = json.getIntValue("count")
      val message: String = json.getString("message")
      val timestamp: Long = json.getLong("timestamp")
      val browse: UserBrowse = UserBrowse.parseStr(message)
      //返回要存储的对象Message
      Message(count, browse, timestamp)
    })

    //6.设置水位线(周期性)------周期性的水印

    val waterData: DataStream[Message] = messages.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[Message] {
      val delayTime: Long = 2000L
      var currentTimestamp: Long = 0L

      //再获取水位线
      override def getCurrentWatermark: Watermark = {
        new Watermark(currentTimestamp - delayTime)
      }

      //先抽取时间
      override def extractTimestamp(element: Message, previousElementTimestamp: Long): Long = {
        val timestamp: Long = element.userBrowse.timestamp
        //谁大取谁，保证时间轴，一直往前走
        currentTimestamp = Math.max(timestamp, currentTimestamp)
        timestamp
      }
    })
    //7.任务执行
    /**
      * 1.实时频道的热点统计
      * 2.实时频道的pvuv分析
      * 3.实时频道的用户新鲜度
      * 4.实时频道的地域分析
      * 5.实时频道的网络类型分析
      * 6.实时频道的浏览器类型分析
      */
    // 1.实时频道的热点统计
    ChannelHotTask.process(waterData)
    //2.实时频道的pvuv分析
    ChannelPvuvTask.process(waterData)
    //3.实时频道的用户新鲜度
    ChannelFreshnessTask.process(waterData)
    //4.实时频道的地域分析
    ChannelRegionTask.process(waterData)
    //5.实时频道的网络类型分析
    ChannelNetworkTask.process(waterData)
    //6.实时频道的浏览器类型分析
    ChannelBrowseTask.process(waterData)

    //8.触发执行
    env.execute()
  }
}
