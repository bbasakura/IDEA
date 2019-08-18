package com.itheima.web;

import com.itheima.Utils.JdbcTempUtils;
import com.itheima.service.UserService;
import javafx.scene.control.Alert;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
//实现方式有继承和实现接口两种，哪一个？
@WebServlet("/案例1")
public class DemoServlet extends HttpServlet {
    //    @Override
//    public void init(ServletConfig servletConfig) {
//
//    }
//
//    @Override
//    public ServletConfig getServletConfig() {
//        return null;
//    }
//
//    @Override
//    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        UserService userService = new UserService();
//        List<Map<String, Object>> ttt=userService.queryAll();
//        for (Map<String, Object> tt :ttt) {
//            System.out.println(tt);
//        }
//    }
//
//    @Override
//    public String getServletInfo() {
//        return null;
//    }
//
//    @Override
//    public void destroy() {
//
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<Map<String, Object>> ttt = userService.queryAll();
        for (Map<String, Object> tt : ttt) {
            System.out.println(tt);
        }

    }

}


