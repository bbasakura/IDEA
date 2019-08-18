package com.itheima01;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        /*
        * 1.创建Runable的接口实现类
        * 2.重写run方法
        * 3.实例化实现类
        * 4.将实例化的对象作为参数传递到Thread的新建对象中
        * 5.调用start（）
        * 6.Thread及其子类的名字用setName（）和getName（）
        * 7.main方法的线程名字查询方法，Thread.currentThread.getName(),
        * 这是获得当前运行的线程名的方法。
        * */
        MyRunnable myRunnable1 = new MyRunnable();
        Thread thread1 = new Thread(myRunnable1);
        thread1.setName("jj");
        thread1.start();
        System.out.println(thread1.getName());
        System.out.println(Thread.currentThread().getName());

    }
}
