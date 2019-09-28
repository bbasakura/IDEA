package cn.itcast.b2c.gciantispider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhDomesticInterConversionRate;
import cn.itcast.b2c.gciantispider.model.NhFlightQueryConversionRate;
import cn.itcast.b2c.gciantispider.model.NhUserConversionRate;
import cn.itcast.b2c.gciantispider.service.INhDomesticInterConversionRateService;
import cn.itcast.b2c.gciantispider.service.INhFlightQueryConversionRateService;
import cn.itcast.b2c.gciantispider.service.INhUserConversionRateService;
/**
 * 可视化转换率控制器
 * 
 */
@Controller
@RequestMapping("/conversionRate")
public class VisualizeConversionRateController {
    
    @Autowired
    INhDomesticInterConversionRateService nhDomesticInterConversionRateService;
    
    @Autowired
    INhFlightQueryConversionRateService nhFlightQueryConversionRateService;
    
    @Autowired
    INhUserConversionRateService nhUserConversionRateService;
    /**
     * 获取国内，国际航班转化率
     * @param startDate
     * @param flightType
     * @return
     */
    @RequestMapping(value = "/getNhDomesticInterConversionRate",method=RequestMethod.POST)
    @ResponseBody
    public List<NhDomesticInterConversionRate> getNhDomesticInterConversionRate(@RequestParam("startDate")String startDate,@RequestParam("flightType")String flightType){
        List<NhDomesticInterConversionRate> list = nhDomesticInterConversionRateService.queryByDate(startDate, flightType);
        if (list!=null && list.size()!=0) {
            return list;
        }
        return null;
    }
    /**
     * 获取用户转化率
     * @param startDate
     * @param userType
     * @return
     */
    @RequestMapping(value = "/getNhUserConversionRate",method=RequestMethod.POST)
    @ResponseBody
    public List<NhUserConversionRate> getNhUserConversionRate(@RequestParam("startDate")String startDate,@RequestParam("userType")String userType){
        List<NhUserConversionRate> list = nhUserConversionRateService.queryByDate(startDate, userType);
        if (list!=null && list.size()!=0) {
            return list;
        }
        return null;
    }
    /**
     * 获取爬虫航班转化率
     * @param startDate
     * @param flightType
     * @return
     */
    @RequestMapping(value = "/getNhFlightQueryConversionRate",method=RequestMethod.POST)
    @ResponseBody
    public  List<NhFlightQueryConversionRate> getNhFlightQueryConversionRate(@RequestParam("startDate")String startDate,@RequestParam("querySpiderType")String querySpiderType){
        List<NhFlightQueryConversionRate> list = nhFlightQueryConversionRateService.queryByDate(startDate, querySpiderType);
        if (list!=null && list.size()!=0) {
            return list;
        }
        return null;
    }
    
}
