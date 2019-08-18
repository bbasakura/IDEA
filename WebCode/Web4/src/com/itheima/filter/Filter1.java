package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//此filter 功能是审计，统计web的效率
@WebFilter( "/")
public class Filter1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        ///放行或者拦截之前
        long begin = System.currentTimeMillis();
        chain.doFilter(req, resp);
        //放行之后
        HttpServletRequest servletRequest=(HttpServletRequest) req;
        HttpServletResponse servletResponse= (HttpServletResponse)resp;
        PrintWriter out = servletResponse.getWriter();
        long end = System.currentTimeMillis();
        long cha= end-begin;
        out.write("运行的效率是："+cha);
        System.out.println("运行的效率是："+cha+((HttpServletRequest) req).getServletPath());
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
