package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @ProjectName: goosip_spider_parent
 * @Package: com.itheima.spider.kafka
 * @ClassName: KafkaSpiderProducer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/11 0011 17:56
 * @Version: 1.0
 */
public class KafkaSpiderProducer {

    public static Properties props = null;

    public static Producer<String, String> producer = null;


    //初始化操作
    static {
        //1. 加载生产者的配置信息
        props = new Properties();
        //kafka集群的地址
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        //消息的确认机制
        props.put("acks", "all");
        //消息的重试
        props.put("retries", 0);
        //消息的批量大小
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        //消息的批量缓存大小
        props.put("buffer.memory", 33554432);
        //消息的key的序列化方式
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //消息的value的序列化方式
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //2. 创建生产者
        producer = new KafkaProducer<String, String>(props);
    }


    /**
     * 将新闻数据写入kafka集群的方法
     * @param newsJson : 新闻的json数据
     */
    public void sendNewsJson(String newsJson){
        producer.send(new ProducerRecord<String, String>("newsjson",newsJson));
    }


}
