package cn.itcast.task

import cn.itcast.`trait`.ProcessData
import cn.itcast.bean.Message
import cn.itcast.map.ChannelNetworkFlatMap
import cn.itcast.reduce.ChannelNetworkReduce
import cn.itcast.sink.ChannelNetworkSink
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
/**
  * 2019/9/5
  */
object ChannelNetworkTask extends ProcessData{
  override def process(waterData: DataStream[Message]): Unit = {

    /**
      * 梳理开发步骤
      * 1.数据转换：bean: ChannelNetwork
      * 2.分组：ChannelId+ timeFormat
      * 3.划分时间窗口
      * 4.数据聚合
      * 5.数据落地：插入到hbase
      */
    //1.数据转换：bean: ChannelNetwork
    waterData.flatMap(new ChannelNetworkFlatMap)
    //2.分组：ChannelId+ timeFormat
      .keyBy(line=>line.getChannelId+line.getTimeFormat)
    //3.划分时间窗口
      .timeWindow(Time.seconds(3))
    //4.数据聚合
      .reduce(new ChannelNetworkReduce)
    //5.数据落地：插入到hbase
      .addSink(new ChannelNetworkSink)
  }
}
