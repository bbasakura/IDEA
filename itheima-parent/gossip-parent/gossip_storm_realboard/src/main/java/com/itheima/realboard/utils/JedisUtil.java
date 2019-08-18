package com.itheima.realboard.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.realboard.utils
 * @ClassName: JedisUtil
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/18 0018 20:32
 * @Version: 1.0
 */
public class JedisUtil {

    private static JedisPool pool = null;

    /**
     * 获取jedis连接池
     * */
    public static JedisPool getPool(){
        if(pool == null){
            //创建jedis连接池配置
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数
            config.setMaxTotal(20);
            //最大空闲连接
            config.setMaxIdle(5);
            //创建redis连接池
            pool = new JedisPool(config,"192.168.72.142",6379,3000);
        }
        return pool;
    }

    /**
     * 获取jedis连接
     * */
    public static Jedis getConn(){
        return getPool().getResource();
    }
}
