package mvprocess

import org.apache.spark.sql.{Dataset, SparkSession}

/**
  * 对电影评分数据进行统计分析，获取Top10电影（电影评分平均值最高，并且每个电影被评分的次数大于2000)
  */
object SparkSQLRatingsProcess {

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


    // TODO: 2、读取电影评分数据, 从本地文件系统读取 , 数据格式：1::1193::5::978300760
    val rawRatingsDS: Dataset[String] = spark.read.textFile("datas/ml-1m/ratings.dat")


    // TODO: 3、对读取的数据进行转换操作，DataFrame -> (UserID::MovieID::Rating::Timestamp)
    val ratingsDS: Dataset[MovieRating] = rawRatingsDS
      .mapPartitions{datas =>
        // val xx: Iterator[String] = datas
        datas.map{data =>
          // 按照分隔符分割字符串
          val Array(userid, movieid, rating, timestamp) = data.trim.split("::")
          // 返回MLRating实例对象
          MovieRating(userid, movieid, rating.toDouble, timestamp.toLong)
        }
      }

    // TODO: 4、使用SQL分析$$$$$$$$$$$$$$$$$$$SQL 分析操作
    // a. 注册Dataset为临时视图
    ratingsDS.createOrReplaceTempView("view_tmp_ratings")
    // b. 编写SQL，执行
    spark.sql(
      """
        			  |SELECT
        			  |  movieId, ROUND(AVG(rating), 2) AS avg_rating, COUNT(movieId) AS count_rating
        			  |FROM
        			  |  view_tmp_ratings
        			  |GROUP BY
        			  |  movieId
        			  |HAVING
        			  |  count_rating > 2000
        			  |ORDER BY
        			  |  count_rating DESC, avg_rating DESC
        			  |LIMIT
        			  |   10
      			""".stripMargin)
      .show(10, truncate = false)

    println("================================================================")

    // TODO: 5、使用DSL分析操作
    ratingsDS
      // 选取字段的值， 将列名的名称（字符串）前面加上 $ 后，将字符串转换为Column对象（表示列）
      .select($"movieId", $"rating")
      // 按照 movieId 分组
      .groupBy($"movieId")
      // 分组后，通常对组内数据进行聚合操作
      .agg(
      count($"movieId").as("count_rating"),
      round(avg($"rating"), 2).as("avg_rating")
    )
      // 过滤符合数据
      .filter($"count_rating".gt(2000))
      // 指定字段排序
      .orderBy($"count_rating".desc, $"avg_rating".desc)
      // 获取10条数据
      .limit(10)
      .show(10, truncate = false)


    Thread.sleep(1000000)


    // 应用运行结束，关闭资源
    spark.stop()
  }

}
