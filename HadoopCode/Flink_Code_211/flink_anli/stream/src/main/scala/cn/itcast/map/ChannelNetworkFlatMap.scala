package cn.itcast.map

import cn.itcast.bean.{ChannelNetwork, Message, UserBrowse, UserState}
import cn.itcast.until.TimeUtil
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.util.Collector

/**
  * 2019/9/5
  */
class ChannelNetworkFlatMap extends RichFlatMapFunction[Message, ChannelNetwork] {

  //定义格式化日期模板
  val hour = "yyyyMMddHH"
  val day = "yyyyMMdd"
  val month = "yyyyMM"

  override def flatMap(value: Message, out: Collector[ChannelNetwork]): Unit = {

    val browse: UserBrowse = value.userBrowse
    val channelID: Long = browse.channelID
    val userID: Long = browse.userID
    val timestamp: Long = browse.timestamp
    val network: String = browse.network

    //获取用户访问状态
    val userState: UserState = UserState.getUserState(timestamp, userID)
    val isNew: Boolean = userState.isNew
    val firstHour: Boolean = userState.isFirstHour
    val firstDay: Boolean = userState.isFirstDay
    val firstMonth: Boolean = userState.isFirstMonth

    //对当前时间戳进行格式化操作
    val hourTime: String = TimeUtil.parseTime(timestamp, hour)
    val dayTime: String = TimeUtil.parseTime(timestamp, day)
    val monthTime: String = TimeUtil.parseTime(timestamp, month)

    //新建bean，封装部分数据
    val channelNetwork = new ChannelNetwork
    channelNetwork.setChannelId(channelID)
    channelNetwork.setNetwork(network)

    //根据用户状态，封装数据
    isNew match {
      case true =>
        channelNetwork.setNewCount(1L)
      case false =>
        channelNetwork.setOldCount(1L)
    }

    //小时维度
    firstHour match {
      case true=>
        channelNetwork.setNewCount(1L)
        channelNetwork.setTimeFormat(hourTime)
        out.collect(channelNetwork)
      case false =>
        channelNetwork.setOldCount(1L)
        channelNetwork.setTimeFormat(hourTime)
        out.collect(channelNetwork)
    }
    //天维度
    firstDay match {
      case true=>
        channelNetwork.setNewCount(1L)
        channelNetwork.setTimeFormat(dayTime)
      case false =>
        channelNetwork.setOldCount(1L)
        channelNetwork.setTimeFormat(dayTime)
        out.collect(channelNetwork)
    }

    //月维度
    firstMonth match {
      case true=>
        channelNetwork.setNewCount(1L)
        channelNetwork.setTimeFormat(monthTime)
      case false =>
        channelNetwork.setOldCount(1L)
        channelNetwork.setTimeFormat(monthTime)
        out.collect(channelNetwork)
    }
  }
}
