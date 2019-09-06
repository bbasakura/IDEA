package cn.itcast.test;


import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Date 2019/9/4
 * 请求测试类
 */
public class HttpTest {

    public static void main(String[] args) throws Exception {

        String address= "http://localhost:8090/report/put";
        String testInfo="====test======";
        sendData(address,testInfo);
    }

    /**
     * 模拟url发送请求
     * @param address
     * @param testInfo
     */
    public static void sendData(String address, String testInfo) throws Exception {

        //新建url连接对象
        URL url = new URL(address);
        //获取连接
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //设置url属性
        urlConnection.setConnectTimeout(60000);//连接超时
        urlConnection.setUseCaches(false);
        urlConnection.setRequestMethod("POST");//post请求
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type","application/json");
        //用户代理
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36");

        //发送数据
        OutputStream os = urlConnection.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        bos.write(testInfo.getBytes());
        bos.flush();
        bos.close();

        //接收响应状态
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[1024];
        String str="";
        while (inputStream.read(bytes,0,1024) != -1){
            str = new String(bytes);
        }
        System.out.println("<<<<:"+str);
    }

}
