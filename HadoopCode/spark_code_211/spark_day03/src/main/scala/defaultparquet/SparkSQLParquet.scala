package defaultparquet  //-----------------parquet是一个关键字，不能用来包名

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * SparkSQL读取Parquet格式数据（列式存储）
  */
object SparkSQLParquet {

  def main(args: Array[String]): Unit = {
    // TODO: 1、构建SparkSession实例对象
    val spark: SparkSession = {
      // 使用建造者模型构建SparkSession实例
      val session: SparkSession = SparkSession.builder()
        .master("local[3]")
        .appName(this.getClass.getSimpleName.stripSuffix("$"))
        .getOrCreate()
      // 设置日志级别
      session.sparkContext.setLogLevel("WARN")
      // 返回
      session
    }
    import spark.implicits._


    // TODO: 2、读取parquet格式文件数据，从本地文件系统读取
    val defaultDF: DataFrame = spark.read.load("datas/resources/users.parquet")
    defaultDF.printSchema()
    defaultDF.show(10, truncate = false)

    println("============================================================")

    val parquetDF: DataFrame = spark.read.parquet("datas/resources/users.parquet")
    parquetDF.printSchema()
    parquetDF.show(10, truncate = false)


    // 应用运行结束，关闭资源
    spark.stop()
  }

}
