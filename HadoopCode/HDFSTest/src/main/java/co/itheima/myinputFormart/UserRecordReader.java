package co.itheima.myinputFormart;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: co.itheima.myinputFormart
 * @ClassName: UserRecordReader
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 18:15
 * @Version: 1.0
 */
public class UserRecordReader extends RecordReader<NullWritable, BytesWritable> {
    //定义需要返回的属性
    private NullWritable nullWritable = NullWritable.get();
    private BytesWritable bytesWritable = new BytesWritable();

    //定义一个分片用于接收传递的 分片数据
    private  FileSplit fileSplit;
    //获取configuration

    private  Configuration conf;
    //设置标志变量
    private boolean isProcess = false;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        /**
         * 初始化方法,只调用一次
         * @param split：当前传进来的数据的位置
         * @param context：管理所有配置以及上下文
         * @throws IOException
         * @throws InterruptedException
         */
        this.fileSplit = (FileSplit) split;
        this.conf = context.getConfiguration();
    }


    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        /**
         * 用于表示当前是否读取完，返回true表示读取完了，返回false表示没有读取完，真正实现读操作的地方
         * @return
         * @throws IOException
         * @throws InterruptedException
         */

        if(!isProcess){
            //通过分片读取对应的数据
            Path path = fileSplit.getPath();
            //构建文件系统打开
            FileSystem fs = FileSystem.get(conf);
            FSDataInputStream open = fs.open(path);
            //将输入流放入字节数组
            byte[] bytes = new byte[(int) fileSplit.getLength()];
            IOUtils.readFully(open,bytes,0, (int) fileSplit.getLength());
            this.bytesWritable.set(bytes,0, (int) fileSplit.getLength());
            //关闭
            IOUtils.closeStream(open);
            fs.close();
            //表示当前已经读完
            isProcess = true;
            return true;
        }
        return false;
    }

    /**
     * 返回当前的key
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return nullWritable;
    }

    /**
     * 返回当前的value
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return bytesWritable;
    }


    @Override
    public float getProgress() throws IOException, InterruptedException {
        //该方法用户获取进度
        return 0;
    }

    @Override
    public void close() throws IOException {
        //关闭资源
    }
}
