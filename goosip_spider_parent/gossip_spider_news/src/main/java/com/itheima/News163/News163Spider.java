package com.itheima.News163;

import com.google.gson.Gson;
import com.itheima.utils.HttpClientUtils;
import com.itheima.utils.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.itheima.utils.IdWorker;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class News163Spider {

    /* *//**
     * json转换的对象
     *//*
    public static  final Gson gson = new Gson();


    *//**
     * id生成器:  两个参数: 0  0
     *//*
    public static  final IdWorker idworker = new IdWorker(0,0);


    *//**
     * 创建dao对象
     *//*
    public static  final NewsDao newsDao = new NewsDao();


    public static void main(String[] args) throws IOException {

        String url = "https://ent.163.com/special/000380VU/newsdata_index_02.js";


        //2. 调用httpClient工具，获取新闻列表json数据
        String jsonNews = HttpClientUtils.doGet(url);

        //3.解析新闻数据

        parseJsonNews(jsonNews);


    }

    private static void parseJsonNews(String jsonNews) throws IOException {

        //1. 处理json字符串，转换成格式良好的json数组

        jsonNews = getJsonString(jsonNews);

        Gson gson = new Gson();

        List<Map<String, Object>> newsList = gson.fromJson(jsonNews, List.class);

        for (Map<String, Object> newsString : newsList) {
            String docurl = (String) newsString.get("docurl");

//          過濾其他的視頻，圖片

            if (docurl.contains("photoview")) {
                continue;
            }
            if (!docurl.contains("ent.163.com")) {
                continue;
            }
            System.out.println("新闻的url：" + docurl);

           parseItemNews(docurl);
        }
    }


    *//**
     *
     * 解析新闻列表中的一条新闻的方法
     * @param docurl 一條新聞的url鏈接
     *//*
    private static News parseItemNews(String docurl) throws IOException {
        News news = new News();

        //2. 获取每一条新闻数据的html页面
        String newsHtml = HttpClientUtils.doGet(docurl);

        //3. 转换成document对象
        Document document = Jsoup.parse(newsHtml);

        //4.解析document对象，--------- >News对象： 唯一标识id    标题  来源   时间   编辑    内容   ur
        //标题
        String title = document.select("#epContentLeft h1").text();

        //时间和来源
        String timeAndSource = document.select(".post_time_source").text();

        String[] strings = timeAndSource.split("　");

//        System.out.println(strings[0] + strings[1]);
        String time = strings[0];
        Elements sourceEl = document.select("#ne_article_source");
        String source = sourceEl.text();
        //内容
        String content = document.select("#endText p").text();
//        System.out.println(content);

        //编辑
        String text = document.select(".ep-editor").text();
        String[] editors = text.split("：");
        String editor = editors[1];
//        System.out.println(editor);

        //id
        news.setId(idworker.nextId() + "");
        news.setUrl(docurl);
        news.setEditor(editor);
        news.setContent(content);
        news.setTime(time);
        news.setTitle(title);
        news.setSource(source);

        return news;
    }



    *//**
     * 处理json数据的方法
     * @param jsonNews
     * @return 处理完格式良好的json字符串
     *//*
    private static String getJsonString(String jsonNews) {

        int startIndex = jsonNews.indexOf("(");
        int lastIndex = jsonNews.lastIndexOf(")");
        jsonNews = jsonNews.substring(startIndex + 1, lastIndex);
        return jsonNews;
    }*/

    /**
     * json转换的对象
     */
    public static final Gson gson = new Gson();
    /**
     * id生成器:  两个参数: 0  0
     */
    public static final IdWorker idworker = new IdWorker(0, 0);


    /**
     * 创建dao对象
     */
    public static final NewsDao newsDao = new NewsDao();


    public static void main(String[] args) throws IOException {

        //1. 确定爬取的url地址
        String url = "https://ent.163.com/special/000380VU/newsdata_index.js";


        //2.使用Utils发送请求,获取响应数据(url列表)httpClient
//        String jsonString = HttpClientUtils.doGet(url);


        //3. 解析json数据
//        parseNewsJson(jsonString);
        page163(url);

    }


    /**
     * 分页爬取的方法
     *
     * @param url
     */
    private static void page163(String url) throws IOException {

        int i = 2;

        while (true) {
            //2.使用httpClientUtils发送请求,获取响应数据(url列表)
            String jsonString = HttpClientUtils.doGet(url);


            //跳出循环的逻辑
            if (StringUtils.isEmpty(jsonString)) {
                System.out.println("爬完了......");
                break;
            }


            //3. 解析json数据
            parseNewsJson(jsonString);


            //4. 构造下一页的url地址,赋值给url
            String pageString = "";
            if (i < 10) {
                pageString = "0" + i;
            } else {
                pageString = i + "";
            }

            i++;
            url = "https://ent.163.com/special/000380VU/newsdata_index_" + pageString + ".js";

        }
    }


    /**
     * 解析json数据(新闻的url列表)
     *
     * @param json
     */
    private static void parseNewsJson(String json) throws IOException {

        //1. 处理json数据,处理成格式良好的json数据
        json = formatJson(json);


        //2. 将json字符串转换成   List<Map<String,Object>>
        List<Map<String, Object>> list = gson.fromJson(json, List.class);

        //3. 遍历list(新闻的url列表)
        for (Map<String, Object> map : list) {
            String docurl = (String) map.get("docurl");

            //图集的url,需要过滤掉
            if (docurl.contains("photoview")) {
                continue;
            }

            //不符合这个规则:ent.163.com
            if (!docurl.contains("ent.163.com")) {
                continue;
            }

            //判断当前url是否已经爬取过
            if (hasParsed(docurl)) {
                continue;
            }


            String title = (String) map.get("title");
            System.out.println(docurl);


            //4 解析每一条新闻数据
            News news = parseNewsItem(docurl);


            //5 保存到数据库中
            newsDao.saveNews(news);


            //6  将爬取后的url保存到redis的set集合中, 防止下次重复爬取
            Jedis jedis = JedisUtils.getJedis();

            jedis.sadd(SpiderConstant.SPIDER_NEWS163, docurl);

            //用完jedis的链接,必须关闭连接
            jedis.close();

        }


    }


    /**
     * 判断给的url是否已经爬取过
     *
     * @param docurl
     * @return
     */
    private static boolean hasParsed(String docurl) {

        Jedis jedis = JedisUtils.getJedis();

        Boolean sismember = jedis.sismember(SpiderConstant.SPIDER_NEWS163, docurl);

        jedis.close();

        return sismember;
    }

    /**
     * 根据当前新闻的url地址,发送请求,获取html页面,  解析html页面,  封装成javabean
     *
     * @param docurl
     */
    private static News parseNewsItem(String docurl) throws IOException {
        //1. 根据url地址发送http请求,获取html页面
        String html = HttpClientUtils.doGet(docurl);


        //2. 根据html页面,转换成document对象
        Document document = Jsoup.parse(html);


        //3. 解析document对象  :  标题   内容   时间    来源   编辑    新闻的url
        String title = document.select("#epContentLeft h1").text();
//        System.out.println(title);
        String content = document.select("#endText p").text();
//        System.out.println(content);

        String sourceString = document.select(".post_time_source").text();
        //2019-06-28 07:19:10　来源: 网易娱乐 举报
        String time = sourceString.substring(0,19);
//        System.out.println(time);

        String source = document.select("#ne_article_source").text();
//        System.out.println(source);

        String editorString = document.select(".ep-editor").text();
        String[] split = editorString.split("：");
//        System.out.println(split[1]);


        //4. 封装成javabean对象返回:  News

        News news = new News();
        //id 使用分布式id生成器
        long id = idworker.nextId();
        news.setId(id + "");
        news.setUrl(docurl);
        news.setTime(time);
        news.setContent(content);
        news.setEditor(split[1]);
        news.setSource(source);
        news.setTitle(title);
        System.out.println(news);

        return news;

    }


    /**
     * 处理json数据成一个格式正确的json字符串
     *
     * @param json 返回
     * @return
     */
    private static String formatJson(String json) {
        int beginIndex = json.indexOf("[");
        int endIndex = json.lastIndexOf(")");

        String substring = json.substring(beginIndex, endIndex);

        return substring;
    }

}

