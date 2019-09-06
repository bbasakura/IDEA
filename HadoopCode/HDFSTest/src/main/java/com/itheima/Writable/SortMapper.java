package com.itheima.Writable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Writable
 * @ClassName: SortMapper
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 11:25
 * @Version: 1.0
 */
public class SortMapper extends Mapper<LongWritable,Text,PairWritable,IntWritable> {

    //定义map的输出的的key和value

    private PairWritable MapoutputKey = new PairWritable();
    private IntWritable MapoutputValue = new IntWritable();


    //map方法


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //自定义计数器
        Counter counter = context.getCounter("MR_COUNT", "MapRecordCounter");
        counter.increment(1L);



        String line =value.toString();
        String[] strs=line.split("\t");
        //设置组合的key和value
        MapoutputKey.setAll(strs[0],Integer.valueOf(strs[1]));
        MapoutputValue.set(Integer.valueOf(strs[1]));

        //输出
        context.write(MapoutputKey,MapoutputValue);
    }
}
