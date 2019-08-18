package com.itheima.utils;

import java.util.UUID;

/**
 * @author itheima
 * @Title: UUidTest
 * @ProjectName goosip_spider_parent
 * @Description: jdk自带的id生成器
 * @date 2019/6/2915:00
 */
public class UUidTest {


    public static void main(String[] args) throws InterruptedException {

        while(true){
            String uuid = UUID.randomUUID().toString();

            System.out.println(uuid);


            Thread.sleep(1000);
        }

    }
}
