package Akka

import java.util.UUID

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

// todo:利用 akka 实现简易版的 spark 通信框架-----Worker 端
class Worker(val memory: Int, val cores: Int, val masterHost: String, val masterPort: String) extends
  Actor {
  println("Worker constructor invoked")
  //定义 workerId
  private val workerId: String = UUID.randomUUID().toString
  //定义发送心跳的时间间隔
  val SEND_HEART_HEAT_INTERVAL = 10000 //10 秒
  //定义全局变量
  var master: ActorSelection = _

  //prestart 方法会在构造代码块之后被调用，并且只会被调用一次
  override def preStart(): Unit = {
    println("preStart method invoked")
    //获取 master actor 的引用
    //ActorContext 全局变量，可以通过在已经存在的 actor 中，寻找目标 actor
    //调用对应 actorSelection 方法，
    // 方法需要一个 path 路径：1、通信协议、2、master 的 IP 地址、3、master 的端口 4、创建 maste
    master =
      context.actorSelection(
        s"akka.tcp://masterActorSystem@$masterHost:$masterPort/user/masterActor")
    //向 master 发送注册信息，将信息封装在样例类中，主要包含：workerId,memory,cores
    master ! RegisterMessage(workerId, memory, cores)
  }

  //receive 方法会在 prestart 方法执行后被调用，不断的接受消息
  override def receive: Receive = {
    //worker 接受 master 的反馈信息
    case RegisteredMessage(message) => {
      println(message)
      //向 master 定期的发送心跳
      //worker 先自己给自己发送心跳
      //需要手动导入隐式转换
      import context.dispatcher     ///------import scala.concurrent.duration._这个包
      context.system.scheduler.schedule(0 millis, SEND_HEART_HEAT_INTERVAL
        millis, self, HeartBeat)
    }
    //worker 接受心跳
    case HeartBeat => {
      //这个时候才是真正向 master 发送心跳
      master ! SendHeartBeat(workerId)
    }
  }
}

object Worker {
  def main(args: Array[String]): Unit = {
    //定义 worker 的 IP 地址
    val host = args(0)
    //定义 worker 的端口
    val port = args(1)
    //定义 worker 的内存
    val memory = args(2).toInt
    //定义 worker 的核数
    val cores = args(3).toInt
    //定义 master 的 ip 地址
    val masterHost = args(4)
    //定义 master 的端口
    val masterPort = args(5)
    //准备配置文件

    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
""".stripMargin
    //通过 configFactory 来解析配置信息
    val config = ConfigFactory.parseString(configStr)
    // 1、创建 ActorSystem，它是整个进程中的老大，它负责创建和监督 actor
    val workerActorSystem = ActorSystem("workerActorSystem", config)
    // 2、通过 actorSystem 来创建 worker actor
    val workerActor: ActorRef = workerActorSystem.actorOf(Props(new
        Worker(memory, cores, masterHost, masterPort)), "workerActor")
    //向 worker actor 发送消息
    workerActor ! "connect"
  }
}
