package com.itheima.spring_12;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class TimeLogInterceptor implements org.aopalliance.intercept.MethodInterceptor {

    //    【需求】：开发一个记录方法运行时间的例子。将目标方法的运行时间，写入到log4j的日志中。
//    日志记录器
    private static Logger LOGGER = Logger.getLogger(TimeLogInterceptor.class);


    @Override
//    invocation：代理对象的包装类，获取代理对象，目标对象，目标方法等

    public Object invoke(MethodInvocation invocation) throws Throwable {

        //方法开始之前的时间
        long beforeTime = System.currentTimeMillis();


        //调用目标原来的方法，并且返回结果，


        Object proceed = invocation.proceed();

        //方法结束后的时间

        long agterTime = System.currentTimeMillis();
        //计算运行的时间

        long runtime = agterTime - beforeTime;

        //控制台u打印的时间

        System.out.println(invocation.getThis().getClass().getName() + "类的" + invocation.getMethod().getName() + "方法运行了" +
                "：" + runtime
        );

        //日志记录的时间
        LOGGER.info(invocation.getThis().getClass().getName() + "类的" + invocation.getMethod().getName() + "方法运行了" +
                "：" + runtime);


        return proceed;
    }
}
