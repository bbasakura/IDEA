package com.itheima.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-service.xml")
public class NewsCacheTest{

    @Autowired
    private NewsCache newsCache;


    @Test
    public void name() throws Exception {
        newsCache.cacheNews("陈妍希");

    }
}