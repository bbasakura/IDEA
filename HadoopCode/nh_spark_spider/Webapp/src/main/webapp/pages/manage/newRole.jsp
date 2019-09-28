<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
	 	ssoindex.inputAlert($("#name"));
		ssoindex.inputAlert($("#cnname"));
	})
</script>
  <body>
    <div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">新增</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
        <div class="modal-b-title">
            <span>基本属性</span>
        </div>
        <table class="edit-pwd">
        	<tr>
        		<th><font class="required">*</font>角色名称</th>
        		<td><input id="name" type="text" placeholder="请输入角色名称" /></td>
        	</tr>
        	<tr>
        		<th><font class="required">*</font>中文名称</th>
        		<td><input id="cnname" type="text" placeholder="请输入中文名称" /></td>
        	</tr>
        	<tr>
        		<th>角色描述</th>
        		<td><input id="description" type="text" placeholder="请输入角色描述" /></td>
        	</tr>
        </table>     

	</div>
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="role.newRole()" class="btn botton-active">确定</button>
	</div>
  </body>
