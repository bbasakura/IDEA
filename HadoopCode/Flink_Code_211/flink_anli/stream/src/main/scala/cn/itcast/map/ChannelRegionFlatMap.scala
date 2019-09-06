package cn.itcast.map

import cn.itcast.bean.{ChannelRegion, Message, UserBrowse, UserState}
import cn.itcast.until.TimeUtil
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.util.Collector

/**
  * 2019/9/5
  */
class ChannelRegionFlatMap extends RichFlatMapFunction[Message, ChannelRegion] {

  //定义格式化模板
  val hour = "yyyyMMddHH"
  val day = "yyyyMMdd"
  val month = "yyyyMM"

  override def flatMap(in: Message, out: Collector[ChannelRegion]): Unit = {

    //取出消息中的部分数据
    val userBrowse: UserBrowse = in.userBrowse
    val channelID: Long = userBrowse.channelID
    val userID: Long = userBrowse.userID
    val timestamp: Long = userBrowse.timestamp
    val country: String = userBrowse.country
    val province: String = userBrowse.province
    val city: String = userBrowse.city

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

    //新建bean对象，封装部分数据
    val channelRegion = new ChannelRegion
    channelRegion.setChannelId(channelID)
    channelRegion.setCity(city)
    channelRegion.setProvince(province)
    channelRegion.setCountry(country)
    channelRegion.setPv(1L)

    //根据用户访问状态，封装数据
    isNew match {
      case true =>
        channelRegion.setNewCount(1L)
        channelRegion.setUv(1L)
      case false =>
        channelRegion.setOldCount(1L)
        channelRegion.setUv(0L)
    }

    //小时维度
    firstHour match {
      case true =>
        channelRegion.setUv(1L)
        channelRegion.setNewCount(1L)
        channelRegion.setTimeFormat(hourTime)
        out.collect(channelRegion)
      case false =>
        channelRegion.setUv(0L)
        channelRegion.setOldCount(1L)
        channelRegion.setTimeFormat(hourTime)
        out.collect(channelRegion)
    }

    //天维度
    firstDay match {
      case true =>
        channelRegion.setUv(1L)
        channelRegion.setNewCount(1L)
        channelRegion.setTimeFormat(dayTime)
        out.collect(channelRegion)
      case false =>
        channelRegion.setUv(0L)
        channelRegion.setOldCount(1L)
        channelRegion.setTimeFormat(dayTime)
        out.collect(channelRegion)
    }

    //月维度
    firstMonth match {
      case true =>
        channelRegion.setUv(1L)
        channelRegion.setNewCount(1L)
        channelRegion.setTimeFormat(monthTime)
        out.collect(channelRegion)
      case false =>
        channelRegion.setUv(0L)
        channelRegion.setOldCount(1L)
        channelRegion.setTimeFormat(monthTime)
        out.collect(channelRegion)
    }

  }
}
