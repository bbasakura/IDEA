package cn.itcast.b2c.gciantispider.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

/**
 * 获取服务器信息的工具类
 * 
 */
public class ElasticUtil {
    
    
    public static String getServerStatusJson() {
        
        String host = ConfigUtil.get("elasticsearch.ADRESS");
        int port = Integer.valueOf(ConfigUtil.get("elasticsearch.PORT"));
        // 对应topbeat配置的数据采集间隔，秒
        int interval = 10;
        /*
         * 查询存在延迟，如查询小于2017-08-15 13:00:10的数据，比这个小几秒的数据，如08秒、05秒这些记录不一定能查到。
         * 一是记录传给es需要时间，二是es处理这些记录也要时间。
         * 这个值需要根据实际做调整，小了会导致数据不完整，可以调大点，虽然会增加返回的数据量。
         */
        int latency = 10;

        // @timestamp >= now - 偏移时间
        String gte = "now-" + (interval + latency) + "s";

        int size = 10000;

        //定时删掉过期索引
        String index = "topbeat-*";
        String[] fields = { "@timestamp", "beat.hostname", "load.load1", "cpu.system_p", "cpu.user_p", "mem.total", "mem.actual_used", "mem.actual_used_p", "fs.device_name", "fs.mount_point",
                "fs.total", "fs.used" };
        TransportClient client = null;
        try {
            client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
            SearchResponse resp = client.prepareSearch(index).addFields(fields).setPostFilter(QueryBuilders.rangeQuery("@timestamp").gte(gte)).addSort("@timestamp", SortOrder.DESC).setSize(size)
                    .execute().get();
            if (resp.status().equals(RestStatus.OK)) {
                SearchHits hits = resp.getHits();

                // 查看返回结果
                List<String> distinct = new ArrayList<String>();
                Map<String, SearchHit> sys = new HashMap<String, SearchHit>();
                Map<String, Map<String, Long>> fs = new HashMap<String, Map<String, Long>>();
                for (SearchHit hit : hits.hits()) {
                    String hostname = hit.field("beat.hostname").value().toString();
                    // key出现第二次表示已遍历完一个周期，应停止
                    String key = hostname + '\1';
                    if ("system".equals(hit.type())) {
                        key += "sys";
                        if (distinct.contains(key)) {
                            break;
                        }
                        distinct.add(key);
                        sys.put(hostname, hit);
                    } else if ("filesystem".equals(hit.type())) {
                        key += hit.field("fs.device_name").value().toString() + '\1' + hit.field("fs.mount_point").value().toString();
                        if (distinct.contains(key)) {
                            break;
                        }
                        distinct.add(key);
                        Map<String, Long> f = fs.get(hostname);
                        if (f == null) {
                            f = new HashMap<String, Long>();
                            fs.put(hostname, f);
                        }
                        Long total = f.get("fs.total");
                        Long used = f.get("fs.used");
                        if (total == null) {
                            total = 0L;
                            used = 0L;
                        }
                        // 将所有挂载点容量加起来作为磁盘总容量
                        total += Long.parseLong(hit.field("fs.total").value().toString());
                        used += Long.parseLong(hit.field("fs.used").value().toString());
                        f.put("fs.total", total);
                        f.put("fs.used", used);
                    } else {
                        continue;
                    }
                }
                // 构造json
                XContentBuilder builder = XContentFactory.jsonBuilder().prettyPrint();
                builder.startArray();
                for (String hostname : sys.keySet()) {
                    SearchHit h = sys.get(hostname);
                    builder.startObject();
                    for (String k : h.fields().keySet()) {
                        builder.field(h.field(k).name(), h.field(k).value());
                    }
                    Map<String, Long> f = fs.get(hostname);
                    if (f != null) {
                        for (String k : f.keySet()) {
                            builder.field(k, f.get(k));
                        }
                    }
                    builder.endObject();
                }
                builder.endArray();
                return builder.string();
            } else {
                return resp.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{ \"error\" : \"" + e.getMessage() + "\"}";
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
    
    
    public static void main(String[] args) {
        String serverStatusJson = getServerStatusJson();
        System.out.println(serverStatusJson);
    }
    
}
