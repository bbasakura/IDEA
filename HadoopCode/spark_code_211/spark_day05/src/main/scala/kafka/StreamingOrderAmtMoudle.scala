package kafka

import java.util.Date

import kafka.serializer.StringDecoder
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


/*
* 实时的订单统计模板，进行词频统计，仿照双十一的各个生省份的订单销售额的统计
* */

object StreamingOrderAmtMoudle {

  /*
*
* 抽取一个函数，专门用来读取数据，经过壮状态操作分析数据，最终讲解过输出
*
* */


  def processData(ssc: StreamingContext): Unit = {

    //1.从流式数据源读取数据
    val kafkaDStream: InputDStream[(String, String)] = {

      //表示从kafaka的topic中读取数据，相关参数的配置
      val kafkaParam: Map[String, String] = Map(
        "bootstrap.servers" -> "hadoop01:9092,hadoop02:9092,hadoop03:9092",
        "auto.offset.reset" -> "largest"
      )
      //设置从哪一个topic中读取数据
      val topics = Set("orderTopic")

      //读取数据有多种方式，在这里，确定用哪一种方式，Direct方式,注意参数[]
      KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
        //a.ssc对象
        ssc,
        //b.kakfaParam
        kafkaParam,
        //c.topics
        topics
      )
    }
    //2.对读取到的数据进行ETL操作（推荐使用Transform对rdd进行操作）
    // 使用transform函数也是对RDD操作，但是无需判断RDD是否有值，
    // 原因在于RDD操作属于Transformation（属于Lazy）需要Action触发
    val ordersDStream: DStream[(Int, Double)] = kafkaDStream.transform { rdd =>
      rdd
        //数据处理的第一步，先过滤一次
        .filter(tuple => null != tuple._2 && tuple._2.trim.split(",").length >= 3)
        // 提取字段信息，省份ID,订单金额，以二元组返回
        .mapPartitions { iter =>
        iter.map { item =>
          // 获取Topic中每条数据Value值，进行分割
          var Array(orderId, provinceId, orderPrice) = item._2.trim.split(",")
          //返回
          (provinceId.toInt, orderPrice.toDouble)
        }
      }
        // TODO: 对每批次数据进行聚合操作 -> 当前批次中，每个省份销售订单额，优化
        .reduceByKey((a, b) => a + b)
    }
    // 3、实时累加统计各省份订单销售额，使用updateStateByKey函数
    val OrderAmtDStream = ordersDStream.updateStateByKey {

      //updateFunc: (Seq[V], Option[S]) => Option[S]

      (values: Seq[Double], states: Option[Double]) => {

        //当前的批次中总的订单销售总额
        val currentOrderAmt: Double = values.sum

        //各省份以前的订单销售总jine，getOrElse（初始值）
        val previousOrderAmt: Double = states.getOrElse(0.0)

        //计算各省份的 最新的订单销售总结额

        val orderAmt: Double = currentOrderAmt + previousOrderAmt


        //返回订单的销售金额

        Some(orderAmt)

      }
    }
    //4、将统计结果打印到控制台
    OrderAmtDStream.foreachRDD{(rdd,time)=>

      // 使用lang3包下FastDateFormat日期格式类，属于线程安全的
      val batchTime: String = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss")
        .format(new Date(time.milliseconds))

      println("-------------------------------------------")
      println(s"Time: $batchTime")
      println("-------------------------------------------")
      // TODO: 先判断RDD是否有数据，有数据在输出
      if(!rdd.isEmpty()){

        rdd
          // 对于结果RDD输出，需要考虑降低分区数目
          .coalesce(1)
          // 对分区数据操作
          .foreachPartition{iter =>iter.foreach(item => println(item))
        }

      }
    }

  }

  def main(args: Array[String]): Unit = {

    //1、构建SparkContext,用于读取的流式数据的读取的和调度Batch job的运行
    val ssc: StreamingContext = {
      //a.创建sparkconf，用于设置application 的相关信息
      val sparkConf = new SparkConf()
        .setMaster("local[3]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
        //设置每秒钟读取kafka中的Topic中的最大的数据量
        .set("spark.streaming.kafka.maxRatePerPartition", "1000")

      //b.创建StreamingContex对象，传递Batch interva （时间间隔，划分流式数据）

      val context = new StreamingContext(sparkConf, Seconds(5))

      //c.设置日志的级别
      context.sparkContext.setLogLevel("WARN")

      //d..返回上下文对象
      context

    }

    //2.设置检查点的目录，将数据的保存到HDFS上
    ssc.checkpoint("datas/streaming/kafka/ckp-t001")

    //3.传递StreamingContext流式上下文实例对象，读取数据进行处理分析
    processData(ssc)

    //4.对于流式应用来说，需要启动应用，正常情况下启动以后一直运行，直到程序异常终止或者人为干涉
    ssc.start()  // 启动接收器Receivers，作为Long Running Task（线程） 运行在Executor
    ssc.awaitTermination()

    // 5.结束Streaming应用执行
    ssc.stop(stopSparkContext = true, stopGracefully = true)


  }
}






