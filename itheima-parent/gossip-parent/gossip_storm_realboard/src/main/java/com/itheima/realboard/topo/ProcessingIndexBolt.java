package com.itheima.realboard.topo;

import com.alibaba.fastjson.JSON;
import com.itheima.realboard.domain.PaymentInfo;
import com.itheima.realboard.utils.JedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.realboard.topo
 * @ClassName: ProcessingIndexBolt
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/18 0018 20:33
 * @Version: 1.0
 */
public class ProcessingIndexBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

        //1. 从kafkaSpout读取订单数据 : json
        String orderJson = input.getString(4);

        //2. json数据转换成pojo对象
        if(StringUtils.isNotEmpty(orderJson)){
            PaymentInfo paymentInfo = JSON.parseObject(orderJson, PaymentInfo.class);

            Jedis jedis = JedisUtil.getConn();
            //3. 实时统计:
            //3.1 平台的角度统计:
            //平台总销售额度 : 每个订单的支付金额的累加   key: itcast:order:total:price:date
            jedis.incrBy("itcast:order:total:price:date",paymentInfo.getPayPrice());
            //平台今天下单人数 :  itcast:order:total:user:date  来一个订单,加一的操作
            jedis.incr("itcast:order:total:user:date");
            //平台商品销售数量   itcast:order:total:num:date
            jedis.incrBy("itcast:order:total:num:date",paymentInfo.getNum());

            //3.2 商品的维度
            //商品的销售额:itcast:order:productId:price:date
            jedis.incrBy("itcast:order:"+paymentInfo.getProductId()+":price:date",paymentInfo.getPayPrice());
            //每个商品的购买人数  itcast:order:productId:user:date
            jedis.incr("itcast:order:"+paymentInfo.getProductId()+":user:date");
            //每个商品的销售数量 itcast:order:productId:num:date
            jedis.incrBy("itcast:order:"+paymentInfo.getProductId()+":num:date",paymentInfo.getNum());

            //3.2 商家的维度统计:
            // 商家的销售额
            jedis.incrBy("itcast:order:"+paymentInfo.getShopId()+":price:date",paymentInfo.getPayPrice());
            //商家的购买人数
            jedis.incr("itcast:order:"+paymentInfo.getShopId()+":user:date");
            //商家的购买商品的数量
            jedis.incrBy("itcast:order:"+paymentInfo.getShopId()+":num:date",paymentInfo.getNum());
            jedis.close();

        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}
