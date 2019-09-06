package json


import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * SparkSQL读取JSON格式压缩数据
  */
object SparkSQLJson {

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
    val jsonDF: DataFrame = spark.read.json("datas/json/2015-03-01-11.json.gz")
    jsonDF.printSchema()
    //jsonDF.show(10, truncate = true)

    println("=======================================================")

    /**
      * 仅仅获取   type、created_at、public、id
      */
    val githubDS: Dataset[String] = spark.read.textFile("datas/json/2015-03-01-11.json.gz")
    /*
    root
       |-- value: string (nullable = true)
     */
    githubDS.printSchema()
    githubDS.show(10, truncate = false)


    // 使用函数，Spark从2.x开始提供对于JSON格式数据解析函数
    import org.apache.spark.sql.functions._
    githubDS
      // def get_json_object(e: Column, path: String): Column
      .select(
      get_json_object($"value", "$.id").as("id"),
      get_json_object($"value", "$.type").as("type"),
      get_json_object($"value", "$.public").as("public"),
      get_json_object($"value", "$.created_at").as("created_at")
    )
      .show(10, truncate = false)


    // 应用运行结束，关闭资源
    spark.stop()
  }

}
