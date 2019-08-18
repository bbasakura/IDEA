package com.itheima.service;

import com.itheima.pojo.News;

import java.util.List;

/**
 * @author itheima
 * @Title: IndexWriterService
 * @ProjectName gossip-parent
 * @Description: 索引写入的接口
 * @date 2019/7/710:47
 */
public interface IndexWriterService {


    /**
     * 索引写入的方法
     * @param newsList : 新闻的列表
     * @throws Exception
     */
    public void newsIndexWriter(List<News> newsList) throws Exception;



}
