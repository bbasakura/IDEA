package cn.itcast.sink

import cn.itcast.bean.ChannelFreshness
import cn.itcast.until.HbaseUtil
import org.apache.commons.lang3.StringUtils
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction

/**
  *  2019/9/5
  */
class ChannelFreshnessSink extends RichSinkFunction[ChannelFreshness] {

  //执行插入hbase
  override def invoke(value: ChannelFreshness): Unit = {

    /**
      * 数据落地：cannelId, time,newCount,oldCount
      * 设计hbase表：
      * 表名：channel
      * 列族：info
      * 列名：channelId ,time,newCount,oldCount
      * rowkey：channelId +time(格式化的日期)
      **/
    //设置hbase表的参数
    val tableName: String = "channel"
    val family: String = "info"
    val newCountCol: String = "newCount"
    val oldCountCol: String = "oldCount"
    val rowkey = value.getChannelId + value.getTimeFormat

    //取newCount和oldCount
    var newCount: Long = value.getNewCount
    var oldCount: Long = value.getOldCount

    //查询hbase的新老用户数据，如果有数据，需要进行累加操作
    val newCountData: String = HbaseUtil.queryByRowkey(tableName, family, newCountCol, rowkey)
    val oldCountData: String = HbaseUtil.queryByRowkey(tableName, family, oldCountCol, rowkey)

    //非空判断
    if (StringUtils.isNotBlank(newCountData)) {
      newCount = newCount + newCountData.toLong
    }
    if (StringUtils.isNotBlank(oldCountData)) {
      oldCount = oldCount + oldCountData.toLong
    }

    //落地数据封装到map
    var map = Map[String, Any]()
    map += ("channelId" -> value.getChannelId)
    map += ("time" -> value.getTimeFormat)
    map += (newCountCol -> newCount)
    map += (oldCountCol -> oldCount)

    //执行插入操作，一次插入多列数据
    HbaseUtil.putMapDataByRowkey(tableName, family, map, rowkey)
  }
}
