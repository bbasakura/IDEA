package com.itheima.kafka.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.kafka.consumer
 * @ClassName: ConsumerTest
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 17:43
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:kafka-consumer.xml")
public class ConsumerTest {
    @Test
    public void Test() throws IOException {
        System.in.read();

    }


}
