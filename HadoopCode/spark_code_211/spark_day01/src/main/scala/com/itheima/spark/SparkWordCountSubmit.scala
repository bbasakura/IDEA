package com.itheima.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCountSubmit {

  def main(args: Array[String]): Unit = {

    if(args.length < 2){
      println("Usage: SparkWordCountSubmit <input> <output> ...........")
      System.exit(0)
    }

    // TODO：1、创建SparkContext实例对象，对象名称为sc
    // a. 创建SparkConf对象封装Application相关配置信息
    val sparkConf: SparkConf = new SparkConf()
      //.setMaster("local[2]")  // 通过spark-submit中--master指定
      .setAppName("SparkWordCountSubmit")
    // b. 构建SparkContext上下文实例对象
    val sc: SparkContext = new SparkContext(sparkConf)
    // 设置日志级别
    sc.setLogLevel("WARN")


    // TODO: 2、读取要处理的数据文件，数据封装到RDD集合中
    val inputRDD: RDD[String] = sc.textFile(args(0))


    // TODO: 3、调用集合RDD中函数处理分析数据
    // a. 对集合中每条数据按照空格进行分割，并将数据扁平化flatten
    val wordsRDD: RDD[String] = inputRDD.flatMap(line => line.split("\\s+"))
    // b. 将每个单词转换为二元组，表示每个单词出现一次
    val tuplesRDD: RDD[(String, Int)] = wordsRDD.map(word => (word, 1))
    // c. 按照Key进行分组聚合操作（先局部聚合，再全局聚合）
    val wordcountsRDD: RDD[(String, Int)] = tuplesRDD.reduceByKey((a, b) => a + b)


    // TODO: 4、保存结果RDD到外部存储系统（HDFS、MySQL、HBase。。。。）
    wordcountsRDD.saveAsTextFile(s"${args(1)}-${System.currentTimeMillis()}")


    // TODO：5、应用运行完成，关闭资源
    sc.stop()
  }

}
