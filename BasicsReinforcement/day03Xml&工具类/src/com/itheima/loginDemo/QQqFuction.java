package com.itheima.loginDemo;

public class QQqFuction {

    @Jurisdiction("1")
    public void look() {
        System.out.println("查看空间");
    }

    @Jurisdiction("2")
    public void photo() {
        System.out.println("查看图片");
    }
    @Jurisdiction("2")
    public void plu() {
        System.out.println("发表说说");
    }
    @Jurisdiction("4")
    public void message() {
        System.out.println("留言");
    }
}
