package com.ithema.spring_16;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JdbcTemplateTEstTest {
    @Autowired
    private BookDao bookDao;


    @Test
    public void test() {

        Book book = new Book();

        book.setName("java编程");
        book.setPrice(12123d);

//        最后一步，调用saveBook方法


        bookDao.saveBook(book);

    }


}