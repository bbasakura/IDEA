package com.ithaima.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

/**
 * @ProjectName: hbaseTest02
 * @Package: com.ithaima.hbase
 * @ClassName: LoadData
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/15 0015 21:35
 * @Version: 1.0
 */
public class LoadData {

    public static void main(String[] args) throws Exception {

        //配置
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "hadoop01,hadoop02,hadoop03");

        Connection connection =  ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        Table table = connection.getTable(TableName.valueOf("test001"));

        //上传数据的类
        LoadIncrementalHFiles load = new LoadIncrementalHFiles(configuration);
        //文件的HDFS路径
        load.doBulkLoad(new Path("hdfs://hadoop01:8020/hbase/output_hfile"), admin,table,connection.getRegionLocator(TableName.valueOf("myuser2")));
        //关流
        admin.close();
        connection.close();


    }

}

