<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
    <script type="text/javascript" src="${kpcas}/js/manage/roleUser.js"></script>
  <body>
    <div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">角色用户</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
		<table id="roleUserList"  class="user-list">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>用户名</th>
					<th>姓名</th>
					<!-- <th>部门</th> -->
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div class="page-right">
			<div id="roleUserPaging" class="content-page">
				<select  name="pageSize">
					<option value="10">10条/页</option>
					<option value="20">20条/页</option>
					<option value="50">50条/页</option>
				</select>
			</div>
			<div id="roleUserPage" class="tcdPageCode"></div>
		</div>

	</div>
	<div class="content-botton-list content-list">
		<button type="button"  class="btn botton-active" data-dismiss="modal">确定</button>
	</div>
  </body>
