package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author itheima
 * @Title: JedisUtils
 * @ProjectName goosip_spider_parent
 * @Description: 获取jedis连接的链接工具
 * @date 2019/6/2812:14
 */
public class JedisUtils {

    //redis服务器的ip
    public static  final  String  host = "192.168.72.142";

    //redis连接的端口
    public static  final  Integer  port = 6379;

    private static JedisPool jedisPool = null;

    //类加载时候,初始化数据库连接池
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);//闲时最大的数量
        config.setMaxTotal(100); //最大有100个
        config.setMinIdle(3);//最小闲时的数量
        //创建redis连接池
        jedisPool =  new JedisPool(config,host,port);

    }


    /**
     * 获取redis连接的静态方法
     * @return
     */
    public static Jedis  getJedis(){
        Jedis jedis = jedisPool.getResource();
        return  jedis;
    }


}
