package com.ithaima.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @ProjectName: hbaseTest02
 * @Package: com.ithaima.hbase
 * @ClassName: Hdfs2Hbase
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/15 0015 20:54
 * @Version: 1.0
 */
public class Hdfs2Hbase  extends Configured implements Tool {




    //mr的run 方法
    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(super.getConf(), "hdfs2Hbase");
        job.setJarByClass(Hdfs2Hbase.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://hadoop01:8020/hbase/input"));
        job.setMapperClass(HdfsMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);


        //表的工具类
        TableMapReduceUtil.initTableReducerJob("test001",HBaseReducer.class,job);
        job.setNumReduceTasks(1);
        boolean b = job.waitForCompletion(true);

        return b?0:1;
    }


    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        int run = ToolRunner.run(configuration, new Hdfs2Hbase(), args);
        System.exit(run);
    }


    public static class HdfsMapper extends Mapper<LongWritable,Text,Text,NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            //NullWritable.get()得到的是一个null?????????

            //直接传递不做处理，值全在value中封装的

            //这地方将key和value做了一个调换位置，所以reduce的key就是这一行的值
            context.write(value,NullWritable.get());
        }
    }

    public static class HBaseReducer extends TableReducer<Text,NullWritable,ImmutableBytesWritable> {

        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

            String[] split = key.toString().split("\t");

            //put里面放的什么值？ ？  rowkey？？？？？？？？？？？？？？？？？？？？？？？？？？？
            Put put = new Put(Bytes.toBytes(split[0]));
            //有添加了列族，再次封装的put

            put.addColumn("f1".getBytes(),"name".getBytes(),split[1].getBytes());
            put.addColumn("f1".getBytes(),"age".getBytes(),Bytes.toBytes(Integer.parseInt(split[2])));

            context.write(new ImmutableBytesWritable(Bytes.toBytes(split[0])),put);
        }
    }

}

