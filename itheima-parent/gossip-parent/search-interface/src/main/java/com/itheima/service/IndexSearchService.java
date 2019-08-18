package com.itheima.service;

import com.itheima.pojo.News;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultBean;

import java.util.List;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.service
 * @ClassName: IndexSearchService
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/8 0008 21:37
 * @Version: 1.0
 */
public interface IndexSearchService {
    public List<News> findByKeywords(ResultBean resultBean)throws Exception;



    public PageBean findByPageQuery(ResultBean resultBean) throws Exception;
}
