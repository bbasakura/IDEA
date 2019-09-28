<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  
  <body>
    <div class="modal-header"
		style="background:#0093d0; border-top-left-radius: 6px;border-top-right-radius: 6px;">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title" style="font-size:12px;color:#ffffff;">重置密码</h4>
	</div>
	<div class="modal-body" style="padding:10px 15px;">
        <div class="user-resetpwd">
        	<span>将密码重置为</span>
        	<input type="text" value="000000" disabled="true"/>
        </div>      
	</div>
	<div class="content-botton-list content-list">
		<button type="button" class="btn" data-dismiss="modal">取消</button>
		<button type="button" onclick="userList.resetPwd();" class="btn botton-active">确定</button>
	</div>
  </body>
