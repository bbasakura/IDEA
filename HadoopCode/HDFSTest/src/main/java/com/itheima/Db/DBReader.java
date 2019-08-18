package com.itheima.Db;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.Db
 * @ClassName: DBReader
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/3 0003 19:11
 * @Version: 1.0
 */
public class DBReader implements Writable,DBWritable {

    private int id;
    private String name ;

    public DBReader(){ }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void write(PreparedStatement statement) throws SQLException {
        statement.setInt(1, id);
        statement.setString(2, name);
    }

    public void readFields(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt(1);
        this.name = resultSet.getString(2);
    }
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
    }
    public void readFields(DataInput in) throws IOException {
        this.id = in.readInt();
        this.name = in.readUTF();
    }
    @Override
    public String toString() {
        return  id + "\t" + name;
    }
}
