package com.itheima.myoutputFormart;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.myoutputFormart
 * @ClassName: UserRecordWriter
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 18:52
 * @Version: 1.0
 */
public class UserRecordWriter  extends RecordWriter<Text,NullWritable> {


    private FSDataOutputStream goodContent;
    private FSDataOutputStream badContent;

    public UserRecordWriter(){

    }
    public UserRecordWriter(FSDataOutputStream goodContent,FSDataOutputStream badContent){
        this.goodContent = goodContent;
        this.badContent = badContent;
    }

    /**
     * 决定怎么输出的方法
     * @param key
     * @param value
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        //获取当前数据的评论
        String comment = key.toString().split("\t")[9];
        if(comment.equals("2")){
            //如果等于2就是差评
            this.badContent.write(key.toString().getBytes());
            //输出完记得换行
            this.badContent.write("\r\n".getBytes());
        }else{
            this.goodContent.write(key.toString().getBytes());
            this.goodContent.write("\r\n".getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        //关闭资源
        IOUtils.closeStream(goodContent);
        IOUtils.closeStream(badContent);
    }
}
