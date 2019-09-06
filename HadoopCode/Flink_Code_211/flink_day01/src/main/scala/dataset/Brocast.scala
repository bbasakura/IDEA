package dataset


import java.util

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

import scala.collection.mutable

object Brocast {

  def main(args: Array[String]): Unit = {

    /**
      * 1.获取执行环境
      * 2.加载数据源
      * 3.数据转换，设置广播变量，获取广播变量内的值
      * 4.数据打印，出发执行
      */
    //1.获取执行环境
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.加载数据源
    val data2 = new mutable.MutableList[(Int, Long, String)]
    data2.+=((1, 1L, "xiaoming"))
    data2.+=((2, 2L, "xiaoli"))
    data2.+=((3, 2L, "xiaoqiang"))
    val ds1 = env.fromCollection(data2)

    val data3 = new mutable.MutableList[(Int, Long, Int, String, Long)]
    data3.+=((1, 1L, 0, "Hallo", 1L))
    data3.+=((2, 2L, 1, "Hallo Welt", 2L))
    data3.+=((2, 3L, 2, "Hallo Welt wie", 1L))
    val ds2 = env.fromCollection(data3)

    //将ds2广播到内存里
    val result: DataSet[(Int, Long, String, String)] = ds1.map(new RichMapFunction[(Int, Long, String), (Int, Long, String, String)] {
      var buffer: mutable.Buffer[(Int, Long, Int, String, Long)] = null
      //获取广播变量
      //open先于map之前执行
      import collection.JavaConverters._

      override def open(parameters: Configuration): Unit = {
        val ds: util.List[(Int, Long, Int, String, Long)] = getRuntimeContext.getBroadcastVariable[(Int, Long, Int, String, Long)]("ds2")
        buffer = ds.asScala
      }

      override def map(value: (Int, Long, String)): (Int, Long, String, String) = {
        var tuple: (Int, Long, String, String) = null
        for (line <- buffer) {
          if (line._2 == value._2) {
            tuple = (value._1, value._2, value._3, line._4)
          }
        }
        tuple
      }
    }).withBroadcastSet(ds2, "ds2")

    //4.数据打印，出发执行
    result.print()

  }


}
