package com.itheima.ack;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.ack
 * @ClassName: AckTopology
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/17 0017 22:22
 * @Version: 1.0
 */
public class AckTopology {

    public static void main(String[] args) {

        TopologyBuilder topologyBuilder = new TopologyBuilder();


        topologyBuilder.setSpout("logSpout",new LogSpout());

        topologyBuilder.setBolt("splitBolt",new SplitBolt()).localOrShuffleGrouping("logSpout");


        LocalCluster localCluster = new LocalCluster();

        Config config = new Config();
        //默认一个worker中就一个acker, 可以不设置,但是不能为0
//        config.setNumAckers(1);

        localCluster.submitTopology("ackTopology",config,topologyBuilder.createTopology());


    }

}
