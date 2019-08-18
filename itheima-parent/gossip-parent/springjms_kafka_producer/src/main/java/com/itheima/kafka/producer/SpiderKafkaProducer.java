package com.itheima.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.kafka.producer
 * @ClassName: SpiderKafkaProducer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 17:32
 * @Version: 1.0
 */
@Component
public class SpiderKafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 生产者中的发送数据的方法
     * @param news : 新闻数据
     */
    public void sendSpider(String news){
        /**
         * 第一个参数:topic
         * 第二个参数:数据
         */
        kafkaTemplate.send("spider-test",news);
    }
}
