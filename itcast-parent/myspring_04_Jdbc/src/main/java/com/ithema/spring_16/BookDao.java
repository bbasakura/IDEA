package com.ithema.spring_16;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookDao {


//    注入jdbctemplate

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveBook(Book book) {

        String sql = "insert into book values(null,?,?)";

        jdbcTemplate.update(sql, book.getName(), book.getPrice());
    }


}
