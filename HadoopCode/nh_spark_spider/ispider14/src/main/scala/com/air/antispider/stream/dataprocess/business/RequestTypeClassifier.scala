package com.air.antispider.stream.dataprocess.business

import com.air.antispider.stream.common.bean.RequestType
import com.air.antispider.stream.dataprocess.constants.{BehaviorTypeEnum, FlightTypeEnum}
import org.apache.spark.broadcast.Broadcast

import scala.collection.mutable.ArrayBuffer

/**
  * 请求类型打标签的工具类
  */
object RequestTypeClassifier {
  /**
    * 根据给定的URL和数据库中的规则信息进行匹配,得到请求类型
    * @param url
    * @param classifyRuleBroadcast
    * @return
    */
  def getClassify(url: String, classifyRuleBroadcast: Broadcast[Map[String, ArrayBuffer[String]]]): RequestType = {
    //取出广播变量中的值.
    val map: Map[String, ArrayBuffer[String]] = classifyRuleBroadcast.value
    //从Map中取出4个集合
    val nationalQueryList: ArrayBuffer[String] = map.getOrElse("nationalQueryList", null)
    val nationalBookList: ArrayBuffer[String] = map.getOrElse("nationalBookList", null)
    val internationalQueryList: ArrayBuffer[String] = map.getOrElse("internationalQueryList", null)
    val internationalBookList: ArrayBuffer[String] = map.getOrElse("internationalBookList", null)

    //使用url 和 4个集合分别进行匹配,看当前的URL到底属于哪一个集合.
    //看看是不是国内查询
    if (nationalQueryList != null) {
      for (value <- nationalQueryList) {
        //看是否和本次的URL匹配
        if (url.matches(value)) {
          //如果匹配上.直接返回国内查询
          return RequestType(FlightTypeEnum.National, BehaviorTypeEnum.Query)
        }
      }
    }
    //国内预订
    if (nationalBookList != null) {
      for (value <- nationalBookList) {
        //看是否和本次的URL匹配
        if (url.matches(value)) {
          //如果匹配上.直接返回国内预订
          return RequestType(FlightTypeEnum.National, BehaviorTypeEnum.Book)
        }
      }
    }
    //国际查询
    if (internationalQueryList != null) {
      for (value <- internationalQueryList) {
        //看是否和本次的URL匹配
        if (url.matches(value)) {
          //如果匹配上.直接返回国际查询
          return RequestType(FlightTypeEnum.International, BehaviorTypeEnum.Query)
        }
      }
    }

    //国际预订
    if (internationalBookList != null) {
      for (value <- internationalBookList) {
        //看是否和本次的URL匹配
        if (url.matches(value)) {
          //如果匹配上.直接返回国际预订
          return RequestType(FlightTypeEnum.International, BehaviorTypeEnum.Book)
        }
      }
    }
    //如果上面都没匹配上,那么返回一个默认值other
    RequestType(FlightTypeEnum.Other, BehaviorTypeEnum.Other)
  }

}
