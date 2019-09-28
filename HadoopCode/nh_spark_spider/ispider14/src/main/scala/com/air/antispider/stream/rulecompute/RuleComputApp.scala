package com.air.antispider.stream.rulecompute

import com.air.antispider.stream.common.bean.{FlowCollocation, ProcessedData}
import com.air.antispider.stream.common.util.jedis.{JedisConnectionUtil, PropertiesUtil}
import com.air.antispider.stream.common.util.kafka.KafkaOffsetUtil
import com.air.antispider.stream.dataprocess.business.AnalyzeRuleDB
import com.air.antispider.stream.rulecompute.bussiness.{ComputeBlackIP, CoreRule, QueryDataPackage}
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.I0Itec.zkclient.ZkClient
import org.apache.commons.lang3.StringUtils
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.JedisCluster

import scala.collection.mutable.ArrayBuffer

/**
  * 黑名单实时计算的主程序
  */
object RuleComputApp {



  def main(args: Array[String]): Unit = {
    //1. 先构建Spark的配置
    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("RuleComputApp")
    //创建SparkContext
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    //2. 获取SparkStreamingContext
    val ssc = new StreamingContext(sc, Seconds(2))
    //创建zk客户端
    val zkHosts: String = PropertiesUtil.getStringByKey("zkHosts", "zookeeperConfig.properties")
    val zkPath: String = PropertiesUtil.getStringByKey("rulecompute.antispider.zkPath", "zookeeperConfig.properties")
    val zkClient = new ZkClient(zkHosts)

    //加载数据,放入广播变量
    //加载关键页面
    val criticalPagesList: ArrayBuffer[String] = AnalyzeRuleDB.queryCriticalPages()
    @volatile var criticalPagesBroadcast: Broadcast[ArrayBuffer[String]] = sc.broadcast(criticalPagesList)
    //加载流程数据
    val flowList: ArrayBuffer[FlowCollocation] = AnalyzeRuleDB.createFlow(0)
    @volatile var flowListBroadcast: Broadcast[ArrayBuffer[FlowCollocation]] = sc.broadcast(flowList)

    //从Kafka中消费数据
    var inputStream: InputDStream[(String, String)] = createStream(zkClient, zkHosts, zkPath, ssc)

    //数据封装为ProcessedData
    val sourceDStream: DStream[String] = inputStream.map(_._2)
    val processedDStream: DStream[ProcessedData] = QueryDataPackage.queryDataLoadAndPackage(sourceDStream)
    //创建Redis连接对象
    val cluster: JedisCluster = JedisConnectionUtil.getJedisCluster

    processedDStream.foreachRDD(processedRDD => {
      //广播变量的更新
      var criticalPagesChangeStatus: String = cluster.get("criticalPagesChangeStatus")
      //判断criticalPagesChangeStatus有没有
      if (StringUtils.isBlank(criticalPagesChangeStatus)) {
        criticalPagesChangeStatus = "true"
        cluster.set("criticalPagesChangeStatus", criticalPagesChangeStatus)
      }
      if (criticalPagesChangeStatus.toBoolean) {
        //先废除之前的广播变量
        criticalPagesBroadcast.unpersist()
        val newCriticalPagesList: ArrayBuffer[String] = AnalyzeRuleDB.queryCriticalPages()
        criticalPagesBroadcast = sc.broadcast(newCriticalPagesList)
        criticalPagesChangeStatus = "false"
        cluster.set("criticalPagesChangeStatus", criticalPagesChangeStatus)
      }

      //更新流程规则数据
      var flowChangeStatus: String = cluster.get("flowChangeStatus")
      //判断criticalPagesChangeStatus有没有
      if (StringUtils.isBlank(flowChangeStatus)) {
        flowChangeStatus = "true"
        cluster.set("flowChangeStatus", flowChangeStatus)
      }
      if (flowChangeStatus.toBoolean) {
        //先废除之前的广播变量
        flowListBroadcast.unpersist()
        val newFlowList: ArrayBuffer[FlowCollocation] = AnalyzeRuleDB.createFlow(0)
        flowListBroadcast = sc.broadcast(newFlowList)
        flowChangeStatus = "false"
        cluster.set("flowChangeStatus", flowChangeStatus)
      }

      //IP段的数据访问量
      val ipBlockMap: collection.Map[String, Int] = CoreRule.ipBlockCount(processedRDD)
      //IP访问量指标统计
      val ipMap: collection.Map[String, Int] = CoreRule.ipCount(processedRDD)
      //IP访问关键页面指标统计
      val ipCriticalPagesMap: collection.Map[String, Int] = CoreRule.ipCriticalPagesCount(processedRDD, criticalPagesBroadcast)
      //IP携带不同UA指标统计
      val ipUAMap: collection.Map[String, Int] = CoreRule.ipUACount(processedRDD)
      //IP访问关键页面最小时间指标统计
      val ipAccessMinTimeMap: collection.Map[String, Int] = CoreRule.ipAccessMinTime(processedRDD, criticalPagesBroadcast)
      //IP访问关键页面最小时间小于预设值的次数指标统计
      val ipAccessMinLessDefaultTimeMap: collection.Map[String, Int] = CoreRule.ipAccessLessDefaultTime(processedRDD, criticalPagesBroadcast)
      //IP查询不同行程的个数
      val ipTravelMap: collection.Map[String, Int] = CoreRule.ipTravelCount(processedRDD)
      //IP携带不同Cookie的个数
      val ipCookieMap: collection.Map[String, Int] = CoreRule.ipCookieCount(processedRDD)

      //使用上面的计算结果开始进行打分.
      ComputeBlackIP.getBlackResult(processedRDD,
        flowListBroadcast,
        ipBlockMap,
        ipMap,
        ipCriticalPagesMap,
        ipUAMap,
        ipAccessMinTimeMap,
        ipAccessMinLessDefaultTimeMap,
        ipTravelMap,
        ipCookieMap
      )











      println("IP段的数据访问量:" + ipBlockMap)
      println("IP的数据访问量:" + ipMap)
      println("IP访问关键页面指标统计:" + ipCriticalPagesMap)
      println("IP携带不同UA指标统计:" + ipUAMap)
      println("IP访问关键页面最小时间指标统计:" + ipAccessMinTimeMap)
      println("IP访问关键页面最小时间小于预设值的次数指标统计:" + ipAccessMinLessDefaultTimeMap)
      println("IP查询不同行程的个数:" + ipTravelMap)
      println("IP携带不同Cookie的个数:" + ipCookieMap)





      processedRDD.foreach(println)
    })



    //更新偏移量
    inputStream.foreachRDD(rdd => {
      //保存偏移量数据
      KafkaOffsetUtil.saveOffsets(zkClient, zkHosts, zkPath, rdd)
    }
    )

    //4. 执行程序
    ssc.start()
    ssc.awaitTermination()

  }


  /**
    * 自定义维护Offset
    * @param zkClient
    * @param zkHosts
    * @param zkPath
    * @param ssc
    * @return
    */
  def createStream(zkClient: ZkClient, zkHosts: String, zkPath: String, ssc: StreamingContext): InputDStream[(String, String)] = {
    //3. 从Kafka中消费数据
    //从kafkaConfig.properties配置文件中获取broker信息/topic信息
    val brokerList: String = PropertiesUtil.getStringByKey("default.brokers", "kafkaConfig.properties")
    val topic: String = PropertiesUtil.getStringByKey("source.query.topic", "kafkaConfig.properties")
    //Kafka连接参数
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokerList)
    //Kafka的topic信息
    val topics = Set[String](topic)

    //获取Zookeeper上面的kafka偏移量
    val topicAndPartitionOpt: Option[Map[TopicAndPartition, Long]] = KafkaOffsetUtil.readOffsets(zkClient, zkHosts, zkPath, topic)
    //如果程序第一次运行,没有数据,那么就还用之前的方式进行消费,如果有数据,就获取最新的偏移量数据

    topicAndPartitionOpt match {
      case Some(topicAndPartition) => {
        //读取偏移量
        val handler = (m: MessageAndMetadata[String, String]) => (m.key, m.message)
        KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, (String, String)](ssc, kafkaParams, topicAndPartition, handler)
      }
      case None => {
        //还按照之前的方式进行消费
        KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)
      }
    }
  }
}
