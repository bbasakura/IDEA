package cn.itcast.reduce

import cn.itcast.bean.ChannelFreshness
import org.apache.flink.api.common.functions.ReduceFunction

/**
  * 2019/9/5
  */
class ChannelFreshnessReduce extends ReduceFunction[ChannelFreshness] {
  override def reduce(value1: ChannelFreshness, value2: ChannelFreshness): ChannelFreshness = {

    val freshness = new ChannelFreshness
    freshness.setChannelId(value1.getChannelId)
    freshness.setNewCount(value1.getNewCount + value2.getNewCount)
    freshness.setOldCount(value1.getOldCount + value2.getOldCount)
    freshness.setTimeFormat(value1.getTimeFormat)
    freshness
  }
}
