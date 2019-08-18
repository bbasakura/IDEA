package com.itheima.ack;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.ack
 * @ClassName: LogSpout
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/17 0017 21:55
 * @Version: 1.0
 */
public class LogSpout extends BaseRichSpout {

    private Random random ;

    private SpoutOutputCollector collector;

    //计数器
    private AtomicInteger counter ;


    //记录发送出去的消息
    private  Map<String,String> pendingMsg;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {

        this.random = new Random();

        this.collector = collector;
        //初始化成1
        this.counter = new AtomicInteger(0);

        this.pendingMsg  = new HashMap<>();
    }

    @Override
    public void nextTuple() {
        String[] sentences = new String[]{ "my storm word count", "hello my storm", "hello storm hello world",
                "how do you do","how are you","Apache Storm is a free and open source distributed"};

        //产生的随机数  0 ---  (length - 1)
        int i = random.nextInt(sentences.length-1);
        //获取发送的数据
        String line = sentences[i];


        //向下游发送数据, 必须传递一个msgId ,而且必须唯一
        int msgId = this.counter.addAndGet(1);
        collector.emit(new Values(line),msgId);
        System.out.println("消息id:  " + msgId + ",发送消息" + line);


        //每发送一条消息,需要记录这条消息: 等成功了,删除这条消息  , 失败了,重新发送
        this.pendingMsg.put(msgId+"",line);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        declarer.declare(new Fields("line"));

    }

    /**
     * 发送出去的消息处理成功调用的方法
     * @param msgId
     */
    @Override
    public void ack(Object msgId) {
        System.out.println("消息处理成功:  " + msgId);

        //处理成功,需要删除消息
        this.pendingMsg.remove(msgId);
    }


    /**
     * 发送出去的消息处理失败调用的方法
     * @param msgId
     */
    @Override
    public void fail(Object msgId) {
        System.out.println("消息处理失败: " +  msgId);


        //消息处理失败了,怎么办?  重新发送   这条数据tuple
        String line = this.pendingMsg.get(msgId);
        collector.emit(new Values(line),msgId);

        System.out.println(msgId + ", 消息重新发送");
    }



}
