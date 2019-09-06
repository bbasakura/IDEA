package dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._


object Union {


  def main(args: Array[String]): Unit = {

    //1.获取执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //2.加载数据源
    val s1 = env.fromElements("java")
    val s2 = env.fromElements("scala")
    val s3 = env.fromElements("java")
    //3.union
    s1.union(s2).union(s3)
      .print()
  }
}
