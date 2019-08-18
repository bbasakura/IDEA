package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.itheima.web.FuckServlet.s2;
import static com.itheima.web.FuckServlet.s1;


@WebFilter("/")
public class Filter3 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        s1 = "**";
        s2 = "**";


        chain.doFilter(req, resp);


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
