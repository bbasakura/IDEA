package com.itheima.spring_01;

public class UserServiceImp implements IUserService {

    private IUSerDao iuSerDao;

    public void setIuSerDao(IUSerDao iuSerDao) {
        this.iuSerDao = iuSerDao;
    }

    @Override
    public void loigin() {

//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        IUSerDao dao = (IUSerDao) applicationContext.getBean("userDaoImp");
        System.out.println("UserServiceImpl-service层方法调用了");

       iuSerDao.test();
    }

}
