package RPc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory


//todo 利用akka实现两个进程之间的跨网络通信-----master端
class master extends Actor{

  println("master 主构造器执行啦啦啦")


    //preStart初始化方法 会构造函数之后 receive方法之前执行 且执行一次
    override def preStart(): Unit = {
      println("master preStart方法执行了")
    }

    //receive方法用于消息的不断接受和处理
    override def receive: Receive = {
      case "hello" => {
        println("a client connected.... 谁来了？")
        sender ! "ok"
      }
    }
}

object master{

  def main(args: Array[String]): Unit = {

    //指定master端的host port----以参数的方式

    var host = args(0)
    var port = args(1)

    //准备配置参数，从字符串中解析出akka启动 所需要的参数------自带的方法，"""""---回车
    var configStr=
      s"""
        |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
        |akka.remote.netty.tcp.hostname = "$host"
        |akka.remote.netty.tcp.port = "$port"
      """.stripMargin

    //创建一个所需的config对象
    var config =ConfigFactory.parseString(configStr)

    //先创建actorSystem,去监督众多的actor
    val masterActorSystem = ActorSystem.create("masterActorSystem",config)

    //由masterActorSystem创建masterActor-----和以前的不同了
    masterActorSystem.actorOf(Props(new master),"masterActor")

    //给自己发消息，测试代码
//    master !"hello"


  }

}