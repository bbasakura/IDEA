package com.air.antispider.stream.dataprocess.business

import java.util.regex.Pattern

import com.air.antispider.stream.common.util.decode.{EscapeToolBox, RequestDecoder}
import com.air.antispider.stream.common.util.jedis.PropertiesUtil
import com.air.antispider.stream.common.util.string.CsairStringUtils

/**
  * 数据切割
  */
object DataSplit {
  /**
    * 数据切割
    * @param mes
    * @return
    */
  def splitMessage(mes: String):(String, String, String, String, String, String, String, String, String, String, String, String) = {
    //我们可以使用#CS#来切割原始字符串获取URL/请求方式/请求体等数据
    //Cookie我们可以使用"; "切割出每个cookie信息,然后用"=",切割出cookie键值对.
    //用#CS#分割数据

    val values = mes.split("#CS#")
    //记录数据长度
    val valuesLength = values.length
    //request 原始数据
    val regionalRequest = if (valuesLength > 1) values(1) else ""
    //分割出 request 中的 url
    //POST /B2C40/dist/main/images/loadingimg.jpg HTTP/1.1
    val request = if (regionalRequest.split(" ").length > 1) {
      regionalRequest.split(" ")(1)
    } else {
      ""}
    //请求方式 GET/POST
    val requestMethod = if (valuesLength > 2) values(2) else ""
    //content_type
    val contentType = if (valuesLength > 3) values(3) else ""
    //Post 提交的数据体
    val requestBody = if (valuesLength > 4) values(4) else ""
    //http_referrer
    val httpReferrer = if (valuesLength > 5) values(5) else ""
    //客户端 IP
    val remoteAddr = if (valuesLength > 6) values(6) else ""
    //客户端 UA
    val httpUserAgent = if (valuesLength > 7) values(7) else ""
    //服务器时间的 ISO8610 格式
    val timeIso8601 = if (valuesLength > 8) values(8) else ""
    //服务器地址
    val serverAddr = if (valuesLength > 9) values(9) else ""
    //Cookie 信息
    //原始信息中获取 Cookie 字符串，去掉空格，制表符
    val cookiesStr = CsairStringUtils.trimSpacesChars(if (valuesLength > 10) values(10) else "")
    //提取 Cookie 信息并保存为 K-V 形式
    val cookieMap = {
      var tempMap = new scala.collection.mutable.HashMap[String, String]
      if (!cookiesStr.equals("")) {
        cookiesStr.split(";").foreach { s =>
          val kv = s.split("=")
          //UTF8 解码
          if (kv.length > 1) {
            try {
              val chPattern = Pattern.compile("u([0-9a-fA-F]{4})")
              val chMatcher = chPattern.matcher(kv(1))
              var isUnicode = false
              while (chMatcher.find()) {
                isUnicode = true
              }
              if (isUnicode) {
                tempMap += (kv(0) -> EscapeToolBox.unescape(kv(1)))
              } else {
                tempMap += (kv(0) -> RequestDecoder.decodePostRequest(kv(1)))
              }
            } catch {
              case e: Exception => e.printStackTrace()
            }
          }}
      }
      tempMap
    }
    //Cookie 关键信息解析
    //从配置文件读取 Cookie 配置信息
    val cookieKey_JSESSIONID = PropertiesUtil.getStringByKey("cookie.JSESSIONID.key",
      "cookieConfig.properties")
    val cookieKey_userId4logCookie = PropertiesUtil.getStringByKey("cookie.userId.key",
      "cookieConfig.properties")
    //Cookie-JSESSIONID
    val cookieValue_JSESSIONID = cookieMap.getOrElse(cookieKey_JSESSIONID, "NULL")
    //Cookie-USERID-用户 ID
    val cookieValue_USERID = cookieMap.getOrElse(cookieKey_userId4logCookie, "NULL")
    (request,requestMethod,contentType,requestBody,httpReferrer,remoteAddr,httpUserAgent,timeIso8601,serverAddr,cookiesStr,cookieValue_JSESSIONID,cookieValue_USERID)
  }

}
