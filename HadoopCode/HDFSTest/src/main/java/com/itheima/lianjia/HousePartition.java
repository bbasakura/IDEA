package com.itheima.lianjia;

import com.itheima.phone.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.phone
 * @ClassName: FlowPartition
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 14:34
 * @Version: 1.0
 */
public class HousePartition extends Partitioner<Text, HouseBean> {


    @Override
    public int getPartition(Text text, HouseBean houseBean, int numPartitions) {

        String line = text.toString();

        String[] strs = line.split(",");
        if (strs[0].equals("浦东")) {
            return 0;
        }else
            return 1;
    }
}

