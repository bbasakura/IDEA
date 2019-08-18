package com.itheima.log_monitor.utils.message;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class ShortMessageUtil {
	private static final long serialVersionUID = 6391455829450707212L;
    private static String smsServiceUrl = "http://v.juhe.cn/sms/send?";
    private static String tokenKey = "6b87459b2b2594d078a1808cc830fc48";

	public  static void  sendShortMessage(String phoneNum, String content) throws Exception{
		//第一步：创建一个默认的http请求
		CloseableHttpClient client = HttpClients.createDefault();
		 StringBuilder sb = new StringBuilder();
         sb.append(smsServiceUrl);
         //以下是参数
         sb.append("mobile=").append(phoneNum)
                 .append("&tpl_id=").append(17979)
                 .append("&tpl_value=").append(URLEncoder.encode(content, "utf-8"))
                 .append("&key=").append(tokenKey);
         String urlStr = sb.toString();
		HttpGet get = new HttpGet(urlStr);
		//第二步：通过httpClient来执行我们请求，并获取响应数据
		CloseableHttpResponse response = client.execute(get);
		//第三步：将我们响应的数据输出
		int statusCode = response.getStatusLine().getStatusCode();
		/**
		 * 请求状态的响应码说明
		 * 2 开头都是响应成功
		 * 3 开头都是重定向
		 * 4 开头都是客户端错误
		 * 5 开头都是服务器端错误
		 */
		System.out.println("请求状态的响应码为"+statusCode);
		if(statusCode == 200){
			HttpEntity entity = response.getEntity();
			String string =EntityUtils.toString(entity, Charset.forName("UTF-8"));
			System.out.println(string);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String line = "#appname#=航母补漏项目&#rid#=金额一百万&#keyword#=专业补漏三十年";
		sendShortMessage("17600600238",line);
	}
	
	
}
