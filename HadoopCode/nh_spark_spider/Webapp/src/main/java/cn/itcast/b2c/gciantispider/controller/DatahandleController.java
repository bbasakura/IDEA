/**
 * 
 */
package cn.itcast.b2c.gciantispider.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.Analyzerule;
import cn.itcast.b2c.gciantispider.model.Datahandle;
import cn.itcast.b2c.gciantispider.service.IDatahandleService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.RedisFlagUtils;

/**
 * @author itheima
 * 数据处理控制器
 *
 */
@Controller
@RequestMapping("/datahandle")
public class DatahandleController {
	private final static Logger logger = Logger.getLogger(DatahandleController.class);
	
	@Autowired
	private IDatahandleService datahandleService;
	
	
	/**
	 * 
	 * 获取数据处理信息
	 * @return
	 */
	@RequestMapping(value="/getAnalyzerule",method = RequestMethod.POST)
	@ResponseBody
	public Analyzerule getAnalyzerule(HttpServletRequest request){
		Analyzerule analyzerule = null;
		String behaviorType = request.getParameter("behaviorType");
		String flightType = request.getParameter("flightType");
		String dataType = request.getParameter("dataType");
		String hql = Common.variableType(dataType);
		analyzerule = datahandleService.getAnalyzerule(behaviorType,flightType,hql);
		analyzerule = null == analyzerule?new Analyzerule():analyzerule;
		return analyzerule;
	}
	
	/**
	 * 
	 * 保存或修改数据
	 * @return
	 */
	@RequestMapping(value="/saveOrUpdateData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveOrUpdateData(HttpServletRequest request){
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Map<String,Object> resultS = new HashMap<String,Object>();
		//航班类型
		String flightType = request.getParameter("flightType");
		//操作类型
		String behaviorType = request.getParameter("behaviorType");
		//数据类型
		String dataType = request.getParameter("dataType");
		//解析规则url
		String requestMatchExpression = request.getParameter("requestMatchExpression");
		//解析匹配规则
		String requestMethod = request.getParameter("requestMethod");
		//是否常规的get请求参数
		String isNormalGet = request.getParameter("isNormalGet");
		//是否常规的form请求参数
		String isNormalForm = request.getParameter("isNormalForm");
		//通过ajax提交的json数据
		String isApplicationJson = request.getParameter("isApplicationJson");
		//通过ajax提交的xml数据
		String isTextXml = request.getParameter("isTextXml");
		//是否json数据格式传参
		String isJson = request.getParameter("isJson");
		//是否xml数据格式传参
		String isXml = request.getParameter("isXml");
		//json或xml数据在form表单数据中的key值
		String formDataField = request.getParameter("formDataField");
		//购票人id -会员
		String bookBookUserId = request.getParameter("bookBookUserId");
		//购票人id -非会员
		String bookBookUnUserId = request.getParameter("bookBookUnUserId");
		//乘机人名
		String bookPsgName = request.getParameter("bookPsgName");
		//乘机人类型
		String bookPsgType = request.getParameter("bookPsgType");
		//证件类型
		String bookIdType = request.getParameter("bookIdType");
		//乘机人证件号
		String bookIdCard = request.getParameter("bookIdCard");
		//联系人名
		String bookContractName = request.getParameter("bookContractName");
		//联系人手机号
		String bookContractPhone = request.getParameter("bookContractPhone");
		//始发地-预订
		String bookDepCity = request.getParameter("bookDepCity");
		//目的地-预订
		String bookArrCity = request.getParameter("bookArrCity");
		//起飞时间-预订
		String bookFlightDate = request.getParameter("bookFlightDate");
		//还不知道是什么鬼
		String bookFlightNo = request.getParameter("bookFlightNo");
		//舱位级别
		String bookCabin = request.getParameter("bookCabin");
		//始发地 -查询
		String queryDepCity = request.getParameter("queryDepCity");
		//目的地 -查询
		String queryArrCity = request.getParameter("queryArrCity");
		//起飞时间 -查询
		String queryFlightDate = request.getParameter("queryFlightDate");
		//成人乘机人数
		String queryAdultNum = request.getParameter("queryAdultNum");
		//儿童乘机人数
		String queryChildNum = request.getParameter("queryChildNum");
		//婴儿乘机人数
		String queryInfantNum = request.getParameter("queryInfantNum");
		//国际-国家
		String queryCountry = request.getParameter("queryCountry");
		//国际-是否往返
		String queryTravelType = request.getParameter("queryTravelType");
		//国际-乘机人姓名中的姓
		String bookPsgFirName = request.getParameter("bookPsgFirName");
		
		String id = "";
		
		Analyzerule analyzerule;
		
		String hql = Common.variableType(dataType);
		analyzerule = datahandleService.getAnalyzerule(behaviorType,flightType,hql);
		if(analyzerule != null){
			id = analyzerule.getId();
		}else {
			id = UUID.randomUUID().toString();
		}
		analyzerule = new Analyzerule(id, flightType, behaviorType, requestMatchExpression, requestMethod, isNormalGet, isNormalForm, isApplicationJson, isTextXml, isJson, isXml, formDataField, bookBookUserId, bookBookUnUserId, bookPsgName, bookPsgType, bookIdType, bookIdCard, bookContractName, bookContractPhone, bookDepCity, bookArrCity, bookFlightDate, bookFlightNo, bookCabin, queryDepCity, queryArrCity, queryFlightDate, queryAdultNum, queryChildNum, queryInfantNum, queryCountry, queryTravelType, bookPsgFirName);
		try{
			datahandleService.saveOrUpdateData(analyzerule);
			RedisFlagUtils.setAnalyzeRuleChangeFlag();
			resultS.put("result", "0");
		}catch(Exception e){
			resultS.put("result", "1");
		}
		
		return resultS;
	}
}
