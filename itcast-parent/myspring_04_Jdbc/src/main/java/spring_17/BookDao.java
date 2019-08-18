package spring_17;

import com.ithema.spring_16.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class BookDao extends JdbcDaoSupport {

    //保存
    public void saveBook(Book book) {

        String sql = "insert into book values(null,?,?)";

        super.getJdbcTemplate().update(sql, book.getName(), book.getPrice());
    }


    //更新
    public void update(Book book) {
        String sql = "update book set name =? ,price =? where id =?";
        super.getJdbcTemplate().update(sql, book.getName(), book.getPrice(), book.getId());
    }

    //删除
    public void delete(Book book) {
        super.getJdbcTemplate().update("delete from book where id =?", book.getId());
    }


    //根据id查询
    public Book findById(Integer id){
        String sql ="select * from book where id = ?";
        return super.getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    //查询所有

    public List<Book> findAll( ){
        String sql ="select * from book";
        return super.getJdbcTemplate().query(sql,BeanPropertyRowMapper.newInstance(Book.class));
    }

    //条件查询: 根据图书名称模糊查询信息
    public List<Book> findByNameLike(String name ){
        String sql ="select * from book where name like ?";
        return super.getJdbcTemplate().query(sql,BeanPropertyRowMapper.newInstance(Book.class),"%"+name+"%");
    }



}
