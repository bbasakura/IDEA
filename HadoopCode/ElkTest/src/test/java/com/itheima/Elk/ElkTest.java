package com.itheima.Elk;

import com.itheima.elk.Person;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import java.net.InetAddress;
/**
 * @ProjectName: ElkTest
 * @Package: com.itheima.Elk
 * @ClassName: ElkTest
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/17 0017 21:48
 * @Version: 1.0
 */
public class ElkTest {

    /**
     *
     * @ProjectName:
     * @Package: com.itheima.Elk
     * @ClassName: ElkTest
     * @Description:
     * @Author: sakura
     * @CreateDate: 2019/8/17 001721:48
     * @Version: 1.0
     */

    //创建客户端
    private TransportClient client;

    @Before
    public void test ()throws  Exception{


        Settings settings = Settings.builder().put("cluster.name", "myes").build();


        //设置集群名称
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop01"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop02"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop03"), 9300));

    }

    //关流程序
    @After
    public void close(){
        client.close();
    }



    /**
     * 发送json数据
     */
    @Test
    public void putJsonData(){

        //构建json数据----阿里的fastjson
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        jsonObject.put("age",20);
        jsonObject.put("address","北京");

        //json转字符串
        String str = JSON.toJSONString(jsonObject);

        //发送数据到es----顺便创建这个数据库？？？？
        client.prepareIndex("test","demo","2")
                .setSource(str,XContentType.JSON)
                .get();
    }

    /**
     * 发送XContentBuilder数据
     */

    @Test
    public  void xContentBuilderData () throws IOException{
        client.prepareIndex("test","demo","3")
                .setSource(XContentFactory.jsonBuilder()
                .startObject()
                .field("name","小舞")
                .field("age",20)
                .field("address","星斗大森林")
                .endObject())
                .get();
    }


    /**
     * bean转json发送到es----☆☆☆☆☆
     */

    @Test
    public  void beanToJson(){

        Person person = new Person();

        person.setName("哈哈哈");
        person.setAge(15);
        person.setAddress("火星");

        client.prepareIndex("test","demo","4")
                .setSource(JSON.toJSONString(person),XContentType.JSON)
                .get();
    }


    /**
     * 批量插入数据
     */


    @Test
    public void  bulkInsertData(){


        //bena对象
        Person person = new Person();
        person.setName("葫芦娃2");
        person.setAge(22);
        person.setAddress("胡芦村2");
        String personStr = JSON.toJSONString(person);

        //map封装的
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","xiaoli");
        map.put("age",20);
        map.put("address","上海");

        //批量发丝能对像
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        //构建IndexRequestBuilder对象
        IndexRequestBuilder indexResponse = client.prepareIndex("test", "demo", "5")
                .setSource(personStr, XContentType.JSON);

        IndexRequestBuilder mapRequestBuilder = client.prepareIndex("test", "demo", "6")
                .setSource(map);

        //封装数据，批量发送

        bulkRequestBuilder.add(indexResponse);
        bulkRequestBuilder.add(mapRequestBuilder);
        bulkRequestBuilder.get();
    }


    /**
     * 初始化一批数据到索引库当中去准备做查询使用
     * 注意这里初始化的时候，需要给我们的数据设置分词属性
     * 而且，这里定义了setting和mapping。~~~~~~~~~~~~
     *
     * @throws Exception
     */
    @Test
    public void createIndexBatch() throws Exception {
        Settings settings = Settings
                .builder()
                .put("cluster.name", "myes") //节点名称， 在es配置的时候设置
                //自动发现我们其他的es的服务器
                .put("client.transport.sniff", "true")
                .build();
        //创建客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop01"), 9300));//以本机作为节点
        //创建映射
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                //      .startObject("m_id").field("type","keyword").endObject()
                .startObject("id").field("type", "integer").endObject()
                .startObject("name").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .startObject("age").field("type", "integer").endObject()
                .startObject("sex").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .startObject("address").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .startObject("phone").field("type", "text").endObject()
                .startObject("email").field("type", "text").endObject()
                .startObject("say").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .endObject()
                .endObject();
        //indexsearch：索引名   mysearch：类型名（可以自己定义）
        PutMappingRequest putmap = Requests.putMappingRequest("indexsearch").type("mysearch").source(mapping);
        //创建索引
        client.admin().indices().prepareCreate("indexsearch").execute().actionGet();
        //为索引添加映射
        client.admin().indices().putMapping(putmap).actionGet();


        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        Person lujunyi = new Person(2, "玉麒麟卢俊义", 28, 1, "水泊梁山", "17666666666", "lujunyi@itcast.com","hello world今天天气还不错");
        Person wuyong = new Person(3, "智多星吴用", 45, 1, "水泊梁山", "17666666666", "wuyong@itcast.com","行走四方，抱打不平");
        Person gongsunsheng = new Person(4, "入云龙公孙胜", 30, 1, "水泊梁山", "17666666666", "gongsunsheng@itcast.com","走一个");
        Person guansheng = new Person(5, "大刀关胜", 42, 1, "水泊梁山", "17666666666", "wusong@itcast.com","我的大刀已经饥渴难耐");
        Person linchong = new Person(6, "豹子头林冲", 18, 1, "水泊梁山", "17666666666", "linchong@itcast.com","梁山好汉");
        Person qinming = new Person(7, "霹雳火秦明", 28, 1, "水泊梁山", "17666666666", "qinming@itcast.com","不太了解");
        Person huyanzhuo = new Person(8, "双鞭呼延灼", 25, 1, "水泊梁山", "17666666666", "huyanzhuo@itcast.com","不是很熟悉");
        Person huarong = new Person(9, "小李广花荣", 50, 1, "水泊梁山", "17666666666", "huarong@itcast.com","打酱油的");
        Person chaijin = new Person(10, "小旋风柴进", 32, 1, "水泊梁山", "17666666666", "chaijin@itcast.com","吓唬人的");
        Person zhisheng = new Person(13, "花和尚鲁智深", 15, 1, "水泊梁山", "17666666666", "luzhisheng@itcast.com","倒拔杨垂柳");
        Person wusong = new Person(14, "行者武松", 28, 1, "水泊梁山", "17666666666", "wusong@itcast.com","二营长。。。。。。");

        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "1")
                .setSource(JSONObject.toJSONString(lujunyi), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "2")
                .setSource(JSONObject.toJSONString(wuyong), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "3")
                .setSource(JSONObject.toJSONString(gongsunsheng), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "4")
                .setSource(JSONObject.toJSONString(guansheng), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "5")
                .setSource(JSONObject.toJSONString(linchong), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "6")
                .setSource(JSONObject.toJSONString(qinming), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "7")
                .setSource(JSONObject.toJSONString(huyanzhuo), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "8")
                .setSource(JSONObject.toJSONString(huarong), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "9")
                .setSource(JSONObject.toJSONString(chaijin), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "10")
                .setSource(JSONObject.toJSONString(zhisheng), XContentType.JSON)
        );
        bulkRequestBuilder.add(client.prepareIndex("indexsearch", "mysearch", "11")
                .setSource(JSONObject.toJSONString(wusong), XContentType.JSON)
        );

        bulkRequestBuilder.get();
        client.close();

    }


    /**
     * 根据id查询数据
     */

    @Test
    public void queryById(){


        //一行的数据是一个document
        GetResponse documentFields = client.prepareGet("indexsearch", "mysearch", "1").get();

        System.out.println(documentFields.getSourceAsString());


    }

    /**
     * 查询所有数据
     */
    @Test
    public void  queryAll(){

        //查询所有数据
        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                .setQuery(QueryBuilders.matchAllQuery())
                .get();

        //遍历获取所有数据----结果集
        //内层hits是查询结果集

        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit : hits) {

            //返回json数据
            System.out.println(hit.getSourceAsString());
            //返回map数据
//            System.out.println(hit.getSourceAsMap());
        }
    }


    /**
     * rage范围查询
     */
    @Test
    public void rangeQuery(){
        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                //年龄的区间查询
                .setQuery(QueryBuilders.rangeQuery("age").lte(30).gte(18))
                .get();

        //获取内层hits，遍历结果集
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }



    /**
     * term词条查询----常用的查询
     */
    @Test
    public void termQuery(){

        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                //词条查询
                .setQuery(QueryBuilders.termQuery("phone", "17666666666"))
                .get();

        //遍历结果集
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * fuzz模糊查询
     * 最大匹配两次
     */
    @Test
    public void fuzzQuery(){

        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                //模糊查询,默认纠错一次，最大可纠错两次
                .setQuery(QueryBuilders.fuzzyQuery("say", "henNo").fuzziness(Fuzziness.TWO))
                .get();

        //遍历结果集
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }


    /**
     * 通配符查询
     * ？：匹配一个字符
     * *：匹配任意多个字符
     */

    @Test
    public void wildcardQuery(){

        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                //通配符查询
                .setQuery(QueryBuilders.wildcardQuery("say", "hel*"))
                .get();

        //遍历查询结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * bool 复合查询
     * 需求：查询年龄是18到28范围内且性别是男性的，或者id范围在10到13范围内的
     * 查询年龄是18到28范围内且性别是男性的 （并） must
     * id范围查询
     * should
     * must
     */


    @Test

    public void boolQuery(){


        //查询年龄是18到28
        RangeQueryBuilder age = QueryBuilders.rangeQuery("age").gt(18).lt(28);

        //男性
        TermQueryBuilder sex = QueryBuilders.termQuery("sex", 1);

        //id范围在10到13范围内的
        RangeQueryBuilder id = QueryBuilders.rangeQuery("id").gte(10).lte(13);


        //构建复合查询
        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                .setQuery(QueryBuilders.boolQuery().should(id).should(QueryBuilders.boolQuery().must(age).must(sex)))
                .get();

        //遍历查询结果集
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

    }

    /**
     * 分页查询
     * size : 条数
     * from :起始行
     */

    @Test
    public void pageQuery(){

        SearchResponse searchResponse = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                //查询全部数据
                .setQuery(QueryBuilders.matchAllQuery())
                //排序
                .addSort("age", SortOrder.DESC)
                //分页查询
                .setSize(5)
                .setFrom(0)
                .get();
        //遍历结果集
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }


    /**
     * 高亮查询----???????
     */

    @Test
    public void highlightQuer(){

        //词条查询对象
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("indexsearch")
                .setTypes("mysearch")
                .setQuery(QueryBuilders.termQuery("say", "大刀"));

        //高亮显示对象
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>")
                .field("say")
                .postTags("<font />");

        //封装高亮显示对象
        SearchResponse searchResponse = searchRequestBuilder.highlighter(highlightBuilder).get();

        //获取内层hits
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            //获取整条文档
            System.out.println(hit.getSourceAsString());
            //获取高亮数据
            Text[] says = hit.getHighlightFields().get("say").getFragments();
            for (Text say : says) {
                System.out.println("高亮数据："+say);
            }
        }
    }

    /**
     * 根据id数据更新---setDoc
     */

    @Test
    public void updateByID(){

        Person person= new Person(5, "宋江", 88, 0, "水泊梁山", "17666666666", "wusong@itcast.com","及时雨宋江");
        client.prepareUpdate("indexsearch","mysearch","1")
                .setDoc(JSON.toJSONString(person),XContentType.JSON).execute().actionGet();
    }



    /**
     * 根据id删除数据 ...client.prepareDelete(库，表).get() ======get(相当于执行！！！！！！！！！！！)
     */
    @Test
    public void delByID(){

        client.prepareDelete("indexsearch","mysearch","1").get();
    }




    /**
     * 根据查询条件删除数据-----特殊的一个DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
     */

    @Test

    public void delByRangeAge(){

        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .source("indexsearch")
                .filter(QueryBuilders.rangeQuery("age").gte(18).lte(28))
                .get();
    }




    /**
     * 删除索引-=------特殊的一个  client.admin().indices().prepareDelete("articles2").get();
     */


    @Test

    public void delindex(){

        client.admin().indices().prepareDelete("articles2").get();





    }
}
