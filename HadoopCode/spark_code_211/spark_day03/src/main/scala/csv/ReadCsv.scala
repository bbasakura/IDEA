package csv

import org.apache.spark.sql.{DataFrame, Dataset,SparkSession}
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

    //无head的csv

    val dataFrame = spark.read
      .option("sep", "\\t")
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("datas/ml-100k/u.dat")
    dataFrame.printSchema()
    dataFrame.show(10)

    val schema = StructType(
      Array(
        StructField("userId", StringType, nullable = true),
        StructField("itemId", StringType, nullable = true),
        StructField("rating", DoubleType, nullable = true),
        StructField("timestamp", LongType, nullable = true)
      )
    )

    val mlsDF: DataFrame = spark.read
      .schema(schema)
      .option("sep", "\\t")
      .csv("datas/ml-100k/u.data")
    mlsDF.printSchema()
    mlsDF.show(10)




  }

}
