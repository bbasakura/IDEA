package dataset


import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
import scala.collection.mutable

object Distinct {


  def main(args: Array[String]): Unit = {

    //1.获取执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //2.加载数据源
    val data = new mutable.MutableList[(Int, String, Double)]
    data.+=((1, "yuwen", 89.0))
    data.+=((2, "shuxue", 92.2))
    data.+=((3, "yingyu", 89.99))
    data.+=((4, "wuli", 93.00))
    data.+=((5, "yuwen", 89.0))
    data.+=((6, "wuli", 93.00))
    val source: DataSet[(Int, String, Double)] = env.fromCollection(data)
    //3.去重操作
    source.distinct(1)   //索引下标=1
      //4.打印，触发执行
      .print()
  }


}
