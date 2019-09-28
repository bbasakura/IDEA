/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhFlowQueryRate;

/**
 * @author itheima
 *
 */
public interface INhFlowQueryRateService {

	
	/**
	 * 获取查定比
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<NhFlowQueryRate> getFlowQueryRate(String startDate,String endDate);
	
}
