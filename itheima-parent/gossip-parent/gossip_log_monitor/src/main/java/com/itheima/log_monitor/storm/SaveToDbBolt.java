package com.itheima.log_monitor.storm;

import com.itheima.log_monitor.domain.LogMonitorRuleRecord;
import com.itheima.log_monitor.utils.JdbcUtils;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Date;


public class SaveToDbBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String myErrorLog = input.getStringByField("myErrorLog");
        String monitorRule = input.getStringByField("monitorRule");
        LogMonitorRuleRecord record  = new LogMonitorRuleRecord();
        String appId =  myErrorLog.split("\001")[0];
        record.setAppId(Integer.parseInt(appId));
        record.setCreateDate(new Date());
        record.setIsClose(1);
        record.setIsEmail(1);
        record.setIsPhone(1);
        record.setNoticeInfo("尊敬的项目负责人，你的项目出现了问题，请及时查看"+myErrorLog);
        record.setUpdateDate(new Date());
        JdbcUtils.saveRuleRecord(record);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
