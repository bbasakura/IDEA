package cn.itcast.reduce

import cn.itcast.bean.ChannelRegion
import org.apache.flink.api.common.functions.ReduceFunction

/**
  *  2019/9/5
  */
class ChannelRegionReduce extends ReduceFunction[ChannelRegion] {
  override def reduce(in1: ChannelRegion, in2: ChannelRegion): ChannelRegion = {

    val channelRegion = new ChannelRegion
    channelRegion.setChannelId(in1.getChannelId)
    channelRegion.setCity(in1.getCity)
    channelRegion.setCountry(in1.getCountry)
    channelRegion.setNewCount(in1.getNewCount + in2.getNewCount)
    channelRegion.setOldCount(in1.getOldCount + in2.getOldCount)
    channelRegion.setProvince(in1.getProvince)
    channelRegion.setPv(in1.getPv + in2.getPv)
    channelRegion.setTimeFormat(in1.getTimeFormat)
    channelRegion.setUv(in1.getUv + in2.getUv)
    channelRegion
  }
}
