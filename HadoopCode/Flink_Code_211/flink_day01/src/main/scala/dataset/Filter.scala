package dataset


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
object Filter {



  def main(args: Array[String]): Unit = {
    /**
      * 1.获取执行环境
      * 2.加载数据源
      * 3.数据过滤
      * 4.数据打印，触发执行
      */
    // 1.获取执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //2.加载数据源
    val source: DataSet[String] = env.fromElements("java","scala","java")
    //3.数据过滤
    //source.filter(line=>line.contains("java"))   //包含直接匹配值
    source.filter(line=> line != "java")  // 不包含！=（scala中）
      //4.数据打印，触发执行
      .print()
  }
}
