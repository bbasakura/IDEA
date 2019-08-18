package com.itheima.realboard.topo;

import com.itheima.realboard.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.realboard.topo
 * @ClassName: RealBoardLook
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/18 0018 20:37
 * @Version: 1.0
 */
public class RealBoardLook {

    public static void main(String[] args) {


        Timer timer = new Timer();
        /**
         * 第一个参数:  要执行的任务
         *
         * 第二个参数:  延迟多长时间启动
         *
         * 第三个参数: 执行的间隔
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //要执行的任务
                Jedis jedis = JedisUtil.getConn();

                //平台的总销售额
                System.out.println("平台的总销售额:" + jedis.get("itcast:order:total:price:date"));
                System.out.println("平台的下单人数:" + jedis.get("itcast:order:total:user:date"));
                System.out.println("平台的商品销售量:" + jedis.get("itcast:order:total:num:date"));

                jedis.close();

            }
        },2000,1000);


    }
}
