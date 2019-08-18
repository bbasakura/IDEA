package com.itheima.spring_19;

import org.springframework.beans.factory.annotation.Autowired;

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
}
