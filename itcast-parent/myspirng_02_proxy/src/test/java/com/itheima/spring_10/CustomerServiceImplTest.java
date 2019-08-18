package com.itheima.spring_10;

import org.junit.Test;


public class CustomerServiceImplTest {

    @Test
    public void testproxy() {

        //目标对象
        ICustomerService target = new CustomerServiceImpl();


        //实例化注入目标对象

        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(target);

        //获取ProxyObject代理对象


        ICustomerService proxyObject = (ICustomerService) jdkProxyFactory.getProxyObject();

        proxyObject.save();

        proxyObject.find();



    }


}