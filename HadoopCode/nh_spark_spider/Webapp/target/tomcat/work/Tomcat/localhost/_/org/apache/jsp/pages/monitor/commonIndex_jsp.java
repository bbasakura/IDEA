/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-09-24 07:39:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.monitor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class commonIndex_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/monitor/commonIndex.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/themes/default/css/demo.css\" /> \r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("        @font-face {font-family: \"iconfont\";\r\n");
      out.write("          src: url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/themes/default/fonts/iconfont.eot'); /* IE9*/\r\n");
      out.write("          src: url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/themes/default/fonts/iconfont.eot#iefix') format('embedded-opentype'), /* IE6-IE8 */\r\n");
      out.write("          url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/themes/default/fonts/iconfont.woff') format('woff'), /* chrome, firefox */\r\n");
      out.write("          url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/themes/default/fonts/iconfont.ttf') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/\r\n");
      out.write("          url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/themes/default/fonts/iconfont.svg#iconfont') format('svg'); /* iOS 4.1- */\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .iconfont {\r\n");
      out.write("          font-family:\"iconfont\" !important;\r\n");
      out.write("          font-size:38px;\r\n");
      out.write("          color: #999;\r\n");
      out.write("          font-style:normal;\r\n");
      out.write("          -webkit-font-smoothing: antialiased;\r\n");
      out.write("          -webkit-text-stroke-width: 0.2px;\r\n");
      out.write("          -moz-osx-font-smoothing: grayscale;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"bj\">\r\n");
      out.write("\t<div class=\"Unified\">\r\n");
      out.write("\t\t<ul class=\"left-menu\">\r\n");
      out.write("\t\t\t<li  id=\"systemMonitor\" class=\"active\">\r\n");
      out.write("\t\t\t\t<i class=\"icon iconfont\">&#xe612;</i>\r\n");
      out.write("\t\t\t\t<div class=\"name\">系统监控</div>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<!-- <li id=\"ruleMonitor\">\r\n");
      out.write("\t\t\t\t<i class=\"icon iconfont\">&#xe610;</i>\r\n");
      out.write("\t\t\t\t<div class=\"name\">规则监控</div>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li id=\"modelMonitor\" >\r\n");
      out.write("\t\t\t\t<i class=\"icon iconfont\">&#xe611;</i>\r\n");
      out.write("\t\t\t\t<div class=\"name\">模型监控</div>\r\n");
      out.write("\t\t\t</li> -->\r\n");
      out.write("\t\t\t<li id=\"propertyMonitor\">\r\n");
      out.write("\t\t\t\t<i class=\"icon iconfont\">&#xe613;</i>\r\n");
      out.write("\t\t\t\t<div class=\"name\">性能监控</div>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<div id=\"rightContent\" class=\"right-content\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}