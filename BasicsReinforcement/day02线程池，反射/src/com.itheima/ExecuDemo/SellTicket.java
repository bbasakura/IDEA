package com.itheima.ExecuDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SellTicket implements Runnable {
    //Runnable 在线程安全的时候，有一次创建多次传入Thread的优势，
//    本质上是一次对象的创建，免去的多次创建对象的麻烦，在用锁的时候，
//    甚至可以直接锁this，而其中的成员变量可以不用static修饰。
//    注意所对象的两个特点：唯一性，和被共享
//    在使用Ruanable创建线程时候i，满足着两个条件
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            synchronized ((this)) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在卖第" + tickets-- + "张票");
                }

            }
        }

    }
    public static void main(String[] args) {
        SellTicket st = new SellTicket();
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(st);
        es.execute(st);
        es.shutdown();




    }
}
