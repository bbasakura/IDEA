/*
 * Created on 2017年6月21日
 * NhModelServiceImpl.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhModelDao;
import cn.itcast.b2c.gciantispider.exception.ServiceException;
import cn.itcast.b2c.gciantispider.model.NhModel;
import cn.itcast.b2c.gciantispider.service.INhModelService;
@Service
public class NhModelServiceImpl implements INhModelService {

    @Autowired
    private INhModelDao nhModelDao;
    
    /**
     * 保存模型信息
     */
    public void saveNhModel(NhModel nhModel){
           try {
               nhModelDao.save(nhModel);
        }
        catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * 修改模型信息
     */
    public void updateNhModel(NhModel nhModel){
        nhModelDao.update(nhModel);
    }
}
