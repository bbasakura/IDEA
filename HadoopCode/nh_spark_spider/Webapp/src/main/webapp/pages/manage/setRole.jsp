<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
    <link rel="stylesheet" href="${kpcas}/plugins/jquery-treetable/css/jquery.treetable.css"/>
    <link rel="stylesheet" href="${kpcas}/plugins/jquery-treetable/css/jquery.treetable.theme.default.css"/>
    <script type="text/javascript" src="${kpcas}/plugins/jquery-treetable/js/jquery.treetable.js"></script>
    <script type="text/javascript" src="${kpcas}/js/manage/setRole.js"></script>
  
  <body>
    <div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">分配权限</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
        <div id="setRoleSearch" class="left-search">
                <input type="text" placeholder="请输入权限名称或权限编号"/>
                <button class="btn search-btn btn-sm botton-active">搜索</button>
            </div>
        <div>
         <table id="treeTable" class="treeTable" style="width:100%;">
         	<thead>
         		<tr>
         			<th width="25%">
         				<input type="checkbox" id="role-checkAll"/><label for="role-checkAll"></label>
         				<span>权限名称</span>	
         			</th>
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
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="role.setRole()" class="btn botton-active">确定</button>
	</div>
  </body>
