package cn.itcast.sink

import cn.itcast.bean.ChannelNetwork
import cn.itcast.until.HbaseUtil
import org.apache.commons.lang3.StringUtils
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction

/**
  * 2019/9/5
  */
class ChannelNetworkSink extends RichSinkFunction[ChannelNetwork] {

  override def invoke(value: ChannelNetwork): Unit = {
    //数据落地
    /**
      * 数据落地：channelId, network,time,newCount,oldCount
      * 设计hbase表：
      * 表名：network
      * 列族：info
      * 列名：channelId, network,time,newCount,oldCount
      * rowkey：channelId +time(格式化的日期)
      */
    val tableName: String = "network"
    val family: String = "info"
    val newCountCol: String = "newCount"
    val oldCountCol: String = "oldCount"
    val rowkey = value.getChannelId + value.getTimeFormat

    var newCount = value.getNewCount
    var oldCount = value.getOldCount

    //通过hbase查询新老用户的数据，如果有数据，需要进行累加操作
    val newCountData: String = HbaseUtil.queryByRowkey(tableName, family, newCountCol, rowkey)
    val oldCountData: String = HbaseUtil.queryByRowkey(tableName, family, oldCountCol, rowkey)
    //非空判断，及累加操作
    if (StringUtils.isNotBlank(newCountData)) {
      newCount = newCount + newCountData.toLong
    }
    if (StringUtils.isNotBlank(oldCountData)) {
      oldCount = oldCount + oldCountData.toLong
    }

    //将插入的多列数据，封装进map
    //列名：channelId, network,time,newCount,oldCount
    var map = Map[String, Any]()
    map += ("channelId" -> value.getChannelId)
    map += ("network" -> value.getNetwork)
    map += ("time" -> value.getTimeFormat)
    map += (newCountCol -> newCount)
    map += (oldCountCol -> oldCount)

    //执行插入操作
    HbaseUtil.putMapDataByRowkey(tableName,family,map,rowkey)
  }

}
