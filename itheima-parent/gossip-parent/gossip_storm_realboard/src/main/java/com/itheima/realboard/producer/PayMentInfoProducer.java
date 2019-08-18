package com.itheima.realboard.producer;

import com.itheima.realboard.domain.PaymentInfo;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.realboard.domain.com.itheima.realboard.producer
 * @ClassName: PayMentInfoProducer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/18 0018 20:23
 * @Version: 1.0
 */
public class PayMentInfoProducer {
    public static void main(String[] args) {
        //1. 加载生产者的配置信息: kafka的集群地址   ack  序列化方式
        Properties props = new Properties();
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        //序列化的方式
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        //2.创建KafkaProducer的对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);


        while (true) {

            try {
                //3. 生产模拟订单
                kafkaProducer.send(new ProducerRecord<String, String>("itcast_order", new PaymentInfo().random()));

                //4. 发送模拟订单数据到kafka集群
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
