package cn.itcast.b2c.gciantispider.util.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

import cn.itcast.b2c.gciantispider.bean.CurSystemPer;
import cn.itcast.b2c.gciantispider.bean.PermissionBean;
import cn.itcast.b2c.gciantispider.service.IPermissionService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.SpringContextUtil;
import cn.itcast.b2c.gciantispider.util.UrlUtil;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;

/**
 * 校验当前登录账号的权限URL
 *
 */
public class ValidatePerUrlFilter implements Filter {
    //当前系统名称
    private String             curSystem ;
    
    public static final String PARAM_NAME_EXCLUSIONS             = "exclusions";
    
    private Set<String>        excludesPattern;
    
    protected PatternMatcher   pathMatcher                       = new ServletPathMatcher();
    
    public void init(FilterConfig filterConfig) throws ServletException {
        curSystem = filterConfig.getServletContext().getInitParameter("curSystem");
        String exclusions = filterConfig.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (exclusions != null && exclusions.trim().length() != 0) {
            excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
        }
    }

    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {        
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(); 
        //没有加载系统权限url, 进行加载
        if (null == session.getAttribute(Constants.LOAD_SYS_PERMISSION)) {
            //查询到该系统配置的权限
            List<String> sysPerUrllList = ((IPermissionService) SpringContextUtil.getBean("permissionService")).getSystemPerBySysName(curSystem);
            session.setAttribute(Constants.LOAD_SYS_PERMISSION, "HAVE_LOAD");
            CurSystemPer.setPerUrList(sysPerUrllList);
        }
        if (isExclusion(request.getRequestURI(),request)) {
            chain.doFilter(request, response);
            return;
        }
        
        List<PermissionBean> permissionList = (List<PermissionBean>) session.getAttribute(Constants.USER_PERMISSION);
        String url = UrlUtil.constructReqUrl(request);             //组装请求URL   
        //校验权限URL
        if (UrlUtil.validatePerUrl(url, permissionList)) {         //有权限，放行  
            chain.doFilter(servletRequest, servletResponse);
            return ;
        }else { //无权限   
            //此处添加跳转到无权限页面
            response.setContentType("text/html;charset=UTF-8"); 
            PrintWriter out = response.getWriter();
            out.print("</br>");
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您无权访问，点击前往");
            out.print("<a href=''>" + "&nbsp;首页" + "</a></br>");
            out.flush();  
            return;
        }                     
    }

    public void destroy() {
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
}
