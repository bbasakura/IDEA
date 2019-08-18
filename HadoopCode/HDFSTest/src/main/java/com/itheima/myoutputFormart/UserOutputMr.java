package com.itheima.myoutputFormart;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.myoutputFormart
 * @ClassName: UserOutputMr
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 18:57
 * @Version: 1.0
 */
public class UserOutputMr extends Configured implements Tool {
    /**
     * 创建Job、封装配置Job、提交Job
     * @param args
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] args) throws Exception {
        //创建Job
        Job job = Job.getInstance(this.getConf(),"modle");
        job.setJarByClass(UserOutputMr.class);
        /**
         * 封装job
         */
        //input：默认从HDFS上读取
//        job.setInputFormatClass(TextInputFormat.class);        设置读取的类的类型，默认 TextInputFormat，一行一行读
        Path inputPath = new Path("file:///F:\\BigData\\就业班资料\\视频\\6、江宗海\\day_04Mapreduce案例和Yarn\\Day05_20190802_MapReduce应用案例及YARN原理\\05_资料书籍\\用户评论");
        FileInputFormat.setInputPaths(job, inputPath);
        //Map
        job.setMapperClass(TestMap.class);//指定对应的Mapper类
        job.setMapOutputKeyClass(Text.class);//指定Map输出的key类型宁
        job.setMapOutputValueClass(NullWritable.class);//指定Map输出value的类型
        //Shuffle：分区、分组、排序、规约
//        job.setCombinerClass(null);//一般等于reduce类
//        job.setPartitionerClass(HashPartitioner.class);//设置分区
//        job.setGroupingComparatorClass(null);//设置自定义的分组
//        job.setSortComparatorClass(null);//设置自定义的排序
        //Reduce
        job.setReducerClass(TestReduce.class);//设置Reduce的类
        job.setOutputKeyClass(Text.class);//设置reduce输出的key类型
        job.setOutputValueClass(NullWritable.class);//设置输出的value类型
//        job.setNumReduceTasks(1);//设置reduce的个数，默认为1
        //Ouptut（自定义输出的时候，此处的配置不起作用,但是一定要配置，不然会删库跑路！！！！！！！！！！！！）
        Path outputPath = new Path("file:///d:\\hadoop\\output\\myout");
        FileSystem fs = FileSystem.get(this.getConf());
        if(fs.exists(outputPath)){
            //如果输出目录已存在，就干掉
            fs.delete(outputPath,true);
        }

        //真正的决定输出在这里
        job.setOutputFormatClass(UserOutputFormat.class);  //输出的类型
        UserOutputFormat.setOutputPath(job,outputPath);    //输出到哪里

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
        int status = ToolRunner.run(conf,new UserOutputMr(),args);
        //根据程序的运行结果退出
        System.exit(status);
    }

    /**
     * 分片任务的处理逻辑
     */
    public static class TestMap extends Mapper<LongWritable,Text,Text,NullWritable> {

        /**
         * 主要实现业务逻辑的
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //读取到数据时，直接输出
            context.write(value, NullWritable.get());
        }
    }

    /**
     * 合并业务处理的逻辑
     */
    public static class  TestReduce extends Reducer<Text,NullWritable,Text,NullWritable> {

        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key,NullWritable.get());
        }
    }


}
