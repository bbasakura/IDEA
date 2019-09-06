package dataset

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
object FlatMap {

  def main(args: Array[String]): Unit = {
    /**
      * 数据源：List(("java", 1), ("scala", 1), ("java", 1))
      * 1. 获取 ExecutionEnvironment 运行环境
      * 2. 使用 fromElements构建数据源
      * 3. 使用 flatMap 执行转换
      * 4. 使用groupBy进行分组
      * 5. 使用sum求值
      * 6. 打印测试
      */
    //1. 获取 ExecutionEnvironment 运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //2. 使用 fromElements构建数据源
    val source: DataSet[List[(String, Int)]] = env.fromElements(List(("java", 1), ("scala", 1), ("java", 1)))
    //3. 使用 flatMap 执行转换
    source.flatMap(line => line)
      //4. 使用groupBy进行分组
      .groupBy(0)
      //5.求和
      .sum(1)
      //6.打印，触发执行
      .print()
  }
}
