package dataset


import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

object Rebalance {


  def main(args: Array[String]): Unit = {

    /**
      * 1. 获取 ExecutionEnvironment 运行环境
      * 2. 生成序列数据源
      * 3. 使用filter过滤大于50的数字
      * 4. 执行rebalance操作
      * 5.使用map操作传入 RichMapFunction ，将当前子任务的ID和数字构建成一个元组
      * 6. 打印测试
      */
    //1.获取 ExecutionEnvironment 运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    //2. 生成序列数据源
    val source: DataSet[Long] = env.generateSequence(0,100)

    //3.filter过滤
    val filterData = source.filter(_>50)

    //4. 执行rebalance操作
    val rebalaceData = filterData.rebalance()

    //5.map数据转换Long->(taskID,Long)
    rebalaceData.map(new RichMapFunction[Long,(Int,Long)] {
      var taskId: Int = 0   // 注意是var

      override def open(parameters: Configuration): Unit = {
        //通过上下文对象，获取任务/线程ID-----使用RichMapFunction的目的
        taskId = getRuntimeContext.getIndexOfThisSubtask
      }
      override def map(value: Long): (Int, Long) = {
        (taskId,value)
      }
    }).print()
  }

}
