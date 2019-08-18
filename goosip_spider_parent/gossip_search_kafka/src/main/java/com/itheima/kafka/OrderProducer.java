package com.itheima.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @ProjectName: goosip_spider_parent
 * @Package: com.itheima.kafka
 * @ClassName: OrderProducer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/10 0010 22:34
 * @Version: 1.0
 */
public class OrderProducer {


    public static void main(String[] args) {

        //0.加载生产者的配置信息
        Properties conf = new Properties();
        //kafka集群的地址信息
        conf.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        //消息的确认机制
        conf.put("acks", "all");
        //消息的重试次数
        conf.put("retries", 0);
        //消息的批量发送的大小
        conf.put("batch.size", 16384);
        //消息批量发送的最小时间间隔
        conf.put("linger.ms", 1);
        //发送消息的缓冲池大小
        conf.put("buffer.memory", 33554432);
        //发送消息的key的序列化方式
        conf.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //发送消息的value的序列化方式
        conf.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        //1. 创建KafkaProducer对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(conf);


        //2. 发送数据到kafka集群
        for (int i = 0; i < 100; i++) {
            /**
             * topic: 将消息发送到哪个topic中
             * key:  消息的key
             * value:  消息的value值
             */
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("order", i+"",i+"");

            kafkaProducer.send(record);

        }

        //3. 关闭资源
        kafkaProducer.close();
    }

    //准备一个控制台的消费者:  消费order这个topic的数据
    //消费数据的方式: 先消费一个分区  在消费另外一个分区  最后消费剩下的一个分区
    //分区内部的数据有序    整体数据无序
}
