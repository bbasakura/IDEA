<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="pragma" content="no-cache">
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
	
	<link type="text/css" rel="stylesheet" href="${kpcas}/themes/sso/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css"  href="${kpcas}/themes/sso/css/zTreeStyle.css" />
    <link rel="stylesheet" type="text/css" href="${kpcas}/themes/sso/css/index.css"/>
    
    <script type="text/javascript" src="${kpcas}/js/manage/jquery-1.7.2.min.js" ></script>
    <script type="text/javascript" src="${kpcas}/js/manage/bootstrap.min.js"></script>
    <script type="text/javascript" src="${kpcas}/js/manage/jquery.ztree.core-3.5.js" ></script>
    <script type="text/javascript" src="${kpcas}/js/manage/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="${kpcas}/js/manage/jquery.ztree.exedit-3.5.js"></script>
    <script type="text/javascript" src="${kpcas}/js/manage/jquery.ztree.exhide-3.5.js"></script>
	<script type="text/javascript" src="${kpcas}/js/manage/jquery.page.js"></script> 
	<script type="text/javascript" src="${kpcas}/js/manage/fastjson.min.js"></script>
	<script type="text/javascript" src="${kpcas}/js/manage/index.js"></script>
	<script type="text/javascript">
		var kpcas='${kpcas}';
		//var account = '${sessionScope.permission.user.name}';
		$.ajaxSetup ({ 
    		cache: false //关闭AJAX相应的缓存 
		});
	</script>
	
   </head> 
   
   <div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-confirm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="static" style="top:200px;">
		<div class="modal-dialog" style="width:351px;">
			<div class="modal-content" style="height:203px;">
				<div class="modal-header"
					style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
					<h4 class="modal-title" style="font-size:12px;color:#ffffff;">提示</h4>
				</div>
				<div class="modal-body" style="padding:10px 15px;">
					<div class="content-body"></div>
					
				</div>
				<div class="content-botton-list content-list">
					<button type="button" class="btn" data-dismiss="modal">取消</button>
					<button type="button" id="confirm" class="btn botton-active">确定</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-alert" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="false" style="top:200px;">
		<div class="modal-dialog" style="width:351px;">
			<div class="modal-content" style="height:203px;">
				<div class="modal-header"
					style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
					<h4 class="modal-title" style="font-size:12px;color:#ffffff;">提示</h4>
				</div>
				<div class="modal-body" style="padding:10px 15px;">
					<div class="content-body"></div>
					
				</div>
				<div class="content-botton-list content-list">
					<button type="button" class="btn botton-active" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div> 
    