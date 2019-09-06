package mysql


import java.util.Properties

import org.apache.spark.TaskContext
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * SparkSQL直接从MySQL表中读取数据
  */
object SparkSQLMySQL {

  def main(args: Array[String]): Unit = {
    // TODO: 1、构建SparkSession实例对象
    val spark: SparkSession = {
      // 使用建造者模型构建SparkSession实例
      val session: SparkSession = SparkSession.builder()
        .master("local[3]")
        .appName(this.getClass.getSimpleName.stripSuffix("$"))
        // 设置join或者agg时分区数目
        .config("spark.sql.shuffle.partitions", "3")
        .getOrCreate()
      // 设置日志级别
      session.sparkContext.setLogLevel("WARN")
      // 返回
      session
    }
    import spark.implicits._
    // 由于要分析数据，使用函数（内置），所以导入SparkSQL函数库包
    import org.apache.spark.sql.functions._


    /**
      * 读取MySQL表中的数据
      */
    val url: String = "jdbc:mysql://localhost:3306/test"
    val table: String = "so"
    //
    val props: Properties = new Properties()
    props.put("user", "root")
    props.put("password","root")
    props.put("driver", "com.mysql.jdbc.Driver")

    // 方式一：单分区模式，读取RDBMs表的数据到DataFrame中，只有一个分区
    /*
      def jdbc(url: String, table: String, properties: Properties): DataFrame
     */
    val soDF: DataFrame = spark.read.jdbc(url, table, props)
    soDF.printSchema()
    soDF.foreachPartition{iter => println(s"p-${TaskContext.getPartitionId()}: ${iter.size}")}


    println("=============================================================")

    /*
      def jdbc(
        url: String,
        table: String,

        columnName: String, // 字段名称
        lowerBound: Long, // 下限值
        upperBound: Long, // 上限值
        numPartitions: Int, // 分区数目

        connectionProperties: Properties
       ): DataFrame
     */
    // TODO: 读取订单销售额大于20且小于200订单数据，分区数目为5个， 20  -  56:0    56 - 92： 1
    val soDF2: DataFrame = spark.read.jdbc(
      url, table,
      "order_amt", 20L, 200L, 5,
      props
    )
    soDF2.printSchema()
    soDF2.foreachPartition{iter => println(s"p-${TaskContext.getPartitionId()}: ${iter.size}")}


    println("=============================================================")
    /*
     def jdbc(
        url: String,
        table: String,
        predicates: Array[String],
        connectionProperties: Properties
      ): DataFrame
     */
    // 设置WHERE条件语句
    val predicates: Array[String] = Array(
      "order_amt >= 20 AND order_amt < 30", "order_amt >= 30 AND order_amt < 40",
      "order_amt >= 40 AND order_amt < 55", "order_amt >= 55 AND order_amt < 80",
      "order_amt >= 80 AND order_amt < 200"
    )
    val soDF3: DataFrame = spark.read.jdbc(
      url, table,
      predicates,
      props
    )
    soDF3.printSchema()
    soDF3.foreachPartition{iter => println(s"p-${TaskContext.getPartitionId()}: ${iter.size}")}



    Thread.sleep(1000000)

    // 应用运行结束，关闭资源
    spark.stop()
  }
}

