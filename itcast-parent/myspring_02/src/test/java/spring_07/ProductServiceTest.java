package spring_07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductServiceTest {

    @Test
    public void test() {


        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");


        ProductService productService = (ProductService) ac.getBean("productService");


        productService.save();


    }

}