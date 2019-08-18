package spring_13;

import com.itheima.spring_12.CustomerService;
import com.itheima.spring_12.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-aspect.xml")
public class MyAspectTest {
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