package com.air.antispider.stream.rulecompute.bussiness

import java.util

import com.air.antispider.stream.common.bean.ProcessedData
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

import scala.collection.mutable.ArrayBuffer

/**
  * 规则计算的工具类
  */
object CoreRule {
  /**
    * IP携带不同Cookie的个数 JSessionID
    * @param processedRDD
    * @return
    */
  def ipCookieCount(processedRDD: RDD[ProcessedData]): collection.Map[String, Int] = {
    //1. 转换 ProcessedData => (ip, JSessionID)
    processedRDD.map(data => {
      //获取数据
      val ip: String = data.remoteAddr
      val sessionID: String = data.cookieValue_JSESSIONID
      (ip, sessionID)
    })
    //2. groupByKey
      .groupByKey()
    //3. 转换(ip, session集合) => (ip, 不同session的个数)
      .map(data => {
      //1.获取数据
      val ip: String = data._1
      val sourceList: Iterable[String] = data._2
      //2.遍历集合,将session放入set去重
      var sessionSet = Set[String]()
      for (sessionID <- sourceList) {
        sessionSet += sessionID
      }
      //3.返回set的长度
      (ip, sessionSet.size)
    })
    //4. 采集数据
      .collectAsMap()
  }


  /**
    * IP查询不同行程的个数
    *
    * @param processedRDD
    * @return
    */
  def ipTravelCount(processedRDD: RDD[ProcessedData]): collection.Map[String, Int] = {
    //1.转换 processedData => (ip,行程:出发地->目的地)
    processedRDD.map(data => {
      //获取数据,IP,出发地,目的地
      val ip: String = data.remoteAddr
      val depcity: String = data.requestParams.depcity
      val arrcity: String = data.requestParams.arrcity
      (ip, depcity + "->" + arrcity)
    })
    //2.groupByKey
      .groupByKey()
    //3.转换 (ip,行程集合) => (ip,不同行程个数)
      .map(data => {
      //取出数据
      val ip: String = data._1
      val sourceList: Iterable[String] = data._2
      //遍历行程的集合,放入set去重
      var travelSet = Set[String]()
      for (travel <- sourceList) {
        travelSet += travel
      }
      //set的长度就是不同行程个数
      (ip, travelSet.size)
    })
    //4.采集数据
      .collectAsMap()
  }


  /**
    * IP访问关键页面最小时间小于预设值的次数指标统计
    *
    * @param processedRDD
    * @param criticalPagesBroadcast
    * @return
    */
  def ipAccessLessDefaultTime(processedRDD: RDD[ProcessedData], criticalPagesBroadcast: Broadcast[ArrayBuffer[String]]): collection.Map[String, Int] = {
    //1. 取出广播变量
    val pageList: ArrayBuffer[String] = criticalPagesBroadcast.value
    //2.过滤关键页面
    processedRDD.filter(data => {
      var flag = false
      for (page <- pageList) {
        if (data.request.matches(page)) {
          flag = true
        }
      }
      flag
    })
    //3.转换操作(ip, 时间戳)
      .map(data => {
      //先取出IP和时间
      val ip: String = data.remoteAddr
      //当前时间格式1990-01-01T12:12:01+8:00
      val dateTime: String = data.timeIso8601
      //因为原始时间中有一个T,我们可以先将T替换成空格,然后在格式化,
      //我们可以使用'T'带指定固定的字符串
      val time: Long = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").parse(dateTime).getTime
      (ip, time)
    })
    //4.groupByKey
      .groupByKey()
    //5.转换操作(ip, 次数)
      .map(data => {
        //取出数据
      val ip: String = data._1
      val sourceTimeList: Iterable[Long] = data._2
      //排序
      val sourceArray: Array[Long] = sourceTimeList.toArray
      util.Arrays.sort(sourceArray)

      //定义一个计数器
      var count = 0
      //遍历集合,计算小于预设值的次数
      for (i<-0 until sourceArray.length -1) {
        //先获取当前值
        val currentTime = sourceArray(i)
        //获取下一个值
        val nextTime = sourceArray(i + 1)
        //前后求差
        val result = nextTime - currentTime
        //如果时间差小于5秒,那么就开始计数.
        //这个预设值,我们应该从MySQL动态加载,传过来.
        if (result < 5) {
          count = count + 1
        }
      }
      //返回次数
      (ip, count)
    })
    //6.采集数据
      .collectAsMap()
  }


  /**
    * IP访问关键页面最小时间指标统计
    *
    * @param processedRDD
    * @param criticalPagesBroadcast
    * @return
    */
  def ipAccessMinTime(processedRDD: RDD[ProcessedData], criticalPagesBroadcast: Broadcast[ArrayBuffer[String]]): collection.Map[String, Int] = {
    //1.取出广播变量中的关键页面列表
    val pageList: ArrayBuffer[String] = criticalPagesBroadcast.value
    //2.过滤关键页面
    processedRDD.filter(data => {
      val url: String = data.request
      //定义一个标记,看是否有符合的请求
      //默认让flag为false,就是我不要这个数据
      var flag = false
      for (page <- pageList) {
        if (url.matches(page)) {
          //我们需要这条数据.
          flag = true
        }
      }
      flag
    })
    //3.转换操作 ProcessedData => (ip, 访问时间戳) 1990-01-01T12:12:01+8:00
      .map(data => {
      //先取出IP和时间
      val ip: String = data.remoteAddr
      //当前时间格式1990-01-01T12:12:01+8:00
      val dateTime: String = data.timeIso8601
      //因为原始时间中有一个T,我们可以先将T替换成空格,然后在格式化,
      //我们可以使用'T'带指定固定的字符串
      val time: Long = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").parse(dateTime).getTime
      (ip, time)
    })
    //4.groupByKey
      .groupByKey()
    //5.转换操作 (IP,List(时间戳)) => (IP, 最小访问时间)
      .map(data => {
      //将时间戳列表转换为最小访问时间
      //1. 取出data中的数据,ip,list
      val ip: String = data._1
      val sourceTimeList: Iterable[Long] = data._2
      //2.对原始时间列表进行排序操作
      //如何排序?冒泡排序/选择排序?
      val sortSourceList: Array[Long] = sourceTimeList.toArray
      //开始排序
      util.Arrays.sort(sortSourceList)
      //3. 遍历时间列表,两两之间求差
      //定义一个差值集合,为了去重,可以定义为Set
      var tempList = Set[Long]()
      for (i<-0 until sortSourceList.length - 1) {
        //先获取当前值
        val currentTime = sortSourceList(i)
        //获取下一个值
        val nextTime = sortSourceList(i + 1)
        //前后求差
        val result = nextTime - currentTime
        //将计算结果放入差值集合
        tempList += result
      }
      //4. 将差值集合排序
      val resultArray: Array[Long] = tempList.toArray
      util.Arrays.sort(resultArray)
      //5. 排序后的差值集合的第一个就是我们的最小访问时间.
      if (resultArray.length > 1) {
        //如果长度大于1,代表有数据
        (ip, resultArray(0).toInt)
      } else {
        //返回-1,代表没有结果.数据异常.
        (ip, -1)
      }
    })
    //6.将数据采集走
      .collectAsMap()
  }

  /**
    * IP携带不同UA指标统计
    *
    * @param processedRDD
    * @return
    */
  def ipUACount(processedRDD: RDD[ProcessedData]): collection.Map[String, Int] = {
    //1.转换: ProcessedData => (ip, ua)
    processedRDD.map(data => {
      //取出IP
      val ip: String = data.remoteAddr
      //取出UA
      val ua: String = data.httpUserAgent
      (ip, ua)
    })
    //2.groupByKey进行分组
      .groupByKey()
    //3.对分组之后的元组进行转换: (ip, List(UA1,UA2)) => (ip, 个数)
      .map(data => {
      //将(ip, List(UA1,UA2)) 转换为 (ip, 个数)
      val ip: String = data._1
      //取出所有的UA
      val uaList: Iterable[String] = data._2
      //定义Set存放UA
      var set = Set[String]()
      //遍历uaList
      for (ua <- uaList) {
        //将数据放入set
        set += ua
      }
      //那么set的长度就是不同的UA个数.
      (ip, set.size)
    })
    //4.采集为Map
      .collectAsMap()
  }

  /**
    * IP访问关键页面指标统计
    *
    * @param processedRDD
    * @param criticalPagesBroadcast
    * @return
    */
  def ipCriticalPagesCount(processedRDD: RDD[ProcessedData], criticalPagesBroadcast: Broadcast[ArrayBuffer[String]]): collection.Map[String, Int] = {
    //1.取出广播变量中的值.
    val criticalPageList: ArrayBuffer[String] = criticalPagesBroadcast.value
    //方案1.转换操作 ProcessedData => (ip, 1)   (ip, 0)
    //方案2.过滤 filter => 转换 ProcessedData => (ip, 1)
    //这里我们采用方案1
    processedRDD.map(data => {
      //访问者的IP
      val ip: String = data.remoteAddr
      //请求的URL
      val url: String = data.request
      //看URL是不是一个关键页面
      var flag = 0
      for (criticalPage <- criticalPageList) {
        //判断URL和关键页面的正则是否能匹配上
        if (url.matches(criticalPage)) {
          //如果匹配上,就把标记改掉
          flag = 1
        }
      }
      //可以直接返回下面的格式,精简代码
//      (ip, flag)

      if (flag == 1) {
        //匹配上了,那么计数为1
        (ip, 1)
      } else {
        //没有匹配上,不是关键页面,计数为0
        (ip, 0)
      }
    })
    //进行数据累加
      .reduceByKey(_ + _)
    //转换map
      .collectAsMap()
  }

  /**
    * IP访问量指标统计
    *
    * @param processedRDD
    * @return
    */
  def ipCount(processedRDD: RDD[ProcessedData]): collection.Map[String, Int] = {
    //1.ProcessedData => (ip, 1)
    processedRDD.map(data => {
      val ip = data.remoteAddr
      (ip, 1)
    })
    //2.数据累加
      .reduceByKey(_ + _)
    //3.将结果采集为Map
      .collectAsMap()
  }

  /**
    * IP段指标统计
    *
    * @param processedRDD
    */
  def ipBlockCount(processedRDD: RDD[ProcessedData]): collection.Map[String, Int] = {
//    1. 我们可以直接使用ProcessedDataRDD进行计算
//      2. 取出IP.得到IP段,
    //将processedData => (ip段, 1)
    val ipBlockRDD: RDD[(String, Int)] = processedRDD.map(data => {
      val ip: String = data.remoteAddr
      //获取IP段 ,192.168.80.1 => 192.168
      //1.使用substring去截取
      //2.使用split去切割
      val ipList: Array[String] = ip.split("\\.")
      if (ipList.length == 4) {
        //说明IP合法.
        val ipBlock = ipList(0) + "." + ipList(1)
        //    3. 给IP段计数为1.
        (ipBlock, 1)
      } else {
        //ip长度不合法.
        ("", 1)
      }
    })
//    4. 数据累加
    val reduceRDD: RDD[(String, Int)] = ipBlockRDD.reduceByKey(_ + _)
//      5. 将数据转换为Map.{"192.168": 34}
    reduceRDD.collectAsMap()
  }

}
