package com.itheima.Mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Mapreduce
 * @ClassName: SumFlow
 * @Description:  整个Mapreduce的程序包含最基本的三个类
 * Driver：整个Job的构建，封装，和运行  包含两个方法（main 和 run（实现接口重写的））
 *
 * Map：第一次分时处理的逻辑  主要的方法 map（也是重写的）
 *
 * Reduce：合并时的处理逻辑  方法  Reduce（chongxie
 *
 * * @Author: sakura
 * @CreateDate: 2019/8/3 0003 8:20
 * @Version: 1.0
 */
public class WCIntegrate extends Configured implements Tool {


    //负责调用当前类的run方法
    public static void main(String[] args) throws Exception {
        //1.管理当前程序的全局配置文件
        Configuration conf = new Configuration();
        //2.自定义的配置，手动添加进入conf对象，conf.set(key,vlaue)
        //就是当前的程序需要一些特殊的配置时，用它来完成

        //3.负调用run方法
        int status = ToolRunner.run(conf, new WCIntegrate(), args);

        //4.根据程序运行的结果退出

        System.exit(status);
    }

    @Override
    public int run(String[] args) throws Exception {

        /**
         * @method: run
         * @return: int
         * @description:  属于Driver中的方法，主要是一堆配置关于JOb的
         * @Author: sakura
         * @Date: 2019/8/3 0003 8:27
         */
        //1.创建一个job(两句)
        Job job = Job.getInstance(this.getConf(),"WCIntegrate");
        //设置Jar运行的了类型，必须要设置
        job.setJarByClass(WCIntegrate.class);

        //2.封装job（五个阶段）

        //2.1 inpiut(两句)默认从HDFS上读取数据
        //job.setInputFormatClass(TextInputFormat.class);  //设置读取的类的类型，默认是TextInputFormat

        Path inputPath = new Path("file:///D:\\wordcount.txt");

        FileInputFormat.setInputPaths(job,inputPath);

        //2.2 Map(三句话)

        job.setMapperClass(TestMap.class);  // 指定对应的Mapper类

        job.setMapOutputKeyClass(Text.class); // 指定map输出的key的类型

        job.setMapOutputValueClass(IntWritable.class); // 指定map输出的value的类型

        //2.3 shuffle: 分区 分组 排序 规约
        //job.setCombinerClass(null);  //规约 ，一般等同于reducelei

        //job.setPartitionerClass(HashPartitioner.class); //自定义分区

        //job.setGroupingComparatorClass(null); //设置自定义的分组

        //job.setSortComparatorClass(null); //设置自定义的排序

        //2.4 reduce（四句话）
        job.setReducerClass(TestReduce.class); //设置reduce的类

        job.setOutputKeyClass(Text.class);  //设置reduce输出的key的类型

        job.setOutputValueClass(IntWritable.class); // 设置reduce输出的values的类型

        //job.setNumReduceTasks(1);//设置reduce的个数，默认为1

        //2.5 output
        Path outputPath = new Path("file:///d:\\output\\wc\\wc2");
        FileSystem fs = FileSystem.get(this.getConf());

        //如果输出的目录已经存在，就干掉

        if(fs.exists(outputPath)){

            fs.delete(outputPath);
        }

        //job.setOutputFormatClass(TextOutputFormat.class); //设置输出的类型，默认的是TextOutputFormat

        FileOutputFormat.setOutputPath(job,outputPath);

        //提交程序
        return job.waitForCompletion(true) ? 0:-1;
    }

    //map第一次分片任务处理逻辑
    public  static class TestMap extends Mapper<LongWritable,Text,Text,IntWritable>{

        //定义全局变量输出用
        private  Text outputKey = new Text();

        private IntWritable outputValue =new IntWritable(1);

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            /**
             * @method: setup
             * @return: void
             * @description: 在map方法之前运行，用于初始化
             * @Author: sakura
             * @Date: 2019/8/3 0003 9:29
             */
        }
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            /**
             * @method: map
             * @return: void
             * @description: 主要实现业务逻辑的
             * @Author: sakura
             * @Date: 2019/8/3 0003 9:30
             */
            String line = value.toString();
            String[] words = line.split(" ");

            for (String word : words) {
                this.outputKey.set(word);
                //输出

                context.write(this.outputKey,this.outputValue);
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {

            /**
             * @method: cleanup
             * @return: void
             * @description: 在map之后执行，主要用于关闭资源
             * @Author: sakura
             * @Date: 2019/8/3 0003 9:31
             */

        }
    }

    //reduce
    public static class TestReduce extends Reducer<Text,IntWritable,Text,IntWritable> {

        //定义全局变量输出用
        private IntWritable outputValue =new IntWritable();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {

        }


        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum =0;
            //取出所有的相同key的value值相加
            for (IntWritable value : values) {
                sum+=value.get();
            }
            //输出
            this.outputValue.set(sum);
            context.write(key,outputValue);
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {


        }
    }














}


