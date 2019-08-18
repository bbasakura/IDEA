package com.itheima.spring_14;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component("myAspect")//相当于<bean id="myAspect" class="cn.itcast.spring.a_aspectj.MyAspect"/>
@Aspect
public class MyAspect {

    //前置通知
    //相当于：<aop:before method="before" pointcut="bean(*Service)"/>
    //@Before("bean(*Service)")：参数值：自动支持切入点表达式或切入点名字
   /* @Before("bean(*Service)")
    public void before(JoinPoint joinPoint){
        System.out.println("=======前置通知。。。。。");
    }*/
    //后置通知
  /*  @AfterReturning(value="bean(*Service)",returning="returnVal")
    public void afterReturning(JoinPoint joinPoint,Object returnVal){
        System.out.println("=======后置通知。。。。。");
    }*/



    //环绕通知：
  /*  @Around("bean(*Service)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("---环绕通知-----前");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("---环绕通知-----后");
        return object;
    }
*/
    //抛出通知

    @AfterThrowing(value="bean(*Service)",throwing="ex")
    public void afterThrowing(JoinPoint joinPoint , Throwable ex){
        System.out.println("---抛出通知。。。。。。"+"抛出的异常信息："+ex.getMessage());
    }


    //最终通知
    //拦截所有以ice结尾的bean
    @After("bean(*ice)")
    public void after(JoinPoint joinPoint){
        System.out.println("+++++++++最终通知。。。。。。。");
    }




}
