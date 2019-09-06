package cn.itcast.sink

import cn.itcast.bean.ChannelPvuv
import cn.itcast.until.HbaseUtil
import org.apache.commons.lang3.StringUtils
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction

/**
  * 2019/9/4
  */
class ChannelPvuvSink extends RichSinkFunction[ChannelPvuv] {

  //执行hbase插入数据的操作
  override def invoke(value: ChannelPvuv): Unit = {

    /**
      * 落地数据： channelID ,time,pv,uv
      * 设计hbase表：
      * 表名：channel
      * 列族：info
      * 列名：channelId ,timeFormat,pv,uv
      * rowkey：channelId +time(格式化的日期)
      */
    val tableName = "channel"
    val family = "info"
    val pvCol = "pv"
    val uvCol = "uv"
    val rowkey = value.getChannelId + value.getTimeFormat

    //获取pv，uv数据
    var pv = value.getPv
    var uv = value.getUv

    //插入数据之前，先查询hbase，如果有数据需要进行累加
    val pvData: String = HbaseUtil.queryByRowkey(tableName,family,pvCol,rowkey)
    val uvData: String = HbaseUtil.queryByRowkey(tableName,family,uvCol,rowkey)
    //非空判断
    if(StringUtils.isNotBlank(pvData)){
      pv = pv +pvData.toLong
    }
    if(StringUtils.isNotBlank(uvData)){
      uv = uv+uvData.toLong
    }

    //封装多列数据，一次性插入
    var map= Map[String,Any]()
    map+=(pvCol->pv)
    map+=(uvCol->uv)
    map+=("channelId"->value.getChannelId)
    map+=("time"->value.getTimeFormat)
    HbaseUtil.putMapDataByRowkey(tableName,family,map,rowkey)
  }
}
