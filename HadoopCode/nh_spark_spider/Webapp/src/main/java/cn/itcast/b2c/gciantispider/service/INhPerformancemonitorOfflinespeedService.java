package cn.itcast.b2c.gciantispider.service;

import cn.itcast.b2c.gciantispider.model.NhPerformancemonitorOfflinespeed;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;

public interface INhPerformancemonitorOfflinespeedService {
    /**
     * 获取离线速度
     * @param date
     * @return
     */
    public NhPerformancemonitorOfflinespeed getOfflineSpeed(String date);
    /**
     * 获取实时速度
     * @return
     */
    public JsonVO getRealtimeSpeed(String key);
}
