package window

import java.util.Date

import kafka.serializer.StringDecoder
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 集成Kafka，采用Direct式读取数据，对每批次（时间为1秒）数据进行词频统计，将统计结果输出到控制台。
  * 	每隔2秒统计最近4秒的各个省份订单数目
  *
  * 数据格式：订单ID,省份ID,订单金额
		orderId,provinceId,orderPrice
  */
object StreamingOrderWindowSQL {

  // Streaming应用BatchInterval
  val STREAMING_BATCH_INTERVAL: Int = 2
  // Streaming应用窗口大小
  val STREAMING_WINDOW_INTERVAL: Int = STREAMING_BATCH_INTERVAL * 2
  val STREAMING_SLIDER_INTERVAL: Int = STREAMING_BATCH_INTERVAL * 1


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
      val context: StreamingContext = new StreamingContext(
        sparkConf, Seconds(STREAMING_BATCH_INTERVAL)
      )
      // 设置日志级别
      context.sparkContext.setLogLevel("WARN")
      // c. 返回上下文对象
      context
    }

    // TODO: 2、从流式数据源读取数据，此处TCP Socket读取数据
    // 表示从Kafka Topic读取数据时相关参数设置
    val kafkaParams: Map[String, String] = Map(
      "bootstrap.servers" ->
        "hadoop01:9092,hadoop02:9092,hadoop03:9092",
      // 表示从Topic的各个分区的哪个偏移量开始消费数据，设置为最大的偏移量开始消费数据
      "auto.offset.reset" -> "largest"
    )
    // 从哪些Topic中读取数据
    val  topics: Set[String] = Set("orderTopic")
    // 采用Direct方式从Kafka 的Topic中读取数据
    val kafkaDStream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, //
      kafkaParams, //
      topics
    )

    // TODO: 5、窗口统计，每隔4秒统计最近6秒的各个省份订单数目$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // def window(windowDuration: Duration, slideDuration: Duration): DStream[T]
    val windowDStream: DStream[(String, String)] = kafkaDStream.window(
      Seconds(STREAMING_WINDOW_INTERVAL),
      Seconds(STREAMING_SLIDER_INTERVAL)
    )

    // TODO: 将统计结果打印到控制台$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    windowDStream.foreachRDD{ (rdd, time) =>
      // 使用lang3包下FastDateFormat日期格式类，属于线程安全的
      val batchTime = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss")
        .format(new Date(time.milliseconds))
      println("-------------------------------------------")
      println(s"Time: $batchTime")
      println("-------------------------------------------")
      // TODO: 先判断RDD是否有数据，有数据在输出哦
      if(!rdd.isEmpty()){
        // TODO: 对窗口中的数据RDD转换为DataFrame，使用DSL统计各个省份订单数目
        // 将RDD缓存到内存
        rdd.persist(StorageLevel.MEMORY_AND_DISK)

        /**
          * RDD转换为DataFrame，就是给RDD加上SChema信息, 采用定义Schema
          *   RDD[Row]  schema
          */
        // a. RDD[Row]
        val rowsRDD: RDD[Row] = rdd
          .filter(msg => null != msg._2 && msg._2.trim.split(",").length >= 3)
          .mapPartitions{iter =>
            iter.map{msg =>
              // 获取Topic中每条数据Value值，进行分割
              val Array(orderId, provinceId, orderPrice) = msg._2.trim.split(",")
              // 返回
              Row(orderId, provinceId.toInt, orderPrice.toDouble)
            }
          }

        // b. 构建Schema信息
        val schema: StructType = StructType(
          StructField("order_id", StringType, nullable = true) ::
            StructField("province_id", IntegerType, nullable = true) ::
            StructField("order_price", DoubleType, nullable = true) ::Nil
        )

        // c. 构建SparkSession实例对象????????最上面的是StreamingContext对象，和SparkSession不同，这是一个入口
        val spark: SparkSession = SparkSession.builder()
          .config(rdd.sparkContext.getConf)
          .config("spark.sql.shuffle.partitions", "3")
          .getOrCreate()
        import spark.implicits._

        // d. 构建DataFrame
        val ordersDF: DataFrame = spark.createDataFrame(rowsRDD, schema)

        // e. 编写DSL分析
        val provinceOrderCountDF = ordersDF.groupBy($"province_id").count()

        provinceOrderCountDF.show(10, truncate = false)

        // 当数据不再使用，释放资源++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        rdd.unpersist()
      }
    }


    // TODO: 5、对于流式应用来说，需要启动应用，正常情况下启动以后一直运行，直到程序异常终止或者人为干涉
    ssc.start()  // 启动接收器Receivers，作为Long Running Task（线程） 运行在Executor
    ssc.awaitTermination()

    // TODO: 6、结束Streaming应用执行
    ssc.stop(stopSparkContext = true, stopGracefully = true)
  }
}
