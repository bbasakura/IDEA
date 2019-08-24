package wordCountCase

import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

//提交任务的样例类
case class SubmitTask(filename: String) {}

//返回结果的样例类
case class ResultTask(result: Map[String, Int]) {}

class Task extends Actor {


  override def act(): Unit = {

    loop {
      react {

        case SubmitTask(filename) => {
          //1、利用Source读取文件内容
          val content: String = Source.fromFile(filename).mkString

          // 2、按照换行符切分  window下文件的换行符 \r\n, linux下文件的换行符 \n
          val lines: Array[String] = content.split("\r\n")

          //3、切分每一行，获取所有的单词
          val words: Array[String] = lines.flatMap(_.split(" "))

          //4、每个单词记为1
          val wordAndOne: Array[(String, Int)] = words.map((_, 1))

          // 5、相同单词进行分组
          val wordGroup: Map[String, Array[(String, Int)]] = wordAndOne.groupBy(_._1)

          // 6、统计每个单词出现的次数
          val result: Map[String, Int] = wordGroup.mapValues(_.length)

          // 7、返回消息给发送方
          sender ! ResultTask(result)


        }
      }
    }
  }
}
  object WorkCount {


    def main(args: Array[String]): Unit = {

      //读取文件目录----得到的是数组
      var files = Array("F:\\1.txt", "F:\\2.txt", "F:\\3.txt")

      //返回的结果状态（Future）存放到Set中
      val futureSet = new mutable.HashSet[Future[Any]]()

      //定义一个List集合存放真正的结果
      val resultList = new ListBuffer[ResultTask]

      //遍历文件数组，准备提交Task

      for (i <- files) {

        //创建actor
        val task = new Task

        //启动actor
        task.start()

        //发送消息===发送的是SubTask
        val reply = task !! SubmitTask(i)

        //添加结果到Set集合中

        futureSet += reply


      }

      //此时得到了Future的Set集合，但这不是真正的返回的的内容结果，只是一个状态

      while (futureSet.size > 0) {

        //过滤出真正有结果数据的Future
        val complete: mutable.HashSet[Future[Any]] = futureSet.filter(_.isSet)
        for (resfu <- complete) {

          //获取future中真正的返回数据结果
          val resuleTrue: Any = resfu.apply()

          //将数据添加到真正返回数据的集合中
          //因为返回的是Any,所以强转以下存储.asInstanceOf
          resultList += resuleTrue.asInstanceOf[ResultTask]

          //在Set集合中删除已经去过数据的Future
          futureSet -= resfu


        }


      }

      println(resultList.map((_.result)).flatten.groupBy(_._1).mapValues(x => x.foldLeft(0)(_ + _._2)))


    }


  }

