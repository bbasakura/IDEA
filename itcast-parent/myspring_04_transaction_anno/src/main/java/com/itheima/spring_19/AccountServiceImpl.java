package com.itheima.spring_19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("accountService")
@Transactional//会对该类中，所有的共有的方法，自动加上事务--全局的设置，默认是可写

public class AccountServiceImpl implements IAccountService {

//        注入AccountDao

    @Autowired
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String outName, String inName, Double money) {

        accountDao.out(outName,money);

        accountDao.in(inName,money);


    }
    @Transactional(readOnly=true)//使用局部覆盖全局的
    public void findAccount(){
        System.out.println("查询帐号的信息了");
    }

}
