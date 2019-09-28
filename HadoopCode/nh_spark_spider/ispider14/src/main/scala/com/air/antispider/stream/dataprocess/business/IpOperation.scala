package com.air.antispider.stream.dataprocess.business

import org.apache.spark.broadcast.Broadcast

import scala.collection.mutable.ArrayBuffer

/**
  * 高频IP打标记
  *
  */
object IpOperation {

  /**
    * 判断当前请求的客户端是否是一个高频IP
    * @param remoteAddr
    * @param blackIPListBroadcast
    * @return
    */
  def getBlackIPStatus(remoteAddr: String, blackIPListBroadcast: Broadcast[ArrayBuffer[String]]): Boolean = {
    //从广播变量中获取黑名单列表.
    val blakIPList: ArrayBuffer[String] = blackIPListBroadcast.value
    //遍历黑名单列表,看客户端IP是否是高频IP.
    for (ip <- blakIPList) {
      if (remoteAddr.equalsIgnoreCase(ip)) {
        //如果客户端IP和数据库中的IP相等,那么本次访问的客户端IP就是一个高频IP
        return true
      }
    }
    //不是高频IP
    false
  }

}
