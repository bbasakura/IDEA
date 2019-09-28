/*
 * Created on 2017年7月28日
 * INhRedisDataService.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service;

public interface IRealTimeComputDataService {
    /**
     * 从redis备份流量数据
     */
    void saveRealTimeComputData();
    /**
     * 统计一天的流量信息
     */
    void saveNhCrawlerCurdayInfo();
    /**
     * 统计链路数据
     */
	void saveDataCollectData();
}
