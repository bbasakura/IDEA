package com.ithaima.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @ProjectName: hbaseTest02
 * @Package: com.ithaima.hbase
 * @ClassName: HbaseMR
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/15 0015 20:15
 * @Version: 1.0
 */
public class HbaseMR extends Configured implements Tool {


    //输入到map的是什么，一个文件，类型Text，put?????????????????????????????????
    public static class HBaseMapper extends TableMapper<Text, Put> {
        /**
         * @param key     我们的主键rowkey
         * @param value   我们一行数据所有列的值都封装在value里面了
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

            //ImmutableBytesWritable：不可变的字节封装类，字节-----rowkry
            byte[] bytes = key.get();
            //转换为String    rowkey
            String rowKey = Bytes.toString(bytes);


            Put put = new Put(key.get());

            Cell[] cells = value.rawCells();
            for (Cell cell : cells) {
                if ("f1".equals(Bytes.toString(CellUtil.cloneFamily(cell)))) {
                    if ("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                        put.add(cell);
                    }
                    if ("age".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))) {
                        put.add(cell);
                    }
                }
            }
            if (!put.isEmpty()) {
                context.write(new Text(rowKey), put);
            }
        }
    }

    public static class HBaseReducer extends TableReducer<Text, Put, ImmutableBytesWritable> {
        @Override
        protected void reduce(Text key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
            for (Put value : values) {
                context.write(null, value);
            }
        }
    }


    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(super.getConf(), "HbaseMr");
        job.setJarByClass(this.getClass());
        Scan scan = new Scan();
        scan.setCaching(500);
        scan.setCacheBlocks(false);
        //使用TableMapReduceUtil 工具类来初始化我们的mapper
        TableMapReduceUtil.initTableMapperJob(TableName.valueOf("test000"), scan, HBaseMapper.class, Text.class, Put.class, job);
        //使用TableMapReduceUtil 工具类来初始化我们的reducer
        TableMapReduceUtil.initTableReducerJob("test001", HBaseReducer.class, job);

        job.setNumReduceTasks(1);

        boolean b = job.waitForCompletion(true);
        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        //创建HBaseConfiguration配置
        Configuration configuration = HBaseConfiguration.create();
        int run = ToolRunner.run(configuration, new HbaseMR(), args);
        System.exit(run);

    }

}



