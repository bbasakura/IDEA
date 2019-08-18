package com.itheima.ManmamBuy;

import org.apache.http.Header;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManmamBuy {

    public static void main(String[] args) throws IOException {
        //    确定目标（登陆的url和跳转的url）

        String loginurl = "http://home.manmanbuy.com/login.aspx?tourl=http%3a%2f%2fhome.manmanbuy.com%2fUserCenter.aspx";

//        String  loginurl="http://home.manmanbuy.com/login.aspx?tourl=http%3a%2f%2fwww.manmanbuy.com%2fdefault.aspx";


        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(loginurl);


        //2.3 设置请求头(注意是请求头)：user-agent  Referer(具体要求什么参数，是摸索出来的，不确定)

        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

        httpPost.setHeader("Referer", "http://home.manmanbuy.com/login.aspx?tourl=http%3a%2f%2fwww.manmanbuy.com%2fdefault.aspx");

        //2.4  封装请求参数：登录表单数据
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();

        list.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwULLTIwNjQ3Mzk2NDFkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQlhdXRvTG9naW4voj01ABewCkGpFHsMsZvOn9mEZg=="));

        list.add(new BasicNameValuePair("__EVENTVALIDATION", "/wEWBQLW+t7HAwLB2tiHDgLKw6LdBQKWuuO2AgKC3IeGDJ4BlQgowBQGYQvtxzS54yrOdnbC"));
        list.add(new BasicNameValuePair("txtUser", "itcast"));
        list.add(new BasicNameValuePair("txtPass", "www.itcast.cn"));
        list.add(new BasicNameValuePair("www.itcast.cn", "itcast"));
        list.add(new BasicNameValuePair("btnLogin", "登陆"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);

        httpPost.setEntity(entity);


//    发送请求

        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 302) {

            //3. 重定向到location这个url上面去，携带cookie，获取html页面

            //获取location响应头

            Header[] locations = response.getHeaders("Location");

            String locationUrl = locations[0].getValue();
            System.out.println("locationUrl = " + locationUrl);

            //获取cookie响应头

            Header[] cookies = response.getHeaders("Set-Cookie");
//            System.out.println( cookies.length);

            HttpGet httpGet = new HttpGet(locationUrl);

            httpGet.setHeaders(cookies);


            CloseableHttpResponse response1 = httpClient.execute(httpGet);

            String html = EntityUtils.toString(response1.getEntity(), "utf-8");

            Document document = Jsoup.parse(html);

//            解析页面，获取积分（兜底 的选择器是浏览器生成的）

            Elements select = document.select("#aspnetForm > div.udivright > div:nth-child(2) > table > tbody > tr > td:nth-child(1) > table:nth-child(2) > tbody > tr > td:nth-child(2) > div:nth-child(1) > font");

            String jifen = select.text();

            System.out.println("jifen = " + jifen);
        }


        httpClient.close();


    }


}
