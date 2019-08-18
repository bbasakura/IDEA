package com.itheima.ack;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.FailedException;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Random;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.ack
 * @ClassName: SplitBolt
 * @Description: 接收上游发送的数据, 随机报错   1 / 0,  处理成功就结束  , 处理失败,需要抛出异常(自动调用fail方法)
 * @Author: sakura
 * @CreateDate: 2019/7/17 0017 21:53
 * @Version: 1.0
 */
public class SplitBolt extends BaseBasicBolt {

    private Random random;


    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

        try {
            //1. 接收上游发送的数据
            String line = input.getStringByField("line");

            //2. 随机抛出异常
            int i = random.nextInt(5);

            int n = 1 / i;


        } catch (Exception e) {
            //处理失败,抛出异常
            throw  new FailedException(e);
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
