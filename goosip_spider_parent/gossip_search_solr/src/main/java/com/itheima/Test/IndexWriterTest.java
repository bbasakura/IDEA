package com.itheima.Test;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;
import pojo.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexWriterTest {

    private  static final String baseUrl="http://localhost:8080/solr/collection1";

   private static HttpSolrServer httpSolrServer =null;

    @Before
    public void init(){
      httpSolrServer = new HttpSolrServer(baseUrl);

    }
    @Test
    public void indedWriterTest01() throws IOException, SolrServerException {
        //1.创建solr的链接对象

        //2.创建document对象

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","1");
        document.addField("title","我爱我的祖国,中华人民共和国");
        document.addField("content","岳云鹏见粉丝摔倒不扶还超有梗");
        document.addField("click","10000");
        document.addField("docurl","https://xw.qq.com/cmsid/20190701V09GRE00");


        //3.调用HttpSolrServer，发送http请求

        UpdateResponse response = httpSolrServer.add(document);


        //4.提交
        httpSolrServer.commit();


        //5.释放资源

        httpSolrServer.shutdown();;

    }
    @Test
    public void indedWriterTest02() throws IOException, SolrServerException {
        //1. 创建solr的连接对象
        HttpSolrServer httpSolrServer = new HttpSolrServer(baseUrl);

        //2. 创建document对象的列表
        List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();

        for(int i=2;i<= 100;i++){
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",i);
            document.addField("title","我爱我的祖国,蓝瘦香菇"+i);
            document.addField("content",i+"岳云鹏见粉丝摔倒不扶还超有梗");
            Integer click = i + 10000;
            document.addField("click",""+click);
            document.addField("docurl","https://xw.qq.com/cmsid/20190701V09GRE00"+i);
            list.add(document);
        }

        //3. 调用httpSolrServer连接对象,发送http请求,完成创建索引的操作
        httpSolrServer.add(list);


        //4.提交
        httpSolrServer.commit();


        //5.释放资源

        httpSolrServer.shutdown();;

    }


    @Test
    public void indexWriterJavaBean() throws IOException, SolrServerException {
        //1. 创建solr的连接对象


        //2. 创建javaBean对象
        News news = new News();

        news.setId("666");
        news.setTitle("我是特殊的标题666");
        news.setContent("一直是哈哈哈哈哈哈6666");
        news.setDocurl("http://www.666.com");
        news.setClick(66666l);


        //3. 调用javaBean添加到索引库
        UpdateResponse updateResponse = httpSolrServer.addBean(news);
        //4.提交
        httpSolrServer.commit();
        //5.释放资源
        httpSolrServer.shutdown();

    }


    @Test
    public void indexWriterJavaBean2() throws IOException, SolrServerException {
        //1. 创建solr的连接对象


        //2. 创建javaBean对象
        News news = new News();

        news.setId("888");
        news.setTitle("我是特殊的标题888");
        news.setContent("一直是哈哈哈哈哈哈888");
        news.setDocurl("http://www.888.com");
        news.setClick(88888l);


        //3. 调用javaBean添加到索引库
        UpdateResponse updateResponse = httpSolrServer.addBean(news);
        //4.提交
        httpSolrServer.commit();
        //5.释放资源
        httpSolrServer.shutdown();

    }





    @Test
    public void indexDelete() throws IOException, SolrServerException {
        //1. 创建solr的连接对象


            //2. 根据id删除、
//        httpSolrServer.deleteById("change.me");

        httpSolrServer.deleteByQuery("id:100");
        //4.提交
        httpSolrServer.commit();
        //5.释放资源
        httpSolrServer.shutdown();

    }





}
