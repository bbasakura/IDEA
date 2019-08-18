package com.itheima.Jsoup;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class JsoupParseDocument {


    //    使用原生JavaScript类似的getElement的方法解析数据
    @Test
    public void parse01() throws IOException {

        String url = "http://www.itcast.cn";

        Document document = getDocument(url);

        Elements nav_txt = document.getElementsByClass("nav_txt");

        Element div = nav_txt.get(0);

        Elements elements = div.getElementsByTag("a");

        for (Element a : elements) {

            System.out.println(a.text());

            System.out.println(a.attr("href"));
        }
    }

    @Test
    public void parse02() throws IOException {
        String url = "http://www.itcast.cn";

        Document document = getDocument(url);

        Elements alist = document.select(".nav_txt a");

        for (Element a : alist) {

            System.out.println(a.text()+" : "+ a.attr("href"));
        }


    }

    private Document getDocument(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);

        String html = EntityUtils.toString(response.getEntity(), "utf-8");

        Document document = Jsoup.parse(html);

        return document;
    }


}
