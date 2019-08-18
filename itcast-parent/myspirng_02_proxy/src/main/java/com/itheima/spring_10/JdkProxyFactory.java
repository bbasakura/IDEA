package com.itheima.spring_10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory implements InvocationHandler {


    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }


//    public Object getProxyObject() {
//        //参数1：目标对象的类加载器
//        //参数2：目标对象实现的接口
//        //参数3：回调方法对象
///**方案一：在内部实现new InvocationHandler()，指定匿名类*/
//        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//                //增强的方法
//                if ("save".equals(method.getName())) {
//                    writeLog();
//                }
//
//                //原来的方法
//                Object object = method.invoke(target, args);
//                return object;
//            }
//        });
//
//    }

//    /**方案二：传递内部类的对象，指定内部类*/
//    public Object getProxyObject(){
//        //参数1：目标对象的类加载器
//        //参数2：目标对象实现的接口
//        //参数3：回调方法对象
//        /**方案二：传递内部类的对象，指定内部类*/
//        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),new MyInvocationHandler());
//    }

//    //自己制定内部类:类的内部可以多次使用类型
//    private class MyInvocationHandler implements InvocationHandler {
//
//        public Object invoke(Object proxy, Method method, Object[] args)
//                throws Throwable {
//            //如果是保存的方法，执行记录日志操作
//            if(method.getName().equals("save")){
//                writeLog();
//            }
//            //目标对象原来的方法执行
//            Object object = method.invoke(target, args);//调用目标对象的某个方法，并且返回目标对象方法的返回值
//            return object;
//        }
//
//    }

    public Object getProxyObject(){
        //参数1：目标对象的类加载器
        //参数2：目标对象实现的接口
        //参数3：回调方法对象
        /**方案三：直接使用调用类作为接口实现类，实现InvocationHandler接口*/
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }



    private static void writeLog() {
        System.out.println("源程序被增强了！！！！！");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果是保存的方法，执行记录日志操作
        if(method.getName().equals("save")){
            writeLog();
        }
        //目标对象原来的方法执行
        Object object = method.invoke(target, args);//调用目标对象的某个方法，并且返回目标对象
        return object;

    }
}
