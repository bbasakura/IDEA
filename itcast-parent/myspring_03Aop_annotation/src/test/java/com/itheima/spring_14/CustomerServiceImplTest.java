package com.itheima.spring_14;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceImplTest {

    //注入要测试bean
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    //测试
    @Test
    public void test() {
        //基于接口
        customerService.save();
        customerService.find();

        //基于类的
        productService.save();
        productService.find();


        //扩展方法执行:customerService是一个动态代理对象，原因，该对象是接口的子类型的对象
        ((CustomerServiceImpl) customerService).update();
    }


}