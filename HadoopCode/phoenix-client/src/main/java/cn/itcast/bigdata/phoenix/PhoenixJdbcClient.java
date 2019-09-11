package cn.itcast.bigdata.phoenix;

import java.sql.*;

/**
 * Phoenix API操作 “映射hbase表的”Phoenix表
 */
public class PhoenixJdbcClient {

	public static void main(String[] args) throws SQLException {

		// 声明变量
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
			// a. 加载驱动类
			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");


			// b. 获取Connection连接
			conn = DriverManager.getConnection(
					"jdbc:phoenix:bigdata-cdh01.itcast.cn,bigdata-cdh02.itcast.cn,bigdata-cdh03.itcast.cn:2181"
			) ;

			// c. 获取Statement对象
			stmt = conn.createStatement();
			// d. 执行SQL查询
			rs = stmt.executeQuery("select * from \"stu\"") ;

			// e. 打印查询结果
			while(rs.next()){
				System.out.println(
						"SID = " + rs.getString(1)
								+ ", name = " + rs.getString(2)
								+ ", age = " + rs.getString(3)
				);
			}

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			// f. 关闭连接
			if(null != rs) rs.close();
			if(null != stmt) stmt.close();
			if(null != conn) conn.close();
		}

	}
}
