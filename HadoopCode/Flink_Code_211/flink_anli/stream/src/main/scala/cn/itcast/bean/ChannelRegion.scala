package cn.itcast.bean

import scala.beans.BeanProperty

/**
  * 2019/9/5
  */
class ChannelRegion {


  @BeanProperty var channelId:Long = 0L
  @BeanProperty var country:String=null
  @BeanProperty var province:String=null
  @BeanProperty var city:String = null
  @BeanProperty var timeFormat:String = null
  @BeanProperty var pv:Long = 0L
  @BeanProperty var uv:Long = 0L
  @BeanProperty var newCount:Long =0L
  @BeanProperty var oldCount:Long = 0L



}
