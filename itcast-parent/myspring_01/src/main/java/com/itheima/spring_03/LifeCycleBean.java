package com.itheima.spring_03;

public class LifeCycleBean {
    public LifeCycleBean() {
        System.out.println("生命周期构造器被使用~~");
    }

    //初始化后自动调用方法：方法名随意，但也不能太随便，一会要配置
    public void init(){
        System.out.println("LifeCycleBean-init初始化时调用");
    }

    //bean销毁时调用的方法
    public void destroy(){
        System.out.println("LifeCycleBean-destroy销毁时调用");
    }

}
