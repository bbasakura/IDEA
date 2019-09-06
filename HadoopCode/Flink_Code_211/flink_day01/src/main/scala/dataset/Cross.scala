package dataset


import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.api.scala._
import scala.collection.mutable

object Cross {

  def main(args: Array[String]): Unit = {
    //1.获取Flink批处理执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //TODO Cross 交叉操作，会产生笛卡尔积
    val data1 = new mutable.MutableList[(Int, String, Double)]
    //学生学号---学科---分数
    data1.+=((1, "yuwen", 90.0))
    data1.+=((2, "shuxue", 20.0))
    data1.+=((3, "yingyu", 30.0))
    data1.+=((4, "yuwen", 40.0))
    data1.+=((5, "shuxue", 50.0))
    data1.+=((6, "yingyu", 60.0))
    data1.+=((7, "yuwen", 70.0))
    data1.+=((8, "yuwen", 20.0))
    val data2 = new mutable.MutableList[(Int, String)]
    //学号 ---班级
    data2.+=((1,"class_1"))
    data2.+=((2,"class_1"))
    data2.+=((3,"class_2"))
    data2.+=((4,"class_2"))
    data2.+=((5,"class_3"))
    data2.+=((6,"class_3"))
    data2.+=((7,"class_4"))
    data2.+=((8,"class_1"))
    //2.加载创建数据源
    val input1: DataSet[(Int, String, Double)] = env.fromCollection(data1)
    val input2: DataSet[(Int, String)] = env.fromCollection(data2)
    //3.执行cross
    val cross = input1.cross(input2){
      (input1 , input2) => (input1._1,input1._2,input1._3,input2._2)
    }
    //4.打印测试
    cross.print()

    //env.execute("join")
  }


}
