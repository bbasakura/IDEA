package dataset
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import scala.collection.mutable


object Join {

  def main(args: Array[String]): Unit = {
    /**
      * 需求：使用join操作，根据学生学号求出每个班级每个学科的最高分数
      */
    //1.获取执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //2.加载数据源
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
    val s1 = env.fromCollection(data1)

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
    val s2 = env.fromCollection(data2)

    //3.join关联操作
    val joinData: DataSet[(Int, String, String, Double)] = s1.join(s2).where(0).equalTo(0) {
      (s1, s2) => (s2._1, s2._2, s1._2, s1._3)
    }
    //4.分组操作
    joinData.groupBy(1,2)
      //求分数的最大值
      .maxBy(3)
      .print()
  }


}
