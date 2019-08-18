package com.itheima.solrCloud;

import com.itheima.pojo.News;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndexWriterTest {


    /**
     * zookeeper集群的地址
     */
    private static final  String zkHost = "node01:2181,node02:2181,node03:2181";

    private CloudSolrServer cloudSolrServer;

    /**
     * 初始化 ,构建连接对象
     */
    @Before
    public void init(){
        //1. 创建solrcloud的连接对象
        cloudSolrServer = new CloudSolrServer(zkHost);


        //2. 设置默认写入的collection
        cloudSolrServer.setDefaultCollection("collection2");

    }



    /**
     * 索引创建
     */
    @Test
    public  void indexWriterTest() throws IOException, SolrServerException {


        //3. 创建document文档对象
        SolrInputDocument document = new SolrInputDocument();

        document.addField("id","1146941841892048900");
        document.addField("title","马天宇郑爽演技被吐槽，青涩偶像剧只剩下了生涩？");
        document.addField("content","马天宇和郑爽主演的《流淌的美好时光》，日前接档《少年派》亮相荧屏，并以一度破2%的最高收视率强势拉开荧屏暑期档的帷幕。但是，几集过后出现流量与批评齐飞的场面，不少观众批评演员表演不够到位");
        document.addField("docurl","https://xw.qq.com/cmsid/20190704A0R1CA00");
        //不是标准的utc标准格式:  默认进去减去了8个小时


        document.addField("time",new Date());
        document.addField("source","方舟先生");
        document.addField("editor","方舟先生");


        //4. 写入索引
        cloudSolrServer.add(document);


        //5. 提交
        cloudSolrServer.commit();

        //6. 释放资源

        cloudSolrServer.shutdown();


    }



    /**
     * 索引写入的操作 :javabean
     */
    @Test
    public  void  indexWriterJavaBean02() throws IOException, SolrServerException {
        //1,2 已经初始化


        //3. 创建News对象
        News news = new News();
        news.setId("1146941842215010306");
        news.setTitle("外媒曝BCC将拍《安娜卡列尼娜》剧集，《名利场》编剧加盟");
        news.setContent("外媒曝BCC将拍《安娜卡列尼娜》剧集，《名利场》编剧加盟");
        news.setEditor("猫眼电影");

        //需要给solr标准的日期格式数据
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss'Z'");
        news.setTime(format.format(date));
        news.setSource("猫眼电影");
        news.setUrl("https://xw.qq.com/cmsid/20190704A0PVUC00");


        //4. 写入索引库
        cloudSolrServer.addBean(news);


        //5. 提交
        cloudSolrServer.commit();

    }


    /**
     * 索引写入多个javabean的操作 :javabean
     */
    @Test
    public  void  indexWriterJavaBeanMany02() throws IOException, SolrServerException {
        //1,2 已经初始化



        //3. 创建News对象
        List<News> list = new ArrayList<News>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss'Z'");
        for(int i=1;i<=2000;i++){
            News news = new News();
            news.setId("1146941842215010306"+i);
            news.setTitle("外媒曝BCC将拍《安娜卡列尼娜》剧集，《名利场》编剧加盟"+i);
            news.setContent("外媒曝BCC将拍《安娜卡列尼娜》剧集，《名利场》编剧加盟"+i);
            news.setEditor("猫眼电影"+i);

            //需要给solr标准的日期格式数据
            Date date = new Date();
            news.setTime(format.format(date));
            news.setSource("猫眼电影");
            news.setUrl("https://xw.qq.com/cmsid/20190704A0PVUC00");
            list.add(news);
        }


        //4. 写入索引库
        cloudSolrServer.addBeans(list);


        //5. 提交
        cloudSolrServer.commit();

    }



    /**
     * 修改索引库
     */
    @Test
    public void updateIndex() throws IOException, SolrServerException {
        //1.2
        //3. 创建News对象
        News news = new News();
        news.setId("1146941842215010306");
        news.setTitle("迪士尼，快把我的小美人鱼还给我");
        news.setContent("迪士尼，快把我的小美人鱼还给我");
        news.setEditor("狗眼电影");

        //需要给solr标准的日期格式数据
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss'Z'");
        news.setTime(format.format(date));
        news.setSource("狗眼电影");
        news.setUrl("https://xw.qq.com/cmsid/20190704A0PVUC00888");


        //4. 修改索引
        cloudSolrServer.addBean(news);

        //5. 提交
        cloudSolrServer.commit();


    }


    /**
     * 释放资源
     */
    @After
    public  void after(){
        cloudSolrServer.shutdown();
    }


    /**
     * 删除索引
     */
    @Test
    public void  deleteIndex() throws IOException, SolrServerException {
        //1.2

        //3.删除:  根据id删除   根据查询删除,   删除所有
//        cloudSolrServer.deleteById("1146941842215010306");

        //查询语法
        cloudSolrServer.deleteByQuery("*:*");


        //4. 提交
        cloudSolrServer.commit();


    }



}
