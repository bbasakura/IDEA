package case_Demo

import scala.util.Random


class case2 {


}

object case2 {

  def main(args: Array[String]): Unit = {

    //匹配字符串
    val arr = Array("hadoop", "zookeeper", "spark")
    val name = arr(Random.nextInt(arr.length))
    name match {
      case "hadoop" => println("大数据分布式存储和计算框架...")
      case "zookeeper" => println("大数据分布式协调服务框架...")
      case "spark" => println("大数据分布式内存计算框架...")
      case _ => println("我不认识你...")
    }


    //匹配类型
    val arr1 = Array("hello", 1, 2.0)
    val v = arr1(Random.nextInt(4))
    println(v)
    v match {
      case x: Int => println("Int " + x)
      case y: Double if (y >= 0) => println("Double " + y)
      case z: String => println("String " + z)
      case _ => throw new Exception("not match exception")
    }
  }
}

