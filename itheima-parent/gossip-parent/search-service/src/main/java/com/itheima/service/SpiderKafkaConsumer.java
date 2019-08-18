package com.itheima.service;

import com.google.gson.Gson;
import com.itheima.pojo.News;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.service
 * @ClassName: SpiderKafkaConsumer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 18:21
 * @Version: 1.0
 */
@Component
public class SpiderKafkaConsumer  implements MessageListener<Integer, String>{

    @Autowired
    private indexWriterServiceImpl indexWriterService;

    /**
     * json转换
     */
    private static  final Gson gson = new Gson();

    /**
     * 格式化日期的对象
     */
    private static  final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static  final  SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss'Z'");


    /**
     * 拉取到新闻数据时,会被调用的方法
     *
     * @param data 一条消息
     */
    @Override
    public void onMessage(ConsumerRecord<Integer, String> data) {

        try {
            //1. 获取kafka中的新闻数据
            String newsjson = data.value();

            News news = gson.fromJson(newsjson, News.class);
            List<News> newsList = new ArrayList<>();
            newsList.add(news);


            //2.处理日期格式:
            Date date = format.parse(news.getTime());
            String time = timeFormat.format(date);
            news.setTime(time);


            //3. 调用索引写入服务,将新闻数据写入索引库(solrcloud)
            indexWriterService.newsIndexWriter(newsList);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }











}
