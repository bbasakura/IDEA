package com.itheima.filter;

import com.itheima.web.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/login")
public class Filter4 implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest=(HttpServletRequest) req;
        HttpServletResponse servletResponse= (HttpServletResponse)resp;
        HttpSession session = servletRequest.getSession();
        session.setAttribute("11",user);
        User a = (User) session.getAttribute("11");
        String username = a.getUsername();
       if(username.equals("A")){
           chain.doFilter(req, resp);
       }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
