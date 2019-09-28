package com.air.antispider.stream.dataprocess.business

import java.util.regex.{Matcher, Pattern}

import com.air.antispider.stream.common.util.decode.MD5

/**
  * 数据加密工具类,手机号加密/身份证号加密
  */
object EncryptedData {

  /**
    * 加密手机号
    * @param message
    * @return
    */
  def encryptedPhone(message: String): String = {
    var tmpMessage = message
    //加密对象
    val md5 = new MD5
    //1. 能找到手机号,用正则
    //手机号正则
    val pattern: Pattern = Pattern.compile("((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0-9])|(18[0,5-9]))\\d{8}")
    //使用正则匹配消息字符串,返回一个matcher对象.matcher可以看做匹配结果对象
    val matcher: Matcher = pattern.matcher(message)
    //看是否能找到,如果找到,就取出来
    while (matcher.find()) {
      //取出匹配到的数据
      val phone: String = matcher.group()
      //2. 开始加密
      tmpMessage = tmpMessage.replace(phone, md5.getMD5ofStr(phone))
    }
    //返回加密之后的数据
    tmpMessage
  }
  /**
    * 加密身份证号
    * @param message
    * @return
    */
  def encryptedID(message: String): String = {
    var tmpMessage = message
    //加密对象
    val md5 = new MD5
    //1. 能找到身份证号,用正则
    //手机号正则
    val pattern: Pattern = Pattern.compile("(\\\\d{18})|(\\\\d{17}(\\\\d|X|x))|(\\\\d{15})")
    //使用正则匹配消息字符串,返回一个matcher对象.matcher可以看做匹配结果对象
    val matcher: Matcher = pattern.matcher(message)
    //看是否能找到,如果找到,就取出来
    while (matcher.find()) {
      //取出匹配到的数据
      val phone: String = matcher.group()
      //2. 开始加密
      tmpMessage = tmpMessage.replace(phone, md5.getMD5ofStr(phone))
    }
    //返回加密之后的数据
    tmpMessage
  }
}
