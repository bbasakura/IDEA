var editPwd = {
	pwdKEY : '',
	pwdRSA_EXPONENT : '',
	pwdRSA_MODULES : '',
	getKey : function(){
		$.ajax({
			url : kpcas+'/auth/login/key',
			type : 'POST',
			dataType : 'json',
			success : function(data){
				editPwd.pwdKEY = data._RSA_KEY;
				editPwd.pwdRSA_EXPONENT = data._RSA_EXPONENT;
				editPwd.pwdRSA_MODULES = data._RSA_MODULES;
			},
			error : function(){
				ssoindex.alert("秘钥获取失败");
			}
		});
	}
};
$(function(){
	$("#edit-pwd-account td").text(account);
	editPwd.getKey();
	ssoindex.inputAlert($("#oldPwd"));
	ssoindex.inputAlert($("#newPwd"));
	ssoindex.inputAlert($("#confirmPwd"));
});
