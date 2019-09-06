package cn.itcast.map

import cn.itcast.bean.{ChannelFreshness, Message, UserBrowse, UserState}
import cn.itcast.until.TimeUtil
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.util.Collector

/**
  *  2019/9/5
  */
class ChannelFreshnessFlatMap extends RichFlatMapFunction[Message, ChannelFreshness] {

  //定义日期格式化模板
  val hour = "yyyyMMddHH"
  val day = "yyyyMMdd"
  val month ="yyyyMM"

  override def flatMap(value: Message, out: Collector[ChannelFreshness]): Unit = {

    val userBrowse: UserBrowse = value.userBrowse
    val channelID: Long = userBrowse.channelID
    val timestamp: Long = userBrowse.timestamp
    val userID: Long = userBrowse.userID
    //获取用户访问状态
    val userState: UserState = UserState.getUserState(timestamp, userID)
    val isNew: Boolean = userState.isNew
    val firstHour: Boolean = userState.isFirstHour
    val firstDay: Boolean = userState.isFirstDay
    val firstMonth: Boolean = userState.isFirstMonth

    //新建bean，封装部分数据
    val freshness = new ChannelFreshness
    freshness.setChannelId(channelID)

    //对当前日期，进行格式化操作
    val hourTime: String = TimeUtil.parseTime(timestamp,hour)
    val dayTime: String = TimeUtil.parseTime(timestamp,day)
    val monthTime: String = TimeUtil.parseTime(timestamp,month)

    //根据用户访问状态，判断是否是新老用户
    isNew match {
      case true =>
        freshness.setNewCount(1L)
      case false =>
        freshness.setOldCount(1L)
    }

    //小时维度
    firstHour match {
      case true=>
        freshness.setNewCount(1L)
        freshness.setTimeFormat(hourTime)
        out.collect(freshness)
      case false=>
        freshness.setOldCount(1L)
        freshness.setTimeFormat(hourTime)
        out.collect(freshness)
    }


    //天维度
    firstDay match {
      case true=>
        freshness.setNewCount(1L)
        freshness.setTimeFormat(dayTime)
        out.collect(freshness)
      case false=>
        freshness.setOldCount(1L)
        freshness.setTimeFormat(dayTime)
        out.collect(freshness)
    }


    //月维度
    firstMonth match {
      case true=>
        freshness.setNewCount(1L)
        freshness.setTimeFormat(monthTime)
        out.collect(freshness)
      case false=>
        freshness.setOldCount(1L)
        freshness.setTimeFormat(monthTime)
        out.collect(freshness)
    }
  }
}
