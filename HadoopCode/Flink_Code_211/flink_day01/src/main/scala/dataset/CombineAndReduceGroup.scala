package dataset

import org.apache.flink.api.common.functions.{GroupCombineFunction, GroupReduceFunction}
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector
object CombineAndReduceGroup {

  def main(args: Array[String]): Unit = {
    /**
      * 数据源：("java", 1), ("scala", 1), ("java", 1)
      * 1. 获取 ExecutionEnvironment 运行环境
      * 2. 使用 fromElements 构建数据源
      * 3. 使用group执行转换操作
      * 4. 自定义匿名函数，来进行数据转换
      * 5. 触发执行，打印测试
      */
    //1. 获取 ExecutionEnvironment 运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //2. 使用 fromElements 构建数据源
    val source: DataSet[(String, Int)] = env.fromElements(("java", 1), ("scala", 1), ("java", 1))
    //3. 使用group执行转换操作
    val groupData: GroupedDataSet[(String, Int)] = source.groupBy(0)
    //4. 自定义匿名函数，来进行数据转换
//    groupData.combineGroup(new CombineAndReduceFunc)
//      //5. 触发执行，打印测试
//      .print()

  }
}



import collection.JavaConverters._

/*class CombineAndReduceFunc extends GroupReduceFunction[(String, Int), (String, Int)]
  with GroupCombineFunction[(String, Int), (String, Int)] {

/*  //reduce后执行
  override def reduce(values: lang.Iterable[(String, Int)], out: Collector[(String, Int)]): Unit = {
    for (line <- values.asScala) {
      out.collect(line)
    }
  }

  //combine先执行
  override def combine(values: lang.Iterable[(String, Int)], out: Collector[(String, Int)]): Unit = {
    var key = ""
    var count: Int = 0
    for (line <- values.asScala) {
      key = line._1
      count = count + line._2
    }
    out.collect((key, count))
  }*/*/
/*
}*/
