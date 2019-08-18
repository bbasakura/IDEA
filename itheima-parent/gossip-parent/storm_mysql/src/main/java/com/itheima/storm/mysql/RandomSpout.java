package com.itheima.storm.mysql;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.tick
 * @ClassName: RandomSpout
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 17:11
 * @Version: 1.0
 */
public class RandomSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;
    private Random random;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {

        //初始化
        this.collector = collector;
        this.random = new Random();

    }

    @Override
    public void nextTuple() {

        String [] names = {"张三 20","小明 28","老王 30","小黑 38"};

        collector.emit(new Values(names[random.nextInt(names.length)]));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        declarer.declare(new Fields("message"));


    }
}
