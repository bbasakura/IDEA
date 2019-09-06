package com.itheima.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 这是通过对对分区内容操作进行词频统计
  */
object SparkWordCountIter {


  def main(args: Array[String]): Unit = {

    //1.构建sparkcontext的对象，需要一个SparkConf(封装applacation的相关信息)

    val conf: SparkConf = new SparkConf()
            .setMaster("local[2]")
            .setAppName(this.getClass.getSimpleName.stripSuffix("$"))

    //2.创建SparckContext对象
    val sc: SparkContext = new SparkContext(conf)

    //设置日志的级别
    sc.setLogLevel("WARN")

    //3.从HDFS上读取文件

    val inputRDD: RDD[String] = sc.textFile("hdfs://hadoop01:8020/input/wc/wordcount.data")

    //4.处理数据（iter方式处理）

    //空格切分，过滤掉空
    val wordRDD = inputRDD.flatMap(line => line.split("\\s+")).filter(word => word.length > 0)
      //针对分区进行操作，将分区中的数据转化为二元组，表示一个单词出现的次数为1
      .mapPartitions { iter => iter.map(word => (word, 1)) }
      //按照key进行聚合操作
      .reduceByKey((a, b) => a + b)

    //5.保存到外部存储
    wordRDD.foreachPartition{data =>data.foreach{

      case(word,count)=>println(s"$word + $count")
    }}

    //6.关闭应用

    sc.stop()

  }

}
