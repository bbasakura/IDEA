package cn.itcast.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

/**
 *  2019/9/4
 * 配置文件（springboot中是没有xml配置文件）
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    //注入属性
    @Value("${kafka.producer.servers}")
    private String servers;
    @Value("${kafka.producer.retries}")
    private int retries;
    @Value("${kafka.producer.batch.size}")
    private int batchSize;
    @Value("${kafka.producer.linger}")
    private int linger;
    @Value("${kafka.producer.buffer.memory}")
    private int memory;


    @Bean
    public KafkaTemplate kafkaTemplate(){

        //添加kafka的配置
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers) ;//browker地址
        map.put(ProducerConfig.RETRIES_CONFIG,retries);
        map.put(ProducerConfig.BATCH_SIZE_CONFIG,batchSize);
        map.put(ProducerConfig.LINGER_MS_CONFIG,linger);
        map.put(ProducerConfig.BUFFER_MEMORY_CONFIG,memory);

        //需要配置key，value的序列化
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);

        //避免数据倾斜
        //算法RoundRobin，轮询的算法
        map.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,RoundRobinPartition.class);

        //kafka核心配置对象
        ProducerFactory kafkaProducerFactory = new DefaultKafkaProducerFactory(map);
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<String, String>(kafkaProducerFactory);
        return kafkaTemplate;
    }
}
