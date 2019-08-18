package com.itheima.timing;

import com.itheima.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author itheima
 * @Title: IndexWriterTiming
 * @ProjectName gossip-parent
 * @Description: 定时任务
 * @date 2019/7/715:45
 */
@Component
public class IndexWriterTiming {

    @Autowired
    private NewsService newsService;

    private Logger LOG = LoggerFactory.getLogger(IndexWriterTiming.class);


    /**
     * 执行啥任务   什么时候执行
     * 定时任务的定义:
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void newsIndexWriterTiming(){

        try {
            System.out.println("开始读取数据库中的数据,写入索引库.........");
            LOG.info("开始读取数据库中的数据,写入索引库.........");
            newsService.newsIndexWriter();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
