package com.itheima.storm.mysql;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.jdbc.bolt.JdbcInsertBolt;
import org.apache.storm.jdbc.common.ConnectionProvider;
import org.apache.storm.jdbc.common.HikariCPConnectionProvider;
import org.apache.storm.jdbc.mapper.JdbcMapper;
import org.apache.storm.jdbc.mapper.SimpleJdbcMapper;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.topology.TopologyBuilder;

import java.util.Map;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.mysql
 * @ClassName: JdbcTopology
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 19:27
 * @Version: 1.0
 */
public class JdbcTopology {

    public static void main(String[] args) {

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("randomSpout",new RandomSpout());

        topologyBuilder.setBolt("splitBolt",new SplitBolt()).localOrShuffleGrouping("randomSpout");


        //组装jdbcBolt(新的东西，JDbc的连接设置)

        //1. 连接对象
        Map hikariConfigMap = Maps.newHashMap();
        //驱动
        hikariConfigMap.put("dataSourceClassName","com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        //url
        hikariConfigMap.put("dataSource.url", "jdbc:mysql://localhost:3306/log_monitor?characterEncoding=utf-8");
        //username
        hikariConfigMap.put("dataSource.user","root");
        //password
        hikariConfigMap.put("dataSource.password","root");
        ConnectionProvider connectionProvider = new HikariCPConnectionProvider(hikariConfigMap);

        String tableName = "user";

        //2. 创建jdbcMapper映射对象
        JdbcMapper jdbcMapper = new SimpleJdbcMapper(tableName,connectionProvider);


        //3. 创建JdbcInsertBolt对象
        JdbcInsertBolt jdbcInsertBolt = new JdbcInsertBolt(connectionProvider,jdbcMapper)
                .withTableName(tableName)
                .withQueryTimeoutSecs(20);

        topologyBuilder.setBolt("jdbcInsertBolt",jdbcInsertBolt).localOrShuffleGrouping("splitBolt");



        LocalCluster localCluster = new LocalCluster();

        Config config = new Config();

        localCluster.submitTopology("jdbcTopology",config,topologyBuilder.createTopology());



    }



}



