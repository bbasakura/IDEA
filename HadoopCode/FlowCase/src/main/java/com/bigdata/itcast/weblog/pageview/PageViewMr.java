package com.bigdata.itcast.weblog.pageview;

import com.bigdata.itcast.weblog.writable.PageViewBean;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 构建pageveiw的分析模型，添加sessionID，step，stay_length
 * Created by Frank on 2019/8/1.
 */
public class PageViewMr extends Configured implements Tool{


    /**
     * 创建Job、封装配置Job、提交Job
     * @param args
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] args) throws Exception {
        //创建Job
        Job job = Job.getInstance(this.getConf(),"pageview");
        job.setJarByClass(PageViewMr.class);
        /**
         * 封装job
         */
        //input：默认从HDFS上读取
        Path inputPath = new Path("/flume/nginx/output/etl");
        FileInputFormat.setInputPaths(job, inputPath);
        //Map
        job.setMapperClass(PageViewMap.class);//指定对应的Mapper类
        job.setMapOutputKeyClass(Text.class);//指定Map输出的key类型宁
        job.setMapOutputValueClass(WebLogBean.class);//指定Map输出value的类型
        //Shuffle：分区、分组、排序、规约
        //Reduce
        job.setReducerClass(PageViewReduce.class);//设置Reduce的类
        job.setOutputKeyClass(NullWritable.class);//设置reduce输出的key类型
        job.setOutputValueClass(PageViewBean.class);//设置输出的value类型
//        job.setNumReduceTasks(1);//设置reduce的个数，默认为1
        //Ouptut
        Path outputPath = new Path("/flume/nginx/output/pageview");
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
        int status =ToolRunner.run(conf,new PageViewMr(),args);
        //根据程序的运行结果退出
        System.exit(status);
    }

    /**
     * 分片任务的处理逻辑
     */
    public static class PageViewMap extends Mapper<LongWritable,Text,Text,WebLogBean>{

        private Text outputKey = new Text();
        private WebLogBean outputValue = new WebLogBean();

        /**
         * 主要实现业务逻辑的
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            //分割给keyvalue赋值
            String[] items = line.split("\001");
            //判断
            if(items[0].equals("true")){
                //给key赋值
                this.outputKey.set(items[1]);
                //给value赋值
                this.outputValue.setAll(true,items[1],items[2],items[3],items[4],items[5],items[6],items[7],items[8]);
                //输出
                context.write(this.outputKey,this.outputValue);
            }else{
                return;
            }
        }
    }

    /**
     * 合并业务处理的逻辑
     */
    public static class  PageViewReduce extends Reducer<Text,WebLogBean,NullWritable,PageViewBean>{

        private NullWritable outputKey = NullWritable.get();
        private  PageViewBean outputValue = new PageViewBean();
        private ArrayList<WebLogBean> beans = new ArrayList<WebLogBean>();
        private SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        /**
         * 每个ip对应的所有访问记录
         * @param key
         * @param values
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void reduce(Text key, Iterable<WebLogBean> values, Context context) throws IOException, InterruptedException {
            //将数据放入一个集合中
            beans.clear();
            for (WebLogBean value : values) {
                //如果集合中放入的是Hadoop序列化类型
                WebLogBean bean = new WebLogBean();
                try {
                    BeanUtils.copyProperties(bean,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                beans.add(bean);
            }
            //对集合中的每个元素按照时间进行排序
            Collections.sort(beans, new Comparator<WebLogBean>() {
                @Override
                public int compare(WebLogBean o1, WebLogBean o2) {
                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = transDate(o1.getS_time());
                        d2 = transDate(o2.getS_time());
                        if(d1 == null || d2 == null){
                            return 0;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return d1.compareTo(d2);
                }
            });

            //添加三个字段输出
            String sessionId = UUID.randomUUID().toString();
            int step = 1;
            //如果这个ip只有一条数据，直接输出
            if(beans.size() == 1){
                this.outputValue.set(
                        sessionId,
                        key.toString(),
                        beans.get(0).getS_time(),
                        beans.get(0).getUrl(),
                        step,
                        "60",//如果不能计算停留时间，给默认值
                        beans.get(0).getHttp_ref(),
                        beans.get(0).getUser_agent(),
                        beans.get(0).getBody_size(),
                        beans.get(0).getStatus()
                        );
                context.write(this.outputKey,this.outputValue);
                return;
            }
            //如果有多条，取每一条，计算停留时间，然后输出
            for(int i = 0;i<beans.size();i++){
                //如果取到第一条，直接取下一条
                if(i == 0){
                    continue;
                }
                //当取到下一条，统计上一条的停留时间，输出上一条
                try {
                    long stay = diffTime(transDate(beans.get(i).getS_time()),transDate(beans.get(i-1).getS_time()));
                    long length = stay /1000;
                    if(length >= 30*60){
                        this.outputValue.set(
                                sessionId,
                                key.toString(),
                                beans.get(i-1).getS_time(),
                                beans.get(i-1).getUrl(),
                                step,
                                "60",//如果不能计算停留时间，给默认值
                                beans.get(i-1).getHttp_ref(),
                                beans.get(i-1).getUser_agent(),
                                beans.get(i-1).getBody_size(),
                                beans.get(i-1).getStatus()
                        );
                        context.write(this.outputKey, this.outputValue);
                        sessionId = UUID.randomUUID().toString();
                        step = 1;
                    } else {
                        this.outputValue.set(
                                sessionId,
                                key.toString(),
                                beans.get(i-1).getS_time(),
                                beans.get(i-1).getUrl(),
                                step,
                                length+"",//如果不能计算停留时间，给默认值
                                beans.get(i-1).getHttp_ref(),
                                beans.get(i-1).getUser_agent(),
                                beans.get(i-1).getBody_size(),
                                beans.get(i-1).getStatus()
                        );
                        context.write(this.outputKey,this.outputValue);
                        step++;
                    }
                    //如果取到了最后一条，除了输出上一条以外，还要输出自己
                    if(i == beans.size() -1){
                        this.outputValue.set(
                                sessionId,
                                key.toString(),
                                beans.get(i).getS_time(),
                                beans.get(i).getUrl(),
                                step,
                                "60",//如果不能计算停留时间，给默认值
                                beans.get(i).getHttp_ref(),
                                beans.get(i).getUser_agent(),
                                beans.get(i).getBody_size(),
                                beans.get(i).getStatus()
                        );
                        context.write(this.outputKey,this.outputValue);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }

        /**
         * 计算时间差值
         * @param d1
         * @param d2
         * @return
         */
        public long diffTime(Date d1 ,Date d2){
            return  d1.getTime() - d2.getTime();
        }
        public Date transDate(String inputTime) throws ParseException {
            return input.parse(inputTime);
        }


    }

}
