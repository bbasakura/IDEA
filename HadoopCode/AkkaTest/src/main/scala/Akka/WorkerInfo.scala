package Akka

//封装 worker 信息
class WorkerInfo(val workerId:String,val memory:Int,val cores:Int) {
  //定义一个变量用于存放 worker 上一次心跳时间
  var lastHeartBeatTime :Long=_
  override def toString: String = {
    s"workerId:$workerId , memory:$memory , cores:$cores"
  }
}