package co.itheima.myinputFormart;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: co.itheima.myinputFormart
 * @ClassName: UserInputFormat
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 18:12
 * @Version: 1.0
 */
public class UserInputFormat extends FileInputFormat<NullWritable, BytesWritable> {

    @Override
    public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        //实例化一个读取器
        UserRecordReader userRecordReader = new UserRecordReader();
        userRecordReader.initialize(split,context);
        return userRecordReader;
    }
}
