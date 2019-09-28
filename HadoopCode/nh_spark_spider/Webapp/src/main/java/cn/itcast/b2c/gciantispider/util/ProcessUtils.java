package cn.itcast.b2c.gciantispider.util;

import redis.clients.jedis.JedisCluster;

/**
 * 流程规格数据渲染工具类
 */
public class ProcessUtils {
    /**
     * 流程信息，状态改变后，均要调用此方法，向redis服务器中存入标识
     */
    public static void setChangeProcessFlag() {
        JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
        // 设置默认值
        jedisCluster.set(Constants.PROCESS_CHANGE, "false");
        // 改变值
        jedisCluster.set(Constants.PROCESS_CHANGE, "true");
    }

}
