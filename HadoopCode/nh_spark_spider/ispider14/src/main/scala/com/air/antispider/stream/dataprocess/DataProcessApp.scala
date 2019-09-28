package com.air.antispider.stream.dataprocess

import com.air.antispider.stream.common.bean.{AnalyzeRule, BookRequestData, ProcessedData, QueryRequestData, RequestType}
import com.air.antispider.stream.common.util.jedis.{JedisConnectionUtil, PropertiesUtil}
import com.air.antispider.stream.dataprocess.business.{AnalyzeBookRequest, AnalyzeRequest, AnalyzeRuleDB, BusinessProcess, DataPackage, DataSend, DataSplit, EncryptedData, IpOperation, RequestTypeClassifier, SparkStreamingMonitor, TravelTypeClassifier, URLFilter}
import com.air.antispider.stream.dataprocess.constants.TravelTypeEnum.TravelTypeEnum
import kafka.serializer.StringDecoder
import org.apache.commons.lang3.StringUtils
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.JedisCluster

import scala.collection.mutable.ArrayBuffer

/**
  * 数据预处理入口主程序
  */
object DataProcessApp {

  def main(args: Array[String]): Unit = {
    //1. 先构建Spark的配置
    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("DataProcessApp")
    //创建SparkContext
    val sc = new SparkContext(conf)


    //2. 获取SparkStreamingContext
    val ssc = new StreamingContext(sc, Seconds(2))

    //加载规则信息/放入广播变量
    //1.URL过滤规则
    val urlFilterRuleList: ArrayBuffer[String] = AnalyzeRuleDB.getFilterRuleList()
    //@volatile 可以让我们安全的修改广播变量
    @volatile var urlFilterRuleBroadcast: Broadcast[ArrayBuffer[String]] = sc.broadcast(urlFilterRuleList)
    //2.请求类型标签规则
    val classifyRuleMap: Map[String, ArrayBuffer[String]] = AnalyzeRuleDB.getClassifyRule()
    @volatile var classifyRuleBroadcast: Broadcast[Map[String, ArrayBuffer[String]]] = sc.broadcast(classifyRuleMap)
    //3.获取查询的解析规则
    val queryRuleList: List[AnalyzeRule] = AnalyzeRuleDB.queryRule(0)
    @volatile var queryRuleBroadcast: Broadcast[List[AnalyzeRule]] = sc.broadcast(queryRuleList)
    //4.获取预订的解析规则
    val bookRuleList: List[AnalyzeRule] = AnalyzeRuleDB.queryRule(1)
    @volatile var bookRuleBroadcast: Broadcast[List[AnalyzeRule]] = sc.broadcast(bookRuleList)
    //5.获取黑名单数据
    val blackIPList: ArrayBuffer[String] = AnalyzeRuleDB.queryIpListToBrocast()
    @volatile var blackIPListBroadcast: Broadcast[ArrayBuffer[String]] = sc.broadcast(blackIPList)

    //3. 获取Kafka数据
    //从kafkaConfig.properties配置文件中获取broker信息/topic信息
    val brokerList: String = PropertiesUtil.getStringByKey("default.brokers", "kafkaConfig.properties")
    val topic: String = PropertiesUtil.getStringByKey("source.nginx.topic", "kafkaConfig.properties")
    //Kafka连接参数
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokerList)
    //Kafka的topic信息
    val topics = Set[String](topic)
    //从Kafka中消费数据
    val inputStream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

    //4. 取出message消息 map: (key, message) => message
    val messageDStream: DStream[String] = inputStream.map(_._2)

    //获取Redis连接对象
    val jedis: JedisCluster = JedisConnectionUtil.getJedisCluster

    //遍历DStream,开始业务处理
    messageDStream.foreachRDD(messageRDD => {
//      每一批次数据执行操作的时候,对广播变量进行更新.

//      1. 从Redis中获取标记
      var urlFilterRuleStatus: String = jedis.get("urlFilterRuleStatus")
      //判断标记是否存在
      if (StringUtils.isBlank(urlFilterRuleStatus)) {
        //如果标记不存在,初始化一个true,然后存入Redis
        urlFilterRuleStatus = "true"
        jedis.set("urlFilterRuleStatus", urlFilterRuleStatus)
      }
//        2. 判断标记,是否需要获取最新的数据
//      3. 如果需要进行数据更新.
      if (urlFilterRuleStatus.toBoolean) {
//      1. 先将之前的广播变量废除掉
        urlFilterRuleBroadcast.unpersist()
//        2. 查询MySQL中最新的数据
        val ruleList: ArrayBuffer[String] = AnalyzeRuleDB.getFilterRuleList()
//        3. 将数据重新放入广播变量
        urlFilterRuleBroadcast = sc.broadcast(ruleList)
//        4. 将标记重新恢复
        urlFilterRuleStatus = "false"
        jedis.set("urlFilterRuleStatus", urlFilterRuleStatus)
      }

      //更新请求标签规则
//      1. 先从Redis中获取标记
      var classifyRuleChangeStatus: String = jedis.get("classifyRuleChangeStatus")
//        2. 判断标记是否存在,如果不存在就初始化一个
      if (StringUtils.isBlank(classifyRuleChangeStatus)) {
        classifyRuleChangeStatus = "true"
        jedis.set("classifyRuleChangeStatus", classifyRuleChangeStatus)
      }
//      3. 如果存在那么就判断是否为true
      if (classifyRuleChangeStatus.toBoolean) {
//        1. 如果为true,那么就开始进行更新操作
//      2. 先将之前的广播变量废除掉
        classifyRuleBroadcast.unpersist()
//        3. 查询最新的数据
        val classifyRule: Map[String, ArrayBuffer[String]] = AnalyzeRuleDB.getClassifyRule()
//        4. 将最新的数据放入广播变量
        classifyRuleBroadcast = sc.broadcast(classifyRule)
//        5. 重置Redis中的标记.
        jedis.set("classifyRuleChangeStatus", "false")
      }
      //更新解析规则
//      1. 先获取Redis中的标记
      var ruleChangeStatus: String = jedis.get("ruleChangeStatus")
//        2. 判断标记是否存在
      if (StringUtils.isBlank(ruleChangeStatus)){
//        1. 标记不存在
//        1. 初始化标记
        ruleChangeStatus = "true"
//        2. 存入Redis
        jedis.set("ruleChangeStatus", ruleChangeStatus)
      }
//        2. 标记存在
//        1. 看标记是否是true
      if (ruleChangeStatus.toBoolean) {
//        1. 如果是true
//        1. 先将之前的广播变量废除掉
        queryRuleBroadcast.unpersist()
        bookRuleBroadcast.unpersist()
//        2. 加载最新的解析规则信息
        val newQueryRuleList: List[AnalyzeRule] = AnalyzeRuleDB.queryRule(0)
        val newBookRuleList: List[AnalyzeRule] = AnalyzeRuleDB.queryRule(1)
//        3. 将最新的数据放入广播变量
        queryRuleBroadcast = sc.broadcast(newQueryRuleList)
        bookRuleBroadcast = sc.broadcast(newBookRuleList)
//        4. 恢复Redis中的标记
        ruleChangeStatus = "false"
        jedis.set("ruleChangeStatus", ruleChangeStatus)
      }

      //更新黑名单广播变量
      //      1. 先从Redis中获取标记
      var blackIPListChangeStatus: String = jedis.get("blackIPListChangeStatus")
      //        2. 判断标记是否存在,如果不存在就初始化一个
      if (StringUtils.isBlank(blackIPListChangeStatus)) {
        blackIPListChangeStatus = "true"
        jedis.set("blackIPListChangeStatus", blackIPListChangeStatus)
      }
      //      3. 如果存在那么就判断是否为true
      if (blackIPListChangeStatus.toBoolean) {
        //        1. 如果为true,那么就开始进行更新操作
        //      2. 先将之前的广播变量废除掉
        blackIPListBroadcast.unpersist()
        //        3. 查询最新的数据
        val queryIPList: ArrayBuffer[String] = AnalyzeRuleDB.queryIpListToBrocast()
        //        4. 将最新的数据放入广播变量
        blackIPListBroadcast = sc.broadcast(queryIPList)
        //        5. 重置Redis中的标记.
        jedis.set("blackIPListChangeStatus", "false")
      }









      //1. 链路统计
      val serversCountMap: collection.Map[String, Int] = BusinessProcess.linkCount(messageRDD)

      //2.URL过滤
      val filterRDD: RDD[String] = messageRDD.filter(message => URLFilter.filterURL(message, urlFilterRuleBroadcast))

      //String => 结构化数据
      val processedDataRDD: RDD[ProcessedData] = filterRDD.map(message => {
        //3.数据加密
        //3.1 加密手机号
        val encryptedPhoneMessage: String = EncryptedData.encryptedPhone(message)
        //3.2 加密身份证号
        val encryptedMessage: String = EncryptedData.encryptedID(encryptedPhoneMessage)
        //4.数据切割
        val (request,//请求的URL
        requestMethod,//请求方式
        contentType,//请求的类型
        requestBody,//请求体内容:json/xml
        httpReferrer,//请求的来源URL
        remoteAddr,//客户端IP.
        httpUserAgent,//浏览器信息
        timeIso8601,//请求时间
        serverAddr,//服务器IP
        cookiesStr,//原始的Cookie字符串
        cookieValue_JSESSIONID,//从原始Cookie字符串中提取的sessionID
        cookieValue_USERID//从原始Cookie字符串中提取的用户ID
          ) =DataSplit.splitMessage(encryptedMessage)
        //5. 数据打标签
        //请求类型打标签
        val requestType:RequestType = RequestTypeClassifier.getClassify(request, classifyRuleBroadcast)
        //往返类型打标签
        val travelType: TravelTypeEnum = TravelTypeClassifier.getTravelType(httpReferrer)

        //查询请求数据解析
        val queryRequestParams: Option[QueryRequestData] = AnalyzeRequest.analyzeQueryRequest(requestType, requestMethod, contentType, request, requestBody, travelType, queryRuleBroadcast.value)
        //预订请求数据解析
        val bookRequstParams: Option[BookRequestData] = AnalyzeBookRequest.analyzeBookRequest(requestType, requestMethod, contentType, request, requestBody, travelType, bookRuleBroadcast.value)
        //数据再加工
        val highFrqIPGroup:Boolean = IpOperation.getBlackIPStatus(remoteAddr, blackIPListBroadcast)
        //数据结构化
        val processedData:ProcessedData= DataPackage.dataPackage(
          "",
          requestMethod,
          request,
          remoteAddr,
          httpUserAgent,
          timeIso8601,
          serverAddr,
          highFrqIPGroup,
          requestType,
          travelType,
          cookieValue_JSESSIONID,
          cookieValue_USERID,
          queryRequestParams,
          bookRequstParams, httpReferrer)

        processedData
      })

      //数据推送
      DataSend.sendData(processedDataRDD)

      //数据预处理状态监控
      SparkStreamingMonitor.streamMonitor(processedDataRDD, sc, serversCountMap)





//      processedDataRDD.foreach(println)
    })

    //5. 执行程序
    ssc.start()
    ssc.awaitTermination()
  }

}
