package cn.itcast.bean

import java.lang

import com.alibaba.fastjson.{JSON, JSONObject}

/**
  * 2019/9/4
  */
case class UserBrowse(
                       //频道ID
                       var channelID: Long = 0L,
                       //产品的类别ID
                       var categoryID: Long = 0L,
                       //产品ID
                       var produceID: Long = 0L,
                       //国家
                       var country: String = null,
                       //省份
                       var province: String = null,
                       //城市
                       var city: String = null,
                       //网络方式
                       var network: String = null,
                       //来源方式
                       var source: String = null,
                       //浏览器类型
                       var browserType: String = null,
                       //进入网站时间
                       var entryTime: Long = 0L,
                       //离开网站时间
                       var leaveTime: Long = 0L,
                       //用户的ID
                       var userID: Long = 0L,
                       //日志产生时间
                       var timestamp: Long = 0L
                     )

object UserBrowse {

  def parseStr(str: String): UserBrowse = {
    //字符串转JSON
    val json: JSONObject = JSON.parseObject(str)
    //把json数据封装到UserBrowse内
    UserBrowse(
      json.getLong("channelID"),
      json.getLong("categoryID"),
      json.getLong("produceID"),
      json.getString("country"),
      json.getString("province"),
      json.getString("city"),
      json.getString("network"),
      json.getString("source"),
      json.getString("browserType"),
      json.getLong("entryTime"),
      json.getLong("leaveTime"),
      json.getLong("userID"),
      json.getLong("timestamp")
    )
  }

}


