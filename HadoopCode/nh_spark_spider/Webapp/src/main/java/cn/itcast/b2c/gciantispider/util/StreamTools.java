package cn.itcast.b2c.gciantispider.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
/**
 * 流的工具类。在调用http restful接口时用到
 * 
 * Title: cas-client-core <br>
 * Description: <br>
 * Copyright: Kingpintcn Information Technology Co.,Ltd
 * 
 * @version 1.0 <br>
 * @creatdate 2017年1月3日 下午1:55:45 <br>
 *
 */
public class StreamTools {
    /**
     * 把输入流的数据转化为字符串
     * 
     * @param is
     *         输入流
     * @return 字符串
     */
    public static String readInputStream(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
            byte[] result = baos.toByteArray();
            
            return new String(result, "UTF-8");

        } catch (Exception e) {
           
            e.printStackTrace();
            return "将输入流转化为字符串失败";
        }
    }
}
