package cn.itcast.b2c.gciantispider.util.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XssFilter implements Filter {

    private String exclusions;

    public void init(FilterConfig config) throws ServletException {

        exclusions = config.getInitParameter("exclusions");
//        exclusions = exclusions != null && "".equals(exclusions.trim()) ? null : exclusions.trim();
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        // 先判断当前请求URL是否需要进行验证，目前只适合用于判断普通的URI
        String uri = request.getRequestURI();
        boolean isExclusions = false;
        if(null != exclusions){
            try {
                isExclusions = uri.matches(exclusions);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (isExclusions) {
            chain.doFilter(request, response);
        }
        else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) arg0), arg1);
        }

    }

    public void destroy() {
    }
}