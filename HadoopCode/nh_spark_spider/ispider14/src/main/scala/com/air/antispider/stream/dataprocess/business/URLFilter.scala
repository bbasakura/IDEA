package com.air.antispider.stream.dataprocess.business

import org.apache.spark.broadcast.Broadcast

import scala.collection.mutable.ArrayBuffer

/**
  * URL过滤的工具类
  */
object URLFilter {
  /**
    * 使用广播变量中的规则进行URL过滤
    * @param message
    * @param urlFilterRuleBroadcast
    * @return
    */
  def filterURL(message: String, urlFilterRuleBroadcast: Broadcast[ArrayBuffer[String]]): Boolean = {
    //1. 取出广播变量中的规则列表
    val filterList: ArrayBuffer[String] = urlFilterRuleBroadcast.value
    //2.从message中取出URL
    var url: String = ""
    val arr: Array[String] = message.split("#CS#")
    if (arr.length > 1) {
      //POST /B2C40/dist/main/images/loadingimg.jpg HTTP/1.1
      val request = arr(1)
      val requestArr: Array[String] = request.split(" ")
      if (requestArr.length > 1) {
        //取出URL
        url = requestArr(1)
        //3. 遍历规则列表集合
        var flag = 0
        for (rule <- filterList) {
          //判断是否符合过滤条件
          if (url.matches(rule)) {
            flag = 1
//            return false
          }
        }
//        return true
        //如果for循环都走完了,flag标记还是0,那么肯定是不符合规则.
        if (flag == 0) {
          return true
        }
      }
    }
    false
  }

}
