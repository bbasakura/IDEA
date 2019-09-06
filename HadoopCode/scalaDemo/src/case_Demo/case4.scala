package case_Demo

import scala.util.Random

//普通样例类，后面要有构造参数
case class SubmitTask(id: String, name: String)
case class HeartBeat(time: Long)

//单例
case object CheckTimeOutTask

object CaseDemo04 extends App{
  val arr = Array(CheckTimeOutTask, HeartBeat(12333), SubmitTask("0001", "task-0001"))

  arr(Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) => {
      println(s"$id, $name")
    }
    case HeartBeat(time) => {
      println(time)
    }
    case CheckTimeOutTask => {
      println("check")
    }
  }

  //option类型
  val map = Map("a" -> 1, "b" -> 2)
  val v = map.get("b") match {
    case Some(i) => i
    case None => 0
  }
  println(v)
  //更好的方式
  val v1 = map.getOrElse("c", 0)
  println(v1)



}
