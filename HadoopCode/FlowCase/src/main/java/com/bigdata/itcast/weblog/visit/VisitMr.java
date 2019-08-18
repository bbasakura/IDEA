package com.bigdata.itcast.weblog.visit;

import com.bigdata.itcast.weblog.writable.PageViewBean;
import com.bigdata.itcast.weblog.writable.VisitBean;
import com.bigdata.itcast.weblog.writable.WebLogBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 构建visit模型，用于会话分析，对每个session进行统计
 * Created by Frank on 2019/8/1.
 */
public class VisitMr extends Configured implements Tool{


    /**
     * 创建Job、封装配置Job、提交Job
     * @param args
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] args) throws Exception {
        //创建Job
        Job job = Job.getInstance(this.getConf(),"visit");
        job.setJarByClass(VisitMr.class);
        /**
         * 封装job
         */
        //input：默认从HDFS上读取
        Path inputPath = new Path("/flume/nginx/output/pageview");
        FileInputFormat.setInputPaths(job, inputPath);
        //Map
        job.setMapperClass(VisitMap.class);//指定对应的Mapper类
        job.setMapOutputKeyClass(Text.class);//指定Map输出的key类型宁
        job.setMapOutputValueClass(PageViewBean.class);//指定Map输出value的类型
        //Reduce
        job.setReducerClass(VisitReduce.class);//设置Reduce的类
        job.setOutputKeyClass(NullWritable.class);//设置reduce输出的key类型
        job.setOutputValueClass(VisitBean.class);//设置输出的value类型
//        job.setNumReduceTasks(1);//设置reduce的个数，默认为1
        //Ouptut
        Path outputPath = new Path("/flume/nginx/output/visit");
        FileSystem fs = FileSystem.get(this.getConf());
        if(fs.exists(outputPath)){
            //如果输出目录已存在，就干掉
            fs.delete(outputPath,true);
        }
//        job.setOutputFormatClass(TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job,outputPath);

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
        int status =ToolRunner.run(conf,new VisitMr(),args);
        //根据程序的运行结果退出
        System.exit(status);
    }

    /**
     * 分片任务的处理逻辑
     */
    public static class VisitMap extends Mapper<LongWritable,Text,Text,PageViewBean>{

        private Text outputKey = new Text();
        private PageViewBean outputValue = new PageViewBean();

        /**
         * 以session作为key，将所有相同session的数据放入一个迭代器
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String[] items = line.split("\001");
            this.outputKey.set(items[0]);
            this.outputValue.set(items[0],items[1],items[2],items[3],Integer.valueOf(items[4]),items[5],items[6],items[7],items[8],items[9]);
            context.write(this.outputKey,this.outputValue);
        }
    }

    /**
     * 合并业务处理的逻辑
     */
    public static class  VisitReduce extends Reducer<Text,PageViewBean,NullWritable,VisitBean>{


        private NullWritable outputKey = NullWritable.get();
        private VisitBean outputValue = new VisitBean();

        private ArrayList<PageViewBean> beans = new ArrayList<PageViewBean>();

        @Override
        protected void reduce(Text key, Iterable<PageViewBean> values, Context context) throws IOException, InterruptedException {
            //放入一个集合
            beans.clear();
            for (PageViewBean value : values) {
                //如果集合中放入的是Hadoop序列化类型
                PageViewBean bean = new PageViewBean();
                try {
                    BeanUtils.copyProperties(bean, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                beans.add(bean);
            }
            //排序
            Collections.sort(beans, new Comparator<PageViewBean>() {
                @Override
                public int compare(PageViewBean o1, PageViewBean o2) {
                    return o1.getStep() > o2.getStep() ? 1:-1;
                }
            });
            //封装输出
            this.outputValue.setSession(key.toString());
            this.outputValue.setIp(beans.get(0).getIp());
            this.outputValue.setInTime(beans.get(0).getTime());
            this.outputValue.setOutTime(beans.get(beans.size() - 1).getTime());
            this.outputValue.setInPage(beans.get(0).getRequest());
            this.outputValue.setOutPage(beans.get(beans.size() - 1).getRequest());
            this.outputValue.setRefere(beans.get(0).getHttp_ref());
            this.outputValue.setPageNum(beans.size());
            context.write(this.outputKey,this.outputValue);
        }
    }

}
