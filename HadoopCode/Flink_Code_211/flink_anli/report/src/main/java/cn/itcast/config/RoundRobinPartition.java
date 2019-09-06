package cn.itcast.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  2019/9/4
 */
public class RoundRobinPartition implements Partitioner {

    //自增长int类型对象
    AtomicInteger atomicInteger = new AtomicInteger(0);
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer partitionCount = cluster.partitionCountForTopic(topic);
        //每次调用，会自增1
        int i = atomicInteger.incrementAndGet() % partitionCount;
        //当自增到1000的时候，重置为0
        if(atomicInteger.get() > 1000){
            atomicInteger.set(0);
        }
        return i;
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
