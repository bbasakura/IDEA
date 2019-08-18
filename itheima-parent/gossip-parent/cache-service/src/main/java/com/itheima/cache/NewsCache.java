package com.itheima.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultBean;
import com.itheima.service.IndexSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.awt.*;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.cache
 * @ClassName: NewsCache
 * @Description: 根据热词调用索引搜索服务，查询热词对应的新闻数据，并将新闻数据缓存到redis中去
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 20:28
 * @Version: 1.0
 */
@Component
public class NewsCache {

    //远程注入索引搜索服务
    @Reference
    private IndexSearchService indexSearchService;

    //注入redis连接池
    @Autowired
    private JedisPool jedisPool;

    public void cacheNews(String keywords)throws Exception{
        /**
         * @method: cacheNews
         * @return: void
         * @description: 根据热词调用搜索服务，然后写入道redis中
         * @Author: sakura
         * @Date: 2019/7/11 0011 20:34
         */
        //1.构建查询对象ResultBean,（所有的数据都在这里面封装）

        ResultBean resultBean = new ResultBean();

        //2.设置查询的关键字keywords

        resultBean.setKeywords(keywords);


        //3.设置分页查询的条件，就是传递给分页查询所需的pageBean

        resultBean.setPageBean(new PageBean());

        //4.调用索引查询的服务，根据热词获取其新闻数据

        PageBean pageBean = indexSearchService.findByPageQuery(resultBean);

        //5.准备缓存最多五页的数据

        Integer pageNumber = pageBean.getPageNumber();


        //6.判断热词所对应的新闻是否大于五页，如果是大于五页，处理的策略是，只缓存前五页，否则全部缓存，

        if (pageNumber>5){
            //通过复制确定最后显示几页
            pageNumber=5;
        }

        //7，开始缓存前五页的数据(注意是从第一页开始的)
        for (int i = 1; i <=pageNumber; i++) {
             //没次查询第i页的数据
            resultBean.getPageBean().setPage(i);
            //执行查询，开始缓存第i页的数据,此时得到的page是一个JSON数据，
            PageBean page =indexSearchService.findByPageQuery(resultBean);

            //将得到的JSON数据转换成JSON类型的字符串

            String pageJson = JSON.toJSONString(page);


            //获取jdeis对象，这是操作redis的对象，
            Jedis jedis = jedisPool.getResource();

            /**
             * jedis.setex(x1,x2,x3)
             * x1:keywords:page
             * x2:有效时长，不可能一直存在，redis资源宝贵
             * x3:value,当前第i页的pageBean的json数据，（字符串的类型的json）
             */
            jedis.setex(keywords+":"+i,12*60*60,pageJson);

            jedis.close();


        }






    }





}
