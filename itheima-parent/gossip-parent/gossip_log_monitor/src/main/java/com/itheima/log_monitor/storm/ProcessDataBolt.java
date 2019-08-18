package com.itheima.log_monitor.storm;

import com.itheima.log_monitor.utils.CommonUtils;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;


public class ProcessDataBolt  extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String errorLog = input.getStringByField("errorLog");
        String[] split = errorLog.split("\001");
        String appId = split[0];
        String ruleContent = split[1];
        //校验规则
        String rule = CommonUtils.checkRules(appId, ruleContent);
        if(rule!=null&&!"".equals(rule)){
            collector.emit(new Values(rule,errorLog));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("rule","errorLog"));
    }



}
