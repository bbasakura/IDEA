package saprkSessionWC

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * 用sparksession计算wc的分组排序
  */
object SparkSessionWordCount {


  def main(args: Array[String]): Unit = {

    //1.构建sparkSession的实例对象

    val spark: SparkSession = SparkSession.builder()

      .master("local[3]")
      .appName(this.getClass.getSimpleName.stripSuffix("$"))
      //      .config("spark.eventLog.enabled",true)
      //      .config("spark.eventLog.dir", "datas/group/group.data")
      //      .config("spark.eventLog.compress", "true")
      //此函数表示，存在即返回，不存在就创建对象，这是单例模式，底层还是SparkContext对象
      .getOrCreate()

    import spark.implicits._




    //2.设置日志的级别

    spark.sparkContext.setLogLevel("WARN")

    //3.读取HDFS上的数据，DataSet= RDD+ Schame

//    import spark.implicits._

    val inputDS: Dataset[String] = spark.read.textFile("datas/wordcount.data")
    //打印Schame的信息
    inputDS.printSchema()
    inputDS.show(10)

    //4.词频统计 DaraFrame= DataSet[Row]

    var wordCountDF: DataFrame = inputDS.flatMap { line => line.split("\\s+").filter(word => null != word && word.length > 0) }
      //按照单词分组，进行统计
      .groupBy("value").count()
    wordCountDF.printSchema()

    wordCountDF.show(10)
    //5.程序运行完成，关闭资源

    spark.stop()

  }


}
