package com.air.antispider.stream.dataprocess.business

import java.util.regex.{Matcher, Pattern}

import com.air.antispider.stream.dataprocess.constants.TravelTypeEnum
import com.air.antispider.stream.dataprocess.constants.TravelTypeEnum.TravelTypeEnum

/**
  * 往返信息打标签
  */
object TravelTypeClassifier {
  /**
    * 根据给定的RefererURL,判断是单程还是往返.
    * @param url
    * @return
    */
  def getTravelType(url: String): TravelTypeEnum = {
//    单程
//    ?t=S&c1=CTU&c2=SHA&d1=2019-09-25&at=1&ct=0&it=0
//    往返
//    ?t=R&c1=CTU&c2=SHA&d1=2019-10-08&d2=2019-11-14&at=1&ct=0&it=0
    //先用?截取 => 参数列表
    //再用&截取 => 所有参数的键值对
    //最后用=截取 => 得到所有的参数信息

    //手机号加密 正则 => mather.find()
    //使用正则来匹配URL中的日期
    val pattern: Pattern = Pattern.compile("(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])")
    //开始和URL进行匹配
    val matcher: Matcher = pattern.matcher(url)
    //定义一个计数器,看能找到几个日期
    var count = 0
    //看是否有匹配的数据
    while (matcher.find()) {
      count = count + 1
    }
    //判断count的结果
    if (count == 1) {
      //单程
      TravelTypeEnum.OneWay
    } else if (count == 2) {
      //往返
      TravelTypeEnum.RoundTrip
    } else {
      //不知道啊
      TravelTypeEnum.Unknown
    }
  }

}
