package com.itheima.controller;

import com.itheima.pojo.News;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultBean;
import com.itheima.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.List;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.controller
 * @ClassName: NewsController
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/8 0008 20:10
 * @Version: 1.0
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/s")
    @ResponseBody
    public List<News> findByKeywords(ResultBean resultBean) {
        /**
         * @method: findByKeywords
         * @return: java.util.List<com.itheima.pojo.News>
         * @description： 关键字查询，返回新闻列表!!!!!!注意在浏览器请求时，会产生请求乱码，解决办法为
         * 在tomcat插件中，配置urlencoding！！！！否则返回的数据一值为空。。。
         * @Author: sakura
         * @Date: 2019/7/8 0008 20:11
         */

        try {
            //判断前端传来的参数是佛正确
            if (resultBean.getKeywords() == null || "".equals(resultBean.getKeywords())) {
                return null;
            }
            //调用service层，开始查询数据
            List<News> newsList = newsService.findByKeywords(resultBean);
            return newsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping("/ps")
    @ResponseBody
    public PageBean findByPageQuery(ResultBean resultBean){
        try {
            //1. 获取请求参数:

            //2. 判断请求参数的合法性
            if(resultBean == null){
                return null;
            }

            if(StringUtils.isEmpty(resultBean.getKeywords())){
                return null;
            }


            String keywords = URLDecoder.decode(resultBean.getKeywords(), "utf-8");
            System.out.println("keywords：" + keywords);
            resultBean.setKeywords(keywords);

            //如果前端不传递page  pagesize pageBean 就是null
            if(resultBean.getPageBean() == null){
                PageBean pageBean = new PageBean();
                resultBean.setPageBean(pageBean);
            }

            //3. 调用service的分页查询方法
            PageBean pageBean = newsService.findByPageQuery(resultBean);

            //4. 返回分页查询结果
            return  pageBean;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
