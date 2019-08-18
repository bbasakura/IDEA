package spring_17;


import com.ithema.spring_16.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class BookDaoTest {

    @Autowired
    private spring_17.BookDao bookDao;

    @Test
    public void test() {

        Book book = new Book();



        bookDao.findById(2);


    }

}