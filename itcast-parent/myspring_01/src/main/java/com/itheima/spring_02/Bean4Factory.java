package com.itheima.spring_02;

import org.springframework.beans.factory.FactoryBean;


public class Bean4Factory implements FactoryBean<Bean4> {
//继承了 FactoryBean

    //产生对象
    public Bean4 getObject() throws Exception {
        return new Bean4();
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }

}


