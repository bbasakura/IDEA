package com.itheima;

import com.alibaba.fastjson.JSONArray;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.*;

/**
 * @author itheima
 * @Title: WordCountBolt
 * @ProjectName gossip-parent
 * @Description: 接收LogSplitBolt发送过来的keywords关键词, 然后进行关键词的统计, 统计出来最热的5条数据
 * 将数据转换成json, 发送给kafkaBolt
 * @date 2019/7/1612:12
 */
public class WordCountBolt extends BaseBasicBolt {

    //热搜关键词的单词统计hashMap
    /**
     * hashMap   必须对hashMap进行排序
     */
//    private TreeMap<String,Integer> wordCounter  = null;

    //wordCounter是一个空的map
    private Map<String, Integer> wordCounter = null;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {

        //初始化Map---->hashMap
        this.wordCounter = new HashMap<>();
    }

    /**
     * 上游发送的数据,没发一条,就会调用这个方法进行处理(被动一方)
     *
     * @param input     : 上游发送的数据
     * @param collector : 发射器
     */
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

        try {
            //1. 接收上游发送的热搜关键词
            String keywords = input.getStringByField("keywords");

            //2. 进行热词的统计
            //2.1 判断keywords是否在hashMap中存在
            if (wordCounter.containsKey(keywords)) {
                //已经存在---wordCounter.get(keywords)得到的是keywoeds的次数
                wordCounter.put(keywords, wordCounter.get(keywords) + 1);
            } else {
                //不存在
                wordCounter.put(keywords, 1);
            }
            //3. 对hashMap进行排序: 按照单词出现的次数进行降序排列
            List<Map.Entry<String, Integer>> listMap = new ArrayList<Map.Entry<String, Integer>>(wordCounter.entrySet());

            // 对HashMap中的 value 进行排序:  需要给一个比较器
            Collections.sort(listMap, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return -(o1.getValue()).toString().compareTo(o2.getValue().toString());
                }
            });

            // 对HashMap中的 value 进行排序后  显示排序结果
            for (int i = 0; i < listMap.size(); i++) {
                String id = listMap.get(i).toString();
                System.out.println(id + "  ");
            }

            //4. 将排序结果的前5条发送到下游kafkaBolt中(别人开发的，传输的数据是json类型的字符串)

            List<Map> keywordsMap = new ArrayList<>();

            for (String word : wordCounter.keySet()) {

//                System.out.println("key:"+word+"  "+"count"+ wordCounter.get(word));

                Map<String, String> hashMap = new HashMap<>();

                hashMap.put("topKeywords",word);
                hashMap.put("score",wordCounter.get(word)+"");
                keywordsMap.add(hashMap);
            }
            // 获取前5条
            int size = keywordsMap.size();
            if (size > 5) {
                keywordsMap = keywordsMap.subList(0, 5);
            }

            //5.转换成json数据

            String jsonString = JSONArray.toJSONString(keywordsMap);
            System.out.println(jsonString);

            //发送jsonString到下游

            collector.emit(new Values(jsonString));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //声明发送到kafkaBolt中的字段名称
        //向kafkaBolt发送数据的字段名称:必须使用message(???别人的代码？？？)
        declarer.declare(new Fields("message"));

    }
}
