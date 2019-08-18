package com.itheima.qiDian;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Qidian {

    public static void main(String[] args) throws IOException {
//    确定目标：https://read.qidian.com/chapter/9qmB8Nn0Ce5hO--gcH8iFg2/qAjgTfX6i7m2uJcMpdsVgA2
        String indexurl = "https://read.qidian.com/chapter/9qmB8Nn0Ce5hO--gcH8iFg2/qAjgTfX6i7m2uJcMpdsVgA2";


        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("d:/诸天一页.txt"))));



        while (true) {
            //3.将html页面转换成document对象
            Document document = getDocument(indexurl);

            //4. 解析document对象，获取想要的数据(小说的，书名 章节名称，内容)

//            书名
            Elements bookname = document.select(".book-photo h1");

            bufferedWriter.write("bookname");
            bufferedWriter.newLine();

//          章节名
            String chaptername = document.select(".j_chapterName").text();

            System.out.println(chaptername);
            bufferedWriter.write(chaptername);
            bufferedWriter.newLine();

//           正文内容
            Elements content = document.select("[class=read-content j_readContent] p");

            for (Element p : content) {
                System.out.println(p.text());
                bufferedWriter.write(p.text());
                bufferedWriter.newLine();
            }
            indexurl = document.select("#j_chapterNext").attr("href");

            indexurl = "http:" + indexurl;

            if (indexurl.contains("lastpage")) {
                break;
            }

            System.out.println(indexurl);
        }
        bufferedWriter.close();
    }

    /**
     * 根据url获取document对象
     *
     * @param url
     * @return
     */
    private static Document getDocument(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");

        CloseableHttpResponse response = httpClient.execute(httpGet);


        String html = EntityUtils.toString(response.getEntity(), "utf-8");

        Document document = Jsoup.parse(html);

        return document;
    }

}
