package com.itheima.lianjia;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.lianjia
 * @ClassName: HouseBean
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 15:02
 * @Version: 1.0
 */
public class HouseBean implements Writable {

    //	小区名称
    //	户型
    //	面积
    //	地区
    //	楼层
    //	朝向
    //	总价
    //	单价
    //	建造年份
    private String name;
    private String hx;
    private Double mj;
    private String dq;
    private String lc;
    private String cx;
    private Double zj;
    private Double dj;


    public HouseBean(String name, String hx, Double mj, String dq, String lc, String cx, Double zj, Double dj, String jznf) {
        this.name = name;
        this.hx = hx;
        this.mj = mj;
        this.dq = dq;
        this.lc = lc;
        this.cx = cx;
        this.zj = zj;
        this.dj = dj;

    }

    public HouseBean() {
    }

    public void setAll(String name, String hx, Double mj, String dq, String lc, String cx, Double zj, Double dj) {
        this.name = name;
        this.hx = hx;
        this.mj = mj;
        this.dq = dq;
        this.lc = lc;
        this.cx = cx;
        this.zj = zj;
        this.dj = dj;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHx() {
        return hx;
    }

    public void setHx(String hx) {
        this.hx = hx;
    }

    public Double getMj() {
        return mj;
    }

    public void setMj(Double mj) {
        this.mj = mj;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public Double getZj() {
        return zj;
    }

    public void setZj(Double zj) {
        this.zj = zj;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(name);
        out.writeUTF(hx);
        out.writeDouble(mj);
        out.writeUTF(dq);
        out.writeUTF(lc);
        out.writeUTF(cx);
        out.writeDouble(zj);
        out.writeDouble(dj);
    }

    @Override
    public void readFields(DataInput in) throws IOException {

        this.name = in.readUTF();
        this.hx = in.readUTF();
        this.mj = in.readDouble();
        this.dq = in.readUTF();
        this.lc = in.readUTF();
        this.cx = in.readUTF();
        this.zj = in.readDouble();
        this.dj = in.readDouble();

    }

    @Override
    public String toString() {
        return name + "\t" + hx + "\t" + mj + "\t" + dq + "\t" + lc + "\t" + cx + "\t" + zj + "\t" + dj;


    }
}
