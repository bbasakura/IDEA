package com.itheima.storm.redis;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.mysql
 * @ClassName: SplitBolt
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 18:11
 * @Version: 1.0
 */
public class SplitBolt  extends BaseBasicBolt {



    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {


        //1. 获取上游的数据

        String message = input.getStringByField("message");

        //2. 切割出来name  age
        String[] split = message.split(" ");

        String name = split[0];

        Integer age = Integer.parseInt(split[1]);

        //3. 向下游发送数据
        collector.emit(new Values(name,age));

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

     // 顺序和上面的字段顺序一致  字段的类型也要一致

        declarer.declare(new Fields("name","age"));

    }
}
