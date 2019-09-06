package com.itheima.Writable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Writable
 * @ClassName: PairWritable
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 11:05
 * @Version: 1.0
 */
public class PairWritable implements WritableComparable<PairWritable> {
    /**
     * @ProjectName: a
     * @Package: com.itheima.Writable
     * @ClassName: PairWritable
     * @Description: 自定义的数据类型和比较器
     * @Author: sakura
     * @CreateDate: 2019/8/3 000311:07
     * @Version: 1.0
     */
    //组合key，第一列是原来的key,第二列是原来的value
    private String first;
    private int second;

    //带参构造
    public PairWritable(String first, int second) {
        this.first = first;
        this.second = second;
    }

    //无参构造
    public PairWritable() {
    }

    //方便shez设置字段
    public void setAll(String first, int second) {
        this.first = first;
        this.second = second;
    }


    //重写比较器
    //每次比较都是调用该方法的对象与传递的参数进行比较，
    //说白了就是第一行与第二行比较完了之后的结果与第三行比较，
    //得出来的结果再去与第四行比较，依次类推
    @Override
    public int compareTo(PairWritable o) {
        System.out.println(o.toString());
        System.out.println(this.toString());

        int comp = this.first.compareTo(o.first);

        if (comp != 0) {
            return comp;
        } else { //如果多一个字段相等，则比较第二个字段
            return Integer.valueOf(this.second).compareTo(Integer.valueOf(o.getSecond()));
        }
    }

    //序列化
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(first);
        out.writeInt(second);
    }

    //反序列化
    @Override
    public void readFields(DataInput in) throws IOException {

        this.first = in.readUTF();
        this.second = in.readInt();

    }

    //getter and setter
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return first + "\t" + second;
    }
}
