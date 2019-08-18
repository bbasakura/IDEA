package com.itheima;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.*;
import java.util.Map;
import java.util.Random;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima
 * @ClassName: ReadFileSpout
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/16 0016 18:01
 * @Version: 1.0
 */
public class ReadFileSpout  extends BaseRichSpout {


//    private BufferedReader bufferedReader=null;

    private SpoutOutputCollector collector = null;

    private Random random = null;


   /* @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        *//**
         * @method: open

         * @return: void
         * @description: 打开文件（初始化的方法, 当前类被初始化被调用的方法:  初始化数据库连接池   初始化线程池      打开文件）
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:33
         *//*

        try {
            bufferedReader=new BufferedReader(new FileReader(new File("D:\\word.txt")));

            //初始化发射器
            this.collector=collector;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void nextTuple() {
        *//**
         * @method: nextTuple

         * @return: void
         * @description: 被循环调用的方法,  读取文件中的一行内容,然后调用这个方法一次
         * @Author: sakura
         * @Date: 2019/7/16 0016 19:33
         *//*


        //读取一行内容
        try {
            String line = bufferedReader.readLine();

            if (StringUtils.isNotEmpty(line)){


                //new Values 转换成字符串？
                collector.emit(new Values(line));
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        *//**
         * @method: declareOutputFields

         * @return: void
         * @description:  声明输出内容的字段名称:

         * @Author: sakura
         * @Date: 2019/7/16 0016 19:33
         *//*
        declarer.declare(new Fields("line"));
    }

        *//*@Override
    public void ack(Object msgId) {
        super.ack(msgId);
    }


    @Override
    public void fail(Object msgId) {
        super.fail(msgId);
    }*/

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        try {
            //1.初始化文件,打开文件
//            bufferedReader = new BufferedReader(new FileReader("D:\\word.txt"));


            random = new Random();

            //2. 初始化发射器
            this.collector = collector;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 被循环调用的方法,  读取文件中的一行内容,然后调用这个方法一次
     */
    @Override
    public void nextTuple() {

        try {
//            //1. 读取文件中的一行内容
////            String line = bufferedReader.readLine();



            String[] sentences = new String[]{ "my storm word count", "hello my storm", "hello storm hello world",
                    "how do you do","how are you","Apache Storm is a free and open source distributed"};

            //产生的随机数  0 ---  (length - 1)
            int i = random.nextInt(sentences.length);


            //获取发送的数据
            String line = sentences[i];



            //2. 将这一行内容,发送到下游的bolt
            if(StringUtils.isNotEmpty(line)){

                //向下游发送数据,每次发送一行内容
                collector.emit(new Values(line));
            }

            //休眠一秒
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  声明输出内容的字段名称:
     *
     * @param declarer: 声明器
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //指定向下游发送的数据的字段名称
        declarer.declare(new Fields("line"));
    }


    /*@Override
    public void ack(Object msgId) {
        super.ack(msgId);
    }


    @Override
    public void fail(Object msgId) {
        super.fail(msgId);
    }*/



}
