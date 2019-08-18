package com.itheima.Jsoup;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import sun.java2d.cmm.ProfileDeferralInfo;

import java.io.File;
import java.io.IOException;

public class JsoupDocument {

    /**
     * 获取document对象的方式一：connect(url).get()    一般用于测试
     * 真正代码： 使用httpclient发送请求，获取html页面，使用jsoup转换成document对象
     */

    @Test
    public void document01() throws IOException {

        String url = "http://www.itcast.cn";

        Document document = Jsoup.connect(url).get();

        String title = document.title();

        System.out.println("title = " + title);
    }


    @Test

    public void document02() throws IOException {
        //确定url
        String url = "http://www.itcast.cn";

//        发送请求，获取html页面
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);

        String html = EntityUtils.toString(response.getEntity(), "utf-8");

//        转换成document对象

        Document document = Jsoup.parse(html);

        String title = document.title();

        System.out.println("title1 = " + title);
    }


    /*
     * Jsoup 还可以获取本地的html页面，转换成document对象（了解）
     *
     * */

    @Test
    public void document03() throws IOException {
//        Document html = Jsoup.parse(new File(""),"utf-8");
    }
    /*
     * 指定一个html片段，转换成document对象
     * */

    @Test

    public void document04() {
        String html = "<a>获取document的四种方式</a>";

        Document document = Jsoup.parseBodyFragment(html);
    }



}
