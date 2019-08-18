package com.itheima.spring_14;


import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void save() {

//        int i = 1/0;
        System.out.println("客户保存了。。。。。");
    }

    @Override
    public int find() {
        System.out.println("客户查询数量了。。。。。");
        return 100;

    }


    public void update() {
        System.out.println("新方法啊哈哈哈。。。。。");

    }

}
