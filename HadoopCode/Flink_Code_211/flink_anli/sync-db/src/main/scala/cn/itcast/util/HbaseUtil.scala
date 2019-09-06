package cn.itcast.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}

/**
  *  2019/9/4
  */
object HbaseUtil {

  //获取配置对象,能够自动加载resources下面的hbase-site.xml
  private val configuration: Configuration = HBaseConfiguration.create()

  //获取connection连接
  private val connection: Connection = ConnectionFactory.createConnection(configuration)
  //获取admin对象
  private val admin: Admin = connection.getAdmin

  /**
    * 对hbase进行增删改查
    * 1.初始化
    * 2.根据rowkey查询数据
    * 3.根据rowkey插入单列数据
    * 4.根据rowkey插入多列数据
    * 5.根据rowkey删除数据
    */

  /**
    * 1.初始化表
    */
  def initTable(tableName: String, family: String): Table = {

    val hbaseTableName: TableName = TableName.valueOf(tableName)
    //先判断表是否存在，存在就直接获取，不存在，需要新建表
    if (!admin.tableExists(hbaseTableName)) {

      //构建表描述器
      val tableDescriptor: HTableDescriptor = new HTableDescriptor(hbaseTableName)
      //构建列族描述器
      val hColDescriptor: HColumnDescriptor = new HColumnDescriptor(family)
      tableDescriptor.addFamily(hColDescriptor)
      //创建表
      admin.createTable(tableDescriptor)
    }

    val table: Table = connection.getTable(hbaseTableName)
    table
  }

  /**
    * 2.根据rowkey查询数据
    */
  def queryByRowkey(tableName: String, family: String, columnName: String, rowkey: String): String = {
    //初始化表
    val table: Table = initTable(tableName, family)
    var str = ""
    try {
      val get = new Get(rowkey.getBytes())
      val result: Result = table.get(get)
      val bytes: Array[Byte] = result.getValue(family.getBytes(), columnName.getBytes())
      if (bytes != null && bytes.size > 0) {
        str = new String(bytes)
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      table.close()
    }

    str
  }

  /**
    * 3.根据rowkey插入单列数据
    */
  def putDataByRowkey(tableName: String, family: String, columnName: String, columnValue: String, rowkey: String): Unit = {

    //初始化表
    val table: Table = initTable(tableName, family)
    try {
      val put = new Put(rowkey.getBytes())
      put.addColumn(family.getBytes(), columnName.getBytes(), columnValue.getBytes())
      table.put(put)
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      table.close()
    }
  }

  /**
    * 4.根据rowkey插入多列数据
    */
  def putMapDataByRowkey(tableName: String, family: String, map: Map[String, Any], rowkey: String): Unit = {

    //初始化表
    val table: Table = initTable(tableName, family)
    try {
      val put = new Put(rowkey.getBytes())
      for ((x, y) <- map) {
        put.addColumn(family.getBytes(), x.getBytes(), y.toString.getBytes())
      }
      table.put(put)
    } catch {
      case e:Exception => e.printStackTrace()
    } finally {
      table.close()
    }
  }

  /**
    * 5.根据rowkey删除数据
    */
  def delByRowkey(tableName:String,family:String,rowkey:String): Unit ={

    //初始化表
    val table: Table = initTable(tableName,family)
    try {
      val delete = new Delete(rowkey.getBytes())
      delete.addFamily(family.getBytes())
      table.delete(delete)
    } catch {
      case e:Exception => e.printStackTrace()
    } finally {
      table.close()
    }
  }

  /**
    * 增删改查测试
    * @param args
    */
  def main(args: Array[String]): Unit = {

//    //插入单列数据
//    putDataByRowkey("pyg-test","info","column","11","001")
//    //查询001的数据
//    val str: String = queryByRowkey("pyg-test","info","column","001")
//    println(str)

    //插入多列数据
    val map = Map("a"->1,"b"->2)
    putMapDataByRowkey("pyg-test","info",map,"002")

    //根据rowkey删除数据
    delByRowkey("pyg-test","info","001")

  }
}
