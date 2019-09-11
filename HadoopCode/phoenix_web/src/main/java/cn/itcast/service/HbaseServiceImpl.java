package cn.itcast.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.itcast.dao.PhoenixHbaseDao;
import cn.itcast.pojo.User;
@Service
public class HbaseServiceImpl implements HbaseService {
	
	/**
	 * 通过userId查询用户
	 * @param UserId
	 * @return List<User>
	 */
	@Autowired
	PhoenixHbaseDao phoenixHbaseDao;
	@Override
	public User queryUserByUserId(String UserId) {
		return null;
	}
	
	/**
	 * 根据条件查询用户
	 * @param
	 * @return List<User>
	 */
	@Override
	public List<User> queryUserByCondition(User user) {
		
		String sqlFromCondition = sqlFromCondition(user);
		//拼接sql 头部分
		sqlFromCondition="select * from \"itcast_adm_personas_hbase_20170101\" where 1=1 " +sqlFromCondition;
		//拼接sql 尾部分
		sqlFromCondition +=" order by to_number(\"user_id\") asc";
		System.out.println(sqlFromCondition);
		
		List<User> userList = phoenixHbaseDao.query(sqlFromCondition);

		return userList;
	}
	
	
	
	//根据条件查询转化为sql
	public String  sqlFromCondition(User user){
		StringBuffer sb = new StringBuffer();
		
		//用户年龄
		String user_age = user.getUser_age();
		if("".equals(user_age)){
		
		}else if(user_age.indexOf('-')>0){
			String age0 = user_age.split("-")[0];
			String age1 = user_age.split("-")[1];
			sb.append(" and to_number(\"user_age\") >=" +age0 );
			sb.append(" and to_number(\"user_age\") <"+age1 );
		}else{
			sb.append(" and to_number(\"user_age\") >=" +user_age);
		}
		
		//用户性别
		String user_sex = user.getUser_sex();
		if(!"".equals(user_sex)){
				sb.append(" and  \"user_sex\" ='"+user_sex+"'");

		}
		
		//省份
		String province = user.getProvince();
		if(!"无限制".equals(province)){
				sb.append(" and  \"province\" ='"+province+"'");
	
		}
		
		//收入
		String monthly_money = user.getMonthly_money();
		if("".equals(monthly_money)){
			
		}else if(monthly_money.indexOf('-')>0){
			String monthly_money0 = monthly_money.split("-")[0];
			String monthly_money1 = monthly_money.split("-")[1];
			sb.append(" and round(to_number(\"monthly_money\"),1)  >="+monthly_money0);
			sb.append(" and round(to_number(\"monthly_money\"),1)  <"  +monthly_money1);
		}else if (monthly_money.indexOf('-')<0 ){
			sb.append(" and round(to_number(\"monthly_money\"),1) >=" +monthly_money);
		}
		//会员积分
		String total_mark = user.getTotal_mark();
		if("".equals(total_mark)){
			
		}else if(total_mark.indexOf('-')>0){
			String total_mark0 = total_mark.split("-")[0];
			String total_mark1 = total_mark.split("-")[1];
			sb.append(" and to_number(\"total_mark\") >=" +total_mark0 );
			sb.append(" and to_number(\"total_mark\") <"  +total_mark1);
		}
		else if(total_mark.indexOf('-')<0){
			sb.append(" and to_number(\"total_mark\") >"  +total_mark);
		}
		
		//最大消费金额
		String max_order_amt = user.getMax_order_amt();
		if("".equals(max_order_amt)){
			
		}else if(max_order_amt.indexOf('-')>0){
			String max_order_amt0 = max_order_amt.split("-")[0];
			String max_order_amt1 = max_order_amt.split("-")[1];
			sb.append(" and round(to_number(\"max_order_amt\"),1) >=" +max_order_amt0);
			sb.append(" and round(to_number(\"max_order_amt\"),1) <"  +max_order_amt1 );
		}
		else if(max_order_amt.indexOf('-')<0){
			sb.append(" and round(to_number(\"max_order_amt\"),1) >"  +max_order_amt);
		}
		
		//近30天购买次数（含退拒）
		String month1_order_cnt = user.getMonth1_order_cnt();
		if("".equals(month1_order_cnt)){
			
		}else if(month1_order_cnt.indexOf('-')>0){
			String month1_order_cnt0 = month1_order_cnt.split("-")[0];
			String month1_order_cnt1 = month1_order_cnt.split("-")[1];
			sb.append(" and to_number(\"month1_order_cnt\") >=" +month1_order_cnt0);
			sb.append(" and to_number(\"month1_order_cnt\") <"  +month1_order_cnt1 );
		}else if(month1_order_cnt.indexOf('-')<0){
			sb.append(" and to_number(\"month1_order_cnt\") >"  +month1_order_cnt);
		}
		
		//近30天购买金额（含退拒)
		String month1_order_amt = user.getMonth1_order_amt();
		if("".equals(month1_order_amt)){
			
		}else if(month1_order_amt.indexOf('-')>0 ){
			String month1_order_amt0 =month1_order_amt.split("-")[0];
			String month1_order_amt1 = month1_order_amt.split("-")[1];
			sb.append(" and round(to_number(\"month1_order_amt\"),1) >=" +month1_order_amt0);
			sb.append(" and round(to_number(\"month1_order_amt\"),1) <"  +month1_order_amt1);
		}else if(month1_order_amt.indexOf('-')<0 ){
			sb.append(" and round(to_number(\"month1_order_amt\"),1) >"  +month1_order_amt);
		}
		//家里下单总数
		String home_order_cnt = user.getHome_order_cnt();
		if("".equals(home_order_cnt)){
			
		}else if(home_order_cnt.indexOf('-')>0 ){
			String home_order_cnt0 = home_order_cnt.split("-")[0];
			String home_order_cnt1 = home_order_cnt.split("-")[1];
			sb.append(" and to_number(\"home_order_cnt\") >=" +home_order_cnt0);
			sb.append(" and to_number(\"home_order_cnt\") <"  +home_order_cnt1);
		}else if(home_order_cnt.indexOf('-')<0 ){
			sb.append(" and to_number(\"home_order_cnt\") >"  +home_order_cnt);
		}
		
		//近30天PC端访问最常用游览器
		String month1_pc_common_browser_name = user.getMonth1_pc_common_browser_name();
		
		if(!"".equals(month1_pc_common_browser_name)){
				sb.append(" and  \"month1_pc_common_browser_name\" ='"+month1_pc_common_browser_name+"'");
		}
		
		//近30天8-9点访问次数(不分PC与APP)
		String month1_hour829_cnt = user.getMonth1_hour829_cnt();
		if("".equals(month1_hour829_cnt)){

		}else if(month1_hour829_cnt.indexOf('-')>0){
			String month1_hour829_cnt0 = month1_hour829_cnt.split("-")[0];
			String month1_hour829_cnt1 = month1_hour829_cnt.split("-")[1];
			sb.append(" and to_number(\"month1_hour829_cnt\") >=" +month1_hour829_cnt0);
			sb.append(" and to_number(\"month1_hour829_cnt\") <"  +month1_hour829_cnt1);
		}else if(month1_hour829_cnt.indexOf('-')<0 ) {
			sb.append(" and to_number(\"month1_hour829_cnt\") >" +month1_hour829_cnt);
		}
		
		//近30天22-23点访问次数(不分PC与APP)
		String month1_hour22223_cnt = user.getMonth1_hour22223_cnt();
		if("".equals(month1_hour22223_cnt)){
			
		}else if(month1_hour22223_cnt.indexOf('-')>0){
			String month1_hour22223_cnt0 = month1_hour22223_cnt.split("-")[0];
			String month1_hour22223_cnt1 = month1_hour22223_cnt.split("-")[1];
			sb.append(" and to_number(\"month1_hour22223_cnt\") >=" +month1_hour22223_cnt0);
			sb.append(" and to_number(\"month1_hour22223_cnt\") <"  +month1_hour22223_cnt1);
		}else if(month1_hour22223_cnt.indexOf('-')<0){
			sb.append(" and to_number(\"month1_hour22223_cnt\") >" +month1_hour22223_cnt);
		}
		return sb.toString();
		}
}
