package com.itheima.MapreduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.IOException;
/**
 * MapReduce实现两个文件的join
 * 在Map端实现关联字段的分组
 * 相同关联字段的数据分组后会放入一个迭代器，在Reduce端实现关联Join
 */
public class ReduceJoin extends Configured implements Tool{
    /**
     * 创建Job、封装配置Job、提交Job
     * @param args
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] args) throws Exception {
        //创建Job
        Job job = Job.getInstance(this.getConf(),"ReduceJoin");
        job.setJarByClass(ReduceJoin.class);
        /**
         * 封装job
         */
        //input：默认从HDFS上读取
//        job.setInputFormatClass(TextInputFormat.class);        设置读取的类的类型，默认 TextInputFormat，一行一行读
        Path inputPath = new Path("file:///F:\\BigData\\就业班资料\\视频\\6、江宗海\\day_04Mapreduce案例和Yarn\\Day05_20190802_MapReduce应用案例及YARN原理\\05_资料书籍\\MapReduce Join");
        FileInputFormat.setInputPaths(job, inputPath);
        //Map
        job.setMapperClass(TestMap.class);//指定对应的Mapper类
        job.setMapOutputKeyClass(Text.class);//指定Map输出的key类型宁
        job.setMapOutputValueClass(Text.class);//指定Map输出value的类型
        //Shuffle：分区、分组、排序、规约
//        job.setCombinerClass(null);//一般等于reduce类
//        job.setPartitionerClass(HashPartitioner.class);//设置分区
//        job.setGroupingComparatorClass(null);//设置自定义的分组
//        job.setSortComparatorClass(null);//设置自定义的排序
        //Reduce
        job.setReducerClass(TestReduce.class);//设置Reduce的类
        job.setOutputKeyClass(Text.class);//设置reduce输出的key类型
        job.setOutputValueClass(Text.class);//设置输出的value类型
//        job.setNumReduceTasks(1);//设置reduce的个数，默认为1
        //Ouptut
        Path outputPath = new Path("file:///d:\\hadoop\\output\\Reducejoin");
        FileSystem fs = FileSystem.get(this.getConf());
        if(fs.exists(outputPath)){
            //如果输出目录已存在，就干掉
            fs.delete(outputPath,true);
        }
//        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job,outputPath);

        //提交程序
        return job.waitForCompletion(true) ? 0:-1;
    }

    /**
     * 负责调用当前类的run方法
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //实例化一个configuration对象，用于管理当前程序的欧所有配置
        Configuration conf = new Configuration();
        //自定义的配置，手动添加进入conf对象，conf.set(key,vlaue)
        //调用当前类的run方法
        int status =ToolRunner.run(conf,new ReduceJoin(),args);
        //根据程序的运行结果退出
        System.exit(status);
    }

    /**
     * 分片任务的处理逻辑
     */
    public static class TestMap extends Mapper<LongWritable,Text,Text,Text>{

        private Text outputKey = new Text();


        /**
         * 主要实现业务逻辑的
         * 输出key:商品id
         * 输出value:订单内容/商品的内容
         * context：输出/整个程序全局的配置管理属性
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //获取当前这条数据来自于哪个文件
            FileSplit fileSplit = (FileSplit) context.getInputSplit();//获取当前数据来自哪个分片
            String fileName = fileSplit.getPath().getName();//从分片中获取对应的文件名
            //判断当前数据是订单还是商品，决定了key的取值
            if(fileName.equals("orders.txt")){
                //获取订单中商品id
                String pid = value.toString().split(",")[2];
                this.outputKey.set(pid);
                //输出
                context.write(this.outputKey,value);
            }else{
                //获取商品属性中的商品id
                String pid = value.toString().split(",")[0];
                this.outputKey.set(pid);
                //输出
                context.write(this.outputKey,value);
            }
        }
    }

    /**
     * 合并业务处理的逻辑
     */
    public static class  TestReduce extends Reducer<Text,Text,Text,Text>{

        private Text outputKey = new Text();
        private Text outpuValue = new Text();

        /**
         * @param key：商品id
         * @param values：要么是商品信息要么是订单信息
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //将该商品对应的所有订单（多条）以及属性（1条）取出
            String productName = "";
            StringBuilder sb = new StringBuilder();
            for (Text value : values) {
                //从属性那条信息中取出商品名称
                if(value.toString().startsWith("p")){
                    productName = value.toString().split(",")[1];
                }else{
                    //这是一条订单信息
                    sb.append(value.toString()+"\t");
                }
            }
            //赋值
            this.outputKey.set(productName);
            this.outpuValue.set(sb.toString());
            //输出
            context.write(outputKey,outpuValue);
        }
    }

}
