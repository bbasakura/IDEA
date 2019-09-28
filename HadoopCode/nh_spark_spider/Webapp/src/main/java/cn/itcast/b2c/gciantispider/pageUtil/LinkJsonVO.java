/*
 * Created on 2017年7月25日
 * JsonVO.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.pageUtil;

import java.util.Map;

import sun.misc.JavaAWTAccess;

/**
 * 用于从redis缓存数据库中读取数据的Bean
 */
public class LinkJsonVO implements Comparable<LinkJsonVO> ,java.io.Serializable{
    private String key;
    private Map<String,Integer> serversCountMap;
    private Map<String,Integer> activeNumMap;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Map<String, Integer> getServersCountMap() {
		return serversCountMap;
	}
	public void setServersCountMap(Map<String, Integer> serversCountMap) {
		this.serversCountMap = serversCountMap;
	}
	
	@Override
	public String toString() {
		return "LinkJsonVO [key=" + key + ", serversCountMap="
				+ serversCountMap + ", activeNumMap=" + activeNumMap + "]";
	}
	public Map<String, Integer> getActiveNumMap() {
		return activeNumMap;
	}
	public void setActiveNumMap(Map<String, Integer> activeNumMap) {
		this.activeNumMap = activeNumMap;
	}
	@Override
	public int compareTo(LinkJsonVO o) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
}
