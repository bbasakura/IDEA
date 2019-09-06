package Akka

trait RemoteMessage extends Serializable{
}
//worker 向 master 发送注册信息，由于不在同一进程中，需要实现序列化
case class RegisterMessage(val workerId:String,val memory:Int,val cores:Int) extends
  RemoteMessage
//master 反馈注册成功信息给 worker,由于不在同一进程中，也需要实现序列化
case class RegisteredMessage(message:String) extends RemoteMessage
//worker 向 worker 发送心跳 由于在同一进程中，不需要实现序列化
case object HeartBeat
//worker 向 master 发送心跳，由于不在同一进程中，需要实现序列化
case class SendHeartBeat(val workerId:String) extends RemoteMessage
//master 自己向自己发送消息，由于在同一进程中，不需要实现序列化
case object CheckOutTime