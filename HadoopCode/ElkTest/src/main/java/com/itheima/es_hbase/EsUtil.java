package com.itheima.es_hbase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import sun.security.krb5.internal.tools.Klist;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ElkTest
 * @Package: com.itheima.es_hbase
 * @ClassName: EsUtil
 * @Description:
 *  /**
 * 开发步骤:
 * 1.获取客户端
 * 2.插入数据
 * 3.查询数据(词条查询)
 * @Author: sakura
 * @CreateDate: 2019/8/18 0018 19:30
 * @Version: 1.0
 */
public class EsUtil {

    //1、获取客户端
    public static TransportClient getClient(){

        //设置集群的名字
        Settings myes= Settings.builder().put("cluster.name", "myes").build();

        //获取客户端
        TransportClient client =null;

        try {
            client = new PreBuiltTransportClient(myes)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop01"),9300))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop02"),9300))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop03"),9300));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return client;
    }

    public static void bulkPutData(List<Article> list){
        /**
         * @method: bulkPutData
         * @return: void
         * @description:  2.插入数据
         * @Author: sakura
         * @Date: 2019/8/18 0018 19:38
         */

        //获取客户端duix
        TransportClient client = getClient();

        //获取批量插入的duix
        BulkRequestBuilder bulk = client.prepareBulk();

        for (Article article : list) {
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("articles", "article", article.getId())
                    .setSource(JSON.toJSONString(article), XContentType.JSON);

            bulk.add(indexRequestBuilder);
        }

        //执行插入
        bulk.get();

        //关流
        client.close();
    }

    public static List<Article> termQuery(String titleTerm){

        List<Article> articles = new ArrayList<>();

        //获取客户端

        TransportClient client = getClient();

        //词条查询-----得到的是一个返回值的结果Searchresponse
        SearchResponse searchResponse = client.prepareSearch("articles").setTypes("article")
                .setQuery(QueryBuilders.termQuery("title", titleTerm))
                .get();


        //获取查询结果
        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit : hits) {
            String str = hit.getSourceAsString();

            //这一步字符串要转化为bean的对象
            Article article = JSONObject.parseObject(str, Article.class);

            //添加到集合中区
            articles.add(article);

        }

        return articles;
    }





}
