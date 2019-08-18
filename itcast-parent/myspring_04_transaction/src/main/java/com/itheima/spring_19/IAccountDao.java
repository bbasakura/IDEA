package com.itheima.spring_19;

public interface IAccountDao {


    //（存入）转入
    public void in(String name, Double money);

    //（取出）转出
    public void out(String name, Double money);


}
