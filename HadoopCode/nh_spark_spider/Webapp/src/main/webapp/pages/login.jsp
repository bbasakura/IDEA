<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
	<meta charset="utf-8" />
	<title>登录</title>
	<jsp:include page="common/common.jsp" />
	<style type="text/css">	
	</style>
	<script>
		
		if (location != top.location){
			top.location = location;
		}
		
		function emptyUserName(){
			$("#userNameInput").val("");
		};
		
		function showOrHiddenPassword(){
			if("password" == $("#passwordInput").attr("type")){
				$("#passwordInput").attr("type","text");
			}else{
				$("#passwordInput").attr("type","password");
			}
		};
		
		function onfocusl(e){
			$('div').removeClass('on');
			$(e).parent('div').addClass('on');
		};
		
		function login(){
			var userName = $("#userNameInput").val();
			debugger
			if(userName == undefined || userName == null || userName == "") {
				$("#errMsg").html("用户名不能为空");
				return false;
			}
			
			cookieUtil.setCookie("userName", userName);
			var pwd = $("#passwordInput").val();
			if(pwd == undefined || pwd == null || pwd == "") {
				$("#errMsg").html("密码不能为空");
				return false;
			}
	
			var key = RSAUtils.getKeyPair('${_RSA_EXPONENT}', '', '${_RSA_MODULES}');
			//对用户名，密码进行加密
			userName = RSAUtils.encryptedString(key, userName);
			pwd = RSAUtils.encryptedString(key, pwd);
			$("#userNameInput").val("");
			$("#userName").val(userName);
			$("#password").val(pwd);
			return true;
		}
	</script>
</head>
<body class="login" style="height: 100%;">
	<div class="login-bg"></div>
	<div class="login-box">
		<div class="login-box-img">
			<img src="${ctx}/themes/default/images/login_01.jpg">
		</div>
		<p class="login-box-text">传智播客反爬虫</p>
		<form method="post" action="${ctx}/auth/logon" onsubmit="return login()">
			<div class="login-name">
				<img src="${ctx}/themes/default/images/login_02.png" >
				<input type="text" id="userNameInput" name="userNameInput" value="${username}" onfocus="onfocusl(this)" placeholder="请输入您的用户名">
				<input type="hidden" name="userName" id="userName"/>
				<input type="hidden" name="source" value="${source }"/>
          		<input type="hidden" name="pwdKey" value="${_RSA_KEY }"/>
          		<input type="hidden" name="rsaExponent" value="${_RSA_EXPONENT }"/>
          		<input type="hidden" name="rsaModules" value="${_RSA_MODULES }"/>
				<span onclick="emptyUserName()"></span>
			</div>
			<div class="login-password">
				<img src="${ctx}/themes/default/images/login_03.png" >
				<input type="password" id="passwordInput" onfocus="onfocusl(this)" placeholder="请输入您的密码">
				<input type="hidden" name="password" id="password"/>
				<span onclick="showOrHiddenPassword()"></span>
			</div>
			<div class="login-title">
				<p id="errMsg">${LOGIN_ERROR_MES }</p>
			</div>
			<div class="login-btn">
				<button class="btn" type="submit">立即登录</button>
			</div>
		</form>
	</div>

</body>
</html>
