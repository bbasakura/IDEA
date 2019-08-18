package com.itheima.news.version2;

import com.google.gson.Gson;
import com.itheima.news.dao.NewsDao;
import com.itheima.news.pojo.News;
import com.itheima.news.pojo.SpiderConstant;
import com.itheima.news.utils.JedisUtils;
import kafka.KafkaSpiderProducer;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author itheima
 * @Title: PublicDaoNode
 * @ProjectName goosip_spider_parent
 * @Description: 读取list队列中的news对象, (添加一块健壮性逻辑: 判断这个url是否已经爬过了)保存到mysql数据库中,然后将新闻的url保存到redis的set集合中
 * @date 2019/7/110:25
 */
public class PublicDaoNode {


    //创建生产者对象
    private static KafkaSpiderProducer kafkaSpiderProducer = new KafkaSpiderProducer();


    /**
     * json转换的对象
     */
    public static  final Gson gson = new Gson();


    /**
     * 创建dao对象
     */
    public static  final NewsDao newsDao = new NewsDao();

    public static void main(String[] args) {


        while (true){

            //1.读取list队列中的news对象
            Jedis jedis = JedisUtils.getJedis();
            List<String> list = jedis.brpop(20, SpiderConstant.SPIDER_NEWS_NEWJSONLIST);
            jedis.close();
            if(list == null || list.size() == 0){
                break;
            }
            String newsJson = list.get(1);
            News news = gson.fromJson(newsJson, News.class);


            //2. 添加一块健壮性逻辑: 判断这个url是否已经爬过了
            if(hasParsed(news.getUrl())){
                continue;
            }

            //3. 保存News对象到mysql数据库中
            newsDao.saveNews(news);



            //保存到数据库的同时,将新闻数据保存到kafka集群中: topic: newsjson
            kafkaSpiderProducer.sendNewsJson(newsJson);


            //4. 将新闻对象的url保存到set集合中
            jedis = JedisUtils.getJedis();
            jedis.sadd(SpiderConstant.SPIDER_NEWS_URLSET,news.getUrl());
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

        Boolean sismember = jedis.sismember(SpiderConstant.SPIDER_NEWS_URLSET, docurl);

        jedis.close();

        return sismember;
    }


}
