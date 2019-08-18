package com.itheima.storm.redis;

import org.apache.storm.redis.common.mapper.RedisDataTypeDescription;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.tuple.ITuple;

import java.util.Map;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.storm.mysql
 * @ClassName: NameValueMapper
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/19 0019 19:41
 * @Version: 1.0
 */
public class NameValueMapper implements RedisStoreMapper {


    //    Map结构中的大K，person里面是小k和小v（name,age）

    private static final String hashKey = "person";

    @Override
    public RedisDataTypeDescription getDataTypeDescription() {


        //映射到redis中的数据类型-------hashkry=person
        RedisDataTypeDescription description = new RedisDataTypeDescription(RedisDataTypeDescription.RedisDataType.HASH, hashKey);

        return description;

    }


    //确定小key
    @Override
    public String getKeyFromTuple(ITuple iTuple) {



        return iTuple.getStringByField("name");
    }



    //确定小value
    @Override
    public String getValueFromTuple(ITuple iTuple) {

        return  iTuple.getIntegerByField("age")+"";
    }
}
