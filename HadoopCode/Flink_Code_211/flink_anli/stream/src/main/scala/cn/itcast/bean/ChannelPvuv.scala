package cn.itcast.bean

import scala.beans.BeanProperty

/**
  *  2019/9/4
  */
class ChannelPvuv {

  //表示：给bean对象添加get和set方法，类似于javaBean的get和set方法
  @BeanProperty var channelId: Long = 0L
  @BeanProperty var timeFormat:String = null
  @BeanProperty var pv:Long = 0L
  @BeanProperty var uv:Long = 0L

//  //get
//  def getChannelId = channelId
//  def getTimeFormat = timeFormat
//  def getPv = pv
//  def getUv = uv
//
//  //set
//  def setChannelId(value:Long) = {channelId = value}
//  def setTimeFormat(value:String)={timeFormat = value}
//  def setPv(value:Long)={pv= value}
//  def setUv(value:Long) = {uv= value}


}
