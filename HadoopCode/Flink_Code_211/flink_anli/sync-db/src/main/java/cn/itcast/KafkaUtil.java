package cn.itcast;



import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;


/**
 * 2019/6/4
 */
public class KafkaUtil {

    public static Properties getKafka(){

        Properties prop = new Properties();
        prop.put("zookeeper.connect","hadoop01:2181,hadoop02:2181,hadoop03:2181");
        prop.put("metadata.broker.list","hadoop01:9092,hadoop02:9092,hadoop03:9092");
        prop.put("serializer.class", StringEncoder.class.getName());

        return prop;
    }
    public static Producer getProducer(){
        ProducerConfig producerConfig = new ProducerConfig(getKafka());
        Producer<Integer, String> producer = new Producer<Integer, String>(producerConfig);
        return producer;
    }

    public static void senMsg(String topic,String key,String data){
        Producer producer = getProducer();
        producer.send(new KeyedMessage(topic,key,data));
    }




}
