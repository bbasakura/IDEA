package com.itheima;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

//    redis服务器的iP地址


    public  static  final  String  host="192.168.72.142";

//   链接端口


    public  static  final  Integer port = 6379;
    public  static JedisPool jedisPool =null;



    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxTotal(100);

        jedisPoolConfig.setMinIdle(3);

//创建redis的连接池


        JedisPool jedisPool1 = new JedisPool(jedisPoolConfig, host, port);

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
