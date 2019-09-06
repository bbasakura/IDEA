package cn.itcast.bean

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}

import scala.collection.mutable.ArrayBuffer

/**
  *  2019/9/5
  */
case class ColumnPair(
                            var columnName: String,
                            var columnValue: String,
                            var isValid: Boolean
                          )


object ColumnPair {

  def parseJsonArray(str: String) = {

    val jsonArray: JSONArray = JSON.parseArray(str)
    //新建数组，封装jsonArray数据
    val pairs: ArrayBuffer[ColumnPair] = new ArrayBuffer[ColumnPair]()

    for (line <- 0 until jsonArray.size()) {
      //解析jsonArray内的一条条json字符串
      val json: JSONObject = jsonArray.getJSONObject(line)
      val pair: ColumnPair = ColumnPair(
        json.getString("columnName"),   //列名
        json.getString("columnValue"),  //列值
        json.getBoolean("isValid")      //更新标识
      )
      pairs += pair            //scala中的数组的  追加到数组对象
    }
    pairs
  }


}
