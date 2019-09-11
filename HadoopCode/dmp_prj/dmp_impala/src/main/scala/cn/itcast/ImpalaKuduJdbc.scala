package cn.itcast

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

object ImpalaKuduJdbc {


  def main(args: Array[String]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    var rs: ResultSet = null
    try {
      // 加载驱动类
      Class.forName("org.apache.hive.jdbc.HiveDriver")
      // 建立连接
      conn = DriverManager.getConnection("jdbc:impala://hadoop02:21050/default")  //----impala的主节点
      // 准备SQL
      val querySQL = "select * from impala_kudu_users where age > 20 limit 10 "
      ps = conn.prepareStatement(querySQL)
      rs = ps.executeQuery()
      // 获取数据
      while (rs.next()) {
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val age = rs.getInt("age")
        println(id, name, age)
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (null != rs) rs.close()
      if (null != rs) ps.close()
      if (null != rs) conn.close()
    }
  }

}
