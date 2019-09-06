package com.bigdata.itcast.weblog.etl;

import com.bigdata.itcast.weblog.util.TimeUtil;
import com.bigdata.itcast.weblog.util.WebLogParseUtil;
import com.bigdata.itcast.weblog.writable.WebLogBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 实现对原始数据进行ETL：过滤、补全、格式化
 * Created by Frank on 2019/8/9.
 */
public class ETLMr extends Configured implements Tool{
    @Override
    public int run(String[] args) throws Exception {
        //创建job
        Job job = Job.getInstance(this.getConf(),"etl");
        job.setJarByClass(ETLMr.class);
        //配置job
        //默认读取昨天的数据，数据是由flume上传的，按天划分数据:/flume/nginx/2019-08-08
        //判断是否给定日期参数，如果给定日期，就处理对应日期的数据，如果没有，就默认处理昨天的数据
        String inputDate = getDate(args);
        Path inputPath = new Path("/flume/nginx/+ inputDate");
        FileInputFormat.setInputPaths(job,inputPath );
        job.setMapperClass(ETLMap.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(WebLogBean.class);
        //etl程序中没有shuffle以及reduce阶段
        job.setNumReduceTasks(0);//如果只有map阶段，要将reduce个数设置为0
        //按照对应的日期存放结果
        Path outputPath = new Path("/flume/nginx/output/etl");
        FileSystem fs = FileSystem.get(this.getConf());
        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }
        FileOutputFormat.setOutputPath(job,outputPath);
        //提交job
        return job.waitForCompletion(true) ? 0:-1;
    }

    public  String getDate(String[] args) {
        String time = null;
        //判断参数中是否有传递日期： -d yyyy-MM-dd
        for(int i = 0;i<args.length;i++){
            if(args[i].equals("-d")){
                time = args[i+1];
            }
        }
        //判定日期是否为空或者是否合法，如果为空或者不合法，就给定默认的日期
        if(StringUtils.isBlank(time) || !TimeUtil.isValidateRunningDate(time)){
            time = TimeUtil.getYesterday();
        }

        return time;
    }

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            int status = ToolRunner.run(conf,new ETLMr(),args);
            System.exit(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ETLMap extends Mapper<LongWritable,Text,NullWritable,WebLogBean>{

        private NullWritable outputKey = NullWritable.get();
        private WebLogBean outputValue = new WebLogBean();
        private WebLogParseUtil webLogParseUtil = new WebLogParseUtil();
        private Set<String> pages = new HashSet<String>();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            //将所有非静态页面的地址放入集合
            pages.add("/about");
            pages.add("/black-ip-list/");
            pages.add("/cassandra-clustor/");
            pages.add("/finance-rhive-repurchase/");
            pages.add("/hadoop-family-roadmap/");
            pages.add("/hadoop-hive-intro/");
            pages.add("/hadoop-zookeeper-intro/");
            pages.add("/hadoop-mahout-roadmap/");
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            //调用工具类对每行的数据进行处理
            outputValue = webLogParseUtil.logParseHandle(line);
            //过滤静态页面
            webLogParseUtil.filterStaticPage(outputValue,pages);
            //输出
            context.write(outputKey,outputValue);

        }
    }

}
