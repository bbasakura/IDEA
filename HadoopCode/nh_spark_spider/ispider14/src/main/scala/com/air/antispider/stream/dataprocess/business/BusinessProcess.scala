package com.air.antispider.stream.dataprocess.business

import com.air.antispider.stream.common.util.jedis.{JedisConnectionUtil, PropertiesUtil}
import org.apache.spark.rdd.RDD
import org.json4s.DefaultFormats
import org.json4s.jackson.Json
import redis.clients.jedis.JedisCluster

/**
  * 链路统计的工具类
  */
object BusinessProcess {
  /**
    * 使用给定的RDD进行活跃连接数/数据采集量的统计
    * @param messageRDD
    */
  def linkCount(messageRDD: RDD[String]): collection.Map[String, Int] = {
//    1. 从信息中提取需要的数据:服务器IP,活跃连接数

    //我们想要的数据有2部分
    //1. 服务器的采集量   String => (ip, 该服务器的采集量)
    val serverCountRDD: RDD[(String, Int)] = messageRDD.map(message => {
      //切割原始字符串,获取服务器IP
      val arr: Array[String] = message.split("#CS#")
      if (arr.length > 9) {
        //有服务器IP
        val ip: String = arr(9)
        //如果能拿到IP,那么计数为1
        (ip, 1)
      } else {
        ("", 1)
      }
    })
      //数据进行累加操作
      .reduceByKey((x, y)=> x+y)
    //2. 服务器的活跃连接数    string => (ip, 活跃连接数)
    val activeNumRDD: RDD[(String, Int)] = messageRDD.map(message => {
      val arr: Array[String] = message.split("#CS#")
      if (arr.length > 11) {
        //肯定有活跃连接数
        //当前服务器IP
        val ip = arr(9)
        //当前活跃连接数
        val num = arr(11).toInt
        (ip, num)
      } else {
        ("", 0)
      }
    })
      //因为活跃连接数不需要进行累加,只需要返回最新的数据就行了
      .reduceByKey((x, y) => y)
//    3. 将数据进行展示.
    //如果数据不为空,将数据存入Redis
    if (!serverCountRDD.isEmpty() && !activeNumRDD.isEmpty()) {
      //将RDD中的计算结果提取出来
      val serversCountMap: collection.Map[String, Int] = serverCountRDD.collectAsMap()
      val activeNumMap: collection.Map[String, Int] = activeNumRDD.collectAsMap()

      //数据进行封装的时候,封装为一个Json,json的数据格式需要和LinkJsonVo的数据结构保持一致,

      //进行数据封装,先定义个Map
      val map = Map(
        "serversCountMap" -> serversCountMap,
        "activeNumMap" -> activeNumMap
      )
      //将map转换为Json字符串
      val json: String = Json(DefaultFormats).write(map)
      println(json)
      //存入Redis的时候,Key必须为CSANTI_MONITOR_LP开头
      //获取配置文件中的Key
      val key: String = PropertiesUtil.getStringByKey("cluster.key.monitor.linkProcess", "jedisConfig.properties") + System.currentTimeMillis()
      //数据的有效期
      val ex: Int = PropertiesUtil.getStringByKey("cluster.exptime.monitor", "jedisConfig.properties").toInt
      //获取Redis连接
      val jedis: JedisCluster = JedisConnectionUtil.getJedisCluster
      //将数据存入Redis
      jedis.setex(key, ex, json)
    }
    //返回数据采集计算结果,让监控模块使用.
    serverCountRDD.collectAsMap()
  }

}
