package com.itheima.es_hbase;

import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: ElkTest
 * @Package: com.itheima.es_hbase
 * @ClassName: es_hbaseTest
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/18 0018 19:52
 * @Version: 1.0
 */
public class es_hbaseTest {

    //测试的主入口

    public static void main(String[] args) throws IOException {

        //解析数据---写入数据
        parseAndWrite();

        //查询数据
        queryData("接班人");
    }

    private static void queryData(String titleTermName) {

        //用es查询title词条,获取索引ID

        List<Article> articles = EsUtil.termQuery(titleTermName);

        for (Article article : articles) {

            //根据bean对象内的ID,查询hbase数据库

            String content = HbaseUtil.queryByRowkey("articles", "article", "content", article.getId());

            System.out.println("新闻的内容是：" + content);


        }


    }

    private static void parseAndWrite() throws IOException {

        /**
         * @method: parseAndWrite
         * @return: void
         * @description: 负责数据的写入和查询解析excel表格
         *               1、解析数据写入hbase和es
         * @Author: sakura
         * @Date: 2019/8/18 0018 19:54
         */
        String pathName ="F:\\BigData\\就业班资料\\视频\\7、张红保\\day04_logStatic\\Day04_ELK\\elk-day02\\hbaseEs.xlsx";

        List<Article> articles = ParseExcelUtil.parseExcel(pathName);

        //写入hbase
        HbaseUtil.putArticles(articles);

        //写入es

        EsUtil.bulkPutData(articles);
    }


}
