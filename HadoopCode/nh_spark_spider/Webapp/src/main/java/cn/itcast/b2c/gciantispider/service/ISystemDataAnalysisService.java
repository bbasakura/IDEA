package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.SystemDataAnalysis;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;

public interface ISystemDataAnalysisService {
    
    /**
     * 获取系统所有分析速度
     * @return
     */
    JsonVO getAllSystemDataAnalysis();
}
