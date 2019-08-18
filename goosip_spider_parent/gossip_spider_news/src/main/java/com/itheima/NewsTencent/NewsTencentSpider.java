package com.itheima.NewsTencent;


import com.google.gson.Gson;
import com.itheima.News163.News;
import com.itheima.News163.NewsDao;
import com.itheima.utils.HttpClientUtils;
import com.itheima.utils.IdWorker;
import com.itheima.utils.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsTencentSpider {

    /**
     * json转换的对象
     */
    public static  final Gson gson = new Gson();


    /**
     * id生成器:  两个参数: 0  0
     */
    public static  final IdWorker idworker = new IdWorker(0,1);


    /**
     * 创建dao对象
     */
    public static  final NewsDao newsDao = new NewsDao();


    public static void main(String[] args) throws IOException {

        //1. 确定url: 热点url(一页)  非热点url
        String  hotUrl = "https://pacaio.match.qq.com/irs/rcd?cid=137&token=d0f13d594edfc180f5bf6b845456f3ea&id=&ext=ent&num=60";

        String noHotUrl = "https://pacaio.match.qq.com/irs/rcd?cid=58&token=c232b098ee7611faeffc46409e836360&ext=ent&page=0";

        pageTencent(hotUrl,noHotUrl);


    }

    /**
     * 分页爬取腾讯娱乐新闻的方法:   热点数据没有分页数据    非热点数据有分页
     */
    public static void pageTencent(String hotUrl, String noHotUrl) throws IOException {

        //一 热点数据
        String hotJson = HttpClientUtils.doGet(hotUrl);
        List<News> hotNewsList = parseNewsTencent(hotJson);
        saveNewsListToDb(hotNewsList);

        //二 非热点数据
        int i = 1;
        while (true) {
            //2. 使用httpClientUtils工具发送http请求,获取数据(json数据)
            String noHotJson = HttpClientUtils.doGet(noHotUrl);

            //3. 解析json数据
            List<News> nohotNewsList = parseNewsTencent(noHotJson);

            if(nohotNewsList == null || nohotNewsList.size() == 0){
                System.out.println("爬完了.......");
                break;
            }

            //4. 将新闻列表保存到数据库中
            saveNewsListToDb(nohotNewsList);


            //5. 获取下一页的url,更新noHotUrl
            noHotUrl = "https://pacaio.match.qq.com/irs/rcd?cid=58&token=c232b098ee7611faeffc46409e836360&ext=ent&page=" + i;
            i++;
        }


    }

    /**
     * 将新闻数据保存到mysql数据库中
     * @param newsList
     */
    private static void saveNewsListToDb(List<News> newsList) {

        for (News news : newsList) {
            newsDao.saveNews(news);

            //将当前新闻的url保存到redis的set集合中
            Jedis jedis = JedisUtils.getJedis();
            jedis.sadd(SpiderConstant.SPIDER_NEWS_TENCENT,news.getUrl());
            jedis.close();
            System.out.println(news);
        }
    }

    /**
     * 根据新闻的json数据,解析成一个新闻的列表
     * @param newsJson
     * @return
     */
    private static List<News> parseNewsTencent(String newsJson) {
        List<News> newsList = new ArrayList<News>();

        //判断是否为空
        if(StringUtils.isEmpty(newsJson)){
            return newsList;
        }

        //1. 进行json转换    string    ------ >  Map<String,Object>
        Map map = gson.fromJson(newsJson, Map.class);

        //2. 从map中获取data新闻数据
        List<Map<String,Object>> data = (List<Map<String, Object>>) map.get("data");
        if(data == null || data.size() == 0){
            return newsList;
        }

        //3. 遍历data数据(新闻数据列表)
        for (Map<String, Object> newsMap : data) {
            //注意: 这个对象放到for循环里面
            News news = new News();
            //获取url地址
            String url = (String) newsMap.get("url");
            //过滤条件:  判断当前url是否已经爬取过
            if(hasParsed(url)){
                //已经爬取过的url,不要重复爬取
                continue;
            }

            System.out.println(url);

            String title = (String) newsMap.get("title");
            String time = (String) newsMap.get("update_time");
            String source = (String) newsMap.get("source");
            String content = (String) newsMap.get("intro");

            news.setId(idworker.nextId() + "");
            news.setUrl(url);
            news.setTitle(title);
            news.setTime(time);
            news.setSource(source);
            news.setEditor(source);
            news.setContent(content);
            //将对象添加到列表中
            newsList.add(news);
        }

        return newsList;

    }

    /**
     * 判断当前给的url是否已经爬取过
     * @param url
     * @return
     */
    private static boolean hasParsed(String url) {
        Jedis jedis = JedisUtils.getJedis();

        //判断给定的url是否是集合中的元素
        Boolean sismember = jedis.sismember(SpiderConstant.SPIDER_NEWS_TENCENT, url);
        jedis.close();
        return sismember;
    }

}
