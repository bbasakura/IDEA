package RPc

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

//todo 利用akka实现两个进程间跨网络通信--worker端
class Worker extends Actor{

  println("worker的主构造器执行了")

  override def preStart(): Unit = {
    println("worker preStart方法执行了")
    //todo 在初始化方法中 想法设法拿到master的引用 向其发送注册信息
    //todo context提供了在已有的actor中找到你需要的actor能力（协议  主机  端口  actorSystem actor  层级）
    val master: ActorSelection = context.actorSelection("akka.tcp://masterActorSystem@192.168.75.25:12345/user/masterActor")
    master ! "hello"
  }

  override def receive: Receive = {
    case "ok" => println("俺注册成功了")
  }
}

object Worker{
  def main(args: Array[String]): Unit = {

    //指定worker运行的host port
    val host = args(0)
    val port = args(1)

    //准备配置参数 从字符串中解析
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
      """.stripMargin

    val config = ConfigFactory.parseString(configStr)

    //创建ActorSystem
    val workerActorSystem: ActorSystem = ActorSystem.create("workerActorSystem",config)

    //由actorSystem 创建workerActor
    val worker: ActorRef = workerActorSystem.actorOf(Props(new Worker),"workerActor")
    //
    //    //给自己发送消息 测试是否正确
    //    worker ! "hello"
  }
}