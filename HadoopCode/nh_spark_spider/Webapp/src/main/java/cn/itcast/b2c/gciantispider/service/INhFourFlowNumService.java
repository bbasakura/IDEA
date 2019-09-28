/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhFourFlowNum;

/**
 * @author itheima
 *
 */
public interface INhFourFlowNumService {
	
	public List<NhFourFlowNum> getNhFourFlowNum(String startDate,String endDate);

}
