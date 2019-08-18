package com.itheima03;
/*
实现Runable接口直接是new Runable
* */
public class Mythread03  {
    public static void main(String[] args) {
        new Thread(){
            //父类引用
            @Override
            public void run() {
                System.out.println("播放背景音乐");
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("显示画面");
            }
        }).start();
    }
}

