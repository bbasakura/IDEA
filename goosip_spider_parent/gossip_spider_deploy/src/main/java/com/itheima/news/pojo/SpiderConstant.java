package com.itheima.news.pojo;

/**
 * @author itheima
 * @Title: SpiderConstant
 * @ProjectName goosip_spider_parent
 * @Description: 常量类
 * @date 2019/6/2915:54
 */
public class SpiderConstant {


    /**
     * 常量: 163爬虫的:   存放已经爬取的url地址的set 集合的key的名称
     */
    public static  final  String SPIDER_NEWS163 = "bigData:spider:163news:docurl";


    /**
     * 常量:  腾讯爬虫的:   存放已经爬取的url地址的set 集合的key的名称
     */
    public static  final  String SPIDER_NEWS_TENCENT = "bigData:spider:newstencent:docurl";


    /**
     * 常量:  163爬虫:   存放爬取到的url的队列
     */
    public static  final  String SPIDER_NEWS_URLLIST = "bigData:spider:urlList";


    /**
     * 常量:  163 和腾讯共用的set集合:  存放已经爬取过的url的set集合:
     */
    public static  final  String SPIDER_NEWS_URLSET = "bigData:spider:urlSet";


    /**
     * 常量: 163 和腾讯共用的list队列:  存放已经爬取到的news对象的json数据
     */
    public static  final  String SPIDER_NEWS_NEWJSONLIST = "bigData:spider:newsJsonList";




}
