package cn.itcast.b2c.gciantispider.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhPerformancemonitorOfflinespeed;
import cn.itcast.b2c.gciantispider.model.ServerStatusInfo;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.service.INhPerformancemonitorOfflinespeedService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.DateFormatter;
import cn.itcast.b2c.gciantispider.util.ElasticUtil;
import cn.itcast.b2c.gciantispider.util.JsonUtil;
/**
 * 
 * 性能监控控制器
 *
 */
@Controller
@RequestMapping(value="/performance")
public class IndexPerformanceMonitorController {
    
    @SuppressWarnings("unused")
    private final static Logger logger = Logger.getLogger(IndexPerformanceMonitorController.class.getName());
    
    @Autowired
    INhPerformancemonitorOfflinespeedService nhPerformancemonitorOfflinespeedService;
    /**
     * 获取所有服务器状态
     * @param request
     * @return
     */
    @RequestMapping(value="/getAllServerStatusInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllServerStatusInfo(HttpServletRequest request){
        List<ServerStatusInfo> serverStatusInfolist = new ArrayList<ServerStatusInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        //serverStatusInfolist = JsonUtil.analysisJsonGetServerStatusInfoList(ElasticUtil.getServerStatusJson());
        //数据备份
//        serverStatusInfoService.dataBackups(serverStatusInfolist);
        map.put("serverStatusInfolist", null);
        
        return map;
    	
    }
    /**
     * 获取系统所有分析速度
     * @param request
     * @return
     */
    @RequestMapping(value="/getAllSystemDataAnalysis",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllSystemDataAnalysis(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String offlineSpeed = null;
        String realtimeSpeed = null;
        String nonRealtimeSpeed = null;
        //转换后的数据
        String realTimeSpeed = null;
        String nonRealTimeSpeed = null;
        //保留小数后的数据
        String newRealTimeSpeed = null;
        String newNonRealTimeSpeed = null;
        //保留3位小数
        DecimalFormat  df = new DecimalFormat("#0.000");
        //获取实时速度
        JsonVO jsonVO = nhPerformancemonitorOfflinespeedService.getRealtimeSpeed(Constants.CSANTI_MONITOR_QUERY);
        if (null != jsonVO) {
             realtimeSpeed = jsonVO.getCountPerMillis();
             //类型转换
             if (realtimeSpeed!=null) {
                 BigDecimal bd = new BigDecimal(realtimeSpeed);
                 realTimeSpeed = bd.toPlainString();
                 newRealTimeSpeed = df.format(Double.valueOf(realTimeSpeed.toString()));
             }else {
                 newRealTimeSpeed = "0";
             }
        }else {
            newRealTimeSpeed = "0";
        }
        //获取离线速度
    /*    String date = DateFormatter.getSpecifiedDayBefore(DateFormatter.getCurrTime());
        NhPerformancemonitorOfflinespeed nhPerformancemonitorOfflinespeed = nhPerformancemonitorOfflinespeedService.getOfflineSpeed(date);
        if (nhPerformancemonitorOfflinespeed != null) {
            offlineSpeed =  nhPerformancemonitorOfflinespeed.getSpeed().toString();
        }else {
            offlineSpeed = "0";
        }*/
        //获取准实时速度
        JsonVO jsonVO2 = nhPerformancemonitorOfflinespeedService.getRealtimeSpeed(Constants.CSANTI_MONITOR_BOOK);
        if (jsonVO2 != null) {
            nonRealtimeSpeed = jsonVO2.getCountPerMillis();
            //类型转换
            if (nonRealtimeSpeed!=null) {
                BigDecimal bd = new BigDecimal(nonRealtimeSpeed);
                nonRealTimeSpeed = bd.toPlainString();
                newNonRealTimeSpeed = df.format(Double.valueOf(nonRealTimeSpeed.toString()));
            }else {
                newNonRealTimeSpeed = "0";
            }
        }else {
            newNonRealTimeSpeed = "0";
        }
        
        offlineSpeed = "0";
        map.put("realtimeSpeed",newRealTimeSpeed );
        map.put("nonreadtime", newNonRealTimeSpeed);
        map.put("offlineSpeed", offlineSpeed);
        return map;
    }
}
