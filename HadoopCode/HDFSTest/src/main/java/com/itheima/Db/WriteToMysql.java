package com.itheima.Db;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Db
 * @ClassName: WriteToMysql
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 19:16
 * @Version: 1.0
 */
public class WriteToMysql {

    public static void main(String[] args) throws  Exception{
        Configuration conf = new Configuration();
        DBConfiguration.configureDB(
                conf,
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test",
                "root",
                "root");
        Job job = Job.getInstance(conf, "read");
        job.setJarByClass(WriteToMysql.class);
        //设置输入
        Path inputPaths = new Path("file:///d:\\hadoop\\input\\Db.txt");
        FileInputFormat.setInputPaths(job, inputPaths);
        //设置map
        job.setMapperClass(WriteMap.class);
        job.setMapOutputKeyClass(DBReader.class);
        job.setMapOutputValueClass(NullWritable.class);
        //设置reduce
        job.setNumReduceTasks(0);
        //设置输出
        job.setOutputFormatClass(DBOutputFormat.class);
        String[] fields = {"id","name"};
        //要求提前创建好表
        DBOutputFormat.setOutput(job, "emp2", fields);
        //提交
        job.waitForCompletion(true);
    }

    public static class WriteMap extends Mapper<LongWritable, Text, DBReader, NullWritable>{

        private DBReader outputKey = new DBReader();
        private NullWritable outputValue = NullWritable.get();

        @Override
        protected void map(LongWritable key, Text value,
                           Mapper<LongWritable, Text, DBReader, NullWritable>.Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            this.outputKey.setId(Integer.valueOf(line.split("\t")[0]));
            this.outputKey.setName(line.split("\t")[1]);
            context.write(outputKey, outputValue);
        }
    }
}
