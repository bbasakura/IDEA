package cn.itcast.b2c.gciantispider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhRulemonitorAntioccupyDao;
import cn.itcast.b2c.gciantispider.model.NhRulemonitorAntioccupy;
import cn.itcast.b2c.gciantispider.service.INhRulemonitorAntioccupyService;

@Service
public class NhRulemonitorAntioccupyServiceImpl implements INhRulemonitorAntioccupyService {
    
    @Autowired
    INhRulemonitorAntioccupyDao nhRulemonitorAntioccupyDao;

    @Override
    public List<NhRulemonitorAntioccupy> getNhRulemonitorAntioccupyByTimeAndType(String timeType, String ruleType) {
        String hql = "from NhRulemonitorAntioccupy r where r.timetype =:timetype and r.ruletype=:ruletype order by r.datetime asc";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("timetype", Integer.valueOf(timeType));
        params.put("ruletype", Integer.valueOf(ruleType));
        return nhRulemonitorAntioccupyDao.find(hql, params);
    }

}
