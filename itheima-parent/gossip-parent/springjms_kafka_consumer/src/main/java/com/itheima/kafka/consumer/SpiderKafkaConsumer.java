package com.itheima.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.kafka.consumer
 * @ClassName: SpiderKafkaConsumer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 17:38
 * @Version: 1.0
 */
@Component
public class SpiderKafkaConsumer implements MessageListener<Integer, String> {


    @Override
    public void onMessage(ConsumerRecord<Integer, String> record) {

        int partition = record.partition();
        System.out.println("partition = " + partition + " , offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());

    }
}





