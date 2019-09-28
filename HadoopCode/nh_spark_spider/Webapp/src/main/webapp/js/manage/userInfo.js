var userInfo = {
	validation : {
		savePwdFlag : true,
		saveInfoFlag : true
	},
	savePwd : function() {
		//debugger
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var confirmPwd = $("#confirmPwd").val();
		if (!oldPwd || !newPwd || !confirmPwd) {
			ssoindex.alert2("请填写完整的密码！");
			return;
		} else if (newPwd != confirmPwd) {
			ssoindex.alert2("确认密码不符合！");
			return;
		}
		var re = '^[0-9a-zA-Z_#]{6,18}$';
		var result = newPwd.match(re);
		if (!result) {
			ssoindex.alert2("密码为6-18位字符，字母区分大小写，不带特殊符号！");
			return;
		}
		// 密码加密
		var key = RSAUtils.getKeyPair(editPwd.pwdRSA_EXPONENT, '',
				editPwd.pwdRSA_MODULES);
		oldPwd = RSAUtils.encryptedString(key, oldPwd);
		newPwd = RSAUtils.encryptedString(key, newPwd);
		if (userInfo.validation.savePwdFlag) {
			userInfo.validation.savePwdFlag = false;
		} else {
			ssoindex.alert("正在处理中，请勿重复提交！");
			return false;
		}
		// 保存修改密码
		$.ajax({
					url : kpcas + '/user/modifyPassword/pwdkey/'
							+ editPwd.pwdKEY + '/oldPassword/' + oldPwd
							+ '/newPassword/' + newPwd,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.result == '0') {
							ssoindex.modal_close();
							ssoindex.alert2("修改成功！");
							setInterval("location.reload()",3000);
						} else {
							ssoindex.alert2(data.msg);
						}
					},
					error : function() {
						ssoindex.alert2("提交失败！");
					},
					complete : function() {
						userInfo.validation.savePwdFlag = true;
					}
				});
	},
	saveInfo : function() {
		var userName = $("#userName").val().replace(/\s+/g, "");
		var userQq = $("#userQq").val().replace(/\s+/g, "");
		var userPhone = $("#userPhone").val().replace(/\s+/g, "");
		var userWebchat = $("#userWebchat").val().replace(/\s+/g, "");
		var userEmail = $("#userEmail").val().replace(/\s+/g, "");
		if (!userName || !userPhone || !userEmail) {
			ssoindex.alert("请填写必填信息！");
			return false;
		}
		if (!ssoindex.validation(userName)) {
			return false;
		}
		if (userName && (userName.length < 1 || userName.length > 64)) {
			ssoindex.alert("用户名应为1-64位的字符！");
			return false;
		}
		if (!(/^1(3|4|5|7|8)\d{9}$/.test(userPhone) || !/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/
				.test(userPhone))) {
			ssoindex.alert("联系电话格式不符合！");
			return false;
		}
		if (!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(userEmail)) {
			ssoindex.alert("邮箱格式不符合！");
			return false;
		}
		if (userWebchat) {
			if (!/^[a-zA-z]\w{3,}$/.test(userWebchat)) {
				ssoindex.alert("微信格式不符合！");
				return false;
			}
		}
		if (userQq) {
			if (!/^\d{3,20}$/.test(userQq)) {
				ssoindex.alert("QQ格式不符合！");
				return false;
			}
		}
		if (userInfo.validation.saveInfoFlag) {
			userInfo.validation.saveInfoFlag = false;
		} else {
			ssoindex.alert("正在处理中，请勿重复提交！");
			return false;
		}
		$.ajax({
					url : kpcas + '/userInfo/modifyUser?name='
							+ encodeURI(userName) + '&qq=' + userQq
							+ '&webchat=' + userWebchat + '&tel=' + userPhone
							+ '&email=' + userEmail,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.result == '0') {
							ssoindex.alert(data.msg);
						} else {
							ssoindex.alert(data.msg);
						}
					},
					error : function() {
						ssoindex.alert("提交失败！");
					},
					complete : function() {
						userInfo.validation.saveInfoFlag = true;
					}
				});
	}
};

$(function() {
			ssoindex.inputAlert($("#userName"));
			ssoindex.inputAlert($("#userPhone"));
			ssoindex.inputAlert($("#userEmail"));
			$("#editPwd").on("click", function() {
						var url = kpcas + '/base/editPwdView';
						ssoindex.modal(url, 504, 346, 150);
					});
			$("#saveInfo").on("click", function() {
						userInfo.saveInfo();
					});
		});