package com.ithaima.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @ProjectName: hbaseTest02
 * @Package: com.ithaima.hbase
 * @ClassName: HBaseLoad
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/15 0015 21:19
 * @Version: 1.0
 */


public class HBaseLoad extends Configured implements Tool {



    //main方法
    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        int run = ToolRunner.run(configuration, new HBaseLoad(), args);
        System.exit(run);
    }

    @Override
    public int run(String[] args) throws Exception {

        //输入的路径
        final String INPUT_PATH = "hdfs://hadoop01:8020/hbase/input";
        //输出的路径
        final String OUTPUT_PATH = "hdfs://hadoop01:8020/hbase/output_hfile";
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        //建表干嘛？？？？？？？
        Table table = connection.getTable(TableName.valueOf("test001"));
        Job job = Job.getInstance(conf);
        job.setJarByClass(HBaseLoad.class);
        job.setMapperClass(LoadMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        job.setOutputFormatClass(HFileOutputFormat2.class);

        //难道是同时写入？？？？？

        //Answer：需求：将我们hdfs上面的这个路径/hbase/input/user.txt的数据文件，
        // 转换成HFile格式，然后load到myuser2这张表里面去

        HFileOutputFormat2.configureIncrementalLoad(job, table, connection.getRegionLocator(TableName.valueOf("test001")));
        FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
        FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
        boolean b = job.waitForCompletion(true);
        return b ? 0 : 1;
    }
    public class LoadMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

        @Override
        protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("\t");

            Put put = new Put(Bytes.toBytes(split[0]));

            put.addColumn("f1".getBytes(), "name".getBytes(), split[1].getBytes());

            put.addColumn("f1".getBytes(), "age".getBytes(), Bytes.toBytes(Integer.parseInt(split[2])));

            context.write(new ImmutableBytesWritable(Bytes.toBytes(split[0])), put);
        }


    }



}




