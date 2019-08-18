package com.itheima.spring_11;

import org.junit.Test;

public class CglibProxyFactoryTest {

    @Test
    public void testCglibProxy() {
        //target目标：
        ProductService target = new ProductService();
        //weave织入，生成proxy代理对象
        //代理工厂对象，注入目标
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(target);
        //获取proxy:思考：对象的类型
        //代理对象，其实是目标对象类型的子类型
        ProductService proxy = (ProductService) cglibProxyFactory.getProxyObject();
        //调用代理对象的方法
        proxy.save();
        System.out.println("————————————————————————————————————————");
        proxy.find();
    }
}