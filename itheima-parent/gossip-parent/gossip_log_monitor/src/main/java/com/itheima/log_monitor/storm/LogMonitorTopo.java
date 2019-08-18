package com.itheima.log_monitor.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

public class LogMonitorTopo {

    public static void main(String[] args) throws Exception {

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder("192.168.72.141:9092,192.168.72.142:9092,192.168.72.143:9092", "log_monitor");

        builder.setGroupId("hello_kafka");

        builder.setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.LATEST);

        KafkaSpoutConfig<String, String> config = builder.build();

        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<String, String>(config);

        topologyBuilder.setSpout("kafkaSpout", kafkaSpout, 1);

        topologyBuilder.setBolt("tickBolt", new StormTickBolt()).localOrShuffleGrouping("kafkaSpout");

        topologyBuilder.setBolt("processDataBolt", new ProcessDataBolt()).localOrShuffleGrouping("tickBolt");

        topologyBuilder.setBolt("notifyMessage", new NotifyMessageBolt()).localOrShuffleGrouping("processDataBolt");

        topologyBuilder.setBolt("saveDBolt", new SaveToDbBolt()).localOrShuffleGrouping("notifyMessage");

        Config stormConfig = new Config();

        if (args != null && args.length > 0) {

            StormSubmitter submitter = new StormSubmitter();

            submitter.submitTopology(args[0], stormConfig, topologyBuilder.createTopology());

        } else {

            LocalCluster cluster = new LocalCluster();

            cluster.submitTopology("localStorm", stormConfig, topologyBuilder.createTopology());
        }
    }

}
