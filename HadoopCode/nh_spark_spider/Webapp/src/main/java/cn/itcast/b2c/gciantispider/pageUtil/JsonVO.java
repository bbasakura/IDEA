/*
 * Created on 2017年7月25日
 * JsonVO.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Kingpintcn Information Technology Co.,Ltd
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package cn.itcast.b2c.gciantispider.pageUtil;

import java.util.Map;

import sun.misc.JavaAWTAccess;

/**
 * 用于从redis缓存数据库中读取数据的Bean
 */
public class JsonVO implements Comparable<JsonVO> ,java.io.Serializable{
    private String key;
    private String costTime;
    private Map<String,Object> serversCountMap;
    private String applicationId;
    private String countPerMillis;
    private String applicationUniqueName;
    private String endTime;
    private String sourceCount;
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getCostTime() {
        return costTime;
    }
    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }
    public Map<String,Object>  getServersCountMap() {
        return serversCountMap;
    }
    public void setServersCountMap(Map<String,Object>  serversCountMap) {
        this.serversCountMap = serversCountMap;
    }
    public String getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    public String getCountPerMillis() {
        return countPerMillis;
    }
    public void setCountPerMillis(String countPerMillis) {
        this.countPerMillis = countPerMillis;
    }
    public String getApplicationUniqueName() {
        return applicationUniqueName;
    }
    public void setApplicationUniqueName(String applicationUniqueName) {
        this.applicationUniqueName = applicationUniqueName;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getSourceCount() {
        return sourceCount;
    }
    public void setSourceCount(String sourceCount) {
        this.sourceCount = sourceCount;
    }
    
  /*  *//**
     * @param costTime
     * @param serversCountMap
     * @param applicationId
     * @param countPerMillis
     * @param applicationUniqueName
     * @param endTime
     * @param sourceCount
     *//*
    public JsonVO(String costTime, String serversCountMap, String applicationId, String countPerMillis, String applicationUniqueName, String endTime, String sourceCount) {
        super();
        this.costTime = costTime;
        this.serversCountMap = serversCountMap;
        this.applicationId = applicationId;
        this.countPerMillis = countPerMillis;
        this.applicationUniqueName = applicationUniqueName;
        this.endTime = endTime;
        this.sourceCount = sourceCount;
    }*/
    @Override
    public int compareTo(JsonVO o) {
        // TODO Auto-generated method stub
        return this.getEndTime().compareTo(o.getEndTime());
    }
   /* @Override
    public String toString() {
        return "JsonVO [costTime=" + costTime + ", serversCountMap=" + serversCountMap + ", applicationId=" + applicationId + ", countPerMillis=" + countPerMillis + ", applicationUniqueName="
                + applicationUniqueName + ", endTime=" + endTime + ", sourceCount=" + sourceCount + "]";
    }*/
    @Override
    public String toString() {
        return "JsonVO [key=" + key + ", costTime=" + costTime + ", serversCountMap=" + serversCountMap + ", applicationId=" + applicationId + ", countPerMillis=" + countPerMillis
                + ", applicationUniqueName=" + applicationUniqueName + ", endTime=" + endTime + ", sourceCount=" + sourceCount + "]";
    }
    
    /*public static void main(String[] args){
        List<JsonVO> jsonVOs = new ArrayList<JsonVO>();
        jsonVOs.add(new JsonVO("","","","","","2017-07-25 15:45:03",""));
        jsonVOs.add(new JsonVO("","","","","","2017-07-25 15:46:03",""));
        jsonVOs.add(new JsonVO("","","","","","2017-07-25 15:43:03",""));
       Collections.sort(jsonVOs);
       System.out.println(jsonVOs);
    }*/
    
}
