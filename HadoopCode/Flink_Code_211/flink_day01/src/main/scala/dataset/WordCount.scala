package dataset

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
object WordCount {

  /**
    * 1.创建scala单例对象，添加main方法
    * 2. 获取Flink执行环境
    * 3. 加载/创建初始数据源
    * 4. 指定这些数据的转换
    * 5. 打印/sink
    * 6.触发执行
    */
  def main(args: Array[String]): Unit = {

    //1.获取Flink执行环境
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.加载/创建初始数据源
    val source: DataSet[String] = env.fromElements("aa bb cc dd dd ee ff gg")

    //3.指定这些数据的转换
    source.flatMap(line=>line.split("\\W+")).map((line=>(line,1))).groupBy(0).sum(1).print()

  }
}
