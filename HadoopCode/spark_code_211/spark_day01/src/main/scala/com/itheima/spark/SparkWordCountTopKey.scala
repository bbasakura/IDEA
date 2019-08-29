package com.itheima.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCountTopKey {


  def main(args: Array[String]): Unit = {

    // TODO：1、创建SparkContext实例对象，对象名称为sc
    // a. 创建SparkConf对象封装Application相关配置信息
    val sparkConf: SparkConf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("SparkWordCount")
    // b. 构建SparkContext上下文实例对象
    val sc: SparkContext = new SparkContext(sparkConf)
    // 设置日志级别
    sc.setLogLevel("WARN")


    // TODO: 2、读取要处理的数据文件，数据封装到RDD集合中
    val inputRDD: RDD[String] = sc.textFile("hdfs://hadoop01:8020/input/wc/wordcount.data")


    // TODO: 3、调用集合RDD中函数处理分析数据
    // a. 对集合中每条数据按照空格进行分割，并将数据扁平化flatten
    val wordsRDD: RDD[String] = inputRDD.flatMap(line => line.split("\\s+"))
    // b. 将每个单词转换为二元组，表示每个单词出现一次
    val tuplesRDD: RDD[(String, Int)] = wordsRDD.map(word => (word, 1))
    // c. 按照Key进行分组聚合操作（先局部聚合，再全局聚合）
    val wordcountsRDD: RDD[(String, Int)] = tuplesRDD.reduceByKey((a, b) => a + b)


    // TODO: 4、保存结果RDD到外部存储系统（HDFS、MySQL、HBase。。。。）
    wordcountsRDD.foreach(println)


    println("=======================================================")
    /**
      * TopKey: 按照词频进行降序排序，获取次数最多的三个单词
      */
    // 方式一：sortByKey按照Key进行排序，建议使用此种方式排序
    /*
    def sortByKey(
      ascending: Boolean = true,
      numPartitions: Int = self.partitions.length
    ): RDD[(K, V)]
     */
    wordcountsRDD
      .map(tuple => tuple.swap)	// 将Key和Value 互换
      .sortByKey(ascending = false)
      .take(3)
      .foreach(println)

    println("=======================================================")
    // 方式二：sortBy按照Key进行排序，底层还是sortByKey函数
    /*
      def sortBy[K](
        f: (T) => K, // 指定如何排序，此处设置按照二元组Value排序
        ascending: Boolean = true,
        numPartitions: Int = this.partitions.length
      )
      (implicit ord: Ordering[K], ctag: ClassTag[K]): RDD[T]
     */
    wordcountsRDD
      .sortBy(tuple => tuple._2, ascending = false)
      .take(3)
      .foreach(println)


    println("=======================================================")
    // 方式三：top函数慎用
    /*
      def top(num: Int)(implicit ord: Ordering[T]): Array[T]
     */
    wordcountsRDD
      .top(3)(Ordering.by(tuple => tuple._2))
      .foreach(println)


    // TODO: 为了查看应用WEB UI，线程休眠
    Thread.sleep(10000000)


    // TODO：5、应用运行完成，关闭资源
    sc.stop()
  }

}
