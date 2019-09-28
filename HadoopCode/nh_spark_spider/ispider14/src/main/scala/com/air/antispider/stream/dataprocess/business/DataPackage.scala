package com.air.antispider.stream.dataprocess.business

import com.air.antispider.stream.common.bean.{BookRequestData, CoreRequestParams, ProcessedData, QueryRequestData, RequestType}
import com.air.antispider.stream.dataprocess.constants.TravelTypeEnum.TravelTypeEnum

/**
  * 数据结构化的工具类
  */
object DataPackage {
  def dataPackage(str: String,
                  requestMethod: String,
                  request: String,
                  remoteAddr: String,
                  httpUserAgent: String,
                  timeIso8601: String,
                  serverAddr: String,
                  highFrqIPGroup: Boolean,
                  requestType: RequestType,
                  travelType: TravelTypeEnum,
                  cookieValue_JSESSIONID: String,
                  cookieValue_USERID: String,
                  queryRequestParams: Option[QueryRequestData],
                  bookRequstParams: Option[BookRequestData],
                  httpReferrer: String): ProcessedData = {
    //将传入的参数封装为ProcessedData对象


    //创建核心请求参数相关的字段
    //航班日期
    var flightDate: String = ""
    //始发地
    var depcity: String = ""
    //目的地
    var arrcity: String = ""

    queryRequestParams match {
      case Some(requestData) => {
        //如果当前的请求参数中有值,那么就将值取出来.
        flightDate = requestData.flightDate
        depcity = requestData.depCity
        arrcity = requestData.arrCity
      }
      case None => {
        //如果没有值,那么就去预订中瞅瞅,看看预订中有没有值.
        bookRequstParams match {
          case Some(bookData) => {
            //如果预订中有值,就取出来
            //[hello,world,java] =>mkString("#") => "hello#world#java"
            flightDate = bookData.flightDate.mkString(",")
            depcity = bookData.depCity.mkString(",")
            arrcity = bookData.arrCity.mkString(",")
          }
          case None => {
            //预订中没有数据,我也不知道怎么办了.空着吧.
          }
        }
      }
    }

    //将上面的3个数据封装成核心请求参数
    val requestParams: CoreRequestParams = CoreRequestParams(flightDate, depcity, arrcity)

    //返回结构化数据.
    ProcessedData("", requestMethod, request,
      remoteAddr, httpUserAgent, timeIso8601,
      serverAddr,  highFrqIPGroup,
      requestType, travelType,
      requestParams, cookieValue_JSESSIONID, cookieValue_USERID,
      queryRequestParams, bookRequstParams,
      httpReferrer)

  }

}
