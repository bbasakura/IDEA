package com.itheima.es_hbase;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ElkTest
 * @Package: com.itheima.es_hbase
 * @ClassName: ParseExcelUtil
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/8/18 0018 18:16
 * @Version: 1.0
 */
public class ParseExcelUtil {
    /**
     * XSSFWorkbook ,解析 .xlsx后缀的excel表格-----百万级别---2007
     * HSSFWorkbook ,解析 .xls后缀的excel ---2003
     */
    public static List<Article> parseExcel(String pathName) throws IOException {
        /**
         * @method: parseExcel
         * @return: java.util.List<com.itheima.es_hbase.Article>
         * @description:  解析表格的工具类
         * @Author: sakura
         * @Date: 2019/8/18 0018 18:18
         */

        ArrayList<Article> articles = new ArrayList<>();

        //读文件的IO流
        FileInputStream fileInputStream = new FileInputStream(new File(pathName));

        //excel解析对象封封装文件IO
        XSSFWorkbook xssfSheets = new XSSFWorkbook(fileInputStream);

        //获取excel数据，获取sheel页的数据
        XSSFSheet sheetAt = xssfSheets.getSheetAt(0);

        //获取表格的最后一行，要轮询？？？？？？？？？？？？？？
        int lastRowNum = sheetAt.getLastRowNum();

        //轮询的获取表格的数据,第一行是标题，剔除，i=1,左后一行要保留i <= array.length
        for (int i = 1; i <= lastRowNum; i++) {

            XSSFRow row = sheetAt.getRow(i);
            String id = row.getCell(0).toString();
            String title = row.getCell(1).toString();
            String from = row.getCell(2).toString();
            String time = row.getCell(3).toString();
            String readCount = row.getCell(4).toString();
            String content = row.getCell(5).toString();

            //Article的构造方法去封装数据
            Article article = new Article(id, title, from, time, readCount, content);

            articles.add(article);
        }
        return articles ;
    }


}
