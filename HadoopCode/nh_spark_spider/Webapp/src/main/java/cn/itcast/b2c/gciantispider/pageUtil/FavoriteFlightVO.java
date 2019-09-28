/*
 * Created on 2017年6月15日
 * FavoriteFlightVO.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.pageUtil;

import java.sql.Timestamp;


public class FavoriteFlightVO {
    private Integer rank;//排名
    private String code;//航班代码
    private String depairport;//起飞地
    private String arrairport;//目的地
//    private Date time;//时间
    private Timestamp time;
    private Integer grapFrequency;//爬取频率
    private String depairArrairport;//起飞地-目的地
    private Timestamp createTime; 
    
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDepairport() {
        return depairport;
    }
    public void setDepairport(String depairport) {
        this.depairport = depairport;
    }
    public String getArrairport() {
        return arrairport;
    }
    public void setArrairport(String arrairport) {
        this.arrairport = arrairport;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public Integer getGrapFrequency() {
        return grapFrequency;
    }
    public void setGrapFrequency(Integer grapFrequency) {
        this.grapFrequency = grapFrequency;
    }
    public String getDepairArrairport() {
        return depairArrairport;
    }
    public void setDepairArrairport(String depairArrairport) {
        this.depairArrairport = depairArrairport;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
   
    
}
