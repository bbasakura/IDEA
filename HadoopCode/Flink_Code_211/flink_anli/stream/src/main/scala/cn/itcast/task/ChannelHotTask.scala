package cn.itcast.task

import cn.itcast.`trait`.ProcessData
import cn.itcast.bean.{ChannelHot, Message}
import cn.itcast.sink.ChannelHotSink
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 2019/9/4
  */
object ChannelHotTask extends ProcessData {
  override def process(waterData: DataStream[Message]): Unit = {

    /**
      * 1.数据转换（新建bean：ChannelHot）
      * 2.数据分组
      * 3.划分时间窗口
      * 4.数据聚合
      * 5.数据落地
      */
    //1.数据转换（新建bean：ChannelHot）-----谁想落地，为了方便操作，建立谁的javaBean，要什么字段，给什么属性。
    //map里面是message的集合，line是其中的一个，一个message，取出想要的字段，组成ChannelHot一个元素转换成一个元素，-------
    waterData.map(line => ChannelHot(line.count, line.userBrowse.channelID, line.timestamp))
      //2.数据分组
      .keyBy(line => line.channnelId)
      //3.划分时间窗口
      .timeWindow(Time.seconds(3))
      //4.数据聚合-------此时的x y是一个个的ChannelHot对象？？？
      .reduce((x, y) => ChannelHot(x.count + y.count, x.channnelId, x.timestmap))
      //5.数据落地---------addSink(自定义的Sink)
      .addSink(new ChannelHotSink)

  }
}
