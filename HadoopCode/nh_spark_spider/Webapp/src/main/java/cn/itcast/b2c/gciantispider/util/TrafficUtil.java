package cn.itcast.b2c.gciantispider.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisCluster;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.pageUtil.LinkJsonVO;
/**
 * 获取流量数据的工具类，从redis服务器上取
 * 
 * Title: gciantispider_web <br>
 * Description: <br>
 *
 */
public class TrafficUtil {
    private final static Logger logger = Logger.getLogger(TrafficUtil.class.getName());
    /**
     * 从redis获取键值开头为strFlage的数据
     * @param strFlage 键值通配符
     * @return
     */
    public static List<JsonVO> trafficInfo(String strFlage){
        JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
        Set<String> keySet = JedisConnectionUtil.keys(jedisCluster,strFlage + "*");
        List<JsonVO> jsonVOs = new ArrayList<JsonVO>();
        try {
            for(String str : keySet){
                JsonVO jsonVO = new JsonVO();
                String value = jedisCluster.get(str);
                jsonVO = JsonResolveUtil.resolveJson(value);
                if (null != jsonVO) {
                    jsonVO.setKey(str);
                    jsonVOs.add(jsonVO);
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            logger.info(e.getMessage());
        }
        return jsonVOs;
    }
    /**
     * 统计链路数据
     * @param strFlage 键值通配符
     * @return
     */
    public static Map<String, Integer> trafficLinkInfo(String strFlage){
        JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
        //从redis中拿出所有数据
        Set<String> keySet = JedisConnectionUtil.keys(jedisCluster,strFlage + "*");
        //转换为可操作性的bean
        List<LinkJsonVO> jsonVOs = new ArrayList<LinkJsonVO>();
      //记录当天的链路总数
    	Map<String, Integer> linkCount = new HashMap<String, Integer>();
        try {
        	//循环所有key
            for(String str : keySet){
            	LinkJsonVO jsonVO = new LinkJsonVO();
                String value = jedisCluster.get(str);
                jsonVO = JsonResolveUtil.resolveLinkJson(value);
                //获取serveraddress的map，其中包含多个serveraddress
                Map<String, Integer> serversCountMap = jsonVO.getServersCountMap();
                Set<String> keySet2 = serversCountMap.keySet();
                for (String string : keySet2) {
                	Integer orDefault = linkCount.get(string);
                	orDefault=orDefault==null?0:orDefault;
                	Integer orDefault2 = serversCountMap.get(string);
                	linkCount.put(string, orDefault+orDefault2);
				}
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            logger.info(e.getMessage());
        }
        return linkCount;
    }
    /**
     * 筛选出近minutes分钟的额数据
     * @return List<JsonVO>
     */
    public static List<JsonVO> getTrafficInfoByMinute(int minutes,String key){
        List<JsonVO> jsonVOs = trafficInfo(key);
        List<JsonVO> sList  = new ArrayList<JsonVO>();
        //获取当前时间
        String timeE = TimeUtil.getCurrTime();
        //获取minutes分钟前的时间
        String timeS = TimeUtil.getTimeByMinute(TimeUtil.yyyy_MM_DDHH24miss(timeE), minutes);
        //选取minutes分钟内的数据
        for(JsonVO jsonVO : jsonVOs){
            //比较时间大小，并对返回的int值做判断
           int i = jsonVO.getEndTime().compareTo(timeS);
           int j = timeE.compareTo(jsonVO.getEndTime());
           if(i>0 && j>0){
               sList.add(jsonVO);
           }
        }
        return sList;
    }
    /**
     * 获取minutes时间内对应的值
     * @param minutes 时间
     * @param key 键值
     * @return
     */
    public static Map<String,Object> getForwardInfo(int minutes,String key){
        Map<String,Object> mapS = new HashMap<String,Object>();
        List<JsonVO> jsonVOs = getTrafficInfoByMinute(minutes,key);
        List<String> endTimeList = new ArrayList<String>();
        List<String> sourceCountList = new ArrayList<String>();
        //对list集合排序
        float currFlowSum = 0;
        Collections.sort(jsonVOs);
        for(JsonVO jsonVO : jsonVOs){
            String endTime = jsonVO.getEndTime();
            String sourceCount = jsonVO.getSourceCount();
            endTimeList.add(endTime);
            sourceCountList.add(sourceCount);
            currFlowSum += Float.valueOf(jsonVO.getSourceCount());
        }
        mapS.put("time", endTimeList);
        mapS.put("value", sourceCountList);
        mapS.put("sum", currFlowSum);
        return mapS;
    }
    /**
     * 获取时间段内redis的数据(默认为一天内)
     * @param key
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<JsonVO> getAllInfoByDay(String key,String startTime,String endTime){
        if(!Common.isEmpty(startTime)){
            startTime = TimeUtil.getLastDayTime();
        }
        if(!Common.isEmpty(endTime)){
            endTime = TimeUtil.getZeroTime();
        }
        List<JsonVO> jsonVOs = trafficInfo(key);
        List<JsonVO> sList  = new ArrayList<JsonVO>();
        for(JsonVO jsonVO : jsonVOs){
            //比较时间大小，并对返回的int值做判断
           int i = jsonVO.getEndTime().compareTo(startTime);
           int j = endTime.compareTo(jsonVO.getEndTime());
           if(i>0 && j>0){
               sList.add(jsonVO);
           }
        }
        return sList;
    }
    public static void main(String[] args){
       //System.out.println(TrafficUtil.trafficInfo("CSANTI_MONITOR_DP"));
       //System.out.println(getTrafficInfoByMinute(-20,"CSANTI_MONITOR_DP"));
        //System.out.println(getForwardInfo(-20,"CSANTI_MONITOR_DP"));
      /*  System.out.println(trafficInfo("CSANTI_MONITOR_QUERY"));
        System.out.println(trafficInfo("CSANTI_MONITOR_BOOK"));
        JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
        String value = jedisCluster.get("CSANTI_MONITOR_DP*");*/
       List<JsonVO> jVos = TrafficUtil.getTrafficInfoByMinute(-2, Constants.CSANTI_MONITOR_DP);
       System.out.println(jVos);
    }
}
