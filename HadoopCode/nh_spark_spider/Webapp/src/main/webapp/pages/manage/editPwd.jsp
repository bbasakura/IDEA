<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("kpcas",path);

%>
<script type="text/javascript">
	var kpcas='${kpcas}';
</script>
<script type="text/javascript" src="${kpcas}/js/manage/editPwd.js"></script>
<script type="text/javascript" src="${kpcas}/js/manage/userInfo.js"></script>

  <body>
  
	<div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">修改密码</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
		<table class="edit-pwd">
			<tr id="edit-pwd-account">
				<th>账号名</th>
				<td></td>
			</tr>
			<tr>
				<th><font class="required">*</font>旧密码</th>
				<td><input id="oldPwd" type="password" placeholder="请输入旧密码"/></td>
			</tr>
			<tr>
				<th><font class="required">*</font>新密码</th>
				<td><input id="newPwd" type="password" placeholder="请输入新密码"/></td>
			</tr>
			<tr>
				<th><font class="required">*</font>确认密码</th>
				<td><input id="confirmPwd" type="password" placeholder="请再次输入新密码"/></td>
			</tr>
		</table>

	</div>
	<div class="content-botton-list content-list" >
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="userInfo.savePwd();" class="btn botton-active">保存</button>
	</div>
</body>