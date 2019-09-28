<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<jsp:include page="common.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript" src="${kpcas}/js/manage/roleManage.js"></script>

  <body>
	<div class="content-head">
        <span>角色管理</span>
    </div>
    <div class="content-body">
        <div id="roleBtn" class="content-botton-list">
            <button id="newRole"  class="btn btn-sm botton-active">新增</button>
            <button id="editRole" class="btn btn-sm">修改</button>
            <button id="setRole"  class="btn btn-sm">权限分配</button>
            <button id="delRole"  class="btn btn-sm">删除角色</button>
            <button id="roleUser" class="btn btn-sm">角色用户</button>
            <div class="user-search">
                <input id="roleSearch" type="text" placeholder="请输入角色名称"/>
                <button id="roleSearchBtn" class="btn search-btn btn-sm">搜索</button>
            </div>
        </div>
        <div>
            <table id="role-list" class="user-list">
                <thead>
                <tr>
                    <th width="5%"><input type="checkbox" id="checkAll"/><label for="checkAll"></label></th>
                    <th width="20%">角色名称</th>
                    <th width="20%">中文名称</th>
                    <th width="30%">角色描述</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                	
                </tbody>
            </table>
        </div>
		<div class="page-right">
			<div id="rolePaging" class="content-page">
				 <select  name="pageSize">
					<option value="10">10条/页</option>
					<option value="20">20条/页</option>
					<option value="50">50条/页</option>
				</select>
			</div>
			<div id="rolePage" class="tcdPageCode"></div>
		</div>
	</div>
  </body>
</html>