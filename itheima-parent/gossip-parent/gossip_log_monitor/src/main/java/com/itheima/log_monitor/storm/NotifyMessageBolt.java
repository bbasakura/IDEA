package com.itheima.log_monitor.storm;

import com.itheima.log_monitor.utils.CommonUtils;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;


public class NotifyMessageBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String rule = input.getStringByField("rule");
        String errorLog = input.getStringByField("errorLog");
        CommonUtils.notifyPeople(rule,errorLog);
        collector.emit(new Values(errorLog,rule));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("myErrorLog","monitorRule"));
    }
}
