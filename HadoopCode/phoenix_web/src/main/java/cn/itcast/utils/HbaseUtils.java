package cn.itcast.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;


import cn.itcast.pojo.User;

/**
 * 操作hbase的工具类
 * @author lishas
 *
 */
public class HbaseUtils {
	public static String tableName="itcast_adm_personas_hbase";
    public static String key="user001";
    public static String familyName1="basicInfo";
    public static String familyName2="category";  
    public static String familyName3="order";  
    public static String familyName4="visit";  
    public static String qualifier="username";  
    public static String value; 
	public static Properties prop=new Properties();
	
	 //加载spring.xml配置文件
	 public static final ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });   
	 public static HbaseTemplate htemplate=null;
	 
	 //给HbaseTemplate对象赋值
	 public static HbaseTemplate getHbaseTemplate(){
		 BeanFactory factory = (BeanFactory) context;   
		 htemplate = (HbaseTemplate) factory.getBean("htemplate");
		 
		 return htemplate;
	 }
    
	public static void readColumnData() throws Exception{
		 InputStream in = Thread.currentThread().getContextClassLoader()  
	                .getResourceAsStream("columnOfUser.properties");  
	            prop.load(in);
	            //System.out.println(prop.getProperty("user_id"));
	}
	
	public static void main(String[] args) throws Exception {
		//readColumnData();
		//HbaseUtils.get(tableName, key);
		User userCondition =new User();
		List<User> userList = HbaseUtils.queryUserList(userCondition,HbaseUtils.getHbaseTemplate(),tableName);
		for (User user : userList) {
			System.out.println(user.getUser_name());
			
		}
	}	 
	
	
	public static List<User> queryUserList(User user,HbaseTemplate htemplate,String tableName){
		 final List<User> userList = new ArrayList<User>();
		 htemplate.execute(tableName, new TableCallback<List<User>>() {
	         public List<User> doInTable(HTableInterface table) throws Throwable {

	             Scan scan = new Scan();
	             FilterList filterList = filterByCondition(user);
	             List<Filter> filters = filterList.getFilters();
	             System.out.println("filterList的大小: "+filters.size());
	             if(filters.size()>0){
		             scan.setFilter(filterList);//设置过滤器
	             }
	             ResultScanner rscanner = table.getScanner(scan);
	             for(Result result : rscanner){
	                 User user = queryResult(result);
	                 userList.add(user);
	             }
	             return userList;
	             
	         }

	     });
		 return userList;
	  }
	
	public  static User queryResult(Result result) throws IOException {
		User user = new User();
		CellScanner cellScanner = result.cellScanner();
		String rowkey=""; //行健
		String familyName="";//列族名
		String qualifierName="";//字段名
		String value="";//字段值
			while (cellScanner.advance()) {
				
				Cell current = cellScanner.current();
				byte[] familyArray = current.getFamilyArray();
				byte[] valueArray = current.getValueArray();
				byte[] qualifierArray = current.getQualifierArray();
				byte[] rowArray = current.getRowArray();
	
				rowkey = new String(rowArray, current.getRowOffset(), current.getRowLength());
				
				familyName = new String(familyArray, current.getFamilyOffset(), current.getFamilyLength());

				qualifierName = new String(qualifierArray, current.getQualifierOffset(), current.getQualifierLength());
				
				value = new String(valueArray, current.getValueOffset(), current.getValueLength());
				
				user.setUser_id(rowkey);
				
				if("user_name".equals(qualifierName)){
					user.setUser_name(value);
				}else if("user_sex".equals(qualifierName)){
					user.setUser_sex(value);
				}else if("user_age".equals(qualifierName)){
					user.setUser_age(value);
				}else if("hex_phone".equals(qualifierName)){
					user.setHex_phone(value);
				}else if("monthly_money".equals(qualifierName)){
					user.setMonthly_money(value);
				}else if("total_mark".equals(qualifierName)){
					user.setTotal_mark(value);
				}else{
					continue;
				}
			
			}
			return user;
			
	}
	/**
	 * 根据User条件组装过滤器
	 * @param user
	 * @return
	 */
	public static FilterList filterByCondition(User user){
		FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
		SingleColumnValueFilter scvf_age0;
		SingleColumnValueFilter scvf_age1;
		SingleColumnValueFilter scvf_age2;
		
		//用户年龄
		
		if("".equals(user.getUser_age())){
			
		}else if(user.getUser_age().indexOf('-')>0){
			scvf_age0 = new SingleColumnValueFilter(familyName1.getBytes(), "user_age".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getUser_age().split("-")[0].getBytes()); 
			scvf_age1 = new SingleColumnValueFilter(familyName1.getBytes(), "user_age".getBytes(), CompareOp.LESS_OR_EQUAL, user.getUser_age().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_age0);
			filterList.addFilter(scvf_age1);
		}else{
			scvf_age2= new SingleColumnValueFilter(familyName1.getBytes(), "user_age".getBytes(), CompareOp.GREATER, user.getUser_age().getBytes());
			filterList.addFilter(scvf_age2);
		}
		//用户性别
		if("".equals(user.getUser_sex())){
			
		}else{
			SingleColumnValueFilter scvf_sex=new SingleColumnValueFilter(familyName1.getBytes(), "user_sex".getBytes(), CompareOp.EQUAL, user.getUser_sex().getBytes());
			filterList.addFilter(scvf_sex);
		}

		
		//用户省份
		if("无限制".equals(user.getProvince())){
			
		}else{
			SingleColumnValueFilter scvf_province=new SingleColumnValueFilter(familyName1.getBytes(), "province".getBytes(), CompareOp.EQUAL, user.getProvince().getBytes());
			filterList.addFilter(scvf_province);
		}

		
		//收入
		SingleColumnValueFilter scvf_monthly_money0;
		SingleColumnValueFilter scvf_monthly_money1;
		SingleColumnValueFilter scvf_monthly_money2;
		if("".equals(user.getMonthly_money())){
			
		}else if(user.getMonthly_money().indexOf('-')>0){
			scvf_monthly_money0 = new SingleColumnValueFilter(familyName1.getBytes(), "monthly_money".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getMonthly_money().split("-")[0].getBytes()); 
			scvf_monthly_money1 = new SingleColumnValueFilter(familyName1.getBytes(), "monthly_money".getBytes(), CompareOp.LESS_OR_EQUAL, user.getMonthly_money().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_monthly_money0);
			filterList.addFilter(scvf_monthly_money1);
		}else{
			scvf_monthly_money2= new SingleColumnValueFilter(familyName1.getBytes(), "monthly_money".getBytes(), CompareOp.GREATER, user.getMonthly_money().getBytes());
			filterList.addFilter(scvf_monthly_money2);
		}
		
		//会员积分
		SingleColumnValueFilter scvf_total_mark0;
		SingleColumnValueFilter scvf_total_mark1;
		SingleColumnValueFilter scvf_total_mark2;
		if("".equals(user.getTotal_mark())){
			
		}else if(user.getTotal_mark().indexOf('-')>0){
			scvf_total_mark0 = new SingleColumnValueFilter(familyName1.getBytes(), "total_mark".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getTotal_mark().split("-")[0].getBytes()); 
			scvf_total_mark1 = new SingleColumnValueFilter(familyName1.getBytes(), "total_mark".getBytes(), CompareOp.LESS_OR_EQUAL, user.getTotal_mark().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_total_mark0);
			filterList.addFilter(scvf_total_mark1);
		}else{
			scvf_total_mark2= new SingleColumnValueFilter(familyName1.getBytes(), "total_mark".getBytes(), CompareOp.GREATER, user.getTotal_mark().getBytes());
			filterList.addFilter(scvf_total_mark2);
		}
		
		//最大消费金额
		SingleColumnValueFilter scvf_max_order_amt0;
		SingleColumnValueFilter scvf_max_order_amt1;
		SingleColumnValueFilter scvf_max_order_amt2;
		if("".equals(user.getMax_order_amt())){
			
		}else if(user.getMax_order_amt().indexOf('-')>0){
			scvf_max_order_amt0 = new SingleColumnValueFilter(familyName3.getBytes(), "max_order_amt".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getMax_order_amt().split("-")[0].getBytes()); 
			scvf_max_order_amt1 = new SingleColumnValueFilter(familyName3.getBytes(), "max_order_amt".getBytes(), CompareOp.LESS_OR_EQUAL, user.getMax_order_amt().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_max_order_amt0);
			filterList.addFilter(scvf_max_order_amt1);
		}else{
			scvf_max_order_amt2= new SingleColumnValueFilter(familyName3.getBytes(), "max_order_amt".getBytes(), CompareOp.GREATER, user.getMax_order_amt().getBytes());
			filterList.addFilter(scvf_max_order_amt2);
		}
		
		//近30天购买次数（含退拒）
		SingleColumnValueFilter scvf_month1_order_cnt0;
		SingleColumnValueFilter scvf_month1_order_cnt1;
		SingleColumnValueFilter scvf_month1_order_cnt2;
		if("".equals(user.getMonth1_order_cnt())){
			
		}else if(user.getMonth1_order_cnt().indexOf('-')>0){
			scvf_month1_order_cnt0 = new SingleColumnValueFilter(familyName3.getBytes(), "month1_order_cnt".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getMonth1_order_cnt().split("-")[0].getBytes()); 
			scvf_month1_order_cnt1 = new SingleColumnValueFilter(familyName3.getBytes(), "month1_order_cnt".getBytes(), CompareOp.LESS_OR_EQUAL, user.getMonth1_order_cnt().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_month1_order_cnt0);
			filterList.addFilter(scvf_month1_order_cnt1);
		}else{
			scvf_month1_order_cnt2= new SingleColumnValueFilter(familyName3.getBytes(), "max_order_amt".getBytes(), CompareOp.GREATER, user.getTotal_order_amt().getBytes());
			filterList.addFilter(scvf_month1_order_cnt2);
		}
		//近30天购买金额（含退拒)
		SingleColumnValueFilter scvf_month1_order_amt0;
		SingleColumnValueFilter scvf_month1_order_amt1;
		SingleColumnValueFilter scvf_month1_order_amt2;
		if("".equals(user.getMonth1_order_amt())){
			
		}else if(user.getMonth1_order_amt().indexOf('-')>0){
			scvf_month1_order_amt0 = new SingleColumnValueFilter(familyName3.getBytes(), "month1_order_amt".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getMonth1_order_amt().split("-")[0].getBytes()); 
			scvf_month1_order_amt1 = new SingleColumnValueFilter(familyName3.getBytes(), "month1_order_amt".getBytes(), CompareOp.LESS_OR_EQUAL, user.getMonth1_order_amt().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_month1_order_amt0);
			filterList.addFilter(scvf_month1_order_amt1);
		}else{
			scvf_month1_order_amt2= new SingleColumnValueFilter(familyName3.getBytes(), "month1_order_amt".getBytes(), CompareOp.GREATER, user.getMonth1_order_amt().getBytes());
			filterList.addFilter(scvf_month1_order_amt2);
		}
		
		//家里下单总数
		SingleColumnValueFilter scvf_home_order_cnt0;
		SingleColumnValueFilter scvf_home_order_cnt1;
		SingleColumnValueFilter scvf_home_order_cnt2;
		if("".equals(user.getHome_order_cnt())){
			
		}else if(user.getHome_order_cnt().indexOf('-')>0){
			scvf_home_order_cnt0 = new SingleColumnValueFilter(familyName3.getBytes(), "home_order_cnt".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getHome_order_cnt().split("-")[0].getBytes()); 
			scvf_home_order_cnt1 = new SingleColumnValueFilter(familyName3.getBytes(), "home_order_cnt".getBytes(), CompareOp.LESS_OR_EQUAL, user.getHome_order_cnt().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_home_order_cnt0);
			filterList.addFilter(scvf_home_order_cnt1);
		}else{
			scvf_home_order_cnt2= new SingleColumnValueFilter(familyName3.getBytes(), "home_order_cnt".getBytes(), CompareOp.GREATER, user.getHome_order_cnt().getBytes());
			filterList.addFilter(scvf_home_order_cnt2);
		}
		
		//近30天PC端访问最常用游览器
		if("".equals(user.getMonth1_pc_common_browser_name())){
			
		}else{
			SingleColumnValueFilter scvf_month1_pc_common_browser_name=new SingleColumnValueFilter(familyName4.getBytes(), "month1_pc_common_browser_name".getBytes(), CompareOp.EQUAL, user.getMonth1_pc_common_browser_name().getBytes());
			filterList.addFilter(scvf_month1_pc_common_browser_name);
		}

		
		//近30天8-9点访问次数(不分PC与APP)
		SingleColumnValueFilter scvf_month1_hour829_cnt0;
		SingleColumnValueFilter scvf_month1_hour829_cnt1;
		SingleColumnValueFilter scvf_month1_hour829_cnt2;
		if("".equals(user.getMonth1_hour829_cnt())){

		}else if(user.getMonth1_hour829_cnt().indexOf('-')>0){
			scvf_month1_hour829_cnt0 = new SingleColumnValueFilter(familyName4.getBytes(), "month1_hour829_cnt".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getMonth1_hour829_cnt().split("-")[0].getBytes()); 
			scvf_month1_hour829_cnt1 = new SingleColumnValueFilter(familyName4.getBytes(), "month1_hour829_cnt".getBytes(), CompareOp.LESS_OR_EQUAL, user.getMonth1_hour829_cnt().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_month1_hour829_cnt0);
			filterList.addFilter(scvf_month1_hour829_cnt1);
		}else{
			scvf_month1_hour829_cnt2= new SingleColumnValueFilter(familyName4.getBytes(), "month1_hour829_cnt".getBytes(), CompareOp.GREATER, user.getMonth1_hour829_cnt().getBytes());
			filterList.addFilter(scvf_month1_hour829_cnt2);
		}
		
		//近30天22-23点访问次数(不分PC与APP)
		SingleColumnValueFilter scvf_month1_hour22223_cnt0;
		SingleColumnValueFilter scvf_month1_hour22223_cnt1;
		SingleColumnValueFilter scvf_month1_hour22223_cnt2;
		if("".equals(user.getMonth1_hour22223_cnt())){
			
		}else if(user.getMonth1_hour22223_cnt().indexOf('-')>0){
			scvf_month1_hour22223_cnt0 = new SingleColumnValueFilter(familyName4.getBytes(), "month1_hour22223_cnt".getBytes(), CompareOp.GREATER_OR_EQUAL, user.getMonth1_hour22223_cnt().split("-")[0].getBytes()); 
			scvf_month1_hour22223_cnt1 = new SingleColumnValueFilter(familyName4.getBytes(), "month1_hour22223_cnt".getBytes(), CompareOp.LESS_OR_EQUAL, user.getMonth1_hour22223_cnt().split("-")[1].getBytes()); 
			filterList.addFilter(scvf_month1_hour22223_cnt0);
			filterList.addFilter(scvf_month1_hour22223_cnt1);
		}else{
			scvf_month1_hour22223_cnt2= new SingleColumnValueFilter(familyName4.getBytes(), "month1_hour22223_cnt".getBytes(), CompareOp.GREATER, user.getMonth1_hour22223_cnt().getBytes());
			filterList.addFilter(scvf_month1_hour22223_cnt2);
		}
		
		return filterList;
	}
	
    
	 	/** 
	     * 写数据 
	     * @param tableName 
	     * @param action 
	     * @return 
	     */  
	    public static Boolean execute(String tableName,TableCallback<Boolean> action) { 
	        return htemplate.execute(tableName, new TableCallback<Boolean>() {  
	            public Boolean doInTable(HTableInterface table) throws Throwable {  
	                boolean flag = false;  
	                try{  
	                    byte[] rowkey = key.getBytes();  
	                    Put put = new Put(rowkey);  
	                    put.add(Bytes.toBytes(familyName1),Bytes.toBytes(qualifier), Bytes.toBytes(value));  
	                    table.put(put);  
	                 flag = true;  
	                }catch(Exception e){  
	                    e.printStackTrace();  
	                }  
	                return flag;  
	            }  
	        });  
	    }
	    
    /** 
      * 通过表名和key获取一行数据 
      * @param tableName 
      * @param rowName 
      * @return 
      */  
    public static Map<String, Object> get(String tableName, String rowName) {  
         return htemplate.get(tableName, rowName,new RowMapper<Map<String,Object>>(){  
               public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {      
                   List<Cell> ceList =   result.listCells();  
                   Map<String,Object> map = new HashMap<String, Object>();  
                        if(ceList!=null&&ceList.size()>0){  
                            for(Cell cell:ceList){
                            	
                                map.put(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+  
                                   "_"+Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()),   
                                   Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));  
                             }
                            }  
                    return  map;  
               }  
        });  
}
	
    /** 
     * 通过表名  key 和 列族 和列 获取一个数据 
     * @param tableName 
     * @param rowName 
     * @param familyName 
     * @param qualifier 
     * @return 
     */  
    public String get(String tableName ,String rowName, String familyName, String qualifier) {  
          return htemplate.get(tableName, rowName,familyName,qualifier ,new RowMapper<String>(){  
                 public String mapRow(Result result, int rowNum) throws Exception {     
                     List<Cell> ceList =   result.listCells();  
                     String res = "";  
                     if(ceList!=null&&ceList.size()>0){  
                         for(Cell cell:ceList){  
                             res = Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());  
                         }  
                     }  
                   return res;  
                 }  
          });  
    }
	    
	/** 
	  * 通过表名，开始行键和结束行键获取数据 
	  * @param tableName 
	  * @param startRow 
	  * @param stopRow 
	  * @return 
	  */  
	public List<Map<String,Object>> find(String tableName,String startRow,String stopRow) {  
	     Scan scan = new Scan();  
	     if(startRow==null){  
	         startRow="";  
	     }  
	     if(stopRow==null){  
	         stopRow="";      
	     }  
	     scan.setStartRow(Bytes.toBytes(startRow));  
	     scan.setStopRow(Bytes.toBytes(stopRow));
	     return     htemplate.find(tableName, scan,new RowMapper<Map<String,Object>>(){  
	           public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {   
	                
	                 List<Cell> ceList =   result.listCells();  
	                 Map<String,Object> map = new HashMap<String,Object>();  
	                 String  row = "";  
	                 if(ceList!=null&&ceList.size()>0){  
	                       for(Cell cell:ceList){  
	                        row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());  
	                        String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());  
	                        String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());  
	                        String quali = Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());  
	                        map.put(family+"_"+quali, value);  
	                       }  
	                       map.put("row",row );  
	                   }  
	                   return  map;  
	               }  
	             });
			}
}
