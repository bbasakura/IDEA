package com.itheima.Jdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JdkGet {


    public static void main(String[] args) throws IOException {


//確定要爬去的url


        String indexurl = "http://www.itcast.cn";

//   創建url對象
        URL url = new URL(indexurl);

//    向url发送请求

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

//        设置请求方法

        urlConnection.setRequestMethod("GET");


//    输入流形式获取数据
        InputStream inputStream = urlConnection.getInputStream();



//打印流内容


        byte[] bytes = new byte[1024];

        int len =0;

        while ((len=inputStream.read(bytes))!=-1){
            String s = new String(bytes, 0, len);

            System.out.println("s = " + s);


        }

//    释放资源



        inputStream.close();

    }
}