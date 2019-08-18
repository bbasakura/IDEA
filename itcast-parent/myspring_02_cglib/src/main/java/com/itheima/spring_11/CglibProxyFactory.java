package com.itheima.spring_11;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyObject() {

        //代理对象生成器（工厂思想）
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);


        //创建获取对象

        return enhancer.create();
    }

    @Override
    public Object intercept(Object Proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        if ("save".equals(method.getName())) {
            writeLog();
        }
        //，目标对象对原来的方法执行
        Object invoke = method.invoke(target);
        return invoke;
    }

    private void writeLog() {

        System.out.println("增强啦，哈哈哈啊哈哈哈");
    }
}
