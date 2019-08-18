package com.itheima.cache;

import com.alibaba.fastjson.JSONArray;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.cache
 * @ClassName: KeywordsConsumer
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/13 0013 17:25
 * @Version: 1.0
 */
@Component
public class KeywordsConsumer implements MessageListener<Integer, String> {

    //1.
    @Autowired
    private NewsCache newsCache;

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data) {


        //获取搜索热词的列表

        String value = data.value();

        //json格式的数据格式化list<map>

        List<Map> keywordsList = JSONArray.parseArray(value, Map.class);

        for (Map map : keywordsList) {

            String topKeywords = (String) map.get("topKeywords");
            String score = (String) map.get("score");

            System.out.println("关键词："+ topKeywords+"点击量："+score);

            //建立缓存


            try {
                newsCache.cacheNews(topKeywords);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
