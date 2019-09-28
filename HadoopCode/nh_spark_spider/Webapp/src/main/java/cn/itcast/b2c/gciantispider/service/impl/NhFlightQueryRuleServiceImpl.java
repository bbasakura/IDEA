/*
 * Created on 2017年7月3日
 * NhFlightQueryRuleServiceImpl.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhFlightQueryRuleDao;
import cn.itcast.b2c.gciantispider.model.NhFlightQueryRule;
import cn.itcast.b2c.gciantispider.service.INhFlightQueryRuleService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.DateFormatter;

@Service
public class NhFlightQueryRuleServiceImpl implements INhFlightQueryRuleService {
    @Autowired
    private INhFlightQueryRuleDao nhFlightQueryRuleDao;
    /**
     *国内、国际单程查询爬取频次
     */
    public List<NhFlightQueryRule> findAllOneWayByTime(String startTime,String endTime){
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "From NhFlightQueryRule f WHERE 1=1";
        if(!Common.isEmpty(startTime)){
            hql = hql + " AND f.recordTime >=:startTime";
            params.put("startTime", DateFormatter.strToDate(startTime));
        }if(!Common.isEmpty(endTime)){
            hql = hql + " AND f.recordTime <=:endTime";
            params.put("endTime", DateFormatter.strToDate(endTime));
        }
        hql = hql + " AND f.wayType=:wayType ORDER BY f.recordTime asc";
        params.put("wayType", 0);
        return nhFlightQueryRuleDao.find(hql, params);
    }
    /**
     * 国内、国际双程查询爬取频次
     */
    public List<NhFlightQueryRule> findTurnAroundByTime(String startTime,String endTime){
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "From NhFlightQueryRule f WHERE 1=1";
        if(!Common.isEmpty(startTime)){
            hql = hql + " AND f.recordTime >=:startTime";
            params.put("startTime", DateFormatter.strToDate(startTime));
        }
        if(!Common.isEmpty(endTime)){
            hql = hql + " AND f.recordTime <=:endTime";
            params.put("endTime", DateFormatter.strToDate(endTime));
        }
        hql = hql + " AND f.wayType=:wayType ORDER BY f.recordTime asc";
        params.put("wayType", 1);
        return nhFlightQueryRuleDao.find(hql, params);
    }
}
