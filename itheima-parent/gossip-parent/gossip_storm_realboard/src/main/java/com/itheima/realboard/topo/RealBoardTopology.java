package com.itheima.realboard.topo;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.realboard.topo
 * @ClassName: RealBoardTopology
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/18 0018 20:35
 * @Version: 1.0
 */
public class RealBoardTopology {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {

        //1. 创建拓扑构建器
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        String bootstrapServers = "node01:9092,node02:9092,node03:9092";
        String topics = "itcast_order";

        //2. 组装的kafkaSpout
        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder(bootstrapServers, topics);
        //消费者组id
        builder.setGroupId("order");
        //有偏移量,从偏移量开始读取消息, 如果没有偏移量,只消费最新的数据
        builder.setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_LATEST);

        KafkaSpoutConfig<String, String> kafkaSpoutConfig = builder.build();

        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<String, String>(kafkaSpoutConfig);
        topologyBuilder.setSpout("kafkaSpout",kafkaSpout);


        //3. 组装ProcessingIndexBolt
        topologyBuilder.setBolt("processingIndexBolt",new ProcessingIndexBolt()).localOrShuffleGrouping("kafkaSpout");


        Config config = new Config();
        StormTopology topology = topologyBuilder.createTopology();

        //4.判断是否应该在集群运行
        if(args != null && args.length > 0){
            //集群中运行
            config.setNumWorkers(2);
            StormSubmitter.submitTopology(args[0],config, topology);
        }else{
            //本地运行
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("realBoardTopology",config,topology);
        }

    }
}
