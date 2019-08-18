package com.itheima.MapreduceJoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.MapreduceJoin
 * @ClassName: MapJoin
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 17:10
 * @Version: 1.0
 */
public class MapJoin extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        //创建Job
        Job job = Job.getInstance(this.getConf(), "modle");
        job.setJarByClass(MapJoin.class);
        /**
         * 封装job
         */
        //将商品数据添加缓存中
        job.addCacheFile(new Path("file:///d:\\hadoop\\input\\product.txt").toUri());
        //input：默认从HDFS上读取
//        job.setInputFormatClass(TextInputFormat.class);        设置读取的类的类型，默认 TextInputFormat，一行一行读
        Path inputPath = new Path("file:///d:\\hadoop\\input\\orders.txt");
        FileInputFormat.setInputPaths(job, inputPath);
        //Map
        job.setMapperClass(TestMap.class);//指定对应的Mapper类
        job.setMapOutputKeyClass(Text.class);//指定Map输出的key类型宁
        job.setMapOutputValueClass(Text.class);//指定Map输出value的类型
        //Reduce
        //job.setReducerClass(TestReduce.class);//设置Reduce的类
        job.setOutputKeyClass(Text.class);//设置reduce输出的key类型
        job.setOutputValueClass(Text.class);//设置输出的value类型
        job.setNumReduceTasks(0);//设置reduce的个数，默认为1
        //Ouptut
        Path outputPath = new Path("file:///d:\\hadoop\\output\\mapjoin");
        FileSystem fs = FileSystem.get(this.getConf());
        if (fs.exists(outputPath)) {
            //如果输出目录已存在，就干掉
            fs.delete(outputPath, true);
        }
//        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, outputPath);

        //提交程序
        return job.waitForCompletion(true) ? 0 : -1;
    }

    public static void main(String[] args) throws Exception {
        //实例化一个configuration对象，用于管理当前程序的欧所有配置
        Configuration conf = new Configuration();
        //自定义的配置，手动添加进入conf对象，conf.set(key,vlaue)
        //调用当前类的run方法
        int status = ToolRunner.run(conf, new MapJoin(), args);
        //根据程序的运行结果退出
        System.exit(status);
    }

    /**
     * 分片任务的处理逻辑
     */
    public static class TestMap extends Mapper<LongWritable, Text, Text, Text> {

        private Text outputKey = new Text();
        //构建一个Map集合
        private Map<String, String> maps = new HashMap<String, String>();

        /**
         * 在Map方法之前执行，作为初始化方法
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {

            //从context中取出缓存数据
            URI[] cacheFiles = context.getCacheFiles();

            //从文件中读取出来
            BufferedReader reader = new BufferedReader(new FileReader(cacheFiles[0].getPath()));
            String line;
            while (StringUtils.isNotBlank(line = reader.readLine())) {
                //取值：商品id、商品名称
                String[] product = line.split(",");
                //将所有商品的id和名字全部放入map集合
                maps.put(product[0], product[1]);
            }
            reader.close();
        }

        /**
         * 主要实现业务逻辑的
         * 输出key:商品id
         * 输出value:订单内容/商品的内容
         * context：输出/整个程序全局的配置管理属性
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //获取到订单的数据，取出商品id
            String pid = value.toString().split(",")[2];
            //与分布式缓存中 的 数据进行关联
            String pName = maps.get(pid);
            //输出
            this.outputKey.set(pName);
            context.write(outputKey, value);
        }
    }

    /**
     * Reduce 合并业务处理的逻辑
     */
    public static class TestReduce extends Reducer<Text, Text, Text, Text> {

        private Text outputKey = new Text();
        private Text outpuValue = new Text();

        /**
         * @param key：商品id
         * @param values：要么是商品信息要么是订单信息
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //将该商品对应的所有订单（多条）以及属性（1条）取出
            String productName = "";
            StringBuilder sb = new StringBuilder();
            for (Text value : values) {
                //从属性那条信息中取出商品名称
                if (value.toString().startsWith("p")) {
                    productName = value.toString().split(",")[1];
                } else {
                    //这是一条订单信息
                    sb.append(value.toString() + "\t");
                }
            }
            //赋值
            this.outputKey.set(productName);
            this.outpuValue.set(sb.toString());
            //输出
            context.write(outputKey, outpuValue);
        }
    }

}


