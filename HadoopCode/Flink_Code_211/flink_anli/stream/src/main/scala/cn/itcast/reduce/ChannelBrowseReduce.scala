package cn.itcast.reduce

import cn.itcast.bean.ChannelBrowse
import org.apache.flink.api.common.functions.ReduceFunction

/**
  * 2019/9/5
  */
class ChannelBrowseReduce extends ReduceFunction[ChannelBrowse] {
  override def reduce(value1: ChannelBrowse, value2: ChannelBrowse): ChannelBrowse = {

    val browse = new ChannelBrowse
    browse.setBrowserType(value1.getBrowserType)
    browse.setChannelId(value1.getChannelId)
    browse.setNewCount(value1.getNewCount + value2.getNewCount)
    browse.setOldCount(value1.getOldCount + value2.getOldCount)
    browse.setPv(value1.getPv + value2.getPv)
    browse.setTimeFormat(value1.getTimeFormat)
    browse.setUv(value1.getUv + value2.getUv)
    browse
  }
}
