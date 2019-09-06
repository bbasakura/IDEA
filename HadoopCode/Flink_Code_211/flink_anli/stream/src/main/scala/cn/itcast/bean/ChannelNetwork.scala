package cn.itcast.bean

import scala.beans.BeanProperty

/**
  *  2019/9/5
  */
class ChannelNetwork {

  @BeanProperty var channelId:Long = 0L
  @BeanProperty var network:String = null
  @BeanProperty var timeFormat:String = null
  @BeanProperty var newCount:Long = 0L
  @BeanProperty var oldCount:Long =0L


}
