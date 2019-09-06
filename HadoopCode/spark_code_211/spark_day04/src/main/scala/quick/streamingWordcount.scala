package quick

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object streamingWordcount {
  /**
    *
    * @param args
    *  sparkStreaming的官方词频统计的案例
    *
    */

  def main(args: Array[String]): Unit = {

    //1.构建streaming Context,也是需要一个sparkConf

    val sparkConf = new SparkConf()
      .setAppName("StreamingWordCount")
      .setMaster("local[3]")


    //2.创建真正的的spark Streamingcontext，传入的参数有一个是间隔的时间
    val ssc = new StreamingContext(sparkConf,Seconds(5))

    //3.设置日志的级别
    ssc.sparkContext.setLogLevel("WARN")

    //TCP 对的socket接受，传入的参数是主机名和开放的端口号
    val inputStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop02",9999,storageLevel = StorageLevel.MEMORY_AND_DISK)

    //开始进行数据的处理
    val wordStream = inputStream.flatMap(line =>
      line.split("\\s+").filter(word => word.length > 0)
    )
    //分组
    val tupleStream = wordStream.map(word=>(word,1))

    //统计
    val res = tupleStream.reduceByKey(_ + _)

    //打印结果
    res.print(10)

    //开启创建的对象，其实是开始创建一个个的receivers
    ssc.start()

    //设置为一直运行，除非主动停止或者人为的暂停
    ssc.awaitTermination()

    //关闭资源
    ssc.stop(stopSparkContext = true,stopGracefully = true)
  }

}
