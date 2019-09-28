package cn.itcast.b2c.gciantispider.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhRulemonitorAntispiderDao;
import cn.itcast.b2c.gciantispider.model.NhRulemonitorAntispider;

@Service
public class NhRulemonitorAntispiderServiceImpl implements INhRulemonitorAntispiderService {
    
    @Autowired
    INhRulemonitorAntispiderDao nhRulemonitorAntispiderDao;

    @Override
    public List<NhRulemonitorAntispider> getNhRulemonitorAntispiderByTimeAndType(String timeType, String ruleType) {
        
        String hql = "from NhRulemonitorAntispider r where r.timetype =:timetype and r.ruletype =:ruletype order by r.datetime asc";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("timetype", Integer.valueOf(timeType));
        params.put("ruletype", Integer.valueOf(ruleType));
        return nhRulemonitorAntispiderDao.find(hql, params);
    }

}
