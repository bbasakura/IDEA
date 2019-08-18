package com.itheima.news.version2;

import com.google.gson.Gson;
import com.itheima.news.pojo.SpiderConstant;
import com.itheima.news.utils.HttpClientUtils;
import com.itheima.news.utils.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author itheima
 * @Title: News163Master
 * @ProjectName goosip_spider_parent
 * @Description: 获取163新闻的url列表,  判断url是否已经爬取过,没有爬取过,
 *                将url存放到redis的list队列中(bigData:spider:urlList),   还要处理分页爬取
 * @date 2019/7/110:25
 */
public class News163Master {

    /**
     * json转换的对象
     */
    public static  final Gson gson = new Gson();



    public static void main(String[] args) throws IOException {

        //1. 确定爬取的url地址
        String url = "https://ent.163.com/special/000380VU/newsdata_index.js";

        page163(url);

    }

    /**
     * 分页爬取的方法
     * @param url
     */
    private static void page163(String  url) throws IOException {

        int i = 2;

        while(true){
            //2.使用httpClientUtils发送请求,获取响应数据(url列表)
            String jsonString = HttpClientUtils.doGet(url);


            //跳出循环的逻辑
            if(StringUtils.isEmpty(jsonString)){
                System.out.println("爬完了......");
                break;
            }


            //3. 解析json数据
            parseNewsJson(jsonString);


            //4. 构造下一页的url地址,赋值给url
            String pageString = "";
            if(i < 10){
                pageString = "0" + i;
            }else{
                pageString = i +"";
            }

            i++;
            url  = "https://ent.163.com/special/000380VU/newsdata_index_" + pageString + ".js";

        }
    }





    /**
     * 解析json数据(新闻的url列表)
     * @param json
     */
    private static void  parseNewsJson(String json) throws IOException {

        //1. 处理json数据,处理成格式良好的json数据
        json = formatJson(json);


        //2. 将json字符串转换成   List<Map<String,Object>>
        List<Map<String,Object>> list = gson.fromJson(json, List.class);

        //3. 遍历list(新闻的url列表)
        for (Map<String, Object> map : list) {
            String docurl = (String) map.get("docurl");

            //图集的url,需要过滤掉
            if(docurl.contains("photoview")){
                continue;
            }

            //不符合这个规则:ent.163.com
            if(!docurl.contains("ent.163.com")){
                continue;
            }

            //判断当前url是否已经爬取过
            if(hasParsed(docurl)){
                continue;
            }



            String title = (String) map.get("title");
            System.out.println(docurl);




            //(添加的代码)没有爬取过,将url存放到redis的list队列中(bigData:spider:urlList)
            Jedis jedis = JedisUtils.getJedis();
            jedis.lpush(SpiderConstant.SPIDER_NEWS_URLLIST,docurl);

            //使用完成,一定要归还给连接池
            jedis.close();


        }


    }


    /**
     * 判断给的url是否已经爬取过
     * @param docurl
     * @return
     */
    private static boolean hasParsed(String docurl) {

        Jedis jedis = JedisUtils.getJedis();

        Boolean sismember = jedis.sismember(SpiderConstant.SPIDER_NEWS_URLSET, docurl);

        jedis.close();

        return sismember;
    }


    /**
     * 处理json数据成一个格式正确的json字符串
     * @param json  返回
     * @return
     */
    private static  String  formatJson(String json){
        int beginIndex = json.indexOf("[");
        int endIndex = json.lastIndexOf(")");

        String substring = json.substring(beginIndex, endIndex);

        return substring;
    }


}
