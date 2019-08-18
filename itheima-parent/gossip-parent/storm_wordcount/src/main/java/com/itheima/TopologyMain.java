package com.itheima;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.thrift.TException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima
 * @ClassName: TopologyMain
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/16 0016 20:11
 * @Version: 1.0
 */
public class TopologyMain {

    /*public static void main(String[] args){


           //           //创建拓扑构造器

           TopologyBuilder topologyBuilder = new TopologyBuilder();


           //组装spout

           topologyBuilder.setSpout("readFileSpout",new ReadFileSpout());

           //组装splitBolt

           topologyBuilder.setBolt("splitBolt",new SplitBolt()).localOrShuffleGrouping("readFileSpout");

           //4. 组装wordCountBolt

           topologyBuilder.setBolt("wordCountBolt",new WordCountBolt()).fieldsGrouping("splitSpout",new Fields("word"));


           //5. 本地模式启动

           LocalCluster localCluster = null;
           try {
                  localCluster = new LocalCluster();
           } catch (Exception e) {
                  e.printStackTrace();
           }

           //拓扑运行需要的配置对象

           Config config = new Config();

           //是否是调试模式启动
           config.setDebug(false);


           StormTopology topology = topologyBuilder.createTopology();

           try {
                  localCluster.submitTopology("wordCountBolt",config,topology);
           } catch (TException e) {
                  e.printStackTrace();
           }


    }*/


    public static void main(String[] args) throws Exception {
        //1. 创建拓扑构造器
        TopologyBuilder topologyBuilder = new TopologyBuilder();


        //2. 组装spout
        /**
         * 第一个参数: spout组件的id 这个拓扑中要唯一
         * 第二个参数: 组件的对象
         */
        topologyBuilder.setSpout("readFileSpout", new ReadFileSpout(), 2);


        //3. 组装splitBolt
        /**
         * 第一个参数: bolt组件的id 这个拓扑中要唯一
         * 第二个参数: 组件的对象
         * localOrShuffleGrouping: 流分组策略   优先找同一个worker中的bolt进行发送, 否则就随机发送
         */
        topologyBuilder.setBolt("splitBolt", new SplitBolt(), 2).localOrShuffleGrouping("readFileSpout");


        //4. 组装wordCountBolt
        /**
         * 第一个参数: bolt组件的id 这个拓扑中要唯一
         * 二个参数: 组件的对象
         * fieldsGrouping : 字段分组: 按照splitBolt发送出去的数据,字段值相同的tuple会发送到同一个bolt中处理
         */
        topologyBuilder.setBolt("wordCountBolt", new WordCountBolt()).fieldsGrouping("splitBolt", new Fields("word"));

        //拓扑运行需要的配置对象
        Config config = new Config();

        //是否是调试模式启动
        config.setDebug(false);

        StormTopology topology = topologyBuilder.createTopology();
        if (args != null && args.length > 0) {
            //只要传递参数,就是集群方式运行
            /**
             * 第一个参数: 集群上运行拓扑的名称
             *
             * 第二个参数:  拓扑运行需要的配置对象
             *
             * 第三个参数:  拓扑对象
             */
            StormSubmitter.submitTopology(args[0], config, topology);
        } else {
            //5. 本地模式启动
            LocalCluster localCluster = new LocalCluster();

            localCluster.submitTopology("wordCountTopology", config, topology);



        }
    }
}