package spring_06;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class LifeCycleBeanTest {
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

//        LifeCycleBean lifeCycleBean = (LifeCycleBean) ac.getBean("lifeCycleBean");

//        ac.close();
        ac.getClass().getMethod("close").invoke(ac);



//        为什么没有输出？？？？？？？？？？？？？？？？

    }

}