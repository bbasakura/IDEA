package com.itheima.PropertiesDemo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
       Properties pp1 = new Properties();
       /*
        pp1.put("001","张一");
        pp1.put("002","张二");
        pp1.put("003","张三");
        System.out.println(pp1.getProperty("001"));
        System.out.println(pp1.getProperty("002"));
        System.out.println(pp1.getProperty("003"));
        Set<String> key = pp1.stringPropertyNames();
        for (String s : key) {
            System.out.println(s+"----"+pp1.getProperty(s));
        }*/
        pp1.load(new FileReader("a.txt"));
        //注意文件一定要在项目的根目录下，一般的找不到文件的异常抛出后，还是找不送到就是文件放错位置了
        Set<String> key1 = pp1.stringPropertyNames();
        for (String s1 : key1) {
            System.out.println(s1+"----"+pp1.getProperty(s1));
        }

    }
}
