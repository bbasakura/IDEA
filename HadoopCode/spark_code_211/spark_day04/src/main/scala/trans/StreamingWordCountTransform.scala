package trans

import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingWordCountTransform {

  /**
    * 利用了transform
    */


  def main(args: Array[String]): Unit = {


    val ssc:StreamingContext= {
      val sparkConf = new SparkConf()
        .setMaster("local[3]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))

      val scontext: StreamingContext = new StreamingContext(sparkConf, Seconds(2))
      scontext.sparkContext.setLogLevel("WARN")
      //返回一个ssc的实例对象
      scontext
    }


    //2.从流流式数据的元读取数据，这里是TCPsocket
    val inputDstream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop02",9999,storageLevel = StorageLevel.MEMORY_AND_DISK)

    //3.对接受的每一批次的流式数据，进行词频统计，,transform 表示对每批次的RDD进行操作

    val wordCountDstream: DStream[(String, Int)] =inputDstream.transform(rdd=>{

      rdd
      //a.过滤脏数据
        .filter(line=>null!= line && line.trim.length>0)
      //b.分割为单词
        .flatMap(line=>line.split("\\s+").filter(word=>word.length>0))
      //c.转换为二元数组,对组内的数据进行操作
        .mapPartitions{iter=>iter.map(word=>(word,1))}
        //d.聚合统计
        .reduceByKey((_ + _ ))   // （a+b=>a+b）
    })
    //将分析的每批次的结果输出，打印在控制台
    wordCountDstream.print(20)

    //启动接收器

    ssc.start()
    ssc.awaitTermination()

    //关闭资源

    ssc.stop(stopSparkContext = true,stopGracefully = true)

  }


}
