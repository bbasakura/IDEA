/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhCrawlerQueryRoutesRank;

/**
 * @author itheima
 *
 */
public interface INhCrawlerQueryRoutesRankService {
	
	/**
	 * 获取爬虫查询航线排行
	 * @return
	 *
	 **/
	
	public List<NhCrawlerQueryRoutesRank> getNhCrawlerQueryRoutesRank(String startTime, String endTime);

}
