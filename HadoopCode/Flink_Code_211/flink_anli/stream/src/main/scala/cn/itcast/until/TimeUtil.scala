package cn.itcast.until

import java.util.Date

import org.apache.commons.lang3.time.FastDateFormat


/**
  *  2019/9/4
  */
object TimeUtil {

  /**
    *
    * @param timestamp long类型的时间（包含时分秒）
    * @param format  格式化模板
    */
  def parseTime(timestamp:Long,format:String): String ={

    val date = new Date(timestamp)
    val fastDateFormat: FastDateFormat = FastDateFormat.getInstance(format)
    val str: String = fastDateFormat.format(date)
    str
  }


}
