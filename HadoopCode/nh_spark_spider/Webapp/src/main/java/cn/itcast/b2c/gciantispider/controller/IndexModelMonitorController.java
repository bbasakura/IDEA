package cn.itcast.b2c.gciantispider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhModelmonitorAccuracy;
import cn.itcast.b2c.gciantispider.model.NhProcessNum;
import cn.itcast.b2c.gciantispider.service.INhModelmonitorAccuracyService;
import cn.itcast.b2c.gciantispider.service.INhProcessNumService;

@Controller
@RequestMapping("/modelMonitor")
public class IndexModelMonitorController {
    
    @Autowired
    INhModelmonitorAccuracyService nhModelmonitorAccuracyService;
    
    @Autowired
    INhProcessNumService nhProcessNumService;
    
    @RequestMapping(value="/getNhModelmonitorAccuracy",method = RequestMethod.POST)
    @ResponseBody
    public List<NhModelmonitorAccuracy> getNhModelmonitorAccuracy(@RequestParam("timeType") String timeType, @RequestParam("flowType") String flowType){
        return nhModelmonitorAccuracyService.getNhModelmonitorAccuracyByType(timeType, flowType);
    }
    
    @RequestMapping(value="/getAllNhProcessNums",method = RequestMethod.POST)
    @ResponseBody
    public List<NhProcessNum> getAllNhProcessNums(){
        return nhProcessNumService.getAllNhProcessNums();
    }

}
