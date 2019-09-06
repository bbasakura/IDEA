package dataset


import org.apache.flink.api.java.aggregation.Aggregations
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._

import scala.collection.mutable
object Aggregate {


  def main(args: Array[String]): Unit = {
    /**
      * 1. 获取 ExecutionEnvironment 运行环境
      * 2. 使用 fromCollection构建数据源
      * 3. 使用group执行分组操作
      * 4.使用minBy求最值元素
      * 5.打印测试
      */
    //1. 获取 ExecutionEnvironment 运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment


    val data = new mutable.MutableList[(Int, String, Double)]
    data.+=((1, "yuwen", 90.0))
    data.+=((2, "shuxue", 20.0))
    data.+=((3, "yingyu", 30.0))
    data.+=((4, "wuli", 40.0))
    data.+=((5, "yuwen", 50.0))
    data.+=((6, "wuli", 60.0))
    data.+=((7, "yuwen", 70.0))
    //2. 使用 fromCollection构建数据源
    val source: DataSet[(Int, String, Double)] = env.fromCollection(data)
    //3. 使用group执行分组操作
    source.groupBy(1)
      //4.使用minBy求最值元素
      //.minBy(2)  //返回的是满足条件的一条数据
      .maxBy(2)
//      .min(2) //返回的是满足条件的最值
//      .max(2)
//      .aggregate(Aggregations.MAX,2)
//      .aggregate(Aggregations.MIN,2)

      //5.打印，触发执行
      .print()
  }
}
