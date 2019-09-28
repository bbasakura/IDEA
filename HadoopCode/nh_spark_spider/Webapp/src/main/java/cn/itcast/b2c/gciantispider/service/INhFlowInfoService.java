/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhFlowInfo;


/**
 * @author itheima
 *
 */
public interface INhFlowInfoService {
	
	/**
	 * 获取流量情况
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<NhFlowInfo> getNhFlowInfo(String startDate,String endDate);
}
