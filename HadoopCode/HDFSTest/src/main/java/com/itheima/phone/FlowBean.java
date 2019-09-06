package com.itheima.phone;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.phone
 * @ClassName: FlowBean
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 13:14
 * @Version: 1.0
 */
public class FlowBean implements Writable{


    private Integer upFlow;
    private Integer  downFlow;
    private Integer upCountFlow;
    private Integer downCountFlow;
    @Override
    public void write(DataOutput out) throws IOException {

        out.writeInt(upFlow);
        out.writeInt(downFlow);
        out.writeInt(upCountFlow);
        out.writeInt(downCountFlow);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readInt();
        this.downFlow = in.readInt();
        this.upCountFlow = in.readInt();
        this.downCountFlow = in.readInt();
    }

    public FlowBean(Integer upFlow, Integer downFlow, Integer upCountFlow, Integer downCountFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.upCountFlow = upCountFlow;
        this.downCountFlow = downCountFlow;
    }
    public FlowBean(){}

    public void setAll(Integer upFlow, Integer downFlow, Integer upCountFlow, Integer downCountFlow){

        this.upFlow=upFlow;
        this.downFlow=downFlow;
        this.upCountFlow=upCountFlow;
        this.downCountFlow=downCountFlow;
    }

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getUpCountFlow() {
        return upCountFlow;
    }

    public void setUpCountFlow(Integer upCountFlow) {
        this.upCountFlow = upCountFlow;
    }

    public Integer getDownCountFlow() {
        return downCountFlow;
    }

    public void setDownCountFlow(Integer downCountFlow) {
        this.downCountFlow = downCountFlow;
    }

    @Override
    public String toString() {
        return  upFlow+"\t"+downFlow+"\t"+upCountFlow+"\t"+downCountFlow;
    }

/*
    @Override
    public int compareTo(FlowBean o) {

        return this.upCountFlow > o.upCountFlow ?-1:1;
    }*/
}
