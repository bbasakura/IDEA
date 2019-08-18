package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.pojo.News;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class indexWriterServiceImpl implements IndexWriterService {

    @Autowired
    private CloudSolrServer cloudSolrServer;


    /**
     * 索引写入的方法
     *
     * @param newsList : 新闻的列表
     * @throws Exception
     */
    @Override
    public void newsIndexWriter(List<News> newsList) throws Exception {

        //1.注入CloudSolrServer对象


        //2.调用cloudSolrServer索引写入的方法
        cloudSolrServer.addBeans(newsList);

        //3. 提交
        cloudSolrServer.commit();

        //4. 千万不要关闭CloudSolrServer
    }



}
