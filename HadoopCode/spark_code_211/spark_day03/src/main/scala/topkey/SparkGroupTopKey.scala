package topkey

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object SparkGroupTopKey {


//实现：对数据文件中的第一个字段进行分组，获取每个组内最大前3个值（TopKey）

  def main(args: Array[String]): Unit = {

    // 1.构建SparkContext实例对象
    val sc: SparkContext = {
      // a. 创建SparkConf，设置应用配置信息
      val sparkConf: SparkConf = new SparkConf()
        .setMaster("local[3]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
      // b. 传递sparkConf实例创建SparkContext上下文实例对象
      SparkContext.getOrCreate(sparkConf)  // TODO: 建议此种写法
    }
    sc.setLogLevel("WARN")


    //2.从HDFS上读取数据

    val dataRDD: RDD[(String, Int)] =sc.textFile("datas/group.data",minPartitions = 2)
    //按需求可知，需要转换为二元组
        .mapPartitions{iter=>
      iter.map{line=>
        //按照空格切分，
      val arr: Array[String] = line.trim.split("\\s+")

        //返回二元組
        (arr(0),arr(1).toInt)
      }
    }
    //3. 使用groupByKey 完成分组、排序及TopKey
    dataRDD.groupByKey()
    //按照key进行分组，将相同key的value放到一起，对分组后的每组数据——再进行排序__val xx: Iterator[(String, Iterable[Int])] = iter
      .mapPartitions { iter =>
      iter.map {
        case(key, values)=>
          //对组内的数据进行排序，values是一个迭代器，要转换成List,才可以sortBy
          val topKey: List[Int] =values.toList.sortBy(x => -x).take(3)
          //返回一个二元组
          (key,topKey)
      }
    }
      //遍历打印输出
      .foreachPartition{datas=>
        datas.foreach(println)
      }
    println("=========================================================")



//    上述方式会造成数据倾斜，不建议使用====== 使用aggregateByKey函数完成


    /*
		  def aggregateByKey[U: ClassTag]
		  (zeroValue: U) // 表示聚合中间临时变量的初始值，此处数据类型为集合，使用可变列表ListBuffer
		  (
		  	seqOp: (U, V) => U, // 分区数据聚合
		  	combOp: (U, U) => U // 分区聚合结果聚合
		  ): RDD[(K, U)]
		 */

    dataRDD.aggregateByKey(new ListBuffer[Int])(

    // seqOp: (U, V) => U
    (u:ListBuffer[Int],v:Int)=> {
      //將分区中的每个不元素加入到列表中，获取前三个，注意函数的参数的传递——————序列化？？？List
      u += v
      u.sortBy(x => -x)
    },  //_________________________________________注意逗号不要少了
      // combOp: (U, U) => U
      (u1:ListBuffer[Int],u2:ListBuffer[Int])=>{
      // 将分区聚合结果合并，排序获取前3个
        u1++=u2
        u1.sortBy(x => -x).take(3)
      }
    )
      //遍历打印
      .foreachPartition(datas=>datas.foreach(print))


    Thread.sleep(10000000)

    // 应用结束，关闭资源
    sc.stop()
}
}
