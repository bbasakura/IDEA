package datastreaming

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

object DataStreamWordCount {


  def main(args: Array[String]): Unit = {

    //执行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //读取数据--------注意包 的导入DataStreamSource和Datastream
    val source: DataStream[String] = env.socketTextStream("localhost",8900)



    //数据转换

    source.flatMap(line => line.split("\\W+"))
      .map(word=>(word,1))
      .keyBy(0)
      .sum(1)
      .print()   //流处理中，打印不是action的算子，不能触发执行
    env.execute("dataStreaming")

  }
}
