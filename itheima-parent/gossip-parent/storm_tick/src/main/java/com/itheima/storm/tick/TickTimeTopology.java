package com.itheima.storm.tick;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.tick
 * @ClassName: TickTimeTopology
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 17:46
 * @Version: 1.0
 */
public class TickTimeTopology {

    public static void main(String[] args) {

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("randomSpout",new RandomSpout());

        topologyBuilder.setBolt("ticktimeBolt",new TicktimeBolt()).localOrShuffleGrouping("randomSpout");

        LocalCluster localCluster = new LocalCluster();

        Config config = new Config();

        localCluster.submitTopology("tickTimeTopology",config,topologyBuilder.createTopology());

    }
}
