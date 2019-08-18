package com.itheima.CookiesDemo;

import sun.util.resources.en.CurrencyNames_en_MT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/cookie_send")
public class CookieSendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个cookie，并发送
        Cookie cookie = new Cookie("name ", URLEncoder.encode("张三", "utf-8"));
        cookie.setMaxAge(30);
        response.addCookie(cookie);
        //创建第二个cookie，并发送到浏览器
        Cookie cookie1 = new Cookie("name", URLEncoder.encode("李四", "utf-8"));
        cookie1.setMaxAge(30);
        response.addCookie(cookie1);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
