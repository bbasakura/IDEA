package cn.itcast.bean

import scala.beans.BeanProperty

/**
  *  2019/9/5
  */
class ChannelFreshness {

  @BeanProperty var channelId: Long = 0L
  @BeanProperty var timeFormat: String = null
  @BeanProperty var newCount: Long = 0L
  @BeanProperty var oldCount: Long = 0L


}
