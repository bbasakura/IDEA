package cn.itcast.`trait`

import cn.itcast.bean.Message
import org.apache.flink.streaming.api.scala.DataStream

/**
  *  2019/9/4
  */
trait ProcessData {

  def process(waterData:DataStream[Message])
//  val waterData: DataStream[Message]   -得到的最近的类型的数据

}
