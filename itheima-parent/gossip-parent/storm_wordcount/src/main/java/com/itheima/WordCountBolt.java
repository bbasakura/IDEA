package com.itheima;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima
 * @ClassName: WordCountBolt
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/16 0016 19:52
 * @Version: 1.0
 */
public class WordCountBolt extends BaseRichBolt {
    //加上static线程更加安全（并行执行时的线程安全-----------并行度带来的问题）
    private static Map<String,Integer>counter = null;

    /*@Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        *//**
         * @method: prepare

         * @return: void
         * @description: 获取上游bolt发送给过来的单词,  然后进行单词统计, 实时打印统计结果
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:53
         *//*
        //1. 初始化hashMap
        this.counter = new HashMap<>();
    }

    @Override
    public void execute(Tuple input) {

        *//**
         * @method: execute

         * @return: void
         * @description: 被循环调用的方法,上游发送的数据,来一条,调用这个方法,处理一次
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:53
         *//*


        String word = input.getStringByField("word");

        Integer count = input.getInteger(Integer.parseInt("count"));

        //判断word是否在hashmap中出现
        if(counter.containsKey(word)){

            //已经存在哦

            Integer newCount = counter.get(word) + count;
            counter.put(word,newCount);
        }else{
            counter.put(word,count);
        }

        //3. 实时打印结果
        System.out.println(counter);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        *//**
         * @method: declareOutputFields

         * @return: void
         * @description: 没有下游的话,这个方法可以不需要
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:53
         *//*

    }*/








    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        //1. 初始化hashMap

        //ConcurrentHashMap  是线程安全的hashmap, 替换掉之前的HashMap


        this.counter = new ConcurrentHashMap<>();
    }


    /**
     * 被循环调用的方法,上游发送的数据,来一条,调用这个方法,处理一次
     * @param input
     */
    @Override
    public void execute(Tuple input) {
        //1. 获取上游发送的单词 及 次数
        String word = input.getStringByField("word");
        Integer count = input.getIntegerByField("count");

        //2.判断word是否在hashmap中出现
        if(counter.containsKey(word)){
            //已经存在
            Integer newCount = counter.get(word) + count;
            counter.put(word,newCount);

        }else{
            counter.put(word,count);
        }

        //3. 实时打印结果
        System.out.println(counter);

    }

    /**
     * 没有下游的话,这个方法可以不需要
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
