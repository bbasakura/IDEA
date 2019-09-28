<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${kpcas}/js/manage/addRole.js"></script>
  
  <body>
    <div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">角色分配</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
		<div id="addRoleSearch" class="left-search">
                <input type="text" placeholder="请输入角色名称"/>
                <button id="addRoleSearchBtn" class="btn search-btn btn-sm botton-active">搜索</button>
            </div>
         <div>
         	<table id="add-role" class="user-list">
         		<thead>
                <tr>
                    <th width="5%"><input type="checkbox" id="checkAll-addRole"/><label for="checkAll-addRole"></label></th>
                    <th width="20%">角色名称</th>
                    <th width="20%">角色中文名</th>
                    <th>角色描述</th>
                </tr>
                </thead>
                <tbody>
                	
                </tbody>
         	</table>
         </div>
         <div class="page-right">
			<div id="addRolePaging" class="content-page">
				 <select  name="pageSize">
					<option value="10">10条/页</option>
					<option value="20">20条/页</option>
					<option value="50">50条/页</option>
				</select>
			</div>
			<div id="addRolePage" class="tcdPageCode"></div>
		</div>
	</div>
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="userList.addRole();" class="btn botton-active">保存</button>
	</div>
  </body>
