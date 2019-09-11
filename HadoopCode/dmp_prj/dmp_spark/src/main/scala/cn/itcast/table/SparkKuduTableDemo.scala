package cn.itcast.table

import java.util

import org.apache.kudu.client.{CreateTableOptions, KuduTable}
import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object SparkKuduTableDemo {



  //创建表的方法
  def createKuduTable(context: KuduContext, tableName: String): Unit = {

    // 1. 定义Schema信息
    val schema: StructType = StructType(
      Array(
        StructField("id", IntegerType, nullable = true),
        StructField("name", StringType, nullable = true),
        StructField("age", IntegerType, nullable = true),
        StructField("gender", StringType, nullable = true)
      )
    )

    //2、设置主键
    val Key= Seq("id")
    //3、设置分区策略及副本数
    val options: CreateTableOptions = new CreateTableOptions()
    //使用Hash分区策略
    val columns: util.ArrayList[String] =new util.ArrayList[String]()
    //主键分区----3个
    columns.add("id")
    options.addHashPartitions(columns,3)

    options.setNumReplicas(3) //设置副本数为3个，必须为计数

    //创建表
    val kuduTable: KuduTable = context.createTable(tableName,schema,Key,options)

    //打印表的地址
    println(kuduTable)
  }

  def main(args: Array[String]): Unit = {

    // TODO: 1、构建SparkSession实例对象

    val sparkConf: SparkConf = new SparkConf().setMaster("local[2]")
      .setAppName(this.getClass
      .getSimpleName.stripSuffix("$"))

    // 采用建造者模式创建SparkSession实例

    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    // TODO: 2、构建KuduContext实例对象，用以操作Kudu中表的数据

    val kuduMaster = "hadoop01:7051,hadoop02:7051,hadoop03:7051"

    val kuduContext = new KuduContext(kuduMaster, spark.sparkContext)

    // TODO：3、创建表
    createKuduTable(kuduContext,"spark_table_test02")


    // TODO: 删除Kudu中表
    if(kuduContext.tableExists("spark_table_test021")){
      val response = kuduContext.deleteTable("spark_table_test02")
      println(response.getElapsedMillis)
    }
    // TODO：4、应用结束，关闭资源
    spark.stop()
  }

}
