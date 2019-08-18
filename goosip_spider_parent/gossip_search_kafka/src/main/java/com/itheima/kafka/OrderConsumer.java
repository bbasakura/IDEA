package com.itheima.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @ProjectName: goosip_spider_parent
 * @Package: com.itheima.kafka
 * @ClassName: OrderConsumer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/10 0010 22:35
 * @Version: 1.0
 */
public class OrderConsumer {

    public static void main(String[] args) {
        //1. 加载kafka消费者的配置信息
        Properties props = new Properties();
        //kafka集群的地址
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        //kafka消费者组的id值(唯一)
        props.put("group.id", "order");
        //消费者自动提交偏移量
        props.put("enable.auto.commit", "true");
        //消费者提交偏移量的时间周期
        props.put("auto.commit.interval.ms", "1000");
        //消息的key的反序列化方式
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //消息value的反序列化方式
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //2. 创建kafkaConsumer对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        //3. 订阅哪个topic的数据(可以同时订阅多个topic)
        consumer.subscribe(Arrays.asList("order"));
        //4. 消费数据(消费者一旦启动,永远执行)
        while (true) {
            //5. 从kafka集群拉取数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            //6. 遍历kafka拉取的数据,打印
            for (ConsumerRecord<String, String> record : records) {
                //打印 消费的分区名  偏移量  key   value值
                int partition = record.partition();
                System.out.println("partition = " + partition +" ,offset = " + record.offset() + ", key = " +record.key()+", value = "+record.value());
            }
        }


    }
}
