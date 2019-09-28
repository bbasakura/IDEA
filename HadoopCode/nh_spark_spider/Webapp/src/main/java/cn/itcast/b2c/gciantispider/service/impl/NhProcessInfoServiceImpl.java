/*
 * Created on 2017年6月21日
 * NhProcessInfoServiceImpl.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhModelDao;
import cn.itcast.b2c.gciantispider.dao.INhProcessInfoDao;
import cn.itcast.b2c.gciantispider.exception.ServiceException;
import cn.itcast.b2c.gciantispider.model.NhModel;
import cn.itcast.b2c.gciantispider.model.NhProcessInfo;
import cn.itcast.b2c.gciantispider.model.NhRule;
import cn.itcast.b2c.gciantispider.pageUtil.ProcessInfoVO;
import cn.itcast.b2c.gciantispider.service.INhProcessInfoService;
import cn.itcast.b2c.gciantispider.util.Common;
@Service
public class NhProcessInfoServiceImpl implements INhProcessInfoService {
    
    @Autowired
    private INhProcessInfoDao nhProcessInfoDao;
    
    @Autowired
    private INhModelDao nhModelDao;
    /**
     * 保存流程信息
     */
    public void saveNhProcessInfo(NhProcessInfo nhProcessInfo){
        try {
            nhProcessInfoDao.save(nhProcessInfo);
        }
        catch (RuntimeException e) {
            // TODO: handle exception
            throw new ServiceException(e.getMessage());
        }
    }
    
    /**
     * 根据流程名称查询相关信息
     */
    public NhProcessInfo getNhProcessInfoByName(String processName){
        String hql = "From NhProcessInfo n where n.processName=:processName";
        Map<String, Object> params = new HashMap<String, Object>();
        if(!Common.isEmpty(processName)){
            params.put("processName", processName);
            return nhProcessInfoDao.get(hql,params);
        }
        return null;
    }
    /**
     * 查询所有流程信息（根据策略或者模型）
     */
    public List findAllProcessInfo(){
        List<Object[]> olist = new ArrayList<Object[]>();
        String sql = "SELECT np.process_name,nm.type,np.create_date,np.create_person,np.id,np.status From nh_process_info np,nh_model nm"
                + " WHERE np.id=nm.id ORDER BY np.create_date DESC,np.process_name";
        olist = nhProcessInfoDao.findBySql(sql);
        List<ProcessInfoVO> processInfoVOlist = new ArrayList<ProcessInfoVO>();
        int size = olist.size();
        for(int i=0;i<size;i++){
            ProcessInfoVO processInfoVO = new ProcessInfoVO();
            Object[] o = olist.get(i); 
            processInfoVO.setProcessName((String)o[0]);
            processInfoVO.setType((Integer)o[1]);
            processInfoVO.setCreateDate((Date)o[2]);
            processInfoVO.setCreatePerson((String)o[3]);
            processInfoVO.setId((String)o[4]);
            processInfoVO.setStatus((Integer)o[5]);
            processInfoVOlist.add(processInfoVO);
        }
        return processInfoVOlist;
    }
    /**
     * 删除流程相关信息
     */
    public void deleteProcess(String id){
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            if(!Common.isEmpty(id)){
                String hql = "From NhProcessInfo np WHERE np.id=:id";
                params.put("id", id);
                NhProcessInfo nhProcessInfo = nhProcessInfoDao.get(hql, params);
                nhProcessInfoDao.delete(nhProcessInfo);
            }
        }
        catch (RuntimeException e) {
            throw new ServiceException("系统异常");
        }
    }
    /**
     * 根据id查询当前流程信息
     */
    public NhProcessInfo getNhProcessInfoById(String id){
        Map<String, Object> params = new HashMap<String, Object>();
        if(!Common.isEmpty(id)){
            String hql = "From NhProcessInfo np WHERE np.id=:id";
            params.put("id", id);
            NhProcessInfo nhProcessInfo = nhProcessInfoDao.get(hql, params);
            return nhProcessInfo;
        }
        return null;
    }
    /**
     * 修改流程信息
     */
    public void updateNhProcessInfo(NhProcessInfo nhProcessInfo){
        nhProcessInfoDao.update(nhProcessInfo);
    }
    /**
     * 根据id更新运行状态
     */
    public void updateStatusbyId(String id){
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            if(!Common.isEmpty(id)){
                String hql = "From NhProcessInfo np WHERE np.id=:id";
                params.put("id", id);
                NhProcessInfo nhProcessInfo = nhProcessInfoDao.get(hql, params);
                //判断运行状态，根据点击操作并设置成相反的值
                if(nhProcessInfo.getStatus()==0){
                    nhProcessInfo.setStatus(1);
                }else{
                    nhProcessInfo.setStatus(0);
                }
                nhProcessInfoDao.update(nhProcessInfo);
            }
        }
        catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
