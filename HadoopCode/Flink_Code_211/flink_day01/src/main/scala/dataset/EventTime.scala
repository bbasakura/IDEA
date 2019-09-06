package dataset


import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
object EventTime {

  def main(args: Array[String]): Unit = {


    /**
      * 需求：以EventTime划分窗口，计算3秒钟内出价最高的产品
      * 1.获取执行环境
      * 2.加载数据源
      * 3.设置事件时间
      * 4.数据转换，新建bean
      * 5.提取时间
      * 6.分组
      * 7.划分时间窗口
      * 8.求最大值
      * 9.数据打印
      * 10.触发执行
      */

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val source: DataStream[String] = env.socketTextStream("localhost",8900)
    //设置时间事件
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //数据转换，新建bean

    val bosess: DataStream[Boss] = source.map { line =>
      val arr: Array[String] = line.split("\\W+")
      Boss(arr(0).toLong, arr(1), arr(2), arr(3).toDouble)
    }
    //提取时间
    bosess.assignAscendingTimestamps(_.timestamp)
    //分组
      .keyBy(line=>line.boss)
    //划分时间的窗口
          .timeWindow(Time)




  }
}

//数据：(时间，公司，产品，价格)
//1527911155000,boos1,pc1,100.0
case class Boss(timestamp: Long, boss: String, product: String, price: Double)
