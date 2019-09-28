/**
 * 
 */
package cn.itcast.b2c.gciantispider.service;

import cn.itcast.b2c.gciantispider.model.NhCrawlerCurdayInfo;

/**
 * @author itheima
 *
 */
public interface INhCrawlerCurdayInfoService {

	/**
	 * 获取爬虫识别情况
	 */
	public NhCrawlerCurdayInfo getNhCrawlerCurdayInfo();
	/*
	 * 获取前一天爬虫识别情况
	 */
	public NhCrawlerCurdayInfo  getNhCrawlerCurdayInfoByDate();
}
