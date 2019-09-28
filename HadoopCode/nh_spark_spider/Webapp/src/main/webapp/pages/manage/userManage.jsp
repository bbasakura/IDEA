<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<jsp:include page="common.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript" src="${kpcas}/js/manage/userList.js"></script>

  <body id="userManageBody">
	<div class="content-head">
        <span>用户管理</span>
    </div>
    <div class="content-body">
        <div class="content-botton-list">
            <button id="addUser"  class="btn btn-sm botton-active">新增用户</button>
            <button id="delUser"  class="btn btn-sm">删除用户</button>
            <button id="addRole"  class="btn btn-sm">角色分配</button>
            <button id="resetPwd"  class="btn btn-sm">重置密码</button>
            
            <div id="user-search" class="user-search">
                <input type="text" placeholder="请输入用户姓名"/>
                <button id="userSearchBtn" class="btn search-btn btn-sm botton-active">搜索</button>
            </div>
        </div>
        <div>
            <table id="user-list" class="user-list">
                <thead>
                <tr>
                    <th width="3%"><input type="checkbox" id="checkAll"/><label for="checkAll"></label></th>
                    <th width="10%">账号</th>
                    <th width="15%">姓名</th>
                    <th width="15%">邮箱</th> 
                    <th width="15%">联系电话</th>
                    <th width="17%">角色</th>
                    <th width="20%">上次登录时间</th>
                </tr>
                </thead>
                <tbody>
                
                </tbody>
            </table>
        </div>
		<div class="page-right">
			<div id="userPaging" class="content-page">
				 <select  name="pageSize">
					<option value="10">10条/页</option>
					<option value="20">20条/页</option>
					<option value="50">50条/页</option>
				</select>
			</div>
			<div id="userPage" class="tcdPageCode"></div>
		</div>
	</div>
  </body>