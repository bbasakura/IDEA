package com.itheima.Db;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Db
 * @ClassName: ReadFromMysql
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 19:14
 * @Version: 1.0
 */
public class ReadFromMysql {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        //连接的配置
        DBConfiguration.configureDB(
                conf,
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test",
                "root",
                "root");
        Job job = Job.getInstance(conf, "read");
        job.setJarByClass(ReadFromMysql.class);
        //设置输入
        job.setInputFormatClass(DBInputFormat.class);
        String[] fields = {"id", "name"};
        DBInputFormat.setInput(
                job,
                DBReader.class,
                "emp",
                null,
                "id",
                fields
        );
        //设置map
        job.setMapperClass(ReadMap.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        //设置reduce
        job.setNumReduceTasks(0);
        //设置输出
        Path outputPath = new Path("file:///d:\\hadoop\\output\\DBoutput");
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileOutputFormat.setOutputPath(job, outputPath);
        //提交
        job.waitForCompletion(true);
    }

    public static class ReadMap extends Mapper<LongWritable, DBReader, IntWritable, Text> {
        @Override
        protected void map(LongWritable key, DBReader value,
                           Mapper<LongWritable, DBReader, IntWritable, Text>.Context context)
                throws IOException, InterruptedException {
            context.write(new IntWritable(value.getId()), new Text(value.getName()));
        }
    }

}
