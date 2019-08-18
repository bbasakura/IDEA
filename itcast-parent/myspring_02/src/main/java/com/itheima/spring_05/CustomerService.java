package com.itheima.spring_05;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(value = "customerService")
public class CustomerService {

//属性注入的发方式
//    第一种：@Value("#{customerDao}")
//   第二种： @Autowired单独使用
//    @Autowired
//    @Qualifier("customerDao")第三种：结合qUalifier使用
//    使用@Autowired注入方法时，通过参数形式注入

    /**
     * @Autowired
     * public viod ads(CustomerDao customerDao){
     *  this.CustomerDAo= customerDao
     *
     * }
     */
//    第三种@Resource
//            配合name使用：name="id",@Resource(name = "customerDao")
//    第四种： JSR-330标准（基于jdk） 提供注解@Inject(麻烦,需要额外导包


    @Inject
    private CustomerDao customerDao;

    public void save(){
        System.out.println("CustomerService业务层被调用了。。。");
        customerDao.save();
    }


}
