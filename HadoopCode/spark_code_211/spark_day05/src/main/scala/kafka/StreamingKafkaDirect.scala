package kafka
import java.util.Date

import kafka.serializer.StringDecoder
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 集成Kafka，采用Direct式读取数据，对每批次（时间为1秒）数据进行词频统计，将统计结果输出到控制台。
  */
object StreamingKafkaDirect {

  def main(args: Array[String]): Unit = {

    // TODO: 1、构建流式上下文实例对象StreamingContext，用于读取流式的数据和调度Batch Job执行
    val ssc: StreamingContext = {
      // a. 创建SparkConf实例对象，设置Application相关信息
      val sparkConf = new SparkConf()
        .setMaster("local[3]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
        //TODO: 设置每秒钟读取Kafka中Topic最大数据量
        .set("spark.streaming.kafka.maxRatePerPartition", "10000")
      // b. 创建StreamingContext实例，传递Batch Interval（时间间隔：划分流式数据）
      val context: StreamingContext = new StreamingContext(sparkConf, Seconds(5))
      // 设置日志级别
      context.sparkContext.setLogLevel("WARN")
      // c. 返回上下文对象
      context
    }

    // 设置检查点目录,通过目录时HDFS上目录，将数据存储在HDFS上
    ssc.checkpoint("datas/streaming/kafka-direct/ckpt-0001/")


    // TODO: 2、从流式数据源读取数据，此处TCP Socket读取数据
    /*
      def createDirectStream[
      K: ClassTag, // Topic中Key数据类型
      V: ClassTag,
      KD <: Decoder[K]: ClassTag, // 表示Topic中Key数据解码（从文件中读取，反序列化）
      VD <: Decoder[V]: ClassTag] (
        ssc: StreamingContext,
        kafkaParams: Map[String, String],
        topics: Set[String]
      ): InputDStream[(K, V)]
     */
    // 表示从Kafka Topic读取数据时相关参数设置
    val kafkaParams: Map[String, String] = Map(
      "bootstrap.servers" ->
        "hadoop01:9092,hadoop02 :9092,hadoop03:9092",
      // 表示从Topic的各个分区的哪个偏移量开始消费数据，设置为最大的偏移量开始消费数据
      "auto.offset.reset" -> "largest"
    )
    // 从哪些Topic中读取数据
    val  topics: Set[String] = Set("testTopic")
    // 采用Direct方式从Kafka 的Topic中读取数据
    val kafkaDStream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, //
      kafkaParams, //
      topics
    )

    // 获取从Kafka读取数据的Message
    val inputDStream: DStream[String] = kafkaDStream.map(tuple => tuple._2)


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
    // TODO: 在DStream中，能对RDD操作的不要对DStream操作。


    // TODO: 4、将分析每批次结果数据输出，此处答应控制台
    // wordCountDStream.print()
    /*
      def foreachRDD(foreachFunc: (RDD[T], Time) => Unit): Unit
        其中Time就是每批次BatchTime，Long类型数据, 转换格式：2019/08/28 16:53:25
     */
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


    // TODO: 5、对于流式应用来说，需要启动应用，正常情况下启动以后一直运行，直到程序异常终止或者人为干涉
    ssc.start()  // 启动接收器Receivers，作为Long Running Task（线程） 运行在Executor
    ssc.awaitTermination()

    // TODO: 6、结束Streaming应用执行
    ssc.stop(stopSparkContext = true, stopGracefully = true)
  }

}
