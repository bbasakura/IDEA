package com.itheima.log_monitor.storm;

import com.itheima.log_monitor.utils.CommonUtils;
import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class StormTickBolt extends BaseBasicBolt {

    private CommonUtils commonUtils;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        commonUtils = new CommonUtils();
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config config = new Config();
        config.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS,5);
        return config;
    }
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        if(input.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)&& input.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID)){
            //缓存mysql中规则数据
            commonUtils.monitorApp();
            commonUtils.monitorRule();
            commonUtils.monitorUser();
        }else{
            Object value = input.getValue(4);
            collector.emit(new Values(value.toString()));
        }
    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("errorLog"));
    }



}
