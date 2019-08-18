package com.itheima05;

public class Moive extends Thread{
    private static int tickets = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "正在出售第" + tickets + "张票");
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        Thread thread3 = new Thread();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
