/*
 * Created on 2017年6月15日
 * IFlightService.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.service;

import java.math.BigInteger;
import java.util.List;

import cn.itcast.b2c.gciantispider.pageUtil.FavoriteFlightVO;

public interface IFlightService {
    /**
     * 近一周最爱航班查询
     * @return
     */
    List<FavoriteFlightVO> getFavoriteFlight(int pages,int rows,String startTime,String endTime);
    
    List<FavoriteFlightVO> getFavoriteFlight(int pages,int rows);
    
    List<FavoriteFlightVO> getFavoriteFlight(String startTime,String endTime);
    
    /**
     * 近一周爬虫非法占座排行
     * @return
     */
    List getSeatArrangement(int pages,int rows,String startTime,String endTime);
    
    BigInteger getAllCount(String startTime,String endTime);
}
