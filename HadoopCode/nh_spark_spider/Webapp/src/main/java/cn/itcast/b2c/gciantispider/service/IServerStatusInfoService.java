package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.ServerStatusInfo;
public interface IServerStatusInfoService {
    /**
     * 获取所有服务器状态
     * @return
     */
    List<ServerStatusInfo> getAllServerStatusInfo();
    
    /**
     * 数据备份
     * 
     **/
    
    void dataBackups(List<ServerStatusInfo> serverStatusInfoList);
}
