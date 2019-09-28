package cn.itcast.b2c.gciantispider.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhCrawlerCurdayInfo;
import cn.itcast.b2c.gciantispider.model.SystemFunctionInfo;
import cn.itcast.b2c.gciantispider.pageUtil.JsonVO;
import cn.itcast.b2c.gciantispider.pageUtil.SpiderVO;
import cn.itcast.b2c.gciantispider.service.INhCrawlerCurdayInfoService;
import cn.itcast.b2c.gciantispider.service.INhSysmonitorSpiderdistinguishService;
import cn.itcast.b2c.gciantispider.service.ISystemFunctionInfoService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.TrafficUtil;

@Controller
@RequestMapping("/systemMonitoring")
public class IndexSystemMonitorController {

    private static final Logger logger = Logger.getLogger(IndexSystemMonitorController.class);

    @Autowired
    private ISystemFunctionInfoService systemFunctionInfoService;

    @Autowired
    private INhSysmonitorSpiderdistinguishService nhSysmonitorSpiderdistinguishService;
    @Autowired
    private INhCrawlerCurdayInfoService nhCrawlerCurdayInfoService;
    /**
     * 
     * 获取爬虫识别情况
     */
    @RequestMapping(value = "/getNhCrawlerCurdayInfoByDate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNhCrawlerCurdayInfoByDate() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SpiderVO spiderVO = nhSysmonitorSpiderdistinguishService.getSysmonitorSpiderdistinguish();
            NhCrawlerCurdayInfo nhCrawlerCurdayInfo = nhCrawlerCurdayInfoService.getNhCrawlerCurdayInfoByDate();
            map.put("spiderVO", spiderVO);
            map.put("flownum", nhCrawlerCurdayInfo.getFlowNum());
            map.put("code", 0);
        }catch (Exception e) {
            logger.info(e.getMessage());
            map.put("result", "失败");
            map.put("code", 1);
            return map;
        }
        return map;
    }
    /**
     * 
     * 获取系统功能运行情况
     */
    @RequestMapping(value = "/getSystemFunctionInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSystemFunctionInfo(HttpServletRequest request) {
        List<SystemFunctionInfo> systemFunctionInfos = new ArrayList<SystemFunctionInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //数据处理模块
//            List<JsonVO> jVos = TrafficUtil.trafficInfo(Constants.CSANTI_MONITOR_DP);
            List<JsonVO> jVos = TrafficUtil.getTrafficInfoByMinute(-2, Constants.CSANTI_MONITOR_DP);
            if(null==jVos || 0==jVos.size() ){
                map.put("sign", 1);
            }else{
                map.put("sign", 0);
            }
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            map.put("result", "失败");
            map.put("sign", 1);
            return map;
        }
        return map;
    }
    /**
     * 
     * 获取实时流量转发情况
     */
    @RequestMapping(value = "/getRealTimeTraffic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getRealTimeTraffic(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            map = TrafficUtil.getForwardInfo(-20,Constants.CSANTI_MONITOR_DP);
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getStackTrace());
            logger.info(e.getMessage());
        }
        return map;
    }
    /**
     * 
     * 获取实时链路流量转发情况
     * 
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/getRealTimeLinkTraffic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getRealTimeLinkTraffic(HttpServletRequest request) {
        List<Object> valuelList = new ArrayList<Object>();
        List<String> keyList = new ArrayList<String>();
        Map mapS = null;
        List<JsonVO> jsonVOs = new ArrayList<JsonVO>();
        jsonVOs = TrafficUtil.getTrafficInfoByMinute(-20,Constants.CSANTI_MONITOR_DP);
        // 对list集合排序
        Collections.sort(jsonVOs);
        // 取list集合最一条数据（即最新的一条数据）
        int size = jsonVOs.size();
        if (size > 0) {
            mapS = new HashMap();
            JsonVO jsonVO = jsonVOs.get(jsonVOs.size() - 1);
            Map<String, Object> serversCountMap = jsonVO.getServersCountMap();
            Set<String> keySet = serversCountMap.keySet();
            for (String key : keySet) {
                Object value = serversCountMap.get(key);
                keyList.add(key);
                valuelList.add(value);
            }
            mapS.put("key", keyList);
            mapS.put("value", valuelList);
            return mapS;
        }
        return mapS;
    }

}
