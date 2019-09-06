package com.itheima.myoutputFormart;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.myoutputFormart
 * @ClassName: UserOutputFormat
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 18:54
 * @Version: 1.0
 */
public class UserOutputFormat extends FileOutputFormat<Text, NullWritable> {
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        //获取文件系统
        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        FSDataOutputStream goodContent = fs.create(new Path("file:///d:\\hadoop\\output\\outputformat\\good\\good.txt"));
        FSDataOutputStream badContent = fs.create(new Path("file:///d:\\hadoop\\output\\outputformat\\bad\\bad.txt"));
        //构建recordWrite
        UserRecordWriter userRecordWriter = new UserRecordWriter(goodContent, badContent);
        return userRecordWriter;
    }


}

