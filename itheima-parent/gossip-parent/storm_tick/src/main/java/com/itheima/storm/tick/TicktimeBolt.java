package com.itheima.storm.tick;

import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.tick
 * @ClassName: TicktimeBolt
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 17:32
 * @Version: 1.0
 */
public class TicktimeBolt extends BaseBasicBolt {

    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config config = new Config();

        //设置定时器的周期  5秒
        config.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS,5);

        return config;
    }




    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {



        //此处判断是否是系统发送的ticktuple
        if(input.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID) && input.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID)){

            SimpleDateFormat format = new SimpleDateFormat();

            String date = format.format(new Date());

            System.out.println("date = " + date);
        }else {
            //如果不是
            //向上游发送正常的数据
            String message = input.getStringByField("message");

            System.out.println("message = " + message);

        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declare) {




    }
}
