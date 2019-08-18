package spring_07;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    //强调：注入必须是bean注入bean
    @Autowired
    private ProductDao productDao;

    //产品的保存
    public void save(){
        System.out.println("产品保存了，--业务层");
        //调用dao层
        productDao.save();
    }



}
