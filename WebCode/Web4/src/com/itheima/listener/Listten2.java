package com.itheima.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class Listten2 implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("一个域对象被添加了名字"+event.getName()+"值："+event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("一个域对象被删除了，名字"+event.getName()+"值："+event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        ServletContext servletContext = event.getServletContext();
        Object newvalue = servletContext.getAttribute(event.getName());
        System.out.println("一个域对象被替换了：名字"+event.getName()+"旧的值："+event.getValue()+"新值："+newvalue);
    }
}
