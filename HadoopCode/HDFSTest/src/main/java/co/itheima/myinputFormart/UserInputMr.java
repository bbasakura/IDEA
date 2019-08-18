package co.itheima.myinputFormart;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: co.itheima.myinputFormart
 * @ClassName: UserInputMr
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 18:31
 * @Version: 1.0
 */
public class UserInputMr extends Configured implements Tool {
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
        job.setJarByClass(UserInputMr.class);

        //input：默认从HDFS上读取
        job.setInputFormatClass(UserInputFormat.class);//使用自定义的输入类
        Path inputPath = new Path("file:///F:\\BigData\\就业班资料\\视频\\6、江宗海\\day_04Mapreduce案例和Yarn\\Day05_20190802_MapReduce应用案例及YARN原理\\05_资料书籍\\shakespear");
        UserInputFormat.setInputPaths(job, inputPath);

        //Map
        job.setMapperClass(TestMap.class);//指定对应的Mapper类
        job.setMapOutputKeyClass(Text.class);//指定Map输出的key类型宁
        job.setMapOutputValueClass(BytesWritable.class);//指定Map输出value的类型

        //Shuffle：分区、分组、排序、规约
//        job.setCombinerClass(null);//一般等于reduce类
//        job.setPartitionerClass(HashPartitioner.class);//设置分区
//        job.setGroupingComparatorClass(null);//设置自定义的分组
//        job.setSortComparatorClass(null);//设置自定义的排序
        //Reduce
//        job.setReducerClass(null);//设置Reduce的类
        job.setOutputKeyClass(Text.class);//设置reduce输出的key类型
        job.setOutputValueClass(BytesWritable.class);//设置输出的value类型
//        job.setNumReduceTasks(1);//设置reduce的个数，默认为1
        //Ouptut
        Path outputPath = new Path("file:///d:\\hadoop\\output\\myinputformat");
        FileSystem fs = FileSystem.get(this.getConf());
        if(fs.exists(outputPath)){
            //如果输出目录已存在，就干掉
            fs.delete(outputPath,true);
        }
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        SequenceFileOutputFormat.setOutputPath(job,outputPath);

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
        int status = ToolRunner.run(conf,new UserInputMr(),args);
        //根据程序的运行结果退出
        System.exit(status);
    }

    /**
     * 分片任务的处理逻辑
     */
    public static class TestMap extends Mapper<NullWritable,BytesWritable,Text,BytesWritable>{

        /**
         * 主要实现业务逻辑的
         */
        @Override
        protected void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
            //获取文件名
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String fileName = fileSplit.getPath().getName();
            context.write(new Text(fileName),value);
    }
    }

    /**
     * 合并业务处理的逻辑
     */
    public static class  TestReduce extends Reducer<Text,Text,Text,Text> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            super.reduce(key, values, context);
        }
    }
}
