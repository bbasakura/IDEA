package com.itheima.es_hbase;

import javafx.scene.control.Tab;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: ElkTest
 * @Package: com.itheima.es_hbase
 * @ClassName: HbaseUtil
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/18 0018 18:30
 * @Version: 1.0
 * 1.获取客户端对象
 * 2.初始化表,判读表是否存在,不存在就需要新建
 * 3.根据rowkey查询数据
 * 4.插入数据
 */

public class HbaseUtil {
    //1、获取客户端的对象
    static Connection connection;

    static {
        Configuration configuration = HBaseConfiguration.create();

        //封装zk
        configuration.set("hbase.zookeeper.quorum","hadoop01:2181,hadoop02:2181,hadoop03:2181");

        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static Table initTable(String tableName,String ...family){
        /**
         * @method: initTable
         * @return: org.apache.hadoop.hbase.client.Table
         * @description:  初始化表，判断是否存在，是就删除，否则就创建Hbas表
         * @Author: sakura
         * @Date: 2019/8/18 0018 18:34
         */
        TableName tblName = TableName.valueOf(tableName);

        Table table =null;

        try {
            //获取admin 对象--------怎么的到的，，，，connect.getAdmin,
            Admin admin = connection.getAdmin();

            //判断表是否存在，
            if(!admin.tableExists(tblName)){

                //构建表的描述器----传入的是表的名字
                HTableDescriptor hTableDescriptor = new HTableDescriptor(tblName);

                //构建列族的描述器---此处用的是循环，，？？？？有很多的列，，多次构建
                for (String colFamily : family) {
                    hTableDescriptor.addFamily(new HColumnDescriptor(colFamily));
                }
                //表不存在，就建表----传入的参数是，表的描述器，根据你的描述创建一个表

                admin.createTable(hTableDescriptor);
                admin.close();
            }
            //获取表-----怎么获取表，只有连接connect能有这个资格
             table = connection.getTable(tblName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    public  static String queryByRowkey(String tableName,String family,String columnName,String rowkey){
        /**
         * @method: queryByRowkey
         * @return: java.lang.String
         * @description: 查询数据，给我带来什么的参数信息，我能查到数据呢，
         * ----------------表的名字，列族，列名，rowkey？？？？？
         * @Author: sakura
         * @Date: 2019/8/18 0018 18:48
         */

        //获取表
        Table table = initTable(tableName, family);
        //获取新闻的content
        String content ="";

        try {
            Get get = new Get(rowkey.getBytes());
            Result result = table.get(get);

            //查询指定的列族下，指定列的值
            byte[] value = result.getValue(family.getBytes(), columnName.getBytes());
            //的到的值就是content的内容
            content = new String(value);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public  static void putArticles(List<Article> list){
        /**
         * @method: putArticles
         * @return: void
         * @description: 插入的数据的方法-----批量插入，传入的是一个list,下面有单个插入的方法
         * @Author: sakura
         * @Date: 2019/8/18 0018 18:57
         */
        for (Article article : list) {
            putDataByRowkey("articles", "article", "title", article.getTitle(), article.getId());
            putDataByRowkey("articles", "article", "from", article.getFrom(), article.getId());
            putDataByRowkey("articles", "article", "time", article.getTime(), article.getId());
            putDataByRowkey("articles", "article", "readCount", article.getReadCount(), article.getId());
            putDataByRowkey("articles", "article", "content", article.getContent(), article.getId());
        }





    }


    //新建put方法插入数据的方法
    public static void putDataByRowkey(String tableName, String family, String columnName,
                                       String columnValue, String rowkey) {

        //获取表
        Table table = initTable(tableName, family);
        try {
            Put put = new Put(rowkey.getBytes());
            put.addColumn(family.getBytes(), columnName.getBytes(), columnValue.getBytes());
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
