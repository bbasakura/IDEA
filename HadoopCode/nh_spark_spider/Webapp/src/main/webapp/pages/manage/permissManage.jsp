<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<jsp:include page="common.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="${kpcas}/plugins/jquery-treetable/css/jquery.treetable.css"/>
    <link rel="stylesheet" href="${kpcas}/plugins/jquery-treetable/css/jquery.treetable.theme.default.css"/>
    <script type="text/javascript" src="${kpcas}/plugins/jquery-treetable/js/jquery.treetable.js"></script> 
    <script type="text/javascript" src="${kpcas}/js/manage/permissionManage.js"></script>

<body >
	<div id="permissManageBody">
		<div class="content-head">
			<span>权限管理</span>
		</div>
		<div class="content-body">
			<div class="content-botton-list">
				<button id="newPermiss"  class="btn btn-sm botton-active">新增</button>
				<button id="editPermiss"  class="btn btn-sm">修改</button>
				<button id="delPermiss"  class="btn btn-sm">删除</button>
				
				<div id="permissionSearch" class="user-search">
					<input type="text" placeholder="请输入权限名称或权限编号"/>
					<button  class="btn search-btn btn-sm botton-active">搜索</button>
				</div>
			</div>
			<div>
				<table id="treeTable-permiss" class="treeTable" style="width:100%;margin-top:20px;">
					<thead>
						<tr>
							<th width="25%"><span>权限名称</span></th>
							<th width="10%">权限编号</th>
							<th width="40%">URL</th>
							<th width="25%">权限描述</th>
						</tr>
					</thead>
					<tbody>
	
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
