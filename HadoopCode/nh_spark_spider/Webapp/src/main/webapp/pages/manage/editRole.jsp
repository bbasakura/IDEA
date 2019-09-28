<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
    	$(function(){
    		var roleId = $("#role-list tbody tr td input[type=checkbox]:checked").val();
    		$.ajax({
				url :  '${kpcas}/role/getRoleById',
				type : 'GET',
				data : {"roleId":roleId},
				dataType : 'json',
				success : function(data){
					if(data.result == '0'){
						$("#roleId").val(roleId);
						$("#name").val(data.systemRole.name);
						$("#cnname").val(data.systemRole.cnname);
						$("#description").val(data.systemRole.description);
					}
					
				},
				error : function(data){
					
				}
			});
    	})
    </script>
  
  <body>
    <div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">修改</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
        <div class="modal-b-title">
            <span>基本属性</span>
        </div>
        <table class="edit-pwd">
        	<tr>
        		<th>角色名称</th>
        		<td><input id="name" type="text" placeholder="请输入角色名称" /></td>
        	</tr>
        	<tr>
        		<th>中文名称</th>
        		<td><input id="cnname" type="text" placeholder="请输入中文名称" /></td>
        	</tr>
        	<tr>
        		<th>角色描述</th>
        		<td><input id="description" type="text" placeholder="请输入角色描述" /></td>
        	</tr>
        </table>     

		<input id="roleId" type="hidden" />
	</div>
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="role.editRole()" class="btn botton-active">确定</button>
	</div>
  </body>
