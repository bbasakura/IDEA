package cn.itcast.bean

/**
  * 2019/9/5
  */
case class HbaseOperation(
                         // 前面两个Bean中取值，组合成这一个的对象，是要写入Hbase的
                         var eventType:String,
                         var tableName:String,
                         var family:String,
                         var columnName:String,
                         var columnValue:String,
                         var rowkey:String
                         )
