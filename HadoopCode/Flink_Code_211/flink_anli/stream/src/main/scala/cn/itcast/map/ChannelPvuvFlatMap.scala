package cn.itcast.map

import cn.itcast.bean.{ChannelPvuv, Message, UserBrowse, UserState}
import cn.itcast.until.TimeUtil
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.util.Collector

/**
  * 2019/9/4
  *
  *
  * Message中取值，组成ChannelPvuv
  */
class ChannelPvuvFlatMap extends RichFlatMapFunction[Message,ChannelPvuv]{

  //定义格式化模板
  val hour = "yyyyMMddHH"
  val day ="yyyyMMdd"
  val month = "yyyyMM"
  override def flatMap(value: Message, out: Collector[ChannelPvuv]): Unit = {

    val browse: UserBrowse = value.userBrowse
    val channelID: Long = browse.channelID
    val timestamp: Long = browse.timestamp
    val userID: Long = browse.userID

    //获取用户访问时间状态
    val userState: UserState = UserState.getUserState(timestamp,userID)
    //获取访问状态
    val isNew: Boolean = userState.isNew
    val firstHour: Boolean = userState.isFirstHour
    val firstDay: Boolean = userState.isFirstDay
    val firstMonth: Boolean = userState.isFirstMonth

    //格式化日期
    val hourTime: String = TimeUtil.parseTime(timestamp,hour)
    val dayTime: String = TimeUtil.parseTime(timestamp,day)
    val monthTime: String = TimeUtil.parseTime(timestamp,month)

    //新建bean,封装部分数据
    val pvuv = new ChannelPvuv
    pvuv.setPv(1L)
    pvuv.setChannelId(channelID)

    //根据用户访问的时间状态，进行设置值，uv
    isNew match {
      case true =>
        pvuv.setUv(1L)
      case false=>
        pvuv.setUv(0L)
    }

    //小时维度
    firstHour match {
      case true=>
        pvuv.setUv(1L)
        pvuv.setTimeFormat(hourTime)
        out.collect(pvuv)
      case false =>
        pvuv.setUv(0L)
        pvuv.setTimeFormat(hourTime)
        out.collect(pvuv)
    }

    //天维度
    firstDay match {
      case true=>
        pvuv.setUv(1L)
        pvuv.setTimeFormat(dayTime)
        out.collect(pvuv)
      case false =>
        pvuv.setUv(0L)
        pvuv.setTimeFormat(dayTime)
        out.collect(pvuv)
    }

    //月维度
    firstMonth match {
      case true=>
        pvuv.setUv(1L)
        pvuv.setTimeFormat(monthTime)
        out.collect(pvuv)
      case false =>
        pvuv.setUv(0L)
        pvuv.setTimeFormat(monthTime)
        out.collect(pvuv)
    }
  }
}
