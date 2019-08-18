package com.itheima02;

public class Mythread02 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <50 ; i++) {
            System.out.println("子线程在运行"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println("主线程在运行"+i);
        }
        Mythread02 mt= new Mythread02();
        Thread t = new Thread(mt);
        t.start();
    }


}
