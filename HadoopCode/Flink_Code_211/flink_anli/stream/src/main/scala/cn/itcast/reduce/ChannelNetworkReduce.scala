package cn.itcast.reduce

import cn.itcast.bean.ChannelNetwork
import org.apache.flink.api.common.functions.ReduceFunction

/**
  *2019/9/5
  */
class ChannelNetworkReduce extends ReduceFunction[ChannelNetwork] {
  override def reduce(in1: ChannelNetwork, in2: ChannelNetwork): ChannelNetwork = {

    val channelNetwork = new ChannelNetwork
    channelNetwork.setChannelId(in1.getChannelId)
    channelNetwork.setNetwork(in1.getNetwork)
    channelNetwork.setNewCount(in1.getNewCount + in2.getNewCount)
    channelNetwork.setOldCount(in1.getOldCount + in2.getOldCount)
    channelNetwork.setTimeFormat(in1.getTimeFormat)
    channelNetwork
  }
}
