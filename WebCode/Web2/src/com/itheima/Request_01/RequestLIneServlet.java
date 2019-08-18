package com.itheima.Request_01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/request_line")
public class RequestLIneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
        System.out.println("method"+request.getMethod());
        System.out.println("RequestUrl"+request.getRequestURL());
        System.out.println("URI"+request.getRequestURI());
        System.out.println("protocol"+request.getProtocol());
        System.out.println("ContextPath"+request.getContextPath());
        System.out.println("SeverletPath"+request.getServletPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
}
