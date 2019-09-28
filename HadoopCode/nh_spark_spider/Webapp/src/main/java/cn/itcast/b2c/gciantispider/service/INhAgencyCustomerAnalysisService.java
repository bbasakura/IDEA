/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhAgencyCustomerAnalysis;

/**
 * @author itheima
 *
 */
public interface INhAgencyCustomerAnalysisService {
	
	/**
	 * 获取代购识别分析
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<NhAgencyCustomerAnalysis> getNhAgencyCustomerAnalysis(String startDate,String endDate);
}
