var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	async : {
		enable : true,
		url : kpcas + "/deptManage/loadDeptTree",
		autoParam : ["id", "level"]
	},
	check : {
		chkboxType : {
			"Y" : "",
			"N" : ""
		},
		enable : true
	},
	callback : {
		onClick : function(event, treeId, treeNode, clickFlag) {

		},
		onCheck : function(event, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("deptTree");
			var nodes = treeObj.getCheckedNodes(true);
			var deptIds = new Array();
			for (var i = 0; i < nodes.length; i++) {
				deptIds.push(nodes[i]['name']);
			}
			$("#dept").val(deptIds.join(","));
		}
	}

};

function showMenu() {
	$("#menuContent").slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
	$("#menuContent").css("overflow", "auto");
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);

}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target)
			.parents("#menuContent").length > 0)) {
		hideMenu();
	}

}

function validation(p, msg) {
	var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	if (pattern.test(p)) {
		ssoindex.alert(msg);
		return false;
	}
	return true;
}

var addUserFlag = true;
function addUserFn() {
	var userAccount = $("#userAccount").val().replace(/\s+/g, "");
	var userName = $("#userName").val().replace(/\s+/g, "");
	var phone = $("#phone").val().replace(/\s+/g, "");
	var email = $("#email").val().replace(/\s+/g, "");
	var userAccountMsg = "账号为1-64位的字符, 不能为空且不能含有特殊符号！";
	var userNameMsg = "姓名应为1-64位的字符, 不能为空且不能含有特殊符号！";

	if ((!validation(userAccount, userAccountMsg))
			|| (!ssoindex.validation(userName, userAccountMsg))) {
		return false;
	}

	if (!userAccount || (userAccount.length < 1 || userAccount.length > 64)) {
		ssoindex.alert(userAccountMsg);
		return false;
	}
	if (!userName || (userName.length < 1 || userName.length > 64)) {
		ssoindex.alert(userNameMsg);
		return false;
	}
	if (!(/^1(3|4|5|7|8)\d{9}$/.test(phone) || /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/
			.test(phone))) {
		ssoindex.alert("联系电话格式不符合！");
		return false;
	}
	if (!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(email)) {
		ssoindex.alert("邮箱格式不符合！");
		return false;
	}
	if (!userAccount || !userName || !phone || !email) {
		ssoindex.alert("请输入完整的个人信息！");
		return;
	}
	// 防止重复提交
	if (addUserFlag) {
		addUserFlag = false;
	} else {
		ssoindex.alert("正在处理中，请勿重复提交！");
		return false;
	}
	$.ajax({
			url : kpcas + '/user/newUser',
			type : 'post',
			dataType : 'json',
			data : {
				"account" : userAccount,
				"name" : userName,
				"tel" : phone,
				"email" : email
			},
			success : function(data) {
				if (data.result == '0') {
					ssoindex.modal_close();
					// 刷新页面
					userList.reload();
				}
				ssoindex.alert(data.msg);

			},
			error : function() {
				ssoindex.alert("提交失败");
			},
			complete : function() {
				addUserFlag = true;
			}
		});

}
