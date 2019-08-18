package com.itheima.SeverletContextDemo;


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet("/123context")
public class SeverletContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、下载的存在hedear中，要进行编码设置，
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("下载", "utf-8") + ".gif");
        //2、浏览器字节输出流
        OutputStream out = response.getOutputStream();
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/download.gif");
//        System.out.println(this.getServletContext().getResourceAsStream("WEB-INF" + filename));
        //3、用工具包的copy功能
        IOUtils.copy(in, out);
        //4、关流
        in.close();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
