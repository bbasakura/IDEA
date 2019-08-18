package com.itheima.spring_02;

public class Bean2Factory {
    //静态方法，用来返回对象的实例
    public static Bean2 getBean2(){
        //在做实例化的时候，可以做其他的事情，即可以在这里写初始化其他对象的代码
        //Connection conn....
        return new Bean2();
    }

}
