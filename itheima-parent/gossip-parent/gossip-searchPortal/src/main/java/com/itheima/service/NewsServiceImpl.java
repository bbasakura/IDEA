package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.GossipConstant;
import com.itheima.mapper.NewsMapper;
import com.itheima.pojo.News;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author itheima
 * @Title: NewsServiceImpl
 * @ProjectName gossip-parent
 * @Description: 新闻的服务实现类
 * @date 2019/7/715:00
 */

//注意:  不需要通过dubbo远程调用的服务,只需要添加spring的注解
@Service
public class NewsServiceImpl implements NewsService {

    /**
     * 注入dao的代理对象
     */
    @Autowired
    private NewsMapper newsMapper;


    @Autowired
    private JedisPool jedisPool;


    //需要找注册中心注入远程服务
    @Reference(timeout = 5000)
    private IndexWriterService indexWriterService;



    //需要找注册中心注入远程搜索服务
    @Reference(timeout = 3000)
    private IndexSearchService indexSearchService;


    /**
     * 打印日志的对象
     */
    private Logger LOG = LoggerFactory.getLogger(NewsServiceImpl.class);


    /**
     * 调用dao,获取新闻列表数据,
     * 调用远程的索引写入服务,将新闻数据写入solrcloud集群
     *
     * @throws Exception
     */
    @Override
    public void newsIndexWriter() throws Exception {
        //1. 注入dao的代理对象

        //从redis中获取maxId  ,如果不存在,初始化为0, 如果存在,就使用这个最大的maxId
        Jedis jedis = jedisPool.getResource();
        String maxId = jedis.get(GossipConstant.BIGDATA_GOSSIP_MAXID);
        jedis.close();

        //如果不存在,初始化为0,
        if (StringUtils.isEmpty(maxId)) {
            maxId = "0";
        }


        SimpleDateFormat oldformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss'Z'");

        while (true) {
            //2. 调用dao层,获取新闻列表数据
            List<News> newsList = newsMapper.queryAndIdGtAndPage(maxId);

            //跳出循环的逻辑, 更新最大的id值到redis中
            if (newsList == null || newsList.size() == 0) {

                jedis = jedisPool.getResource();
                jedis.set(GossipConstant.BIGDATA_GOSSIP_MAXID, maxId);
                jedis.close();
                break;
            }


            //添加一段处理日期的逻辑，数据库中的是一个非标的日期字符串，
            //先通过format.parse转成非标的Date类型，再将format.format（）
            //转换成标准的日期字符串
            for (News news : newsList) {
                String time = news.getTime();
                Date date = oldformat.parse(time);
                String newTime = newformat.format(date);
                //将日期转换成标准格式
                news.setTime(newTime);
            }

            //3.调用远程的索引写入的服务
            indexWriterService.newsIndexWriter(newsList);

            System.out.println("写入solrcloud索引库的数据条数: " + newsList.size());
            LOG.info("写入solrcloud索引库的数据条数: " + newsList.size());

            //4. 更新maxId值:  当前页的最大的id值
            maxId = newsMapper.queryAndIdMax(maxId);

        }


    }

    @Override
    public List<News> findByKeywords(ResultBean resultBean) throws Exception {
        /**
         * @method: findByKeywords

         * @return: java.util.List<com.itheima.pojo.News>
         * @description: 关键字检索
         * @Author: sakura
         * @Date: 2019/7/8 0008 19:47
         */
        //1.调用solr服务，获取结果数据

        List<News> newsList = indexSearchService.findByKeywords(resultBean);

        //2.对结果数据进行处理，根据经验content的数据大概只要显示70个字左右
        for (News news : newsList) {

            String content = news.getContent();

            //对content的长度进行判断，如果大于70之后，截取0-69

            if (content.length() > 70) {

                content = content.substring(0, 69)+"...";
                news.setContent(content);
            }
        }
        //3.返回数据给controller层
        return newsList;
    }

    @Override
    public PageBean findByPageQuery(ResultBean resultBean) throws Exception {

        PageBean pageBean = indexSearchService.findByPageQuery(resultBean);

        for (News news : pageBean.getNewsList()) {
            String content = news.getContent();

            if(content.length() > 70){
                content = content.substring(0, 69) +  "...";
                news.setContent(content);
            }
        }

        return pageBean;
    }


}
