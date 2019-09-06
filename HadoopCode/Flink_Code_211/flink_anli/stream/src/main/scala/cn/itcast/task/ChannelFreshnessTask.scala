package cn.itcast.task

import cn.itcast.`trait`.ProcessData
import cn.itcast.bean.Message
import cn.itcast.map.ChannelFreshnessFlatMap
import cn.itcast.reduce.ChannelFreshnessReduce
import cn.itcast.sink.ChannelFreshnessSink
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  *  2019/9/5
  */
object ChannelFreshnessTask extends ProcessData {
  override def process(waterData: DataStream[Message]): Unit = {

    /**
      * 开发步骤梳理
      * 1.数据转换--》ChannelFreshness
      * 2.数据分组
      * 3.划分时间窗口
      * 4.数据聚合
      * 5.数据落地
      */
    //1.数据转换--》ChannelFreshness
    waterData.flatMap(new ChannelFreshnessFlatMap)
      //2.数据分组
      .keyBy(line => line.channelId + line.timeFormat)
      //3.划分时间窗口
      .timeWindow(Time.seconds(3))
      //4.数据聚合
      .reduce(new ChannelFreshnessReduce)
      //5.数据落地
      .addSink(new ChannelFreshnessSink)
  }
}
