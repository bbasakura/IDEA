package com.air.antispider.stream.dataprocess.business

import java.lang

import com.air.antispider.stream.common.bean.ProcessedData
import com.air.antispider.stream.common.util.jedis.{JedisConnectionUtil, PropertiesUtil}
import com.air.antispider.stream.common.util.spark.SparkMetricsUtils
import com.alibaba.fastjson.JSONObject
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.json4s.DefaultFormats
import org.json4s.jackson.Json
import redis.clients.jedis.JedisCluster

/**
  * 反爬虫平台状态监控工具类
  */
object SparkStreamingMonitor {
  /**
    * 数据预处理状态监控
    * @param processedDataRDD
    */
  def streamMonitor(processedDataRDD: RDD[ProcessedData], sc: SparkContext, serversCountMap: collection.Map[String, Int]): Unit = {

    //应用的名字
    val appName = sc.appName
    //应用的ID
    val appID = sc.applicationId

//    1. 先发送HTTP请求,获取到Spark监控的Json字符串.
    //定义访问的URL
    //动态获取节点访问的 URL
//    val sparkDriverHost = sc.getConf.get("spark.org.apache.hadoop.yarn.server.webproxy.amfilter.AmIpFilter.param.PROXY _URI_BASES")
    //在 yarn 上运行的监控数据 json 路径 val
//    url = s"${sparkDriverHost}/metrics/json

    val url = "http://localhost:4040/metrics/json/"
      val jsonObj: JSONObject = SparkMetricsUtils.getMetricsJson(url)
//    2. 去Json中获取开始/结束时间
//      1. 从响应的Json对象中获取gauges对象'
    val gaugesObj: JSONObject = jsonObj.getJSONObject("gauges")
//      2. 从gagues对象中获取结束时间:`local-1569483924032.driver.DataProcessApp.StreamingMetrics.streaming.lastCompletedBatch_processingEndTime`
    //因为我们的key不是写死的,需要动态获取当前应用的ID和应用名称.
    val endTimeKey = appID + ".driver." + appName + ".StreamingMetrics.streaming.lastCompletedBatch_processingEndTime"
    val startTimeKey = appID + ".driver." + appName + ".StreamingMetrics.streaming.lastCompletedBatch_processingStartTime"
//    3. 从上面的结果对象中获取value值,这个值就是我们的结束时间.
    val endTime: Long = gaugesObj.getJSONObject(endTimeKey).getLong("value").toLong
    val startTime: Long = gaugesObj.getJSONObject(startTimeKey).getLong("value").toLong
//    4. 使用结束时间-开始时间得到时间差.
    val costTime: Long = endTime - startTime
//    5. 使用本批次的数据量/时间差 = 处理速度.
    val count: Long = processedDataRDD.count()
    //为了防止出现时间差等于0,报除0异常,我们需要对时间差进行判断
    var countPerMillis: Double = 0.0
    if (costTime > 0) {
      //一定要toDouble,否则所有的计算结果都不准确.
      countPerMillis = count.toDouble / costTime.toDouble
    }
    //定义结束时间,格式为: 2019-09-26 11:36:02, 必须为这个格式.
    //yyyy-MM-dd hh:mm:ss hh指12小时制,HH24小时制.
    val endTimeStr = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(endTime)

//    3. 将数据交给前端展示
//      1. 将上面的数据封装成Json.
    //通过跟踪Java代码,发现数据都被封装成了JsonVo对象
//    private String costTime;
//    private Map<String,Object> serversCountMap;
//    private String applicationId;
//    private String countPerMillis;
//    private String applicationUniqueName;
//    private String endTime;
//    private String sourceCount;

    //开始将数据封装为Map.
    val map = Map(
      "costTime" -> costTime,
      "serversCountMap" -> serversCountMap,
      "applicationId" -> appID,
      "applicationUniqueName" -> appName,
      "countPerMillis" -> countPerMillis,
      "endTime" -> endTimeStr,
      "sourceCount" -> count
    )
    //将Map->Json字符串
    val json: String = Json(DefaultFormats).write(map)
//      2. 将Json存入Redis.
    //获取jedis连接对象
    val jedis: JedisCluster = JedisConnectionUtil.getJedisCluster

    //获取Redis的key
    val key: String = PropertiesUtil.getStringByKey("cluster.key.monitor.dataProcess","jedisConfig.properties") + System.currentTimeMillis()
    //获取超时时间
    val ex: Int = PropertiesUtil.getStringByKey("cluster.exptime.monitor","jedisConfig.properties").toInt

    jedis.setex(key, ex, json)

  }

}
