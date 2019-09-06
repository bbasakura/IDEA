package dataset


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector

object Reduce {


  def main(args: Array[String]): Unit = {
    /**
      * 数据源：("java", 1), ("scala", 1), ("java", 1)
      * 1. 获取 ExecutionEnvironment 运行环境
      * 2. 使用 fromElements 构建数据源
      * 3. 使用 map和group执行转换操作
      * 4.使用reduce进行聚合操作
      * 5.打印测试
      */
    //1. 获取 ExecutionEnvironment 运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    //2. 使用 fromElements 构建数据源
    val source: DataSet[(String, Int)] = env.fromElements(("java", 1), ("scala", 1), ("java", 1))

    //3. group执行转换操作
    val groupData: GroupedDataSet[(String, Int)] = source.groupBy(0)

    //4.使用reduce进行聚合操作
    // reduce
    //groupData.reduce((x, y) => (x._1, x._2 + y._2))


    //reduceGroup
        groupData.reduceGroup {

      (in: Iterator[(String, Int)], out: Collector[(String, Int)]) => {
        val tuple: (String, Int) = in.reduce((x, y) => (x._1, x._2 + y._2))
        out.collect(tuple)
      }
    }
      //5.打印测试
      .print()


  }
}