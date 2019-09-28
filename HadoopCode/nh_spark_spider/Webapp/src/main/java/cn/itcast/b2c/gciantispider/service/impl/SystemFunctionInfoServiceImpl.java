package cn.itcast.b2c.gciantispider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.ISystemFunctionInfoDao;
import cn.itcast.b2c.gciantispider.model.SystemFunctionInfo;
import cn.itcast.b2c.gciantispider.service.ISystemFunctionInfoService;

@Service
public class SystemFunctionInfoServiceImpl implements ISystemFunctionInfoService {
    @Autowired
    private ISystemFunctionInfoDao systemFunctionInfoDao;
    
    /**
     * 获取所有系统功能运行状态
     * @return List<SystemFunctionInfo>
     */
    public List<SystemFunctionInfo> getAllSystemFunctionInfo(){
        String hql = "From SystemFunctionInfo order by modelName";
        return systemFunctionInfoDao.find(hql);
    }
}
