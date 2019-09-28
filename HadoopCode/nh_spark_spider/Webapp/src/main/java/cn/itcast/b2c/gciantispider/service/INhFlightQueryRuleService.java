/*
 * Created on 2017年7月3日
 * INhFlightQueryRuleService.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhFlightQueryRule;


public interface INhFlightQueryRuleService {

    /**
     * 国内、国际单程查询爬取频次
     */
    List<NhFlightQueryRule> findAllOneWayByTime(String startTime,String endTime);
    
    /**
     * 国内、国际双程查询爬取频次
     */
    List<NhFlightQueryRule> findTurnAroundByTime(String startTime,String endTime);
}
