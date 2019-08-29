package custom

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
/*
* 自定义的接收器，自定义接收器Receiver从TCP Socket读取数据，需要如下两个参数（hostname: port)
  * 	表示从哪台机器的那个端口上读取数据
*
*
*
*
* */

class CustomReceiver(val host:String,val port:Int) extends Receiver[String](storageLevel = StorageLevel.MEMORY_AND_DISK) {
  override def onStart(): Unit = {

    //启动一个线程一直接受消息

    new Thread(

      new Runnable {
        override def run(): Unit = receive()
      }
    ).start()



  }

  override def onStop(): Unit = {}



  private def receive(): Unit = {

    var socket: Socket = null
    var userInput: String = null
    try {
      // Connect to host:port
      socket = new Socket(host, port)

      // Until stopped or connection broken continue reading
      val reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))

      userInput = reader.readLine()
      while(!isStopped && userInput != null) {
        // TODO: 将读取的数据进行存储
        store(userInput)

        userInput = reader.readLine()
      }
      reader.close()
      socket.close()

      // Restart in an attempt to connect again when server is active again
      restart("Trying to connect again")
    } catch {
      case e: java.net.ConnectException =>
        // restart if could not connect to server
        restart("Error connecting to " + host + ":" + port, e)
      case t: Throwable =>
        // restart if there is any other error
        restart("Error receiving data", t)
    }














  }
}
