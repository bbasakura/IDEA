package com.itheima.Search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;
import pojo.News;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class IndexSearchHighTest {

    private  static final String baseUrl="http://localhost:8080/solr/collection1";

    private static HttpSolrServer httpSolrServer =null;

    @Before
    public void init(){
        httpSolrServer = new HttpSolrServer(baseUrl);

    }

    /**
     * 排序条件查询
     */
    @Test
    public void sortSearch() throws SolrServerException {
        //1. 创建solr的链接对象

        //2. 进行查询
        SolrQuery solrQuery = new SolrQuery("*:*");


        //3. 设置排序条件
        //排序字段  ORDER.desc 降序   ORDER.asc 升序
        String field = "click";
        solrQuery.setSort(field, SolrQuery.ORDER.desc);
        solrQuery.setRows(50);

        //4. 执行查询
        QueryResponse response = httpSolrServer.query(solrQuery);


        //5. 获取数据打印
        List<News> newsList = response.getBeans(News.class);

        for (News news : newsList) {
            System.out.println(news);
        }

        //6.释放资源
        httpSolrServer.shutdown();
    }



    /**
     * 分页查询 : page: 查询第几页  pageSize: 每页显示多少条数据
     */
    @Test
    public void indexSearchPage() throws SolrServerException {
        Integer page = 4;

        Integer pageSize = 18;

        //计算起始下标
        Integer start = ( page - 1) * pageSize ;


        //1. 创建solr的链接对象

        //2. 进行查询
        SolrQuery solrQuery = new SolrQuery("*:*");


        //3. 设置分页查询条件 start  rows
       solrQuery.setStart(start);

       solrQuery.setRows(pageSize);


        //4. 执行查询
        QueryResponse response = httpSolrServer.query(solrQuery);


        //5. 获取数据打印
        List<News> newsList = response.getBeans(News.class);

        for (News news : newsList) {
            System.out.println(news);
        }

        //6.释放资源
        httpSolrServer.shutdown();

    }



    /**
     * 高亮查询
     */
    @Test
    public void highLightSearch() throws SolrServerException {
        //1. 创建solr的连接对象

        //2. 创建查询对象
        SolrQuery solrQuery = new SolrQuery("title:蓝瘦香菇");

        //2.1 开启高亮查询
       solrQuery.setHighlight(true);

        //2.2 设置的高亮字段
        solrQuery.addHighlightField("title");
        solrQuery.addHighlightField("content");

        //2.3 设置高亮的前缀和后缀
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");


        //3. 执行查询
        QueryResponse response = httpSolrServer.query(solrQuery);

        //4. 获取响应数据,打印结果
        List<News> newsList = response.getBeans(News.class);


        // 获取所有的高亮内容
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        //将高亮的内容获取出来,替换news对象上不高亮的内容: title   content
        for (News news : newsList) {

            //根据文档的id,获取当前文档的高亮内容
            String id = news.getId();

            Map<String, List<String>> map = highlighting.get(id);

            //获取title的高亮部分
            List<String> titleList = map.get("title");
            if(titleList != null && titleList.size() > 0){
                String hiTitle = titleList.get(0);
                //替换不亮的title
                news.setTitle(hiTitle);
            }


            //获取content的高亮部分
            List<String> contentList = map.get("content");
            if(contentList != null && contentList.size() > 0){
                String hiContent = contentList.get(0);
                //替换高亮content
                news.setContent(hiContent);
            }


            System.out.println(news);
        }

        //5.释放资源
        httpSolrServer.shutdown();


    }




    /**
     * 创建索引: 带加权因子
     */
    @Test
    public void indexWriterBoost01() throws IOException, SolrServerException {
        //1. 创建solr的连接对象,连接集群版本使用CloudSolrServer

        //2. 创建document对象
        SolrInputDocument document = new SolrInputDocument();

        document.addField("id","999");

        //带加权因子的字段: 加权默认是1
        document.addField("title","蓝瘦香菇哈哈哈哈",100000);
        document.addField("content","uuujjhjhhjghjhjhjjhhjhjhj");
        document.addField("click","10000000");
        document.addField("docurl","https://xw.qq.com/cmsid/20190701V09GRE00");

        //3. 调用httpSolrServer连接对象,发送http请求,完成创建索引的操作
        httpSolrServer.add(document);

        //4. 提交
        httpSolrServer.commit();

        //5. 释放资源
        httpSolrServer.shutdown();
    }

}
