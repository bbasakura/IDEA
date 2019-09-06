package cn.itcast.map

import cn.itcast.bean.{ChannelBrowse, Message, UserBrowse, UserState}
import cn.itcast.until.TimeUtil
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.util.Collector

/**
  * 2019/9/5
  */
class ChannelBrowseFlatMap extends RichFlatMapFunction[Message, ChannelBrowse] {

  //定义格式化模板
  val hour = "yyyyMMddHH"
  val day = "yyyyMMdd"
  val month = "yyyyMM"

  override def flatMap(in: Message, out: Collector[ChannelBrowse]): Unit = {

    //取出消息中的部分数据
    val userBrowse: UserBrowse = in.userBrowse
    val channelID: Long = userBrowse.channelID
    val userID: Long = userBrowse.userID
    val timestamp: Long = userBrowse.timestamp
    val browserType: String = userBrowse.browserType

    //格式化时间戳
    val hourTime: String = TimeUtil.parseTime(timestamp, hour)
    val dayTime: String = TimeUtil.parseTime(timestamp, day)
    val monthTime: String = TimeUtil.parseTime(timestamp, month)

    //获取用户访问状态
    val userState: UserState = UserState.getUserState(timestamp, userID)
    val isNew: Boolean = userState.isNew
    val firstHour: Boolean = userState.isFirstHour
    val firstDay: Boolean = userState.isFirstDay
    val firstMonth: Boolean = userState.isFirstMonth

    //新建bean，封装部分数据
    val browse = new ChannelBrowse
    browse.setBrowserType(browserType)
    browse.setChannelId(channelID)
    browse.setPv(1L)

    //根据用户的访问状态，封装数据
    isNew match {
      case true =>
        browse.setUv(1L)
        browse.setNewCount(1L)
      case false =>
        browse.setUv(0L)
        browse.setOldCount(1L)
    }

    //小时维度
    firstHour match {
      case true =>
        browse.setNewCount(1L)
        browse.setUv(1L)
        browse.setTimeFormat(hourTime)
        out.collect(browse)
      case false =>
        browse.setOldCount(1L)
        browse.setTimeFormat(hourTime)
        out.collect(browse)
    }

    //天维度
    firstDay match {
      case true =>
        browse.setNewCount(1L)
        browse.setUv(1L)
        browse.setTimeFormat(dayTime)
        out.collect(browse)
      case false =>
        browse.setOldCount(1L)
        browse.setTimeFormat(dayTime)
        out.collect(browse)
    }

    //月维度
    firstMonth match {
      case true =>
        browse.setNewCount(1L)
        browse.setUv(1L)
        browse.setTimeFormat(monthTime)
        out.collect(browse)
      case false =>
        browse.setOldCount(1L)
        browse.setTimeFormat(monthTime)
        out.collect(browse)
    }
  }
}
