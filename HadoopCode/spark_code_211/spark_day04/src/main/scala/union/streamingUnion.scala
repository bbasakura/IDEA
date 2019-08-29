package union

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object streamingWordcount {


  def main(args: Array[String]): Unit = {

    //streaming Context

    val sparkConf = new SparkConf()
      .setAppName("StreamingWordCount")
      .setMaster("local[3]")

    val ssc = new StreamingContext(sparkConf,Seconds(5))

    ssc.sparkContext.setLogLevel("WARN")

    val inputStream0: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop02",9999,storageLevel = StorageLevel.MEMORY_AND_DISK)
    val inputStream1: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop02",9988,storageLevel = StorageLevel.MEMORY_AND_DISK)

    //因为只有一个的Jvm,开了两个的master，两个的分区，找不到另一个，每次接受数据的时候，就会jinggao。
    //设置storageLevel = StorageLevel.MEMORY_AND_DISK，消除警告

    //多个的ReceiverInputDStream合并，一同接受消息
    val inputStream = inputStream0.union(inputStream1)


    val wordStream = inputStream.flatMap(line =>
      line.split("\\s+").filter(word => word.length > 0)
    )
    val tupleStream = wordStream.map(word=>(word,1))

    val res = tupleStream.reduceByKey(_ + _)

    res.print(10)

    ssc.start()

    ssc.awaitTermination()


    ssc.stop(stopSparkContext = true,stopGracefully = true)

  }

}
