<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" href="${kpcas}/themes/sso/css/error.css" /> 
  	<title>页面错误</title> 
  	<jsp:include page="common.jsp"></jsp:include> 
  </head>
  <body>
  	   <jsp:include page="top.jsp"></jsp:include>
       <div class="error-contain">
	        <div class="error-img"></div>
	        <div class="error-msg"><span style="color:#333;">页面出现错误，点击前往</span><span style="color:#666;"></span></div>
	        <div class="error-reload" onclick="window.location.href='${kpcas}/pages/index.jsp'">首页</div>
      </div>
       
  </body>
</html>
