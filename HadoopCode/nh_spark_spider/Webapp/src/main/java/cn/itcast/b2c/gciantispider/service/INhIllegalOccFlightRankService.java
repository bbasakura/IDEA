/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import java.math.BigInteger;
import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhIllegalOccFlightRank;

/**
 * @author itheima
 *
 */
public interface INhIllegalOccFlightRankService {

	/**
	 * 获取爬虫非法占座排行
	 * @return
	 **/
	List<NhIllegalOccFlightRank> getNhIllegalOccFlightRank(int pages,int rows,String startTime,String endTime);
    
	/**
	 * 获取数据量
	 * @return
	 **/
    BigInteger getAllCount(String startTime,String endTime);
}
