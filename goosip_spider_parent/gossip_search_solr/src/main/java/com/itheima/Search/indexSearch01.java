package com.itheima.Search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;
import pojo.News;

import java.io.IOException;
import java.util.List;

public class indexSearch01 {

    private  static final String baseUrl="http://localhost:8080/solr/collection1";

    private static HttpSolrServer httpSolrServer =null;

    @Before
    public void init(){
        httpSolrServer = new HttpSolrServer(baseUrl);

    }

    @Test
    public void indexDelete() throws IOException, SolrServerException {
        //1. 创建solr的连接对象


        SolrQuery solrQuery = new SolrQuery("title:*国");
        solrQuery.setRows(Integer.MAX_VALUE);

        QueryResponse response = httpSolrServer.query(solrQuery);

        SolrDocumentList results = response.getResults();

        //5. 遍历打印文档内容

        for (SolrDocument document : results) {
            String id = (String) document.get("id");
            System.out.println("id:" + id);
            String title = (String) document.get("title");
            System.out.println("title:" + title);
            String content = (String) document.get("content");
            System.out.println("content:" + content);
            String docurl = (String) document.get("docurl");
            System.out.println("docurl:" + docurl);
            Long click = (Long) document.get("click");
            System.out.println("click: " + click);

        }
        //5.释放资源
        httpSolrServer.shutdown();

    }


    @Test
    public void indexSearchJavaBean() throws SolrServerException {
        //1. 创建solr的连接对象
        //2. 创建查询对象
        //2.1 查询所有数据
//        SolrQuery solrQuery = new SolrQuery("*:*");
        SolrQuery solrQuery = new SolrQuery("title:*国");

        //3. 执行查询
        QueryResponse response = httpSolrServer.query(solrQuery);

        //4. 获取响应数据: 反射技术
        List<News> list = response.getBeans(News.class);


        //5. 遍历打印文档内容
        for (News news : list) {
            System.out.println(news);
        }

        //5. 释放资源
        httpSolrServer.shutdown();
    }


    @Test
    public void  indexHighSearch() throws SolrServerException {

        //1. 创建solr的连接对象

        //2. 创建查询对象: 根据查询语法查询
        //2.1 通配符查询:   *   ?
//        SolrQuery solrQuery = new SolrQuery("title:祖?");

        //2.2 模糊查询:title:兰瘦古香~2
//        SolrQuery solrQuery = new SolrQuery("title:蓝瘦古香~2");

        //2.3 区间范围查询: {min TO max}  [min TO max] {min TO max] [min TO max}
//        click:[10080 TO 20000]
//        SolrQuery solrQuery = new SolrQuery("click:[10000 TO *]");
        //2.4 布尔查询 AND  与   OR  或    NOT  非
        //2.5 子查询  改变优先级
        SolrQuery solrQuery = new SolrQuery("( title:祖?  AND click:[10000 TO *] ) OR ( title:腿~ ) NOT click:[10050 TO 10080]");

        //一次性返回所有内容
        solrQuery.setRows(Integer.MAX_VALUE);


        //3. 执行查询
        QueryResponse response = httpSolrServer.query(solrQuery);


        //4.获取响应的数据
        List<News> newsList = response.getBeans(News.class);

        //5. 打印结果
        for (News news : newsList) {
            System.out.println(news);
        }

        //6. 关闭资源
        httpSolrServer.shutdown();


    }



}
