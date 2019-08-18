package com.itheima.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener("/1234")
public class HelloListener implements ServletContextListener{


    public HelloListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("上下文域被创建了"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("上下文域被销毁了"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }

}
