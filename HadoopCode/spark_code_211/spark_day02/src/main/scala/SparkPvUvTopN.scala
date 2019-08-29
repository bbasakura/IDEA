import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 对网站点击流日志进行分析：PV、UV和TopN URL
  */
object SparkPvUvTopN {


  def main(args: Array[String]): Unit = {

    // 1.构建SparkContext实例对象，用于读取数据和调度Job执行
    val sparkConf = new SparkConf()
      .setMaster("local[3]").setAppName("SparkPvUvTopN")
    // 2.建议使用getOrCreate获取SparkContext实例
    val sc: SparkContext = SparkContext.getOrCreate(sparkConf)
    // 设置日志级别
    sc.setLogLevel("WARN")

    // TODO: 2、读取网站点击流日志数据
    // 设置分区数目
        val accessLogsRDD: RDD[String] = sc.textFile("datas/logs/access.log",minPartitions = 3)
    /**
      * TODO: 3、需求 -> PV、UV和TopN URL分析
      *   - PV： url不为空，如果为空，脏数据，不合格
      * （Page View）访问量, 即页面浏览量或点击量，衡量网站用户访问的网页数量；
      *   - UV：实际项目中，网站在收集流量的数据的时候，将会给每个用户生成一个唯一ID，用于统计UV。
      * 使用session、cookie统计不准确，此处使用IP暂代。
      * （Unique Visitor）独立访客，统计1天内访问某站点的用户数(以cookie为依据);
      *   - TopN URL:
      * 统计来源最多的URL，访问我们网站从哪个URL连接过来的，
      */
    val filterRDD = accessLogsRDD
      .map(line => line.split("\\s+")) // 每行数据分割,出来是一行行的数据
      // 过滤不合格的数据，URL不能为空，refer也不可能为null，arr(0).trim去除空格
      .filter(arr => arr.length >= 11 && arr(0).trim.length > 0 && arr(6) != null && arr(10).trim != null)
      // 返回三元组格式(ip, url, refer)，需要的三个数据，一起放在元祖中，方便操作
      .map(arr => (arr(0), arr(6), arr(10)))  //（a，b，c）直接创建元祖，注意创建方式

    // 由于RDD被后续使用多次，所以需要进行缓存操作
    filterRDD.persist(StorageLevel.MEMORY_AND_DISK)

    // TODO: 3.1、统计PV
    val totalPv: Long = filterRDD.count()
    println(s"Total PV = $totalPv")

    // TODO: 3.2 统计UV
    val totalUv: Long = filterRDD
      .map(tuple => tuple._1) // 获取IP地址，用于确定唯一用户，
      .distinct() // 去重操作
      .count()
    println(s"Total UV = $totalUv")

    // TODO: 3.3 统计获取TopN Refer  于
    val top10Url: Array[(String, Int)] = filterRDD
      .map(tuple => (tuple._3, 1))
      .reduceByKey(_ + _) // 聚合统计各个来源URL出现的次数
      .sortBy(tuple => tuple._2, ascending = false)
      .take(10)
    top10Url.foreach(println)

    // 释放存储
    filterRDD.unpersist()

    // 程序运行结束，关闭资源
    if (!sc.isStopped) sc.stop()
  }
}
