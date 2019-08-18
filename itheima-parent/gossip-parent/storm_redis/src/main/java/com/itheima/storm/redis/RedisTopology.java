package com.itheima.storm.redis;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.redis.bolt.RedisStoreBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.mysql
 * @ClassName: JdbcTopology
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 19:27
 * @Version: 1.0
 */
public class RedisTopology {

    public static void main(String[] args) {

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("randomSpout",new RandomSpout());

        topologyBuilder.setBolt("splitBolt",new SplitBolt()).localOrShuffleGrouping("randomSpout");


        //组装jedispool(新的东西，redis的连接设置)

        //构建redisBolt对象(和kafka有点像)
        //设置俩config，和host,port
        JedisPoolConfig.Builder builder = new JedisPoolConfig.Builder().setHost("192.168.72.142").setPort(6379).setTimeout(5000);


        //得到配置文件jedisPoolConfig
        JedisPoolConfig jedisPoolConfig = builder.build();

        //storm的tuple数据如何映射到redis中的映射器
        RedisStoreMapper storeMapper = new NameValueMapper();

        //得到真正的redisBolt
        RedisStoreBolt redisBolt = new RedisStoreBolt(jedisPoolConfig, storeMapper);

        //组装rredisbolt
        topologyBuilder.setBolt("redisBolt",redisBolt).localOrShuffleGrouping("splitBolt");

        //本地运行
        LocalCluster localCluster = new LocalCluster();

        //得到config配置文件
        Config config = new Config();

        //本地提交topology
        localCluster.submitTopology("jdbcTopology",config,topologyBuilder.createTopology());



    }



}



