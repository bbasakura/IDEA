package com.itheima.HttpClient.Jdk;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientPost {

    public static void main(String[] args) throws IOException {
        String indexurl = "https://www.qidian.com/";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(indexurl);

        //post请求头和请求参数怎么传递
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

        //将表单数据封装到请求体中
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("username","itcast"));
        list.add(new BasicNameValuePair("password","www.itcast.cn"));
        list.add(new BasicNameValuePair("age","12"));
        list.add(new BasicNameValuePair("bithday","xxx"));

        HttpEntity formEntity = new UrlEncodedFormEntity(list);

        httpPost.setEntity(formEntity);


        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode==200){

            Header[] allHeaders = response.getAllHeaders();
            for (Header allHeader : allHeaders) {

                System.out.println(allHeader.getName() + allHeader.getValue());

            }
            HttpEntity entity = response.getEntity();


            String s = EntityUtils.toString(entity, "utf-8");

            System.out.println("s = " + s);


//            關閉資源
            httpClient.close();








        }

    }

}

