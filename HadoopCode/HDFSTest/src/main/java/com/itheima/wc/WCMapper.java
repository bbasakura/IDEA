package com.itheima.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.wc
 * @ClassName: WCMapper
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 7:48
 * @Version: 1.0
 */
public class WCMapper extends Mapper<LongWritable,Text,Text,IntWritable> {


    private  Text outputKey= new Text();

    private IntWritable outputValue=new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**
         * @method: map  一个keyvalue调用一次这让个map方法
         * @return: void
         * @description: key是每一行的偏移量，value
         * 是每一行的内容
         * @Author: sakura
         * @Date: 2019/8/3 0003 8:01
         */

        String line = value.toString();
        //对得到的value值进行切分，得到想要每个单词

        String[] words = line.split(",");

        //将每个单词取出，作为key
        for (String word : words) {

            this.outputKey.set(word);
            //不要忘记输出
            context.write(this.outputKey,this.outputValue);

        }






    }
}
