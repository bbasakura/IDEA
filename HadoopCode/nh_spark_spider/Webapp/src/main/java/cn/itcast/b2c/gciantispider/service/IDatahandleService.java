/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;


import cn.itcast.b2c.gciantispider.model.Analyzerule;

/**
 * @author itheima
 *
 */
public interface IDatahandleService {
	
	/**
	 * 
	 * 获取数据处理
	 * @return
	 */
	public Analyzerule getAnalyzerule(String behaviorType,String flightType,String hql);
	
	/**
	 * 
	 * 保存或修改数据
	 */
	public void saveOrUpdateData(Analyzerule analyzerule);
	
}
