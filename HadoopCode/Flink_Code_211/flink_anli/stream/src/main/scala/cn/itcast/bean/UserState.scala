package cn.itcast.bean

import cn.itcast.until.{HbaseUtil, TimeUtil}
import org.apache.commons.lang3.StringUtils

/**
  * 2019/9/4
  */
//用户状态，如果状态值为true，说明是首次访问，uv=1
//如果状态值为flase，说明不是首次访问，uv=0
case class UserState(
               isNew:Boolean = false,
               isFirstHour:Boolean = false,
               isFirstDay:Boolean = false,
               isFirstMonth:Boolean= false
               )

object UserState{

  //定义格式化模板
  val hour ="yyyyMMddHH"
  val day ="yyyyMMdd"
  val month = "yyyyMM"

  /**
    *
    * @param timestamp
    * @return
    *         usreId :作为hbase的rowkey
    */
  def getUserState(timestamp:Long,userId:Long): UserState ={

    //定义hbase表参数
    val tableName= "userState"
    val family = "info"
    val firstVisitCol = "firstVisitTime"  //首次访问时间列
    val lastVisitCol = "lastVisitTime"   //最近一次的访问时间列
    val rowkey = userId.toString

    //初始化访问状态
    var isNew:Boolean = false
    var isFirstHour:Boolean = false
    var isFirstDay:Boolean = false
    var isFirstMonth:Boolean= false

    //先查询hbase表userState的首次访问时间列，如果为null，说明是第一次访问,用户访问状态全部为true
    val firstTimeStr: String = HbaseUtil.queryByRowkey(tableName,family,firstVisitCol,rowkey)
    if(StringUtils.isBlank(firstTimeStr)){

      isNew = true
      isFirstHour = true
      isFirstDay = true
      isFirstMonth = true

      //把首次访问时间插入hbase,对表的首次访问列和最近访问列进行初始化
      HbaseUtil.putDataByRowkey(tableName,family,firstVisitCol ,timestamp.toString,rowkey)
      HbaseUtil.putDataByRowkey(tableName,family,lastVisitCol ,timestamp.toString,rowkey)
      UserState(isNew,isFirstHour,isFirstDay,isFirstMonth)
    }else{  //说明首次访问列不为null,对最近一次访问列的时间，与当前进行大小判断
        //如果首次访问列得时间不为null，查询最近一次的访问时间
        val lastVisitTimeData: String = HbaseUtil.queryByRowkey(tableName,family,lastVisitCol,rowkey)

      //小时 2019090419                                                 18
      if(TimeUtil.parseTime(timestamp,hour).toLong > TimeUtil.parseTime(lastVisitTimeData.toLong,hour).toLong){
        isFirstHour = true
      }

      //天   20190905                                                 20190905
      if(TimeUtil.parseTime(timestamp,day).toLong > TimeUtil.parseTime(lastVisitTimeData.toLong,day).toLong){
        isFirstDay = true
      }

      //月
      if(TimeUtil.parseTime(timestamp,month).toLong > TimeUtil.parseTime(lastVisitTimeData.toLong,month).toLong){
        isFirstDay = true
      }

      HbaseUtil.putDataByRowkey(tableName,family,lastVisitCol,timestamp.toString,rowkey)
      UserState(isNew,isFirstHour,isFirstDay,isFirstMonth)
    }
  }
}

