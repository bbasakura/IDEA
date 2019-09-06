package hive


import org.apache.spark.sql.SparkSession

/**
  * 在IDEA中读取Hive表的数据，进行分析处理
  */
object SparkSQLHive {

  def main(args: Array[String]): Unit = {


    // TODO: 1、构建SparkSession实例对象
    val spark: SparkSession = {
      // 使用建造者模型构建SparkSession实例
      val session: SparkSession = SparkSession.builder()
        .master("local[3]")
        .appName(this.getClass.getSimpleName.stripSuffix("$"))
        // 设置join或者agg时分区数目
        .config("spark.sql.shuffle.partitions", "3")
        // 告知应用，集成Hive，读取Hive元数据
        .enableHiveSupport()
        .getOrCreate()
      // 设置日志级别
      session.sparkContext.setLogLevel("WARN")
      // 返回
      session
    }
    import spark.implicits._
    // 由于要分析数据，使用函数（内置），所以导入SparkSQL函数库包
    import org.apache.spark.sql.functions._


    // 采用SQL方式读取Hive表中的数据
    spark.sql(
      """
        			  |select deptno, avg(sal) as avg_sal from db_hive.emp group by deptno
      			""".stripMargin)
      .show(10, truncate = false)


    Thread.sleep(1000000)

    // 应用运行结束，关闭资源
    spark.stop()
  }


}

