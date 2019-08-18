package com.itheima.HDFSURI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.HDFSURI
 * @ClassName: HDFSStanderAPI
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/30 0030 22:01
 * @Version: 1.0
 */
public class HDFSStanderAPI {



    @Test
    public void getHDFS() throws Exception {
        //所有的Hadoop的程序中都需要一个Configuration对象,用于管理当前程序的配置
        Configuration conf = new Configuration();
        //指定hdfs的地址
        conf.set("fs.defaultFS","hdfs://hadoop01:8020");
        FileSystem fs1 = FileSystem.get(conf);
        FileSystem fs2 = FileSystem.get(new URI("hdfs://hadoop01:8020"),conf);
        FileSystem fs3 = FileSystem.newInstance(conf);

        System.out.println(fs1);
    }

    @Test
    public void getPathFile () throws IOException {
        //列举某个目录下的文件
        Configuration conf = new Configuration();
        //指定hdfs的地址
        conf.set("fs.defaultFS","hdfs://hadoop01:8020");
        FileSystem fs = FileSystem.get(conf);
/*        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatuse : fileStatuses) {
            if(fileStatuse.isDirectory()){
                System.out.println("d--- "+fileStatuse.getPath());
            }else
                System.out.println("f--- "+fileStatuse.getPath());
        }*/
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fs.listFiles(new Path("/"), true);
        while(locatedFileStatusRemoteIterator.hasNext()){
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            if(next.isDirectory()){
                System.out.println("d--- "+next.getPath());
            }else
                System.out.println("f--- "+next.getPath());
        }
        fs.close();
    }

    @Test
    public void getFiletoLocal() throws IOException {
        //下载一个文件到本地
        Configuration conf = new Configuration();
        //指定hdfs的地址
        conf.set("fs.defaultFS","hdfs://hadoop01:8020");
        FileSystem fs = FileSystem.get(conf);
        //创建文件夹
        fs.mkdirs(new Path("/frank02"));
        //删除
        if(fs.exists(new Path("/frank"))){      //匹配？？？
            fs.delete(new Path("/frank"),true);
        }
        //测试：
//        fs.copyFromLocalFile();
        fs.close();
    }
}
