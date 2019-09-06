package cn.itcast.task

import cn.itcast.`trait`.ProcessData
import cn.itcast.bean.Message
import cn.itcast.map.ChannelPvuvFlatMap
import cn.itcast.reduce.ChannelPvuvReduce
import cn.itcast.sink.ChannelPvuvSink
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  *2019/9/4
  */
object ChannelPvuvTask extends ProcessData {
  override def process(waterData: DataStream[Message]): Unit = {
    /**
      * 开发步骤
      * 1.数据转换
      * 2.分组
      * 3.划分时间窗口
      * 4.数据聚合
      * 5.数据落地
      */
    //1.数据转换------为什么这里使用的flatmap 自定义的reduce?-----因为是分三个不同的维度统计，
    waterData.flatMap(new ChannelPvuvFlatMap)
      //2.分组
      .keyBy(line => line.getChannelId + line.getTimeFormat)
      //3.划分时间窗口
      .timeWindow(Time.seconds(3))
      //4.数据聚合
      .reduce(new ChannelPvuvReduce)
       //5.数据落地
      .addSink(new ChannelPvuvSink)
  }
}
