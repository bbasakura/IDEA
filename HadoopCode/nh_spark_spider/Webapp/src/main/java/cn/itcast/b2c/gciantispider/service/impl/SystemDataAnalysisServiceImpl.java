package cn.itcast.b2c.gciantispider.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.ISystemDataAnalysisDao;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.service.ISystemDataAnalysisService;
import cn.itcast.b2c.gciantispider.util.TrafficUtil;
@Service
public class SystemDataAnalysisServiceImpl implements ISystemDataAnalysisService {
    @Autowired
    private ISystemDataAnalysisDao systemDataAnalysisDao;
    /**
     * 获取所有系统分析数据速度
     */
    public JsonVO getAllSystemDataAnalysis(){
        List<JsonVO> jsonVOs = TrafficUtil.trafficInfo("DP");
        Collections.sort(jsonVOs);
        JsonVO jsonVO = new JsonVO();
        if(jsonVOs!=null && jsonVOs.size()!=0){
            jsonVO = jsonVOs.get(jsonVOs.size()-1);
        }
        return jsonVO;
    }
}
