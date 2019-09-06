package dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object Map {

  def main(args: Array[String]): Unit = {

    /**
      * 数据源： List("1,张三", "2,李四", "3,王五", "4,赵六")
      * 1. 获取 ExecutionEnvironment 运行环境
      * 2. 使用 fromCollection 构建数据源
      * 3. 创建一个 User 样例类
      * 4. 使用 map 操作执行转换
      * 5. 打印测试
      */
    // 1. 获取 ExecutionEnvironment 运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    //2. 使用 fromCollection 构建数据源
    val source: DataSet[String] = env.fromCollection(List("1,张三", "2,李四", "3,王五", "4,赵六"))

    //4. 使用 map 操作执行转换
    val users: DataSet[User] = source.map(line => {
      val arr: Array[String] = line.split(",")
      User(arr(0).toInt, arr(1))
    })
    //5. 打印测试
    users.print()
  }
}
//3. 创建一个 User 样例类,同一个object
case class User(id:Int,name:String)

