package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/")
public class Filter2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest=(HttpServletRequest) req;
        HttpServletResponse servletResponse= (HttpServletResponse)resp;
        //这里面不能再有响应输出，否则乱码~~~~！！！~！！！！！
//         想要输出的话，在处理后试试看，是可以的！！
//        PrintWriter out = servletResponse.getWriter();
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html; charset=utf-8");
        //处理过之后放行
        chain.doFilter(req, resp);
        PrintWriter out = servletResponse.getWriter();
    }
//public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//    // 因为以往在编写Servlet时，在doPost方法的第一行，要解决request和response的乱码
//    // 也就意味着，使用Filter的话，在Servlet接收到请求之后，request和response就已经准备好了(已经设置好了utf-8的编码格式)
//
//    HttpServletRequest request = (HttpServletRequest)req;
//    HttpServletResponse response = (HttpServletResponse) resp;
//
//    // 解决request乱码
//    request.setCharacterEncoding("utf-8");
//
//    // 解决response乱码
//    response.setContentType("text/html; charset=utf-8");
//
//    chain.doFilter(req, resp);
//}


    public void init(FilterConfig config) throws ServletException {


    }

}
