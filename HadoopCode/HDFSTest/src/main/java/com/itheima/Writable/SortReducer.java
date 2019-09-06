package com.itheima.Writable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Writable
 * @ClassName: SortReducer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 11:34
 * @Version: 1.0
 */
public class SortReducer extends Reducer<PairWritable, IntWritable, Text, IntWritable> {

    private Text ReduceoutputKey = new Text();

    //计数器
    public static enum Counter {
        REDUCE_INPUT_RECORDS, REDUCE_INPUT_VAL_NUMS,
    }


    @Override
    protected void reduce(PairWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {


        //计数器
        context.getCounter(Counter.REDUCE_INPUT_RECORDS).increment(1L);

        //迭代输出
        for (IntWritable value : values) {
        //计数器
            context.getCounter(Counter.REDUCE_INPUT_VAL_NUMS).increment(1L);
            ReduceoutputKey.set(key.getFirst());
            context.write(ReduceoutputKey, value);
        }
    }


}
