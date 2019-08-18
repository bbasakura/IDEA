package com.itheima.spring_12;

import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerService {

    public void save() {

//        int i= 1/0;
        System.out.println("商品保存了。。。。。");

    }

    public int find() {
        System.out.println("商品查询数量了。。。。。");
        return 99;
    }



}
