package com.itheima.log_monitor.utils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.itheima.log_monitor.domain.LogMonitorApp;
import com.itheima.log_monitor.domain.LogMonitorRule;
import com.itheima.log_monitor.domain.LogMonitorRuleRecord;
import com.itheima.log_monitor.domain.LogMonitorUser;
import org.springframework.jdbc.core.JdbcTemplate;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.RowMapper;


public class JdbcUtils implements  Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1268780790340521215L;
	
	private static  JdbcTemplate jdbcTemplate;
	private static DruidDataSource druidDataSource;
	
	
	static {
		if(null == druidDataSource){
			druidDataSource  = new DruidDataSource();
			druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
			druidDataSource.setUrl("jdbc:mysql://192.168.72.141:3306/log_monitor?characterEncoding=utf-8");
			druidDataSource.setUsername("root");
			druidDataSource.setPassword("123456");
		}
		if(null == jdbcTemplate ){
			jdbcTemplate = new JdbcTemplate(druidDataSource);
		}
	}
	
	
	
	public static   void saveRuleRecord(LogMonitorRuleRecord ruleRecord){
		jdbcTemplate.update("insert into log_monitor_rule_record (appId,ruleId,isEmail,isPhone,isClose,noticeInfo,createDate,updateDate) VALUES(?,?,?,?,?,?,?,?);", ruleRecord.getAppId(),ruleRecord.getRuleId(),ruleRecord.getIsEmail(),ruleRecord.getIsPhone(),ruleRecord.getIsClose(),ruleRecord.getNoticeInfo(),ruleRecord.getCreateDate(),ruleRecord.getUpdateDate());
	}
	
	
	
	public  List<LogMonitorRule> queryAllRules(){
		
		List<LogMonitorRule> query = jdbcTemplate.query("select * from log_monitor_rule ", new RuleRowMapper());
		return query;
	}
	
	
	public List<LogMonitorApp> queryAllApp(){
		List<LogMonitorApp>	query = jdbcTemplate.query("select * from log_monitor_app ", new AppRowMapper());
		return query;
	}
	
	public List<LogMonitorUser> queryAllUser(){
		 List<LogMonitorUser> allUser = jdbcTemplate.query("select * from log_monitor_user ", new LogMonitorUserRowMapper());
		 return allUser;
	}
	
	
	
	
	
	
	
	public class RuleRowMapper implements RowMapper<LogMonitorRule> {
		public LogMonitorRule mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMonitorRule rule = new LogMonitorRule();
			rule.setRuleId(rs.getInt("ruleId"));
			rule.setName(rs.getString("name"));
			rule.setDesc(rs.getString("desc"));
			rule.setKeyword(rs.getString("keyword"));
			rule.setIsValid(rs.getInt("isValid"));
			rule.setAppId(rs.getInt("appId"));
			rule.setCreateDate(rs.getDate("createDate"));
			rule.setUpdateDate(rs.getDate("updateDate"));
			rule.setCreateUser(rs.getString("createUser"));
			rule.setUpdateUser(rs.getString("updateUser"));
			return rule;
		}
	}
	
	
	
	
	public class AppRowMapper implements RowMapper<LogMonitorApp>{
		public LogMonitorApp mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMonitorApp logMonitorApp = new LogMonitorApp();
			logMonitorApp.setAppId(rs.getInt("appId"));;
			logMonitorApp.setName(rs.getString("name"));
			logMonitorApp.setDesc(rs.getString("desc"));
			logMonitorApp.setIsOnline(rs.getInt("isOnline"));
			logMonitorApp.setTypeId(rs.getInt("typeId"));
			logMonitorApp.setCreateDate(rs.getDate("createDate"));
			logMonitorApp.setUpdateDate(rs.getDate("updateaDate"));
			logMonitorApp.setCreateUser(rs.getString("createUser"));
			logMonitorApp.setUpdateUser(rs.getString("updateUser"));
			return logMonitorApp;
		}
	}
	
	
	public class LogMonitorUserRowMapper implements RowMapper<LogMonitorUser>{
		public LogMonitorUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMonitorUser user = new LogMonitorUser();
			user.setUserId(rs.getLong("userId"));
			user.setName(rs.getString("name"));
			user.setMobile(rs.getString("mobile"));
			user.setEmail(rs.getString("email"));
			user.setIsValid(rs.getInt("isValid"));
			user.setCreateDate(rs.getDate("createDate"));
			user.setUpdateDate(rs.getDate("updateDate"));
			user.setCreateUser(rs.getString("createUser"));
			user.setUpdateUser(rs.getString("updateUser"));
			user.setChargeAppId(rs.getInt("chargeAppId"));
			return user;
		}
	}

}
