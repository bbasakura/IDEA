<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
    	$(function(){
    		var check = $("#treeTable-permiss tbody tr td input[type=checkbox]:checked");
    		if(check&&check.length == 1){
    			var id =  check.parent("td").parent("tr").attr("data-tt-id");
    			$.ajax({
					url : kpcas+'/permission/getPermissionById',
					type : 'GET',
					data : {"id":id},
					dataType : 'json',
					success : function(data){
						if(data.result == '0'){
							$("#name").val(data.per.name);
							$("#number").val(data.per.num);
							$("#url").val(data.per.url);
							$("#description").val(data.per.description);
						}else{
							ssoindex.alert(data.msg);
						}
					},
					error : function(){
						ssoindex.alert("系统错误");
					}
				});
    		}
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
            <span>权限属性</span>
        </div>
        <table class="edit-pwd">
        	<tr>
        		<th>权限名称</th>
        		<td><input id="name" type="text" placeholder="请输入权限名称" /></td>
        	</tr>
        	<tr>
        		<th>权限编号</th>
        		<td><input id="number" type="text" placeholder="请输入权限编号" /></td>
        	</tr>
        	<tr>
        		<th>URL</th>
        		<td><input id="url" type="text" placeholder="请输入URL" /></td>
        	</tr>
        	<tr>
        		<th>权限描述</th>
        		<td><input id="description" type="text" placeholder="请输入权限描述" /></td>
        	</tr>
        </table>     
	</div>
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="permission.editPermiss()" class="btn botton-active">确定</button>
	</div>
  </body>
