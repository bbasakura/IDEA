package com.itheima.kafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.kafka.producer
 * @ClassName: ProducerTest
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 17:33
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:kafka-producer.xml")
public class ProducerTest {

    @Autowired
    private SpiderKafkaProducer spiderKafkaProducer;


    @Test
    public void name() throws Exception {
        spiderKafkaProducer.sendSpider("中间商赚差价");
    }
}
