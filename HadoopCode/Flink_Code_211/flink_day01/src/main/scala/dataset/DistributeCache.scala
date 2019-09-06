package dataset

import java.io.File

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


object DistributeCache {



  def main(args: Array[String]): Unit = {

    /**
      * 1.获取执行环境
      * 2.注册分布式文件
      * 3.数据转换：新建样例类，读取分布式缓存的数据
      * 4.数据打印，触发执行
      */
    //1.获取执行环境
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //2.注册分布式文件
    env.registerCachedFile("hdfs://node01:8020/tmp/subject.txt","cache")

    //本地集合数据
    val clazz:DataSet[Clazz] = env.fromElements(
      Clazz(1,"class_1"),
      Clazz(2,"class_1"),
      Clazz(3,"class_2"),
      Clazz(4,"class_2"),
      Clazz(5,"class_3"),
      Clazz(6,"class_3"),
      Clazz(7,"class_4"),
      Clazz(8,"class_1")
    )

    //3.数据转换：新建样例类，读取分布式缓存的数据
    clazz.map(new RichMapFunction[Clazz,Info] {
      val buffer = new ArrayBuffer[String]()
      override def open(parameters: Configuration): Unit = {
        //获取分布缓存文件数据
        val cache: File = getRuntimeContext.getDistributedCache.getFile("cache")
        //解析
        val lines: Iterator[String] = Source.fromFile(cache.getAbsoluteFile).getLines()
        lines.foreach(line=>{
          buffer.append(line)
        })

      }

      override def map(value: Clazz): Info = {
        var info: Info = null
        for(line<- buffer){
          val arr: Array[String] = line.split(",")
          if(arr(0).toInt == value.id){
            info = Info(value.id,value.clazz,arr(1),arr(2).toDouble)
          }
        }
        info
      }
    }).print()
  }
}
case class Clazz(id:Int,clazz:String)
//组成：(学号 ， 班级 ， 学科 ， 分数)
case class Info(id:Int,clazz:String,subject:String,score:Double)
