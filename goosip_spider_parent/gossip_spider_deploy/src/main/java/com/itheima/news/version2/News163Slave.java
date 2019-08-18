package com.itheima.news.version2;

import com.google.gson.Gson;
import com.itheima.news.pojo.News;
import com.itheima.news.pojo.SpiderConstant;
import com.itheima.news.utils.HttpClientUtils;
import com.itheima.news.utils.IdWorker;
import com.itheima.news.utils.JedisUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;

/**
 * @author itheima
 * @Title: News163Slave
 * @ProjectName goosip_spider_parent
 * @Description: 获取list队列中的url, 然后发送请求,获取数据,解析成一个News对象,将News对象保存到redis的list队列中(bigData:spider:newsJsonList)
 * @date 2019/7/110:25
 */
public class News163Slave {

    /**
     * id生成器:  两个参数: 0  0    1,0
     */
    public static  final IdWorker idworker = new IdWorker(1,0);


    /**
     * json转换的对象
     */
    public static  final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {

        while (true){
            //1.  获取url
            Jedis jedis = JedisUtils.getJedis();
            //带阻塞的弹出方法: 如果没有数据, 阻塞20秒, 如果20秒之内有新数据,继续执行,
//            String url = jedis.rpop(SpiderConstant.SPIDER_NEWS_URLLIST);
            //返回值:两个元素  下标为0   key    下标为1   url值
            List<String> list = jedis.brpop(20, SpiderConstant.SPIDER_NEWS_URLLIST);

            if(list == null  || list.size() == 0){
                break;
            }
            //如果没有跳出循环,需要获取url,下标为1
            String url = list.get(1);

            jedis.close();

            //2.调用parseNewsItem, 获取解析后的News对象
            News news = parseNewsItem(url);
            String newsJson = gson.toJson(news);


            //3. 将News对象保存到redis的list队列中(bigData:spider:newsJsonList)
            jedis = JedisUtils.getJedis();
            jedis.lpush(SpiderConstant.SPIDER_NEWS_NEWJSONLIST, newsJson);
            jedis.close();
        }


    }




    /**
     * 根据当前新闻的url地址,发送请求,获取html页面,  解析html页面,  封装成javabean
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
        String time = sourceString.substring(0, 19);
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
        news.setId(id+"");
        news.setUrl(docurl);
        news.setTime(time);
        news.setContent(content);
        news.setEditor(split[1]);
        news.setSource(source);
        news.setTitle(title);
        System.out.println(news);

        return news;

    }


}
