package Akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._

// todo:利用 akka 实现简易版的 spark 通信框架-----Master 端
class Master extends Actor{
  //构造代码块先被执行
  println ("master constructor invoked")

  //定义一个 map 集合，用于存放 worker 信息
  private val workerMap = new mutable.HashMap[String,WorkerInfo]()
  //定义一个 list 集合，用于存放 WorkerInfo 信息，方便后期按照 worker 上的资源进行排序
  private val workerList = new ListBuffer[WorkerInfo]
  //master 定时检查的时间间隔
  val CHECK_OUT_TIME_INTERVAL =15000 //15 秒
  //prestart 方法会在构造代码块执行后被调用，并且只被调用一次
  override def preStart(): Unit = {
    println ("preStart method invoked")
    //master 定时检查超时的 worker
    //需要手动导入隐式转换
    import context .dispatcher
    context .system.scheduler.schedule(0 millis, CHECK_OUT_TIME_INTERVAL
      millis, self ,CheckOutTime)
  }
  //receive 方法会在 prestart 方法执行后被调用，表示不断的接受消息
  override def receive: Receive = {
    //master 接受 worker 的注册信息
    case RegisterMessage (workerId,memory,cores) =>{
      //判断当前 worker 是否已经注册
      if(! workerMap .contains(workerId)){
        //保存信息到 map 集合中
        val workerInfo = new WorkerInfo(workerId,memory,cores)
        workerMap .put(workerId,workerInfo)
        //保存 workerinfo 到 list 集合中
        workerList +=workerInfo
        //master 反馈注册成功给 worker
        sender ! RegisteredMessage (s"workerId:$workerId 注册成功")
      }
    }
    //master 接受 worker 的心跳信息
    case SendHeartBeat (workerId)=>{
      //判断 worker 是否已经注册，master 只接受已经注册过的 worker 的心跳信息
      if( workerMap .contains(workerId)){
        //获取 workerinfo 信息
        val workerInfo: WorkerInfo = workerMap (workerId)
        //获取当前系统时间
        val lastTime: Long = System. currentTimeMillis ()
        workerInfo. lastHeartBeatTime =lastTime
      }
    }
    case CheckOutTime=>{
      //过滤出超时的 worker 判断逻辑： 获取当前系统时间 - worker 上一次心跳时间 >master 定检查的时间间隔
      val outTimeWorkers: ListBuffer[WorkerInfo] = workerList .filter(x =>
        System. currentTimeMillis () -x. lastHeartBeatTime > CHECK_OUT_TIME_INTERVAL )
      //遍历超时的 worker 信息，然后移除掉超时的 worker
      for(workerInfo <- outTimeWorkers){
        //获取 workerid
        val workerId: String = workerInfo.workerId
        //从 map 集合中移除掉超时的 worker 信息
        workerMap .remove(workerId)
        //从 list 集合中移除掉超时的 workerInfo 信息
        workerList -= workerInfo
        println ("超时的 workerId:" +workerId)
      }
      println ("活着的 worker 总数：" + workerList .size)
      //master 按照 worker 内存大小进行降序排列
      println ( workerList .sortBy(x => x.memory).reverse.toList)
    }
  }
}
object Master{
  def main(args: Array[String]): Unit = {
    //master 的 ip 地址
    val host=args(0)
    //master 的 port 端口
    val port=args(1)
    //准备配置文件信息
    val configStr=
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
""".stripMargin
    //配置 config 对象 利用 ConfigFactory 解析配置文件，获取配置信息
    val config=ConfigFactory. parseString (configStr)

    // 1、创建 ActorSystem,它是整个进程中老大，它负责创建和监督 actor，它是单例对象
    val masterActorSystem = ActorSystem ("masterActorSystem",config)
    // 2、通过 ActorSystem 来创建 master actor
    val masterActor: ActorRef = masterActorSystem.actorOf( Props (new Master),"masterActor")
    // 3、向 master actor 发送消息
    //masterActor ! "connect"
  }
}
