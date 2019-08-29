package source

import java.util.Date

import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingSourceFile {

  /*
  从TCP Sockect 中读取数据，对每批次（时间为1秒）数据进行词频统计，将统计结果输出到控制台。
   * */

  def main(args: Array[String]): Unit = {
    val ssc: StreamingContext = {
      val sparkConf: SparkConf = new SparkConf()
        .setMaster("local[3]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
      val context: StreamingContext = new StreamingContext(sparkConf, Seconds(3))

      context.sparkContext.setLogLevel("WARN")

      context
    }

    //读取数据=========此处发生变化，从本地文件系统中读取数据

val inputDstream: DStream[String] = ssc.textFileStream("file:///D:/datas/")
    //处理数据
    val wordConutDstring: DStream[(String, Int)] = inputDstream
      .filter(line => null != line && line.trim.length > 0)
      .flatMap(iter => iter.split("\\s+").filter(line => line.trim.length > 0))
      .mapPartitions(iter =>
        iter.map(word => (word, 1)))
      .reduceByKey(_ + _)


    //  对DStream中每批次结果RDD数据进行输出操作，
    wordConutDstring.foreachRDD{(rdd,time)=>

      val batchTime = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss")
        .format(new Date(time.milliseconds))

      println("==========================")
      println(s"Time:$batchTime")
      println("==========================")

      //加了一步操作，时候有数据，有数据的时候在输出
      if(!rdd.isEmpty()){

        rdd
        //结果输出的Rdd,是要进行降低分区数目的
          .coalesce(1)
        //对分区的数据进行操作
          .foreachPartition(iter=>
          iter.foreach(item=>println(item))
        )
      }
    }

    // TODO: 5、对于流式应用来说，需要启动应用，正常情况下启动以后一直运行，直到程序异常终止或者人为干涉
    ssc.start()  // 启动接收器Receivers，作为Long Running Task（线程） 运行在Executor
    ssc.awaitTermination()

    // TODO: 6、结束Streaming应用执行
    ssc.stop(stopSparkContext = true, stopGracefully = true)

    }
}
