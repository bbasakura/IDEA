package csv

import org.apache.spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}
import org.apache.spark.sql.types._

object ReadCsv {


  def main(args: Array[String]): Unit = {

    // TODO: 1、构建SparkSession实例对象这是一个方法

    val spark: SparkSession = {
      val session: SparkSession = SparkSession.builder()
        .master("local[3]")
        .appName(this.getClass.getSimpleName.stripSuffix("$"))
        .getOrCreate()
      //设置日志级别
      session.sparkContext.setLogLevel("WARN")
      //返回去一个sparkSession的实例对象
      session
    }
    import spark.implicits._   //spark是一个对象  =====val spark: SparkSession



    val schema = StructType(
      Array(
        StructField("userId", StringType, nullable = true),
        StructField("itemId", StringType, nullable = true),
        StructField("rating", DoubleType, nullable = true),
        StructField("timestamp", LongType, nullable = true)
      )
    )


    //无head的csv  当读取CSV格式数据时，如果文件首行不是列名称，自定Schema信息

    val mlsDF: DataFrame = spark.read
      .schema(schema)
      .option("sep", "\\t")
      .csv("datas/ml-100k/u.data")
    mlsDF.printSchema()
    mlsDF.show(10)
println("===============================================================")

    //第一行是head的CSV的文件，，，，简单年多了…………………………………………………………………………………………………………………………
    val dataFrame = spark.read
      .option("sep", "\\t")
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("datas/ml-100k/u.dat")
    dataFrame.printSchema()
    dataFrame.show(10)



    println("==============================================================")
    mlsDF
      // 降低分区数为1，此时保存JSON文件就是一个文件
      .coalesce(1)
      .write
      .mode(SaveMode.Overwrite) // 设置保存模式
      .json("datas/ml-json/")


    spark.read    //链式编程————————————————————————————————————————————————————保存到文件
      .option("sep", "\\t")
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("datas/ml-100k/u.dat")
      .coalesce(1)
      .write
      .mode(SaveMode.Overwrite) // 设置保存模式
      .json("datas/ml-json/")



    //关闭应用
    spark.stop()
  }

}
