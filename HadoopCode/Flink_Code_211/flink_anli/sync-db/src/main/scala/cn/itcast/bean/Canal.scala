package cn.itcast.bean

import com.alibaba.fastjson.{JSON, JSONObject}

/**
  *  2019/9/5
  */
case class Canal(
                  var columnValueList: String,
                  var dbName: String,
                  var emptyCount: String,
                  var eventType: String,
                  var logFileName: String,
                  var logFileOffset: String,
                  var tableName: String,
                  var timestamp: String
                )

object Canal {

  def parseStr(str: String): Canal = {

    val json: JSONObject = JSON.parseObject(str)
    Canal( //封装到一个对象中的
      json.getString("columnValueList"),
      json.getString("dbName"),
      json.getString("emptyCount"),
      json.getString("eventType"),
      json.getString("logFileName"),
      json.getString("logFileOffset"),
      json.getString("tableName"),
      json.getString("timestamp")
    )
  }
}


