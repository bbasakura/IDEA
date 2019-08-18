package com.itheima.spring_12;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class TimeLogInterceptorTest {

    //注入要测试的Bean
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @Test
    public void test() {
        //customerService

        customerService.find();
        customerService.save();

        System.out.println("+++++++++++++++++++++++");

        //productService

        productService.find();

        productService.save();
    }


}