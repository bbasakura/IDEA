package com.itheima.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.wc
 * @ClassName: WCReduce
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 7:51
 * @Version: 1.0
 */
public class WCReduce extends Reducer<Text,IntWritable,Text,IntWritable> {

    private IntWritable outputValue = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        /**
         * @method: reduce  每一个keyvalue都来调用一次
         * @return: void
         * @description:  key是分组以后的key， values是相同的key对应的values
         * @Author: sakura
         * @Date: 2019/8/3 0003 8:09
         */

        int sum =0;

        //对当前单词对应的的所有的value值全部取出累加
        for (IntWritable value : values) {
            sum+=value.get();
        }

        //进行输出
        this.outputValue.set(sum);
        context.write(key,this.outputValue);



    }
}
