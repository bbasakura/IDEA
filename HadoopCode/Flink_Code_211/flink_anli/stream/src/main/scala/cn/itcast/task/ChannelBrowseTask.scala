package cn.itcast.task

import cn.itcast.`trait`.ProcessData
import cn.itcast.bean.Message
import cn.itcast.map.ChannelBrowseFlatMap
import cn.itcast.reduce.ChannelBrowseReduce
import cn.itcast.sink.ChannelBrowseSink
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  *  2019/9/5
  */
object ChannelBrowseTask extends ProcessData {

  /**
    * 开发步骤梳理
    * 1.数据转换--》ChannelBrowse
    * 2.数据分组
    * 3.划分时间窗口
    * 4.数据聚合
    * 5.数据落地
    */
  override def process(waterData: DataStream[Message]): Unit = {

    //1.数据转换--》ChannelBrowse
    waterData.flatMap(new ChannelBrowseFlatMap)
      //2.数据分组
      .keyBy(line => line.getChannelId + line.getTimeFormat)
      //3.划分时间窗口
      .timeWindow(Time.seconds(3))
      //4.数据聚合
      .reduce(new ChannelBrowseReduce)
      //5.数据落地
      .addSink(new ChannelBrowseSink)

  }
}
