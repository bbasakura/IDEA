package cn.itcast.sink

import cn.itcast.bean.ChannelHot
import cn.itcast.until.HbaseUtil
import org.apache.commons.lang3.StringUtils
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction

/**
  * 2019/9/4
  */
class ChannelHotSink extends RichSinkFunction[ChannelHot] {

  /**
    * 执行数据插入操作（传入要实现的对象）----统一的extend RIchSinkFuction
    * 重写Invoke的方法，这是数据插入的真实现的地方，
    * 先设计好表的样式，
    *
    * @param value
    */
  override def invoke(value: ChannelHot): Unit = {

    /**
      * 落地数据： channelID ,count
      * 设计hbase表：
      * 表名：channel
      * 列族：info
      * 列名：channelId ,count
      * rowkey：channelId
      */
    val tableName = "channel"
    val family = "info"
    val countCol = "count"
    val rowkey = value.channnelId
    var count: Int = value.count

    //需要先查询hbase，如果hbase里有count数据，需要累加操作，再插入hbase----作为聚合的特点，否则会覆盖。
    val countData: String = HbaseUtil.queryByRowkey(tableName, family, countCol, rowkey.toString)
    //非空判断 ""------、根据rowKey查询的-----表里面是否有内容？？----有就count（聚合的字段累加？）
    if (StringUtils.isNotBlank(countData)) {
      count = count + countData.toInt
    }

    //执行插入操作------批量插入，形式为：key:value,,,列名：列值 (a->b)写法
    val map = Map(countCol -> count, "channelId" -> value.channnelId)

    HbaseUtil.putMapDataByRowkey(tableName,family,map,rowkey.toString)

  }

}
