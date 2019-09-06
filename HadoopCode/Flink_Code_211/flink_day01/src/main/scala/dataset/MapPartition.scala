package dataset


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object MapPartition {

  def main(args: Array[String]): Unit = {
    /**
      * 数据源：("java", 1), ("scala", 1), ("java", 1)
      * 1.获取执行环境
      * 2.加载数据源
      * 3.数据转换mapPartition
      * 4.打印，触发执行
      */
    //1.获取执行环境
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.加载数据源
    val source: DataSet[(String, Int)] = env.fromElements(("java", 1), ("scala", 1), ("java", 1) 	)

    //3.数据转换mapPartition
    val values: DataSet[(String, Int)] = source.mapPartition(line => {
      line.map(x => (x._1, x._2))
    })

    //4.打印，触发执行
    values.print()
  }


}
