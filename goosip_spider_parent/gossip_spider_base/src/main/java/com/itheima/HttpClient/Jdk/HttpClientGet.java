package com.itheima.HttpClient.Jdk;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientGet {
    public static void main(String[] args) throws IOException {

//  目前为止，大部分的网站用HTTPGet请求可以成功，但是用HTTpPOst请求不会返回结果
//      1确定目标
        String indexurl = "http://www.4399.com/";
//      2.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
//      3.创建HTTpGEt请求对象】
        HttpGet httpGet = new HttpGet(indexurl);
//      4.设置请求头
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
//      5.httpclient 执行 Httpget请求，得到响应对像，request
        CloseableHttpResponse response = httpClient.execute(httpGet);
//      6.判断状态码是否正常
        int statusCode = response.getStatusLine().getStatusCode();
//      7.如果正常的话，获取响应头的keys 和 values
        if (statusCode == 200) {
            Header[] allHeaders = response.getAllHeaders();

            for (Header allHeader : allHeaders) {
                System.out.println(allHeader.getName() + allHeader.getValue());
            }
//      8.response.getEntity得到entity,里面是的请求返回的响应体内容
            HttpEntity entity = response.getEntity();
//      9.通过EntityUtils工具类，将得到的响应内容，装换成字符串，
            String html = EntityUtils.toString(entity, "utf-8");

            System.out.println("html = " + html);
//      10.释放资源
            httpClient.close();


        }


    }


}