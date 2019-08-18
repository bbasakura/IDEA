package com.itheima.service;

import com.itheima.pojo.News;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultBean;

import java.util.List;

/**
 * @author itheima
 * @Title: NewsService
 * @ProjectName gossip-parent
 * @Description: 新闻的服务接口
 * @date 2019/7/714:59
 */
public interface NewsService {


    /**
     * 调用dao,获取新闻列表数据,
     * 调用远程的索引写入服务,将新闻数据写入solrcloud集群
     * @throws Exception
     */
    public  void newsIndexWriter() throws Exception;



    public List<News> findByKeywords(ResultBean resultBean) throws Exception;

    /**
     * @method: findByKeywords
     * @param: keywords
     * @return: java.util.List<com.itheima.pojo.News>
     * @description:
     * @Author: sakura
     * @Date: 2019/7/8 0008 19:43
     */

    public PageBean findByPageQuery(ResultBean resultBean) throws Exception;
}
