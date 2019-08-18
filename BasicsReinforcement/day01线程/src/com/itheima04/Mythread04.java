package com.itheima04;

public class Mythread04 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "在执行第" + i + "次");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "在执行第" + i + "次");
        }
        Mythread04 mythread04 = new Mythread04();
        Thread thread = new Thread(mythread04);
        thread.start();

    }
}


