package com.itheima.mapper;

import com.itheima.pojo.News;

import java.util.List;

public interface NewsMapper {
    /**
     * 查询新闻数据(一次取100条数据)
     * @param maxid ： 获取数据的起始id（上100条数据的最大id）
     * @return
     * sql: select * from news where id > maxid  limit 0,100
     */
    // 查询数据
    public List<News> queryAndIdGtAndPage(String maxid);


    /**
     * 获取当前100数据的最大id值
     * @param maxid  最大的id
     * @return
     *
     * select max(id) from (select * from news where id > maxid  limit 0,100 ) as temp
     */
    public String queryAndIdMax(String maxid);
}
