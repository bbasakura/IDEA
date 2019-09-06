package com.itheima.Writable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Writable
 * @ClassName: SecondarySort
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 11:46
 * @Version: 1.0
 */
public class SecondarySort extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        int status = ToolRunner.run(conf, new SecondarySort(), args);
        System.exit(status);
    }

    @Override
    public int run(String[] args) throws Exception {
        //创建JOb
        Job job = Job.getInstance(this.getConf(), "writable");
        job.setJarByClass(SecondarySort.class);

        job.setInputFormatClass(TextInputFormat.class);
        Path inputPath = new Path("file:///D:\\hadoop\\input\\writable.txt");
        FileInputFormat.setInputPaths(job, inputPath);

        Path outPath = new Path("file:///D:\\hadoop\\output\\writable");
        FileOutputFormat.setOutputPath(job, outPath);
        FileSystem fs = FileSystem.get(this.getConf());

        //如果输出的目录已经存在，就干掉

        if(fs.exists(outPath)){

            fs.delete(outPath);
        }

        //map设置
        job.setMapperClass(SortMapper.class);
        job.setMapOutputKeyClass(PairWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        //reduce设置
        job.setReducerClass(SortReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //提交job
        return job.waitForCompletion(true) ? 0 : -1;
    }
}
