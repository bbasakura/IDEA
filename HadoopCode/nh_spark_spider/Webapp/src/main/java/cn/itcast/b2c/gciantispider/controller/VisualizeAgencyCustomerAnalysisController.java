package cn.itcast.b2c.gciantispider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhAgencyCustomerAnalysis;
import cn.itcast.b2c.gciantispider.service.INhAgencyCustomerAnalysisService;

/**
 * @author itheima
 * 可视化代购识别分析控制器
 */
@Controller
@RequestMapping("/agencyCustomerAnalysis")
public class VisualizeAgencyCustomerAnalysisController {

	@Autowired
	private INhAgencyCustomerAnalysisService nhAgencyCustomerAnalysisService;
	/**
	 * 获取代购客户分析
	 * 
	 */
	@RequestMapping(value = "/getNhAgencyCustomerAnalysis",method=RequestMethod.POST)
	@ResponseBody
	public List<NhAgencyCustomerAnalysis> getNhAgencyCustomerAnalysis(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
		return nhAgencyCustomerAnalysisService.getNhAgencyCustomerAnalysis(startDate, endDate);
	}
}
