/*
 * Created on 2017年6月21日
 * INhProcessInfoService.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhProcessInfo;
import cn.itcast.b2c.gciantispider.pageUtil.ProcessInfoVO;

public interface INhProcessInfoService {
    /**
     * 保存流程信息
     * @param nhProcessInfo
     */
    void saveNhProcessInfo(NhProcessInfo nhProcessInfo);
    /**
     * 根据名称查询流程信息
     * @param processName
     * @return
     */
    NhProcessInfo getNhProcessInfoByName(String processName);
    /**
     * 查询所有流程信息（根据策略或者模型）
     * @param strategy
     * @param type
     * @return
     */
    List findAllProcessInfo();
    /**
     * 根据id删除流程信息
     * @param id
     */
    void deleteProcess(String id);
    /**
     * 根据id查询当前流程信息
     * @param id
     * @return
     */
    NhProcessInfo  getNhProcessInfoById(String id);
    /**
     * 修改流程信息
     * @param nhProcessInfo
     */
    void updateNhProcessInfo(NhProcessInfo nhProcessInfo);
    
    /**
     * 根据id更新运行状态
     * @param id
     */
    void updateStatusbyId(String id);
}
