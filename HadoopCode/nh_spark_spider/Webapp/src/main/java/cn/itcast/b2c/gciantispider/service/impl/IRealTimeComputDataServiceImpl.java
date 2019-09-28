/*
 * Created on 2017年7月28日
 * NhRedisDataServiceImpl.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;
import cn.itcast.b2c.gciantispider.dao.IDataCollectDao;
import cn.itcast.b2c.gciantispider.dao.INhCrawlerCurdayInfoDao;
import cn.itcast.b2c.gciantispider.dao.IRealTimeComputDataDao;
import cn.itcast.b2c.gciantispider.exception.ServiceException;
import cn.itcast.b2c.gciantispider.model.Datacollect;
import cn.itcast.b2c.gciantispider.model.NhCrawlerCurdayInfo;
import cn.itcast.b2c.gciantispider.model.NhLinkTrafficInformation;
import cn.itcast.b2c.gciantispider.model.RealTimeComputData;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.pageUtil.LinkJsonVO;
import cn.itcast.b2c.gciantispider.service.IRealTimeComputDataService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.JedisConnectionUtil;
import cn.itcast.b2c.gciantispider.util.TimeUtil;
import cn.itcast.b2c.gciantispider.util.TrafficUtil;

@Service
public class IRealTimeComputDataServiceImpl implements
		IRealTimeComputDataService {

	@Autowired
	private IRealTimeComputDataDao nRealTimeComputDataDao;

	@Autowired
	private INhCrawlerCurdayInfoDao nhCrawlerCurdayInfoDao;
	@Autowired
	private IDataCollectDao dataCollectDao;

	public void saveRealTimeComputData() {
		JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
		List<JsonVO> jsonVOs = TrafficUtil
				.trafficInfo(Constants.CSANTI_MONITOR_DP);
		List<JsonVO> jList = new ArrayList<JsonVO>();
		Map<String, Object> mapS = new HashMap<String, Object>();
		// 获取当前时间
		String timeE = TimeUtil.getCurrTime();
		// 获取30分钟前的时间
		String timeS = TimeUtil.getTimeByMinute(
				TimeUtil.yyyy_MM_DDHH24miss(timeE), -30);
		for (JsonVO jsonVO : jsonVOs) {
			String time = jsonVO.getEndTime();
			// 比较时间大小，并对返回的int值做判断
			int i = jsonVO.getEndTime().compareTo(timeS);
			if (i < 0) {
				jList.add(jsonVO);
			}
		}
		try {
			// 遍历jsonvo并向nhRedisData实体set向对应的属性
			for (JsonVO jsonVO : jList) {
				Set<NhLinkTrafficInformation> nhLinkTrafficInformations = new HashSet<NhLinkTrafficInformation>();
				RealTimeComputData realTimeComputData = new RealTimeComputData();
				realTimeComputData.setId(UUID.randomUUID().toString());
				realTimeComputData.setCostTime(jsonVO.getCostTime());
				realTimeComputData
						.setCountPerMillis(jsonVO.getCountPerMillis());
				// realTimeComputData.setCountPerMillis(Float.parseFloat(jsonVO.getCountPerMillis()));
				realTimeComputData.setApplicationId(jsonVO.getApplicationId());
				realTimeComputData.setApplicationUniqueName(jsonVO
						.getApplicationUniqueName());
				realTimeComputData.setEndTime(Timestamp.valueOf(jsonVO
						.getEndTime()));
				realTimeComputData.setSourceCount(Float.parseFloat(jsonVO
						.getSourceCount()));
				Map<String, Object> serversCountMap = jsonVO
						.getServersCountMap();
				// 获取serversCountMap中所有的key
				Set<String> keySet = serversCountMap.keySet();
				// 根据key取出相应value值
				if (null != keySet) {
					for (String key : keySet) {
						NhLinkTrafficInformation nhLinkTrafficInformation = new NhLinkTrafficInformation();
						Object value = serversCountMap.get(key);
						nhLinkTrafficInformation.setId(UUID.randomUUID()
								.toString());
						nhLinkTrafficInformation.setIpAddress(key);
						nhLinkTrafficInformation.setIpValue(Float
								.parseFloat(value.toString()));
						nhLinkTrafficInformation.setEndTime(Timestamp
								.valueOf(jsonVO.getEndTime()));
						nhLinkTrafficInformation
								.setRealTimeComputData(realTimeComputData);
						nhLinkTrafficInformations.add(nhLinkTrafficInformation);
					}
				}
				realTimeComputData
						.setNhLinkTrafficInformations(nhLinkTrafficInformations);
				// 保存主表及其从表的数据
				nRealTimeComputDataDao.save(realTimeComputData);
				// 根据key删除redis中的数据
				jedisCluster.del(jsonVO.getKey());
			}
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void saveNhCrawlerCurdayInfo() {
		NhCrawlerCurdayInfo nhCrawlerCurdayInfo = new NhCrawlerCurdayInfo();
		List<RealTimeComputData> realTimeComputDataList = new ArrayList<RealTimeComputData>();
		Map<String, Object> params = new HashMap<String, Object>();
		String lastDay = TimeUtil.getLastDay();
		try {
			// 查询前一天的所有数据
			// String hql =
			// "From RealTimeComputData rtc WHERE DATE_FORMAT(rtc.endTime,'%Y-%m-%d') =:lastDay";
			String sql = "SELECT source_count FROM real_time_comput_data WHERE DATE_FORMAT(end_time,'%Y-%m-%d')=:lastDay";
			params.put("lastDay", lastDay);
			// realTimeComputDataList = nRealTimeComputDataDao.find(hql,
			// params);
			List scList = new ArrayList();
			scList = nRealTimeComputDataDao.findBySql(sql, params);
			float sumS = 0;
			// 累加流量信息得到一天内总流量
			if (null != scList) {
				for (int i = 0; i < scList.size(); i++) {
					float sourceCount = Float.valueOf(String.valueOf(scList
							.get(i)));
					sumS += sourceCount;
				}
			}
			nhCrawlerCurdayInfo.setId(UUID.randomUUID().toString());
			// 为了防止精度丢失，先将float转换成string再转换成double System.currentTimeMillis()
			nhCrawlerCurdayInfo.setFlowNum(Double.parseDouble(String
					.valueOf(sumS)));
			nhCrawlerCurdayInfo.setIdentifyTime(new Timestamp(System
					.currentTimeMillis()));
			nhCrawlerCurdayInfoDao.save(nhCrawlerCurdayInfo);
		} catch (RuntimeException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void saveDataCollectData() {

		JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
		// 记录当天的链路总数
		Map<String, Integer> linkCount = TrafficUtil
				.trafficLinkInfo(Constants.CSANTI_MONITOR_LP);
		// 循环存储
		Set<String> keySet = linkCount.keySet();
		for (String serverAddr : keySet) {
			// 根据serveraddr查询链路历史数据
			String hql = "from Datacollect where server_name = :serverName";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("serverName", serverAddr);
			Datacollect datacollect = dataCollectDao.get(hql, params);
			// 历史数据不存在，新存入数据
			if (datacollect == null) {
				datacollect = new Datacollect();
				datacollect.setId(UUID.randomUUID().toString());
				datacollect.setServerName(serverAddr);
				datacollect.setLastThreeDaysNum(linkCount.get(serverAddr));
				datacollect.setBeforeYesterdayNum(0);
				datacollect.setYesterdayNum(linkCount.get(serverAddr));
				dataCollectDao.save(datacollect);
			} else {
				// 历史数据存在，累加
				datacollect.setLastThreeDaysNum(linkCount.get(serverAddr)
						+ datacollect.getYesterdayNum()
						+ datacollect.getBeforeYesterdayNum());
				datacollect
						.setBeforeYesterdayNum(datacollect.getYesterdayNum());
				datacollect.setYesterdayNum(linkCount.get(serverAddr));
				dataCollectDao.update(datacollect);
			}
		}
	}

	public static void main(String[] args) {
		IRealTimeComputDataServiceImpl a = new IRealTimeComputDataServiceImpl();
		a.saveDataCollectData();
	}
}
