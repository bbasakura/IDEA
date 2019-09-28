/*
 * Created on 2017年6月21日
 * NhRuleServiceImpl.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhRuleDao;
import cn.itcast.b2c.gciantispider.exception.ServiceException;
import cn.itcast.b2c.gciantispider.model.NhProcessInfo;
import cn.itcast.b2c.gciantispider.model.NhRule;
import cn.itcast.b2c.gciantispider.service.INhRuleService;

@Service
public class NhRuleServiceImpl implements INhRuleService {

    @Autowired
    private INhRuleDao nhRuleDao;
    
    /**
     * 保存规则信息
     */
    public void saveNhRule(NhRule nhRule){
        try {
            nhRuleDao.save(nhRule);
        }
        catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    
    /**
     * 根据流程id查询相关规则信息
     */
    public List<NhRule> findNhRuleById(String id){
        List<NhRule> nhRulelist = new ArrayList<NhRule>();
        List<Object[]> oList = new ArrayList<Object[]>();
        Map<String, Object> params = new HashMap<String, Object>();
      // String hql = "From NhRule nr WHERE nr.nhProcessInfo.id = :id";
        String sql = "SELECT r.id,r.rule_name,r.rule_type,r.arg0,r.arg1,r.arg2,r.last_arg,r.status from nh_rule r WHERE r.process_id=:id ORDER BY r.rule_name";
        params.put("id", id);
       // nhRulelist = 
        //nhRulelist = nhRuleDao.find(hql, params);
        oList = nhRuleDao.findBySql(sql, params);
        int size = oList.size();
        for(int i=0;i<size;i++){
            Object[] o = oList.get(i);
            NhRule nhRule = new NhRule();
            nhRule.setId((String)o[0]);
          /*  nhRule.setRuleName((String)o[1]);
            nhRule.setRuleType((String)o[2]);
            nhRule.setArg0((String)o[3]);
            nhRule.setArg1((Integer)o[4]);
            nhRule.setArg2((Integer)o[5]);*/
            nhRule.setScore((Integer)o[6]);
            nhRule.setStatus((Integer)o[7]);
            nhRulelist.add(nhRule);
        }
        return nhRulelist;
    }
    
    public void updateNhRule(NhRule nhRule){
        nhRuleDao.update(nhRule);
    }
}
