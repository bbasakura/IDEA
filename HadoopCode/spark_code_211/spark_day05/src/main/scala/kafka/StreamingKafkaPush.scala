package kafka

import java.util.Date

import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


//集成Kafka，采用Push方式读取数据，对每批次（时间为1秒）数据进行词频统计，将统计结果输出到控制台。
object StreamingKafkaPush {

  def main(args: Array[String]): Unit = {

    // 1.构建流式上下文实例对象StreamingContext，用于读取流式的数据和调度Batch Job执行
    val ssc: StreamingContext = {
      // a. 创建SparkConf实例对象，设置Application相关信息
      val sparkConf = new SparkConf()
        .setMaster("local[3]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
      // b. 创建StreamingContext实例，传递Batch Interval（时间间隔：划分流式数据）
      val context: StreamingContext = new StreamingContext(sparkConf, Seconds(5))
      // 设置日志级别
      context.sparkContext.setLogLevel("WARN")
      // c. 返回上下文对象
      context
    }


    // 2、从流式数据源读取数据，此处TCP Socket读取数据
    /*
      def createStream(
        ssc: StreamingContext,
        zkQuorum: String,
        groupId: String,
        topics: Map[String, Int],
        storageLevel: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER_2
      ): ReceiverInputDStream[(String, String)]
     */
    // 表示KafkaCluster依赖Zookeeper集群地址信息（保存ZNODE信息）
    val zkQuorum: String = "hadoop01:2181,hadoop02 :2181,hadoop03:2181"
    // 消费组ID
    val groupId: String = "group-id-00001"
    // 设置消费Topic名称及相应分区数目，Map of (topic_name to numPartitions) to consume
    val topics: Map[String, Int] = Map("testTopic" -> 3)
    // 采用Receiver方式读取Kafka Topic中的数据， 在Topic中每条数据，由Key和Value组成，Value表示Message信息
    val kafkaDStream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
      ssc, //
      zkQuorum, //
      groupId, //
      topics, //
      storageLevel = StorageLevel.MEMORY_AND_DISK
    )
    // 获取从Kafka读取数据的Message??????????????????读取一个元祖的第二个元素》？？？？是的是的
    val inputDStream: DStream[String] = kafkaDStream.map(pair => pair._2)


    // TODO: 3、对接收每批次流式数据，进行词频统计WordCount
    /*
      def transform[U: ClassTag](transformFunc: RDD[T] => RDD[U]): DStream[U]
     */
    val wordCountDStream: DStream[(String, Int)] = inputDStream.transform(rdd => {   // transform表示对DStream中每批次数据RDD进行操作
      // val xx: RDD[String] = rdd
      rdd
        // a. 过滤“脏数据”
        .filter(line => null != line && line.trim.length > 0)
        // b. 分割为电磁
        .flatMap(line => line.split("\\s+").filter(word => word.length > 0))
        // c. 转换为二元组
        .mapPartitions{iter => iter.map(word => (word, 1))}
        // d. 聚合统计
        .reduceByKey((a, b)  => a + b)
    })
    // 在DStream中，能对RDD操作的不要对DStream操作。


    //  4、将分析每批次结果数据输出，此处答应控制台
    // 对DStream中每批次结果RDD数据进行输出操作，
    wordCountDStream.foreachRDD{ (rdd, time) =>
      // 使用lang3包下FastDateFormat日期格式类，属于线程安全的
      val batchTime = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss")
        .format(new Date(time.milliseconds))
      println("-------------------------------------------")
      println(s"Time: $batchTime")
      println("-------------------------------------------")
      // TODO: 先判断RDD是否有数据，有数据在输出哦
      if(!rdd.isEmpty()){
        rdd
          // 对于结果RDD输出，需要考虑降低分区数目
          .coalesce(1)
          // 对分区数据操作
          .foreachPartition{iter =>
          iter.foreach(item => println(item))
        }
      }
    }


    //  5、对于流式应用来说，需要启动应用，正常情况下启动以后一直运行，直到程序异常终止或者人为干涉
    ssc.start()  // 启动接收器Receivers，作为Long Running Task（线程） 运行在Executor
    ssc.awaitTermination()

    // 6、结束Streaming应用执行
    ssc.stop(stopSparkContext = true, stopGracefully = true)
  }





}
