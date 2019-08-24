package abstract_Demo

import scala.actors.Actor

class Actor3 extends Actor {
  override def act(): Unit = {
    receive{
      case "start" => {
        println("starting ...")
      }
    }
  }
}

object Actor3 {

  def main(args: Array[String]) {
    val actor = new Actor3
    actor.start()
    actor ! "start"

    println("消息发送完成！")
  }




}
