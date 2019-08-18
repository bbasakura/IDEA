package com.itheima.phone;

import javafx.scene.control.Skin;
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
public class FlowPartition extends Partitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String line = text.toString();
        if (line.startsWith("135")) {
            return 0;
        } else if (line.startsWith("136")) {
            return 1;
        } else if (line.startsWith("137")) {
            return 2;
        }else
            return 3;
    }

}

