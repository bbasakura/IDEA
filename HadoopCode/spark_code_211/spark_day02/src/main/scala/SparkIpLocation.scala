import java.sql
import java.sql.DriverManager

import com.mysql.jdbc.Connection
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 需要通过日志信息（运行商或者网站自己生成）和城市ip段信息来判断用户的ip段，统计热点经纬度。
  * 思路
  * 1、加载城市ip段信息，获取ip起始数字和结束数字，经度，维度
  * 2、加载日志数据，获取ip信息，然后转换为数字，和ip段比较
  * 3、比较的时候采用二分法查找，找到对应的经度和维度
  * 4、然后对经度和维度做单词计数
  */
object SparkIpLocation {


  /**
    * 将IP地址转换Long类型数组
    */
  def ipToLong(ip: String): Long = {
    val fragments = ip.split("[.]")
    var ipNum = 0L
    for (i <- 0 until fragments.length) {
      ipNum = fragments(i).toLong | ipNum << 8L // ??????
    }
    // 返回
    ipNum
  }

  /**
    * 采用二分查找获取IP地址对应的经纬度数据信息,中间的是一段一段的ip范围，只要的我取出的这一个ip
    * 落在了某一端中，则是匹配完成，找到了对应的城市信息
    */
  def binarySearch(ipArray: Array[(Long, Long, String, String)], ipLong: Long): Int = {
    // 先定义二分查找起始和结束下标（IP表中某一个城市的起始点和结束点）
    var startIndex = 0
    var endIndex = ipArray.length - 1

    while (startIndex <= endIndex) {
      // 计算中间数据（某一段范围的）下标
      //      val middleIndex = (startIndex + endIndex) / 2----不安全的算法，一溢出
      val middleIndex = startIndex + (endIndex - startIndex) / 2
      // 获取起始IP和结束IP数字，用于比较
      val (startIpLong, endIpLong, _, _) = ipArray(middleIndex)

      // 如果ip值，大于起始值，小于结束值，直接返回
      if (startIpLong <= ipLong && ipLong <= endIpLong) {
        return middleIndex
      }
      // 如果ip值小于起始值，ip位于左边
      if (ipLong < startIpLong) {
        endIndex = middleIndex - 1
      }
      // 如果ip值大于结束值，ip位于右边
      if (ipLong > endIpLong) {
        startIndex = middleIndex + 1
      }
    }
    // 如果查找不到返回-1
    -1
  }


  /**
    * 将分区中的数据保存到MySQL表中------原始的JDBC操作数据库
    */
  def saveToMySQL(iter: Iterator[((String, String), Int)]): Unit = {
    // 加载驱动类
    Class.forName("com.mysql.jdbc.Driver")
    // 连接数据库三要素
    val url = "jdbc:mysql://localhost:3306/test"
    val username = "root"
    val password = "123456"

    var conn: sql.Connection = null
    try {
      // i. 获取数据库连接
      conn = DriverManager.getConnection(url, username, password)
      // ii.-----实际工作中，用upsert语句，判断是否存在，如果存在，值相加，不存在，直接储存
      val sqlStr = "INSERT INTO tb_iplocation(longitude, latitude, total_count) VALUES(?, ?, ?)"
      val pstmt = conn.prepareStatement(sqlStr)
      // iii. Insert Data To MySQL Table
      iter.foreach { case ((longitude, latitude), cnt) =>
        println(s"longitude = $longitude, latitude = $latitude, count = $cnt")
        // 将字段进行赋值
        pstmt.setString(1, longitude)
        pstmt.setString(2, latitude)
        pstmt.setInt(3, cnt)
        // 工作中的向数据库插入数据，也是批量插入，要么全部成功，要么全部数百，Batch--加入批次中
        pstmt.addBatch()
      }
      // 执行批次插入
      pstmt.executeBatch()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (null != conn) conn.close()
    }
  }


  def main(args: Array[String]): Unit = {

    // TODO: 1、构建SparkContext实例对象，用于读取数据和调度Job执行
    val sparkConf = new SparkConf()
      .setMaster("local[3]").setAppName("SparkPvUvTopN")
    // 建议使用getOrCreate获取SparkContext实例----首次判断
    val sc: SparkContext = SparkContext.getOrCreate(sparkConf)
    // 设置日志级别
    sc.setLogLevel("WARN")

    // TODO: 2、加载IP地址信息，用于后面日志信息的查询范围，获取起始IP地址和结束IP地址数字及对应经纬度
    val ipInfoRDD: RDD[(Long, Long, String, String)] = sc
      .textFile("datas/ips/ip.txt") // 读取文本文件数据
      // 过滤不合格的数据
      .filter(line => null != line && line.trim.length > 0 && line.trim.split("\\|").length >= 15)
      .mapPartitions { iter =>
        // 针对每个分区数据操作
        iter.map { line =>
          val arr = line.split("\\|")
          // 以元组的形式返回(startIp, endIp, longitude, latitude)
          (arr(2).toLong, arr(3).toLong, arr(arr.length - 2), arr(arr.length - 1))
        }
      }

    val ipInfoArray: Array[(Long, Long, String, String)] = ipInfoRDD.collect()
    // TODO：使用广播变量将IpArray数据广播到Executor中使用
    val ipArrayBroadcast: Broadcast[Array[(Long, Long, String, String)]] = sc.broadcast(ipInfoArray)

    // TODO: 3、读取访问日志数据
    val longLatCountRDD: RDD[((String, String), Int)] = sc
      // 读取数据并过滤不合格的额数据
      .textFile("datas/ips/20090121000132.394251.http.format", minPartitions = 2)
      .filter(line => null != line && line.trim.split("\\|").length >= 2)
      // 对每个分区的数据获取IP地址，将其转换为对应区域的经纬度数据
      .mapPartitions { iter =>
      iter.map { log =>
        // a. 获取访问IP地址
        val ipValue: String = log.split("\\|")(1)
        // b. 将IP地址转换为Long类型数字
        val ipLong: Long = ipToLong(ipValue)
        // c. 依据IP地址查找对应区域经度和维度数据
        val ipArrayValue: Array[(Long, Long, String, String)] = ipArrayBroadcast.value
        val ipIndex = binarySearch(ipArrayValue, ipLong)
        // 获取经度和维度
        ((ipArrayValue(ipIndex)._3, ipArrayValue(ipIndex)._4), 1)
      }
    }
      .reduceByKey((a, b) => a + b)

    // TODO: 4、将统计的结果数据保存的MySQL数据库表中
    longLatCountRDD
      // 降低分区数目
      .coalesce(1)
      // 保存数据到MySQL表中
      .foreachPartition(saveToMySQL)

    // 程序运行结束，关闭资源
    if (!sc.isStopped) sc.stop()
  }


}
