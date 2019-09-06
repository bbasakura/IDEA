package cn.itcast.task

import cn.itcast.CanalClient.ColumnValuePair
import cn.itcast.bean.{Canal, ColumnPair, HbaseOperation}
import org.apache.flink.streaming.api.scala.DataStream
import org.apache.flink.streaming.api.scala._

import scala.collection.mutable.ArrayBuffer


/**
  * 2019/9/5
  * 业务处理类：主要用来封装最终的hbase操作类数据：HbaseOperation
  */

object ProcessData {
  def process(waterCanals: DataStream[Canal]) = {


    val value: DataStream[HbaseOperation] = waterCanals.flatMap {
      line =>
        val columnValueList: String = line.columnValueList  //JSON数组

        val tableName: String = line.tableName
        val dbName: String = line.dbName
        val eventType: String = line.eventType

        //解析columnValueList
        val pairs: ArrayBuffer[ColumnPair] = ColumnPair.parseJsonArray(columnValueList)

        //hbase表名
        val hbaseTableName: String = dbName + "-" + tableName
        val family: String = "info"
        val rowkey: String = pairs(0).columnValue

        //对pairs做数据转换操作，取列名和列值
        //(3)HbaseOperation(eventType,tableName,family,columnName,columnValue,rowkey)
        eventType match {
          case "INSERT" =>
            pairs.map(line => HbaseOperation(eventType, hbaseTableName, family, line.columnName, line.columnValue, rowkey))
          case "UPDATE" =>
            pairs.filter(_.isValid).map(line => HbaseOperation(eventType, hbaseTableName, family, line.columnName, line.columnValue, rowkey))
          case _ => {
            //为什么用List收集？？？用flateMap结果用集合收集？？？？
            List(HbaseOperation(eventType, hbaseTableName, family, null, null, rowkey))
          }
        }
    }
    value
  }

}
