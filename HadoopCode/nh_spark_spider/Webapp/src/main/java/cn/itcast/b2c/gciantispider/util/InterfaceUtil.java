package cn.itcast.b2c.gciantispider.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * http restful 接口调用工具类
 * 
 * Title: kingsso <br>
 * Description: <br>
 * Copyright: Kingpintcn Information Technology Co.,Ltd
 * 
 * @version 1.0 <br>
 * @creatdate 2016年12月28日 下午3:39:36 <br>
 *
 */
public class InterfaceUtil {
    /**
     * 功能：使用 HttpURLConnection 方式调用接口
     * @param pathUrl：Url地址
     * @param encoding：编码方式，如 UTF-8
     * @param method：请求方式，如GET、POST、DELETE、PUT
     * @return
     */
    public static String inkoveInterface(String pathUrl, String encoding, String method ,String responseCookie){
        String result = null;
        try {
            URL url = new URL(pathUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(method);
            if (null!=responseCookie && !"".equals(responseCookie)) {
                conn.setRequestProperty("Cookie", "JSESSIONID=" + responseCookie);      // session id
            }
            //设置是否从httpUrlConnection读入，默认情况下是true;   
            conn.setDoInput(true);
            //设置是否从httpUrlConnection读出
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            //设置编码
            conn.setRequestProperty("Content-Type", "text/plain; charset=" + encoding);
            //设置超时
            conn.setConnectTimeout(10*1000);
            
            System.out.println("输出结果：" + conn.getResponseCode()); 
            
            //响应代码 200表示成功
            if ( 200 == conn.getResponseCode()) {
                InputStream inStream = conn.getInputStream();   
                result = StreamTools.readInputStream(inStream);
            }else {
                //响应异常，打印出错误信息
                System.out.println("响应异常，响应码为" + conn.getResponseCode());
            }
        }
        catch (Exception e) {    
            e.printStackTrace();
        }
        
        return result;
    }
    
}
