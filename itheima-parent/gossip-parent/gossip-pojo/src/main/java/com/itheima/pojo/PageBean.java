package com.itheima.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author itheima
 * @Title: PageBean
 * @ProjectName gossip-parent
 * @Description: 封装返回分页结果的javabean对象:
 * @date 2019/7/815:16
 */
public class PageBean implements Serializable {

    //总条数
    private Integer page = 1; //当前页(默认第一页)
    private Integer pageSize = 15; //每页显示的条数
    private Integer pageCount; // 总条数
    private Integer pageNumber; //总页数
    private List<News> newsList; //当前页的数据
    //get 和 set


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", pageNumber=" + pageNumber +
                ", newsList=" + newsList +
                '}';
    }
}
