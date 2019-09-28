package cn.itcast.b2c.gciantispider.util.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import cn.itcast.b2c.gciantispider.util.Constants;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;

public class LoginFilter implements Filter {
    
    public static final String PARAM_NAME_EXCLUSIONS             = "exclusions";
    
    private Set<String>        excludesPattern;
    
    protected PatternMatcher   pathMatcher                       = new ServletPathMatcher();

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        String reqURI = request.getRequestURI();
        
        if (isExclusion(reqURI,request)) {
            chain.doFilter(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        //没登陆
        if (null==session.getAttribute(Constants.HAVE_LOGIN)) {
            String requestType = request.getHeader("X-Requested-With"); 
            //处理ajax请求
            if("XMLHttpRequest".equals(requestType)){
                doFilterAjax(request, response, chain);
                return;
            }else{ //处理普通页面请求
                String url = request.getRequestURL().toString();
                String queryString = request.getQueryString();
                if(!StringUtils.isEmpty(queryString)){
                    url +="?"+queryString;
                }
                
                request.setAttribute("LOGIN_SOURCE",url);
                request.getRequestDispatcher("/auth/login").forward(request, response);
                return;
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (exclusions != null && exclusions.trim().length() != 0) {
            excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
        }
    }
    
    public boolean isExclusion(String requestURI, HttpServletRequest request) {
        if (excludesPattern == null) {
            return false;
        }

        String contextPath = request.getContextPath();
        if (contextPath != null && requestURI.startsWith(contextPath)) {
            requestURI = requestURI.substring(contextPath.length());
            if (!requestURI.startsWith("/")) {
                requestURI = "/" + requestURI;
            }
        }

        for (String pattern : excludesPattern) {
            if (pathMatcher.matches(pattern, requestURI)) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * 处理AJAX的登录处理
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilterAjax(HttpServletRequest request,HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        response.setStatus(Constants.RETURN_CODE_NOTLOGIN);
        response.setCharacterEncoding("UTF-8");
        try {  
            PrintWriter writer = response.getWriter();  
             writer.write("user not login");  
             writer.flush();  
         } catch (IOException e) {  
               
         }   
    }

}
