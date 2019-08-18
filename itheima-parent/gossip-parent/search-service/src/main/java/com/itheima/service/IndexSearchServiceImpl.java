package com.itheima.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.pojo.News;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.ResultBean;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.service
 * @ClassName: IndexSearchServiceImpl
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/8 0008 21:37
 * @Version: 1.0
 */
@Service
public class IndexSearchServiceImpl implements IndexSearchService {

    @Autowired
    private CloudSolrServer cloudSolrServer;


    //不分页查询
    @Override
    public List<News> findByKeywords(ResultBean resultBean) throws Exception {

        //注入solrcloud链接对象

        //获取查询对象，抽取方法
        SolrQuery solrQuery = getSolrQuery(resultBean);

        //执行查询，获取响应
        QueryResponse response = cloudSolrServer.query(solrQuery);

        //获取响应数据
        SolrDocumentList documentList = response.getResults();

        List<News> newsList = getNewsList(response, documentList);

        return newsList;
    }

    //分页查询
    @Override
    public PageBean findByPageQuery(ResultBean resultBean) throws Exception {

        //1. 获取分页的原始数据; page  pagesize，从javaBean中拿到其中的一个javaBean的属性
        Integer page = resultBean.getPageBean().getPage();
        Integer pageSize = resultBean.getPageBean().getPageSize();

        //2.创建solrQuery查询对象
        SolrQuery solrQuery = getSolrQuery(resultBean);


        //设置分页条件 start  rows （start的计算方法）
        solrQuery.setStart( (page - 1 ) * pageSize );
        solrQuery.setRows(pageSize);
        //执行查询,获取响应
        QueryResponse response = cloudSolrServer.query(solrQuery);
        //获取响应数据
        SolrDocumentList solrDocumentList = response.getResults();
        //获取结果列表  (返回当前页的数据)
        List<News> newsList = getNewsList(response, solrDocumentList);

        //封装返回结果
        PageBean pageBean = resultBean.getPageBean();

        //封装当前页的数据
        pageBean.setNewsList(newsList);

        //封装总条数
        Long numFound = solrDocumentList.getNumFound();
        pageBean.setPageCount(numFound.intValue());

        //封装总页数
        Double pageNum = Math.ceil((double) numFound / pageSize);
        pageBean.setPageNumber(pageNum.intValue());


        return pageBean;

    }


    public List<News> getNewsList(QueryResponse response, SolrDocumentList documentList) {


        List<News> newsList = new ArrayList<>();

        //获取高亮的显示内容，因为通过查询得知，高亮的内容是单独的一个map数据
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (SolrDocument document : documentList) {

            News news = new News();

            String id = (String) document.get("id");
            news.setId(id);

            //获取title的高亮内容；
            String title = (String) document.get("title");

            Map<String, List<String>> map = highlighting.get(id);
            List<String> titleList = map.get("title");
            //判断一下
            if (titleList != null && titleList.size() > 0) {

                title = titleList.get(0);
            }
            news.setTitle(title);

            //solr中存储的是Date的类型
            Date time = (Date) document.get("time");
            //时间减去八个小时，得到lon毫秒值，通过Date，得到Date类型的值
            time = new Date(time.getTime() - (8 * 60 * 60 * 1000));
            //将得到的Date类型的值格式化成字符串yyyy-MM-dd HH:mm:ss格式，
            news.setTime(format.format(time));


            String source = (String) document.get("source");
            news.setSource(source);

            String editor = (String) document.get("editor");
            news.setEditor(editor);

            String content = (String) document.get("content");
            //获取content的高亮内容

            List<String> contentlist = map.get("content");
            if (contentlist != null && contentlist.size() > 0) {
                content = contentlist.get(0);
            }
            news.setContent(content);

            String url = (String) document.get("docurl");
            news.setUrl(url);

            newsList.add(news);
        }

        return newsList;
    }

    public SolrQuery getSolrQuery(ResultBean resultBean) throws Exception {
        /**
         * @method: getSolrQuery

         * @return: org.apache.solr.client.solrj.SolrQuery
         * @description:
         * @Author: sakura
         * @Date: 2019/7/9 0009 12:41
         */
        //2. 创建查询对象
        SolrQuery solrQuery = new SolrQuery("text:" + resultBean.getKeywords());

        //2.1  设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("title");
        solrQuery.addHighlightField("content");
        solrQuery.setHighlightSimplePre("<font style='color:red'>");
        solrQuery.setHighlightSimplePost("</font>");


        //2.2 添加过滤条件(搜索工具的过滤条件)
        String editorString = resultBean.getEditor();
        String sourceString = resultBean.getSource();

        //页面传递的时间格式类型:  07/06/2019 11:48:19   MM/dd/yyyy HH:mm:ss
        SimpleDateFormat pageFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        SimpleDateFormat solrFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        //solr需要的日期格式:  yyyy-MM-dd'T' HH:mm:ss'Z'

        String startDate = resultBean.getStartDate();
        String endDate = resultBean.getEndDate();
        //01 时间范围查询: 起始时间和结束时间都必须存在,添加时间过滤条件
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            //日期格式的转换: MM/dd/yyyy HH:mm:ss  ------->yyyy-MM-dd'T'HH:mm:ss'Z'
            Date startTime = pageFormat.parse(startDate);
            startDate = solrFormat.format(startTime);

            Date endTime = pageFormat.parse(endDate);
            endDate = solrFormat.format(endTime);

            //说明前端传递了时间范围查询
            solrQuery.addFilterQuery("time:[" + startDate + " TO " + endDate + "]");

        }
        //02  添加编辑过滤条件
        if (StringUtils.isNotEmpty(editorString)) {
            solrQuery.addFilterQuery("editor:" + editorString);
        }
        //03 添加来源过滤条件
        if (StringUtils.isNotEmpty(sourceString)) {
            solrQuery.addFilterQuery("source:" + sourceString);
        }

        return solrQuery;

    }
}


