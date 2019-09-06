package state



import java.util.Date

import kafka.serializer.StringDecoder
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 集成Kafka，采用Direct式读取数据，对每批次（时间为1秒）数据进行词频统计，将统计结果输出到控制台。
  * 	仿双十一实时累加统计各个省份订单销售额。
  *
  * 数据格式：订单ID,省份ID,订单金额
		orderId,provinceId,orderPrice
  */
object StreamingOrderupdateStateByKey {

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


    // TODO: 3、对获取的数据进行ETL处理
    val ordersDStream: DStream[(Int, Double)] = kafkaDStream.transform{ rdd =>
      // TODO: 使用transform函数也是对RDD操作，但是无需判断RDD是否有值，
      // TODO：	原因在于RDD操作属于Transformation（属于Lazy）需要Action触发

      rdd
        // 过滤无效数据
        .filter(tuple => null != tuple._2 && tuple._2.trim.split(",").length >= 3)
        // 提取字段信息，省份ID,订单金额，以二元组返回
        .mapPartitions{iter =>
        iter.map{message =>
          // 获取Topic中每条数据Value值，进行分割
          val Array(orderId, provinceId, orderPrice) = message._2.trim.split(",")
          // 返回
          (provinceId.toInt, orderPrice.toDouble)
        }
      }
        // TODO: 对每批次数据进行聚合操作 -> 当前批次中，每个省份销售订单额，优化
        .reduceByKey((a, b) => a + b)
    }

    // 4、实时累加统计各省份订单销售额，使用updateStateByKey函数_____=++++++++++++++++++++++
    /*
      def updateStateByKey[S: ClassTag](
        updateFunc: (Seq[V], Option[S]) => Option[S]
      ): DStream[(K, S)]
      核心：
          按照key更新状态信息，针对应用按照省份ID实时更新订单销售额
      解释函数参数：
        (Seq[V], Option[S])
          - Seq[V]: 表示的是当前批次中所有Key的Value集合，
            针对应用来时，表示某个省份的所有的订单的销售额
          - Option[S]：表示的是当前批次中Key的以前状态
            针对应用来说，表示某个省份以前的总的订单销售额，此时S泛型类型：Double类型
        Option[S]：
          表示的当前Key的最新状态，针对应用来说就是某个省份的最新的总的订单销售额
     */
    val orderProvinceAmtDStream: DStream[(Int, Double)] = ordersDStream.updateStateByKey(
      // (Seq[V], Option[S]) => Option[S]
      (values: Seq[Double], state: Option[Double]) => {
        // a. 统计当前批次中省份的总的订单销售额
        val currentOrderAmt = values.sum

        // b. 获取省份以前的总的订单销售额
        val previousOrderAmt = state.getOrElse(0.0)

        // c. 计算最新省份订单销售额
        val orderAmt = currentOrderAmt + previousOrderAmt

        // d. 返回最新订单总的销售额
        Some(orderAmt)
      }
    )


    // TODO: 将统计结果打印到控制台
    orderProvinceAmtDStream.foreachRDD{ (rdd, time) =>
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
          .foreachPartition{iter =>iter.foreach(item => println(item))
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
