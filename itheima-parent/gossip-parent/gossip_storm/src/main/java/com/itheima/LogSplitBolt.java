package com.itheima;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.List;

/**
 * @author itheima
 * @Title: LogSplitBolt
 * @ProjectName gossip-parent
 * @Description: 读取kafkaSpout发送过来的日志数据, 进行切割日志, 完成搜索关键词的获取,将搜索关键词keywords向下游bolt发送
 * @date 2019/7/1612:10
 */
public class LogSplitBolt extends BaseBasicBolt {
    /**
     * kafka中只要写入数据,就会调用这个方法
     * 读取上游KafKaSpout的数据
     *
     * @param input     : 上游过来的一条数据
     * @param collector : 发射器
     */
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        //1.读取上游过来的 日志数据
        //断点调试,查看日志value值在哪个索引下标中
        List<Object> values = input.getValues();

        String log = input.getString(4);


        //2. 判断是否为空, 判断是否包含分隔符

        if(StringUtils.isNotEmpty(log)&&log.contains("#CS#")){
            //开始切割

            int lastIndexOf = log.lastIndexOf("#CS#");
            String keywords = log.substring(lastIndexOf + 4);

            //将切割出来的结果发送给下游
            collector.emit(new Values(keywords));
        }

    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //声明发送到下游bolt的字段的名称
        declarer.declare(new Fields("keywords"));
    }
}
