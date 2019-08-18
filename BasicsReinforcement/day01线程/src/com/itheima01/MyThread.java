package com.itheima01;
/*
 * 1.创建Thread的集成类
 * 2.重写run方法
 * 3.实例化子类
 * 4.用子类对象
 * 5.调用start（）
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.setName("hh");
        myThread2.setName("gg");
        myThread1.start();
        myThread2.start();
        System.out.println(myThread1.getName());
        System.out.println(myThread2.getName());

    }
}

