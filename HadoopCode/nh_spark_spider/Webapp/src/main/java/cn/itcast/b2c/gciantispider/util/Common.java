package cn.itcast.b2c.gciantispider.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class Common {
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * @return String
	 */
	public static String fromDateH(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd
	 * @return String
	 */
	public static String fromDateY(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}
	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}
	
	
	
	
	public static void testAll(Class s){
		testF(s);
		testAdd(s);
		testUpdate(s);
	}
	public static void testF(Class s){
	     // Class cls = Class.forName(classPath);  //com.geocompass.model.STSTBPRPModel
          Field[] fieldlist = s.getDeclaredFields();
          for (int i = 0; i < fieldlist.length; i++) {
              Field fld = fieldlist[i];
              System.out.println(add_Fun(fld.getName())+" as "+fld.getName()+(i<fieldlist.length-1?",":""));
          }
          
          System.out.println("");
          System.out.println("");
          System.out.println("");

	}
	
	public static String add_Fun(String str){
		String up="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<str.length();i++){
			if(up.indexOf(str.substring(i, i+1))>=0){
				str=str.substring(0,i)+"_"+str.substring(i);
				i++;
			}
		}
		return str;
		
	}
	public static void testAdd(Class s){
	     // Class cls = Class.forName(classPath);  //com.geocompass.model.STSTBPRPModel
        Field[] fieldlist = s.getDeclaredFields();
        System.out.println("insert into "+s.toString()+"(");
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            System.out.println(add_Fun(fld.getName())+(i<fieldlist.length-1?",":")"));
        }
        System.out.println(" values (");
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            System.out.println("#{"+fld.getName()+"}"+(i<fieldlist.length-1?",":")"));
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");  
	}
	
	public static void testUpdate(Class s){
		Field[] fieldlist = s.getDeclaredFields();
		for (int i = 1; i < fieldlist.length; i++) {
           Field fld = fieldlist[i];
           String s1="<if test=\"";
           System.out.print(s1);
           System.out.print(fld.getName());
           System.out.print(" != null and ");
           System.out.print(add_Fun(fld.getName()));
           System.out.println(" != ''\"> ");
          
           System.out.print(fld.getName());
           System.out.print("=#{");       
           System.out.println(fld.getName()+"} "+(i<fieldlist.length-1?",":""));
           System.out.println("</if>");  
       }
	}
	public static String getKeyId(){
		UUID uuid = UUID.randomUUID();
		return DateFormatter.long2YYYYMMDDHHmmss(new Date())+uuid.toString();
	}
	
	public static String getNowMTime(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
	}
	
	public static String getKeyId(String prix){
		UUID uuid = UUID.randomUUID();
		return (prix+getKeyId()).substring(0, 50);
		//(KEY_PRIFF+Common.getKeyId()).substring(0, 50)
	}
	public static List   filterRepeat(List list,String proprer){
		for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
		     for ( int j = list.size() - 1 ; j > i; j -- ) {
		       if (list.get(j).equals(list.get(i))) {
		         list.remove(j);
		       }
		      }
		    }
        return list;
	}
	public static String[] splitStr(String str){
		if(str==null){
			return new String[0];
		}
		List<String> list=new ArrayList<String>();
		while(str.indexOf(",")>=0){
			int index=str.indexOf(",");
			list.add(str.substring(0, index));
			str=str.substring(index+1);
		}
		list.add(str);
		String[] array = (String[])list.toArray(new String[list.size()]);
		return array;
	}
	

	public static List<String> splitStrTolist(String str){
		if(str==null){
			return new ArrayList<String>();
		}
		List<String> list=new ArrayList<String>();
		while(str.indexOf(",")>=0){
			int index=str.indexOf(",");
			list.add(str.substring(0, index));
			str=str.substring(index+1);
		}
		list.add(str);
		return list;
	}	

	//获取properties属性值
	 public static String getProperty(String path, String key){
		 Properties prop = new Properties();   
	     InputStream in = Object.class.getResourceAsStream(path);
	     try {
			prop.load(in);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	 }
	public static String getMapKeyValue(Map map,String key){
			if(map==null||map.get(key)==null){
				return "";
			}
			else{
				return map.get(key).toString();
			}
	}
	
	/**
	 * 判断变量是否为""
	 * 
	 **/
	
	public static String isVacancy(String str){
		if(str!=null && str.trim().length() != 0){
			return str;
		}
		return null;
	}

	/**
	 * 判断变量的类型
	 *
	 **/
	
	public static String variableType(String dataType){
		String hql = "";
		if(dataType == null){
			return null;
		}
		
		if(dataType.equals("0")){
			hql = "from Analyzerule anal where anal.isJson = 'true' and anal.isApplicationJson = 'true' and anal.flightType = :flightType and anal.behaviorType = :behaviorType";
		}else if(dataType.equals("1")){
			hql = "from Analyzerule anal where anal.isJson = 'true' and anal.isApplicationJson = 'false' and anal.flightType = :flightType and anal.behaviorType = :behaviorType";
		}else if(dataType.equals("2")){
			hql = "from Analyzerule anal where anal.isXml = 'true' and anal.isTextXml = 'true' and anal.flightType = :flightType and anal.behaviorType = :behaviorType";
		}else if(dataType.equals("3")){
			hql = "from Analyzerule anal where anal.isXml = 'true' and anal.isNormalForm = 'true' and anal.flightType = :flightType and anal.behaviorType = :behaviorType";
		}else if(dataType.equals("4")){
			hql = "from Analyzerule anal where anal.isXml = 'false' and anal.isJson = 'false' and anal.isNormalForm = 'true' and anal.flightType = :flightType and anal.behaviorType = :behaviorType";
		}else if(dataType.equals("5")){
			hql = "from Analyzerule anal where anal.isNormalGet = 'true' and anal.flightType = :flightType and anal.behaviorType = :behaviorType";
		}
		return hql;
	}
	
}
