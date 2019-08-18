package com.itheima.News163;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class NewsDao extends JdbcTemplate {


    //数据源
    private static ComboPooledDataSource dataSource;


    static {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setJdbcUrl("jdbc:mysql://192.168.72.141:3306/gossip?characterEncoding=utf-8");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public NewsDao() {
        //将初始化的数据源赋值给父类的DataSource
        super(dataSource);
    }


    /**
     * 保存新闻的方法
     *
     * @param news 新闻对象
     */
    public void saveNews(News news) {
        String sql = "insert into news(id,title,url,content,source,time,editor) values(?,?,?,?,?,?,?)";
        update(sql, news.getId(), news.getTitle(), news.getUrl(), news.getContent(), news.getSource(), news.getTime(), news.getEditor());
    }
}
