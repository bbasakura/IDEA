package com.itheima.news.news163;

import com.google.gson.Gson;
import com.itheima.news.dao.NewsDao;
import com.itheima.news.pojo.News;
import com.itheima.news.pojo.SpiderConstant;
import com.itheima.news.utils.HttpClientUtils;
import com.itheima.news.utils.IdWorker;
import com.itheima.news.utils.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author itheima
 * @Title: News163Spider
 * @ProjectName goosip_spider_parent
 * @Description: 163娱乐新闻的爬虫
 * @date 2019/6/2911:25
 */
public class News163Spider {

    /**
     * json转换的对象
     */
    public static  final Gson gson = new Gson();


    /**
     * id生成器:  两个参数: 0  0
     */
    public static  final IdWorker idworker = new IdWorker(0,0);


    /**
     * 创建dao对象
     */
    public static  final NewsDao newsDao = new NewsDao();


    public static void main(String[] args) throws IOException {

        //1. 确定爬取的url地址
        String url = "https://ent.163.com/special/000380VU/newsdata_index.js";


        //2.使用httpClientUtils发送请求,获取响应数据(url列表)
//        String jsonString = HttpClientUtils.doGet(url);


        //3. 解析json数据
//        parseNewsJson(jsonString);
        page163(url);

    }


    /**
     * 分页爬取的方法
     * @param url
     */
    private static void page163(String  url) throws IOException {

        int i = 2;

        while(true){
            //2.使用httpClientUtils发送请求,获取响应数据(url列表)
            String jsonString = HttpClientUtils.doGet(url);


            //跳出循环的逻辑
            if(StringUtils.isEmpty(jsonString)){
                System.out.println("爬完了......");
                break;
            }


            //3. 解析json数据
            parseNewsJson(jsonString);


            //4. 构造下一页的url地址,赋值给url
            String pageString = "";
            if(i < 10){
                pageString = "0" + i;
            }else{
                pageString = i +"";
            }

            i++;
            url  = "https://ent.163.com/special/000380VU/newsdata_index_" + pageString + ".js";

        }
    }





    /**
     * 解析json数据(新闻的url列表)
     * @param json
     */
    private static void  parseNewsJson(String json) throws IOException {

        //1. 处理json数据,处理成格式良好的json数据
        json = formatJson(json);


        //2. 将json字符串转换成   List<Map<String,Object>>
        List<Map<String,Object>> list = gson.fromJson(json, List.class);

        //3. 遍历list(新闻的url列表)
        for (Map<String, Object> map : list) {
            String docurl = (String) map.get("docurl");

            //图集的url,需要过滤掉
            if(docurl.contains("photoview")){
                continue;
            }

            //不符合这个规则:ent.163.com
            if(!docurl.contains("ent.163.com")){
                continue;
            }

            //判断当前url是否已经爬取过
            if(hasParsed(docurl)){
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

            jedis.sadd(SpiderConstant.SPIDER_NEWS163,docurl);

            //用完jedis的链接,必须关闭连接
            jedis.close();

        }


    }


    /**
     * 判断给的url是否已经爬取过
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


    /**
     * 处理json数据成一个格式正确的json字符串
     * @param json  返回
     * @return
     */
    private static  String  formatJson(String json){
        int beginIndex = json.indexOf("[");
        int endIndex = json.lastIndexOf(")");

        String substring = json.substring(beginIndex, endIndex);

        return substring;
    }

}
