package cn.itcast.b2c.gciantispider.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.JedisCluster;
import cn.itcast.b2c.gciantispider.service.IRealTimeComputDataService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.JedisConnectionUtil;

/**
 * 备份数据的定时器 主要备份到mysql数据库
 */
@Component
public class BackupDataQuartz {

	@SuppressWarnings("unused")
	private final static Logger logger = Logger
			.getLogger(BackupDataQuartz.class);

	@Autowired
	private IRealTimeComputDataService realTimeComputDataService;

	/**
	 * 每隔两个小时 定时获取redis上的链路数据，存到mysql对应的表中(datacollect)，
	 * 然后删掉redis上已经备份到mysql的数据。
	 * 
	 */
	//@Scheduled(cron = "* * 0/2 * * ?")
	@Scheduled(cron = "0/10 * * * * ?")
	@Transactional
	public void BackupRedisLinkData() {
		realTimeComputDataService.saveDataCollectData();
	}

	/**
	 * 每隔两个小时
	 * 定时获取redis上的流量数据，获取到的数据是一个小时前的流量数据，存到mysql对应的表中(real_time_comput_data)，
	 * 然后删掉redis上已经备份到mysql的数据。
	 * 
	 */
	// @Scheduled(cron="0 * 0/2 * * ?")
	@Scheduled(cron = "0/10 * * * * ?")
	@Transactional
	public void BackupRedisFlowData() {
		realTimeComputDataService.saveRealTimeComputData();
	}

	/**
	 * 获取前一天RealTimeComputData实体中所有sourceCount的值累加 将累加的值保存到nhCrawlerCurdayInfo中
	 */
	// @Scheduled(cron="0 * 0/2 * * ?")
	@Scheduled(cron = "0/10 * * * * ?")
	@Transactional
	public void BackupRedisRealTimeComputData() {
		realTimeComputDataService.saveNhCrawlerCurdayInfo();
	}

	/**
	 * 流量值置零操作 重新设置设置当天的流量值为0
	 */
	// @Scheduled(cron="0 0 1 * * ?")
	public void setRedisFlowSumData() {
		JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
		jedisCluster.set(Constants.CURR_FLOW_SUM, "0");
	}

	public static void main(String[] args) {
		BackupDataQuartz p = new BackupDataQuartz();
		// p.BackupRedisFlowData();
		// p.BackupRedisRealTimeComputData();
		p.BackupRedisLinkData();
	}

}