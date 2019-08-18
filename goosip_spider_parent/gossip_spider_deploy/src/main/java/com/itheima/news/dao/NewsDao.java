package com.itheima.news.dao;

import com.itheima.news.pojo.News;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

/**
 * @author itheima
 * @Title: NewsDao
 * @ProjectName goosip_spider_parent
 * @Description: dao层
 * @date 2019/6/2915:13
 */
public class NewsDao extends JdbcTemplate {

    private static ComboPooledDataSource comboPooledDataSource = null;

    //初始化数据源
    static {
        comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.72.141:3306/gossip?characterEncoding=utf-8");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }



    //在子类中调用父类的构造方法
    public NewsDao(){
        super(comboPooledDataSource);
    }


    /**
     * 保存新闻数据到数据库的方法
     * @param news
     */
    public void saveNews(News news){
        String sql = "insert into news(id,title,url,content,editor,time,source) values(?,?,?,?,?,?,?)";
        this.update(sql,news.getId(),news.getTitle(),news.getUrl(),news.getContent(),news.getEditor(),news.getTime(),news.getSource());
    }


}
