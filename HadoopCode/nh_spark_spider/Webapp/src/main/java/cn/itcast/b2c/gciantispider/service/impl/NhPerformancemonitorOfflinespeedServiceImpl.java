package cn.itcast.b2c.gciantispider.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhPerformancemonitorOfflinespeedDao;
import cn.itcast.b2c.gciantispider.model.NhPerformancemonitorOfflinespeed;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.service.INhPerformancemonitorOfflinespeedService;
import cn.itcast.b2c.gciantispider.util.DateFormatter;
import cn.itcast.b2c.gciantispider.util.TrafficUtil;

@Service("nhPerformancemonitorOfflinespeedService")
public class NhPerformancemonitorOfflinespeedServiceImpl implements INhPerformancemonitorOfflinespeedService {
    
    @Autowired
    INhPerformancemonitorOfflinespeedDao nhPerformancemonitorOfflinespeedDao;

    @Override
    public NhPerformancemonitorOfflinespeed getOfflineSpeed(String date) {
        String hql = "from NhPerformancemonitorOfflinespeed n where n.date =:date";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", DateFormatter.strToDate(date));
        return nhPerformancemonitorOfflinespeedDao.get(hql, map);
    }
    
    @Override
    public JsonVO getRealtimeSpeed(String key){
        List<JsonVO> jsonVOs = TrafficUtil.trafficInfo(key);
        Collections.sort(jsonVOs);
        JsonVO jsonVO = new JsonVO();
        if(jsonVOs!=null && jsonVOs.size()!=0){
            jsonVO = jsonVOs.get(jsonVOs.size()-1);
        }
        return jsonVO;
    }
}
