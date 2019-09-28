<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("kpcas",path);	
%>
    <script type="text/javascript" src="${kpcas}/js/manage/addUser.js"></script>

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
		<table class="add-user-info">
			<tr>
				<th><font class="required">*</font>账号</th>
				<td><input type="text" id="userAccount" name="account" placeholder="请输入账号"/></td>
				<!-- <th><font class="required">*</font>性别</th>
				<td>
					<div class="sex-radio"><input type="radio" id="male" name="sex" checked value="1"/><label for="male"></label></div>
					<div class="sex-text">男</div>
					<div class="sex-radio"><input type="radio" id="female" name="sex" value="0"/><label for="female"></label></div>
					<div class="sex-text">女</div>
				</td> -->
				<th><font class="required">*</font>姓名</th>
				<td><input type="text" id="userName" name="userName" placeholder="请输入您的姓名"/></td>
			</tr>
			<tr>
				<!-- <th><font class="required">*</font>姓名</th>
				<td><input type="text" id="userName" name="userName" placeholder="请输入您的姓名"/></td> -->
				<th><font class="required">*</font>联系电话</th>
				<td><input type="text" id="phone" name="phone" placeholder="请输入您的联系电话"/></td>
				<th><font class="required">*</font>邮箱</th>
				<td><input type="text" id="email" name="email" placeholder="请输入您的邮箱"/></td>
			</tr>
			<tr>
				<!-- <th><font class="required">*</font>邮箱</th>
				<td><input type="text" id="email" name="email" placeholder="请输入您的邮箱"/></td> -->
				<!-- <th>所属部门</th>
				<td>
					<input id="dept" type="text" id="dept" name="dept" readonly="true"/>
					<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
					<div id="menuContent"  style="display:none; position: absolute;overflow:auto;width:179px;height:150px;z-index:9999;	background:#fff;border:1px solid #ddd;border-radius:4px;margin-top:2px;">
						<ul id="deptTree" class="ztree treeSelect" style="margin-top:0;"></ul>
					</div>
				</td> -->
			</tr>
		</table>
		<div class="modal-b-title">
            <span>基本属性</span>
        </div>
        <table class="add-user-info">
        	<tr>
        		<th>初始密码</th>
        		<td><input type="text" id="pwd" name="pwd" value="000000" disabled="true"/></td>
        		<!-- <th>密码期限</th>
        		<td>
        			<div class="sex-radio"><input type="checkbox" checked id="pwdTime" name="pwdTime"/><label for="pwdTime"></label></div>
        			<div class="sex-text">30天</div>
        		</td>  -->
        	</tr>
        </table> 
		
	</div>
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="addUserFn();" class="btn botton-active">保存</button>
	</div>

