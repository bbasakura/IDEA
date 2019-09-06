package com.bigdata.itcast.weblog.util;

import com.bigdata.itcast.weblog.writable.WebLogBean;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * 在Map方法中进行调用，对每条数据进行解析，处理，返回WebLogBean对象
 * 194.237.142.21 									ip				0
 - 												默认横线
 - 												userid			2
 [18/Sep/2013:06:49:18 							time			3
 +0000] 											时区
 "GET 											请求方式
 /wp-content/uploads/2013/07/rstudio-git3.png 	url				6
 HTTP/1.1" 										请求协议
 304 											状态			8
 0 												字节大小		9
 "-" 											来源页面		10
 "Mozilla/4.0 (compatible;)"						客户端			11
 * Created by Frank on 2019/8/9.
 */
public class WebLogParseUtil {

    private SimpleDateFormat input = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    private SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public WebLogBean logParseHandle(String line){
        //实例化一个对象
        WebLogBean logInfo = new WebLogBean();
        //给对象赋值
        String[] items = line.split(" ");
        if(items.length > 11){
            //赋值
            if(StringUtils.isNotBlank(items[0])){
                logInfo.setIp(items[0]);
            }else
                logInfo.setIsvalid(false);
            logInfo.setUser_id(items[2]);
            //获取时间并转换
            String s_time = dateFormat(items[3].substring(1));
            //判断时间是否合法
            if(null == s_time){
                logInfo.setIsvalid(false);
            }
            logInfo.setS_time(s_time);
            logInfo.setUrl(items[6]);
            logInfo.setStatus(items[8]);
            logInfo.setBody_size(items[9]);
            logInfo.setHttp_ref(items[10]);
            //将客户端字段进行拼接，如果长度大于12，就代表浏览器字段有多个，就拼接
            if(items.length > 12){
                StringBuilder sb = new StringBuilder();
                for(int i = 11;i<items.length;i++){
                    sb.append(items[i]);
                }
                logInfo.setUser_agent(sb.toString());
            }else{
                logInfo.setUser_agent(items[11]);
            }
            //过滤所有状态大于400的
            if(Integer.parseInt(logInfo.getStatus()) >= 400){
                logInfo.setIsvalid(false);
            }

        }else{
            //长度小于等于11,不合法
            logInfo.setIsvalid(false);
        }
        return logInfo;
    }

    /**
     * 将日期进行转换
     * @param inputTime
     * @return
     */
    public String dateFormat(String inputTime){
        String outTime = null;
        try {
            Date parse = input.parse(inputTime);
            outTime = output.format(parse);
        } catch (ParseException e) {
            return null;
        }
        return outTime;
    }

    public void filterStaticPage(WebLogBean outputValue, Set<String> pages) {
        //判断当前访问的页面是不是静态页面，如果是就设置为false
        if(!pages.contains(outputValue.getUrl())){
            outputValue.setIsvalid(false);
        }
    }
}
