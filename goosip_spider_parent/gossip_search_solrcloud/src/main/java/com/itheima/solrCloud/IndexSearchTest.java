package com.itheima.solrCloud;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class IndexSearchTest {

    /**
     * zookeeper集群的地址
     */
    private static final String zkHost = "node01:2181,node02:2181,node03:2181";

    private CloudSolrServer cloudSolrServer;

    /**
     * 初始化 ,构建连接对象
     */
    @Before
    public void init() {
        //1. 创建solrcloud的连接对象
        cloudSolrServer = new CloudSolrServer(zkHost);


        //2. 设置默认写入的collection
        cloudSolrServer.setDefaultCollection("collection2");

    }


    /**
     * 查询索引
     */
    @Test
    public void searchIndex() throws SolrServerException {
        //1, 2

        //3. 创建查询对象
        SolrQuery solrQuery = new SolrQuery("*:*");


        //4. 执行查询
        QueryResponse response = cloudSolrServer.query(solrQuery);


        //5.遍历打印结果
        SolrDocumentList documentList = response.getResults();

        for (SolrDocument document : documentList) {
            String id = (String) document.get("id");
            System.out.println("id:" + id);
            String title = (String) document.get("title");
            System.out.println("title:" + title);
            String content = (String) document.get("content");
            System.out.println("content:" + content);
            String docurl = (String) document.get("docurl");
            System.out.println("docurl:" + docurl);
            String editor = (String) document.get("editor");
            System.out.println("editor:" + editor);
            String source = (String) document.get("source");
            System.out.println("source:" + source);
            Date time = (Date) document.get("time");
            System.out.println("time:" + time);
        }


    }


    /**
     * 查询索引
     */
    @Test
    public void searchIndexJavaBean() throws SolrServerException {
        //1, 2

        //3. 创建查询对象
        SolrQuery solrQuery = new SolrQuery("*:*");


        //4. 执行查询
        QueryResponse response = cloudSolrServer.query(solrQuery);


        //5.遍历打印结果
        //日期类型向 string数据类型转换过程中出错了
        /*List<News> newsList = response.getBeans(News.class);
        for (News news : newsList) {
            System.out.println(news);
        }*/

    }


    /**
     * 释放资源
     */
    @After
    public void after() {
        cloudSolrServer.shutdown();
    }

}




