package com.itheima.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @ProjectName: HDFSTest
 * @Package: PACKAGE_NAME
 * @ClassName: com.itheima.wc.WCMR
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/30 0030 19:28
 * @Version: 1.0
 */
public class WCMR  extends Configured implements Tool{

    public static void main(String[] args) throws Exception {

        //创建一个配置对象，用来管理全局的配置文件
        Configuration conf = new Configuration();
        //返回一个整数值，判定是否提交成功
        int status = ToolRunner.run(conf, new WCMR(), args);
        //根据status的值判定什么时候退出系统
        System.exit(status);
    }


    @Override
    public int run(String[] args) throws Exception {
        /**
         * @method: run
         * @return: int
         * @description: 功能：1.创建一个mapreduce的job\
         *                     2.配置这个JOb
         *                     3.提交这个job
         * @Author: sakura
         * @Date: 2019/8/3 0003 7:39
         */

        //创建job
        Job job =  Job.getInstance(this.getConf(),"wc");

        //设置Jar包运行的类
        job.setJarByClass(WCMR.class);

        //配置job，也是mapreduce的五大步骤

        //input
        Path inputPath = new Path("file:///d:\\wordcount.txt");
        FileInputFormat.setInputPaths(job,inputPath);

        //map
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //shuffle

        //redce

        job.setReducerClass(WCReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);//注意在reduce阶段，就输出的是一个OUtPutKey和value

        //Output(此时负责文件的输出)

        Path outPutPath = new Path("file:///d:\\output\\wc\\wc1");
        FileOutputFormat.setOutputPath(job,outPutPath);

        //提交job
        return job.waitForCompletion(true) ? 0:-1;
    }
}






