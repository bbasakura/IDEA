package cn.itcast.task

import cn.itcast.`trait`.ProcessData
import cn.itcast.bean.Message
import cn.itcast.map.ChannelRegionFlatMap
import cn.itcast.reduce.ChannelRegionReduce
import cn.itcast.sink.ChannelRegionSink
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  *  2019/9/5
  */
object ChannelRegionTask extends ProcessData {
  override def process(waterData: DataStream[Message]): Unit = {

    /**
      * 开发步骤：
      * 1.数据转换：bean：ChannelRegion
      * 2.数据分组
      * 3.划分时间窗口
      * 4.数据聚合
      * 5.数据落地
      */
    //1.数据转换：bean：ChannelRegion
    waterData.flatMap(new ChannelRegionFlatMap)
      //2.数据分组
      .keyBy(line => line.getChannelId + line.getTimeFormat)
      //3.划分时间窗口
      .timeWindow(Time.seconds(3))
      //4.数据聚合
      .reduce(new ChannelRegionReduce)
      //5.数据落地
      .addSink(new ChannelRegionSink)
  }
}
