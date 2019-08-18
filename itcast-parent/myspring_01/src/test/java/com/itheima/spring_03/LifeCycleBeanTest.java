package com.itheima.spring_03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleBeanTest {
    @Test
    public void test() {
        //先获取spring的容器，工厂，上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //对于单例此时已经被初始化
        //获取bean
        LifeCycleBean lifeCycleBean = (LifeCycleBean) applicationContext.getBean("lifeCycleBean");
        System.out.println(lifeCycleBean);
        //为什么没有销毁方法调用。
        //原因是：使用debug模式jvm直接就关了，spring容器还没有来得及销毁对象。
        //解决：手动关闭销毁spring容器，自动销毁单例的对象

        //其本身没有close方法，所以向上找到ClassPathXmlApplicationContext，强制转换
        ((ClassPathXmlApplicationContext) applicationContext).close();

    }


}