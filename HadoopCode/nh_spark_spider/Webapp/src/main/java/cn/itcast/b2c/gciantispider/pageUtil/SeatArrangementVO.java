/*
 * Created on 2017年6月15日
 * SeatArrangementVO.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.pageUtil;

import java.util.Date;

public class SeatArrangementVO {
   // private Integer rank;
    private String code;
    private String depairport;//起飞地
    private String arrairport;//目的地
    private Date time;
    private Integer grapFrequency;
    private String shipSpace;
    private Float price;
    private Integer frequency;
    
    public Integer getFrequency() {
        return frequency;
    }
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
    /*public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }*/
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
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public Integer getGrapFrequency() {
        return grapFrequency;
    }
    public void setGrapFrequency(Integer grapFrequency) {
        this.grapFrequency = grapFrequency;
    }
    public String getShipSpace() {
        return shipSpace;
    }
    public void setShipSpace(String shipSpace) {
        this.shipSpace = shipSpace;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    
}
