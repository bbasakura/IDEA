package cn.itcast.reduce

import cn.itcast.bean.ChannelPvuv
import org.apache.flink.api.common.functions.ReduceFunction

/**
  * 2019/9/4
  */
class ChannelPvuvReduce extends ReduceFunction[ChannelPvuv]{
  override def reduce(in1: ChannelPvuv, in2: ChannelPvuv): ChannelPvuv = {

    val pvuv = new ChannelPvuv
    pvuv.setTimeFormat(in1.getTimeFormat)
    pvuv.setChannelId(in1.getChannelId)
    pvuv.setUv(in1.getUv+in2.getUv)
    pvuv.setPv(in1.getPv+in2.getPv)
    pvuv
  }
}
