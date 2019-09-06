package com.itheima;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: hbaseTest_01
 * @Package: com.itheima
 * @ClassName: hbaseTest
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/14 0014 21:06
 * @Version: 1.0
 */
public class hbaseTest {


    @Test
    public void createTable1() throws IOException {
        //1.创建客户端

        Configuration configuration = HBaseConfiguration.create();

        configuration.set("hbase.zookeeper.quorum", "hadoop01:218,hadoop02:2181,hadoop03:2181");

        Connection connection = ConnectionFactory.createConnection(configuration);

        //2.集群客户端实力对象

        Admin admin = connection.getAdmin();

        TableName tableName = TableName.valueOf("test");

        //3.构建表名

        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);

        //4.构建列族
        HColumnDescriptor info = new HColumnDescriptor("info");
        HColumnDescriptor data = new HColumnDescriptor("data");

        //5.指定列族添加到哪一张表中

        hTableDescriptor.addFamily(info);
        hTableDescriptor.addFamily(data);

        //6.创建表，需要上面的表名字

        admin.createTable(hTableDescriptor);

        //7.关流

        admin.close();
        connection.close();


    }

    @Test
    public void createTable() throws IOException {
        //创建配置文件对象，并指定zookeeper的连接地址
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");

        //集群配置↓
        configuration.set("hbase.master", "hadoop03:60000");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        //通过HTableDescriptor来实现我们表的参数设置，包括表名，列族等等
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("test000"));
        //添加列族
        hTableDescriptor.addFamily(new HColumnDescriptor("f1"));
        //添加列族
        hTableDescriptor.addFamily(new HColumnDescriptor("f2"));
        //创建表
        boolean test000 = admin.tableExists(TableName.valueOf("test000"));
        if (!test000) {
            admin.createTable(hTableDescriptor);
        }
        //关闭客户端连接
        admin.close();
    }


    /**
     * 插入数据
     */
    @Test
    public void addDatas() throws IOException {
        //获取连接
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        //获取表
        Table test000 = connection.getTable(TableName.valueOf("test000"));
        //创建put对象，并指定rowkey
        Put put = new Put("001".getBytes());
        put.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(1));
        put.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("张三"));
        put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(18));

        put.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("地球人"));
        put.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15874102589"));
        //插入数据
        test000.put(put);
        //关闭表
        test000.close();

    }


    @Test
    public void insertBatchData() throws IOException {

        //获取连接
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        //获取表
        Table test000 = connection.getTable(TableName.valueOf("test000"));
        //创建put对象，并指定rowkey
        Put put = new Put("0002".getBytes());
        put.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(1));
        put.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("曹操"));
        put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(30));
        put.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("沛国谯县"));
        put.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("16888888888"));
        put.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("helloworld"));

        Put put2 = new Put("0003".getBytes());
        put2.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(2));
        put2.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("刘备"));
        put2.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(32));
        put2.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put2.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("幽州涿郡涿县"));
        put2.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("17888888888"));
        put2.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("talk is cheap , show me the code"));


        Put put3 = new Put("0004".getBytes());
        put3.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(3));
        put3.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("孙权"));
        put3.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(35));
        put3.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put3.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("下邳"));
        put3.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("12888888888"));
        put3.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("what are you 弄啥嘞！"));

        Put put4 = new Put("0005".getBytes());
        put4.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(4));
        put4.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("诸葛亮"));
        put4.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(28));
        put4.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put4.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("四川隆中"));
        put4.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("14888888888"));
        put4.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("出师表你背了嘛"));

        Put put5 = new Put("0006".getBytes());
        put5.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(5));
        put5.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("司马懿"));
        put5.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(27));
        put5.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put5.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("哪里人有待考究"));
        put5.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15888888888"));
        put5.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("跟诸葛亮死掐"));


        Put put6 = new Put("0007".getBytes());
        put6.addColumn("f1".getBytes(), "id".getBytes(), Bytes.toBytes(5));
        put6.addColumn("f1".getBytes(), "name".getBytes(), Bytes.toBytes("xiaobubu—吕布"));
        put6.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(28));
        put6.addColumn("f2".getBytes(), "sex".getBytes(), Bytes.toBytes("1"));
        put6.addColumn("f2".getBytes(), "address".getBytes(), Bytes.toBytes("内蒙人"));
        put6.addColumn("f2".getBytes(), "phone".getBytes(), Bytes.toBytes("15788888888"));
        put6.addColumn("f2".getBytes(), "say".getBytes(), Bytes.toBytes("貂蝉去哪了"));

        List<Put> listPut = new ArrayList<Put>();
        listPut.add(put);
        listPut.add(put2);
        listPut.add(put3);
        listPut.add(put4);
        listPut.add(put5);
        listPut.add(put6);

        test000.put(listPut);
        test000.close();
    }


    Connection connection;
    Table table;

    @Before
    public void initTable() {

        try {
            //1.创建客户端连接
            Configuration configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
            connection = ConnectionFactory.createConnection(configuration);
            //集群的客户端实例对象
            TableName tableName = TableName.valueOf("test000");
            Admin admin = connection.getAdmin();
            if (!admin.tableExists(tableName)) {
                //构建表名
                HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
                //构建列族
                HColumnDescriptor info = new HColumnDescriptor("f1");
                HColumnDescriptor data = new HColumnDescriptor("f2");
                hTableDescriptor.addFamily(info);
                hTableDescriptor.addFamily(data);
                //创建表
                admin.createTable(hTableDescriptor);
                //关流
                admin.close();
            }

            table = connection.getTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        if (table != null) {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {

            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test

    public void getByRowkey() throws IOException {

        /**
         * @method: getByRowkey

         * @return: void
         * @description: 通过rowkey查询数据
         * @Author: sakura
         * @Date: 2019/8/14 0014 21:24
         */

        Get get = new Get("0002".getBytes());
        Result result = table.get(get);

        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {

            byte[] bytes = CellUtil.cloneQualifier(cell);

            String columnName = Bytes.toString(bytes);

            if (columnName.equals("id") || columnName.equals("name")) {
                //建表时的类型
                System.out.println(Bytes.toInt(CellUtil.cloneValue(cell)));

            } else {
                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

    }

    @Test

    public void queryByStartKeyAndEndKey() throws IOException {
        /**
         * @method: queryByStartKeyAndEndKey

         * @return: void
         * @description: 根据startKey和endKey查询数据
         * @Author: sakura
         * @Date: 2019/8/14 0014 22:21
         */


        //创建scan对像
        Scan scan = new Scan();

        //设置起始值和停止值
        scan.setStartRow("0002".getBytes());
        scan.setStopRow("0006".getBytes());

        //得到scanner

        ResultScanner scanner = table.getScanner(scan);

        for (Result result : scanner) {

//            System.out.println(new String(result.getRow()));
            System.out.println(result.getColumnCells("f1".getBytes(), "id".getBytes()));
            /**
             * 结果：
             * [0002/f1:id/1565790361769/Put/vlen=4/seqid=0]
             * [0003/f1:id/1565790361769/Put/vlen=4/seqid=0]
             [0004/f1:id/1565790361769/Put/vlen=4/seqid=0]
             [0005/f1:id/1565790361769/Put/vlen=4/seqid=0]
             */
        }

    }

    /**
     * rowFilter(过滤器，去除这一个)
     */
    @Test
    public void rowFilter() throws IOException {

        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.LESS, new BinaryComparator("0005".getBytes()));
        Scan scan = new Scan();
        scan.setFilter(rowFilter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
//            Cell[] cells = result.rawCells();
//            for (Cell cell : cells) {
//                byte[] bytes = CellUtil.cloneValue(cell);
//                System.out.println(Bytes.toString(bytes));
//            }

            System.out.println(new String(result.getRow()));
        }
    }


    /**
     * familyFilter  列族过滤器
     */
    @Test
    public void familyFilter() throws IOException {

        FamilyFilter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("1"));
        Scan scan = new Scan();
        scan.setFilter(familyFilter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            System.out.println(new String(result.getRow()));
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {

                byte[] qualifier = CellUtil.cloneQualifier(cell);
                String columnName = Bytes.toString(qualifier);
                if (columnName.equals("id") || columnName.equals("age")) {
                    System.out.println(Bytes.toInt(CellUtil.cloneValue(cell)));
                } else {
                    System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        }
    }


    /**
     * valueFilter  值过滤器，相同输出 ，可以迷糊匹配
     */
    @Test
    public void valueFilter() throws IOException {

//        ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("操"));


        //单列值过滤器
//        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("f1".getBytes(), "name".getBytes(), CompareFilter.CompareOp.EQUAL, "曹操".getBytes());
        //单列值排除过滤器
        SingleColumnValueExcludeFilter singleColumnValueExcludeFilter = new SingleColumnValueExcludeFilter("f1".getBytes(), "name".getBytes(), CompareFilter.CompareOp.EQUAL, "曹操".getBytes());
        Scan scan = new Scan();
//        scan.setFilter(valueFilter);
//        scan.setFilter(singleColumnValueFilter);
        scan.setFilter(singleColumnValueExcludeFilter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                String columnName = Bytes.toString(CellUtil.cloneQualifier(cell));
                if (columnName.equals("id") || columnName.equals("age")) {
                    System.out.println(Bytes.toInt(CellUtil.cloneValue(cell)));
                } else {
                    System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        }
    }



    /**
     * 多过滤器综合查询
     */
    @Test
    public void filterList() throws IOException {

        FilterList filterList = new FilterList();
        PrefixFilter prefixFilter = new PrefixFilter("00".getBytes());
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter("f1".getBytes(), "name".getBytes(), CompareFilter.CompareOp.EQUAL, "刘备".getBytes());
        filterList.addFilter(prefixFilter);
        filterList.addFilter(singleColumnValueFilter);
        Scan scan = new Scan();
        scan.setFilter(filterList);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            System.out.println(Bytes.toString(result.getRow()));
        }

    }




    /**
     * 删除数据
     */
    @Test
    public  void  deleteByRowKey() throws IOException {

        Delete delete = new Delete("001".getBytes());
        table.delete(delete);
        table.close();
    }


    @Test
    public void  deleteTable() throws IOException {

        Admin admin = connection.getAdmin();
        admin.disableTable(TableName.valueOf("test"));
        admin.deleteTable(TableName.valueOf("test"));
        admin.close();
    }


}
