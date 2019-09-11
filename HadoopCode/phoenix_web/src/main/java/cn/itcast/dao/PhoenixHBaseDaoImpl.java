package cn.itcast.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.pojo.User;

@Repository
public class PhoenixHBaseDaoImpl implements PhoenixHbaseDao {
	@Autowired
	@Qualifier("phoenixJdbcTemplate")
    private JdbcTemplate jdbcTemplate;  
		
    public PhoenixHBaseDaoImpl(JdbcTemplate template) {  
        this.jdbcTemplate = template;  
    }  
    public PhoenixHBaseDaoImpl() {  
        super();  
  
    }  
    
    /*
     * JdbcTemplate类支持的回调类:
     *  用于根据JdbcTemplate提供的连接创建相应的语句;
     *  PreparedStatementCallback:通过回调获取JdbcTemplate提供的PreparedStatement,
     *  用户可以在该PreparedStatement执行任何数量的操作;
     * 
     */
   public List<User> query(String querySql) {  
         	//可以用于执行任何SQL语句

       return (List<User>) jdbcTemplate.execute(querySql,new PreparedStatementCallback<Object>() {  
 
           public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {  
                //通过PreparedStatement执行 executeQuery方法，回调处理ResultSet
               ResultSet rs=ps.executeQuery();  
               List<User> list=new ArrayList<User>();  
               	//将ResultSet结果集转化成对象
               while (rs.next()) {
            	   User user = new User();
            	   user.setUser_id(rs.getString("user_id"));
            	   System.out.print("user_id="+user.getUser_id()+"\t");
            	   
            	   user.setUser_name(rs.getString("user_name"));
            	   System.out.print("user_name="+user.getUser_name()+"\t");
            	   
            	   user.setUser_sex(rs.getString("user_sex"));  
            	   System.out.print("user_sex="+user.getUser_sex()+"\t");
            	   
            	   user.setUser_age(rs.getString("user_age"));  
            	   System.out.print("user_age="+user.getUser_age()+"\t");
            	   
            	   user.setMonthly_money(rs.getString("monthly_money")); 
            	   System.out.print("monthly_money="+user.getMonthly_money()+"\t");
            	   
            	   user.setTotal_mark(rs.getString("total_mark"));  
            	   System.out.println("total_mark="+user.getTotal_mark()+"\t");
            	   
                   list.add(user);  
               }  
                 
               return list;  
           }
       }); 
        
        
    }
  
    @Override  
    public void update(String querySql) {  
        System.out.println(querySql);  
        //update方法用于执行新增、修改、删除等语句
        jdbcTemplate.update(querySql);  
    }  
  
    @Override  
    public void batchUpdate(String updateSQL) {  
        System.out.println("##########BATCH UPDATE:"+updateSQL);  
        //批处理相关语句
        jdbcTemplate.batchUpdate(updateSQL);
    }  
  
    public JdbcTemplate getJdbcTemplate() {  
        return jdbcTemplate;  
    }  
  
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    }  
}
