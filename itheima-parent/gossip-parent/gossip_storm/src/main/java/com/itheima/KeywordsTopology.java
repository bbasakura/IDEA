package com.itheima;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.Properties;

/**
 * @author itheima
 * @Title: KeywordsTopology
 * @ProjectName gossip-parent
 * @Description: 热词统计拓扑结构: 组装kafkaSpout  LogSplitBolt  WordCountBolt   kafkaBolt
 * @date 2019/7/1612:13
 */
public class KeywordsTopology {

    public static void main(String[] args) {

        //1. 创建拓扑构造器
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        //2. 组装spout:  KafkaSpout
        //2.1 创建KafkaSpout: 需要和kafka做对接

        //kafka集群地址
        String bootstrapServers = "node01:9092,node02:9092,node03:9092";
        //kafka消费的topic的名称
        String  topics = "logs";
        //创建builder对象
        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder(bootstrapServers,topics);
        //设置消费者的组id
        builder.setGroupId(topics);
        //设置消费的方式: 有偏移量从偏移量开始消费,  没有偏移量,消费最新的消息
        builder.setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_LATEST);

        //2.2 创建kafkaSpoutConfig对象
        KafkaSpoutConfig<String, String> kafkaSpoutConfig = builder.build();

        //2.3 创建KafkaSpout对象
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<String, String>(kafkaSpoutConfig);

        topologyBuilder.setSpout("kafkaSpout",kafkaSpout);


        //3. 组装LogSplitBolt
        topologyBuilder.setBolt("logSplitBolt",new LogSplitBolt()).localOrShuffleGrouping("kafkaSpout");


        //4. 组装wordCountBolt
        topologyBuilder.setBolt("wordCountBolt",new WordCountBolt()).fieldsGrouping("logSplitBolt",new Fields("keywords"));



        //5. 组装kafkaBolt  : 将统计结果写入kafka集群的keywords这个topic
        //5.1  创建KafkaBolt对象
        //5.2 创建生产者的配置信息
        Properties properties = new Properties();
        //kafka集群地址
        properties.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        properties.put("acks", "1");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        String productTopic = "keywords";

        KafkaBolt<String, String> kafkaBolt = new KafkaBolt<String, String>().
                withProducerProperties(properties)
                .withTopicSelector(productTopic);
        topologyBuilder.setBolt("kafkaBolt",kafkaBolt).localOrShuffleGrouping("wordCountBolt");



        //6. 创建本地集群
        LocalCluster localCluster = new LocalCluster();


        //7.提交拓扑
        Config config = new Config();
        config.setDebug(false);

        StormTopology topology = topologyBuilder.createTopology();

        localCluster.submitTopology("keywordsTopology",config,topology);


    }

}
