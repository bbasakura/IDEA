package com.air.antispider.stream.rulecompute.bussiness

import com.air.antispider.stream.common.bean.{FlowCollocation, ProcessedData}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

import scala.collection.mutable.ArrayBuffer

/**
  * 计算黑名单IP的工具类
  */
object ComputeBlackIP {
  /**
    * 通过计算结果找到黑名单数据
    * @param processedRDD
    * @param flowListBroadcast
    * @param ipBlockMap
    * @param ipMap
    * @param ipCriticalPagesMap
    * @param ipUAMap
    * @param ipAccessMinTimeMap
    * @param ipAccessMinLessDefaultTimeMap
    * @param ipTravelMap
    * @param ipCookieMap
    */
  def getBlackResult(processedRDD: RDD[ProcessedData],
                     flowListBroadcast: Broadcast[ArrayBuffer[FlowCollocation]],
                     ipBlockMap: collection.Map[String, Int],
                     ipMap: collection.Map[String, Int],
                     ipCriticalPagesMap: collection.Map[String, Int],
                     ipUAMap: collection.Map[String, Int],
                     ipAccessMinTimeMap: collection.Map[String, Int],
                     ipAccessMinLessDefaultTimeMap: collection.Map[String, Int],
                     ipTravelMap: collection.Map[String, Int],
                     ipCookieMap: collection.Map[String, Int]) = {

    //我们可以从Rdd中获取到IP,再去Map中取出计算结果
    processedRDD.foreach(processedData => {
      //获取IP/IP段
      val ip: String = processedData.remoteAddr
      //获取IP段
      val ipArray: Array[String] = ip.split("\\.")
      var ipBlock = ""
      if (ipArray.length == 4) {
        ipBlock = ipArray(0) + "." + ipArray(1)
      }

      //获取当前IP的计算结果
      val ipBlockCount: Int = ipBlockMap.getOrElse(ipBlock, -1)
      val ipCount: Int = ipMap.getOrElse(ip, -1)
      val ipCriticalPagesCount: Int = ipCriticalPagesMap.getOrElse(ip, -1)
      val ipUACount: Int = ipUAMap.getOrElse(ip, -1)
      val ipAccessMinTime: Int = ipAccessMinTimeMap.getOrElse(ip, -1)
      val ipAccessMinLessDefaultTime: Int = ipAccessMinLessDefaultTimeMap.getOrElse(ip, -1)
      val ipTravelCount: Int = ipTravelMap.getOrElse(ip, -1)
      val ipCookieCount: Int = ipCookieMap.getOrElse(ip, -1)

      //使用计算结果开始进行黑名单打分.
      val map = Map[String,Int](
        "ipBlock" -> ipBlockCount,
        "ip" -> ipCount
      )








    })










  }

}
