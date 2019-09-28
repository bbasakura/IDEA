package cn.itcast.b2c.gciantispider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhFlowInfo;
import cn.itcast.b2c.gciantispider.service.INhFlowInfoService;


/**
 * @author itheima
 * 可视化爬虫对系统稳定性影响控制器
 */

@Controller
@RequestMapping("/nhFlowInfo")
public class VisualizeFlowInfoController {
	
	@Autowired
	private INhFlowInfoService nhFlowInfoService;
	
	/*@Autowired
	private INhFlowInfoRateService nhFlowInfoRateService;*/
	/**
	 * 获取流量情况
	 * @param date 日期
     * @return
	 */
	@RequestMapping(value = "/getNhFlowInfo" , method = RequestMethod.POST)
	@ResponseBody
	public List<NhFlowInfo> getNhFlowInfo(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
		return nhFlowInfoService.getNhFlowInfo(startDate, endDate);
	}
	/**
	 * 获取爬虫流量比率
	 * @param date 日期
	 * @return
	 */
	@RequestMapping(value = "/getNhFlowInfoRate" , method = RequestMethod.POST)
	@ResponseBody
	public List<NhFlowInfo> getNhFlowInfoRate(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
		return nhFlowInfoService.getNhFlowInfo(startDate, endDate);
	}
	
}
