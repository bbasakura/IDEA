package com.itheima;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima
 * @ClassName: SplitBolt
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/16 0016 19:44
 * @Version: 1.0
 */
public class SplitBolt extends BaseRichBolt {


    private OutputCollector collector;

    /*@Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        *//**
         * @method: prepare

         * @return: void
         * @description: 获取spout发送过来一行数据,  按照空格进行切分开 ,  将切割出来的每个单词发送到下游的bolt
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:45
         *//*
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {

        *//**
         * @method: execute

         * @return: void
         * @description: 处理上游发送过来的数据的方法:   被动被调用的方法, 上游只要有数据,就会调用这个方法处理
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:45
         *//*


        //获取上游发送的数据，参数是一个字段
        String line = input.getStringByField("line");

        //按照空格进行切割

        String[] words = line.split(" ");

        //3. 将切割出来的单词发送到下游去处理

        for (String word : words) {

            //向下游发送数据
            collector.emit(new Values(word,1));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        *//**
         * @method: declareOutputFields

         * @return: void
         * @description: 声明输出内容的字段名称:
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:45
         *//*

        declarer.declare(new Fields("word","count"));



    }*/










    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

        //1. 初始化发射器
        this.collector = collector;
    }

    /**
     * 处理上游发送过来的数据的方法:   被动被调用的方法, 上游只要有数据,就会调用这个方法处理
     */
    @Override
    public void execute(Tuple input) {
        //1.获取上游发送的数据内容
        //里面的参数: 上游发送过来数据的字段名称
        String line = input.getStringByField("line");

        //2. 按照空格进行切割
        String[] words = line.split(" ");

        //3. 将切割出来的单词发送到下游去处理
        for (String word : words) {
            //向下游发送数据
            collector.emit(new Values(word,1));
        }
    }

    /**
     *  声明输出内容的字段名称:
     * @param declarer : 声明器
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //声明发送到下游的数据的字段名称, 按照发送的顺序写
        declarer.declare(new Fields("word","count"));
    }
}
