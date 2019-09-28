package cn.itcast.b2c.gciantispider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhFlowQueryRate;
import cn.itcast.b2c.gciantispider.model.NhFourFlowNum;
import cn.itcast.b2c.gciantispider.service.INhFlowQueryRateService;
import cn.itcast.b2c.gciantispider.service.INhFourFlowNumService;

/**
 * @author itheima 
 * 可视化爬虫对查定比影响控制器
 * 
 */
@Controller
@RequestMapping("/flowQueryRate")
public class VisualizeFlowQueryRateController {

	@Autowired
	private INhFlowQueryRateService nhFlowQueryRateService;
	
	@Autowired
	private INhFourFlowNumService nhFourFlowNumService;
	/**
     * 获取各种用户查定比
     * @param date 日期
     * @return
     */
	@RequestMapping(value = "/getFlowQueryRate", method = RequestMethod.POST)
	@ResponseBody
	public List<NhFlowQueryRate> getFlowQueryRate(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
		return nhFlowQueryRateService.getFlowQueryRate(startDate, endDate);
	}
	/**
     * 获取四类用户占比
     * @param date 日期
     * @return
     */
	@RequestMapping(value = "/getNhFourFlowNum", method = RequestMethod.POST)
	@ResponseBody
	public List<NhFourFlowNum> getNhFourFlowNum(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
		return nhFourFlowNumService.getNhFourFlowNum(startDate, endDate);
	}
	
}
