package com.itheima.HDFSURI;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @ProjectName: HDFSTest
 * @Package: com.itheima.HDFSURI
 * @ClassName: demo1
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/30 0030 21:30
 * @Version: 1.0
 */
public class demo1 {


    @Test
    public  void test() throws Exception {

        //注册HDFS的uri
//        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());


        //定义输入的io流

        InputStream inputStream = new URL("hdfs://hadoop01:8020/hadoop-env.sh").openStream();

        //定义输出的IO流

        FileOutputStream fileOutputStream =new FileOutputStream(new File("D:\\11111-env.sh"));


        IOUtils.copyBytes(inputStream,fileOutputStream,200);

        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(fileOutputStream);




         //使用工具类实现流的复制








    }


}
