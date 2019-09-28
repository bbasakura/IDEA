var userList = {
	pageNo : 1,
	pageSize : 10,
	pageCount : 0,
	validation : {
		delUserFlag : true
	},
	init : function(pageNo, pageSize) {
		var seaStr = $("#user-search input").val();
		$.ajax({
			url : kpcas + '/user/getAllUser',
			type : "GET",
			data : {
				"seaStr" : seaStr,
				"pageNo" : pageNo,
				"pageSize" : pageSize
			},
			dataType : "json",
			success : function(data) {
				data = FastJson.format(data);
				if (data.result == '0') {
					// i表示在data中的索引位置，n表示包含的信息的对象
					var table = '';
					$.each(data.userList, function(i, n) {
								var roleStr = new Array();
								$.each(n.refUserRoles, function(i, n) {
									roleStr.push(n.systemRole.cnname);
								});
								roleStr = roleStr.join(",");
								table += '<tr data-state="'
										+ "1"
										+ '" '
										+ (i % 2 == 0
												? "class=\"list-bg\""
												: "")
										+ '><td><input type="checkbox" id="checkUser'
										+ i + '" name="checkUser" value="'
										+ n.id + '"/><label for="checkUser' + i
										+ '"></label></td>' + '<td>' + n.name
										+ '</td><td>' + n.user.name
										+ '</td><td>' + n.user.email + '</td>'
										+ '<td>' + n.user.mobile + '</td><td>'
										+ roleStr + '</td>' + '<td>'
										+ n.lastLogTime + '</td></tr>';
							});
					$("#user-list tbody").html(table);
					userList.pageCount = data.pageCount;
					$("#userPage").createPage({
								pageCount : userList.pageCount,
								current : userList.pageNo,
								backFn : function(p) {
									userList.pageNo = p;
									userList.init(userList.pageNo,
											userList.pageSize);
								}
							});
					userList.initButton();
					$("#user-list thead tr th input[type=checkbox]").prop(
							"checked", false);
				} else if (data.result == '1') {
					ssoindex.alert(data.msg);
				} else if (data.result == '99') {
					ssoindex.alert(data.msg);
				}
			},
			error : function(data) {
				ssoindex.alert(data.msg);
			}
		});
	},
	delUser : function() {
		var list = new Array();
		$("#user-list tbody input[type=checkbox]:checked").each(function(i) {
					list.push($(this).val());
				});
		if (userList.validation.delUserFlag) {
			userList.validation.delUserFlag = false;
		} else {
			ssoindex.alert("正在处理中，请勿重复提交！");
			return false;
		}
		$.ajax({
					url : kpcas + '/user/delUser',
					type : 'POST',
					data : {
						"ids" : list
					},
					dataType : 'json',
					success : function(data) {
						if (data.result == '0') {
							$("#modal-confirm").modal('hide');
							// 刷新页面
							userList.reload();
						}
						ssoindex.alert(data.msg);
					},
					error : function() {
						ssoindex.alert("系统错误!");
					},
					complete : function() {
						userList.validation.delUserFlag = true;
					}
				});
	},
	addRole : function() {
		var list = new Array();
		$("#add-role tbody tr td input[type=checkbox]:checked").each(
				function(i) {
					list.push($(this).val());
				});
		var ids = new Array();
		$("#user-list tbody input[type=checkbox]:checked").each(function() {
					ids.push($(this).val());
				});
		$.ajax({
					url : kpcas + '/user/configRole',
					type : 'POST',
					data : {
						"list" : list,
						"ids" : ids
					},
					dataType : 'json',
					success : function(data) {
						if (data.result == '0') {
							ssoindex.modal_close();
							// 刷新页面
							userList.reload();
						}
						ssoindex.alert(data.msg);
					},
					error : function() {
						ssoindex.alert("系统错误!");
					}
				});
	},
	isTimeout : function(date) {
		var day30 = 30 * 24 * 60 * 60 * 1000;
		var now = new Date();
		date.setTime(date.getTime() + day30);
		if (date.getTime() >= now.getTime()) {
			return 1;// 不超时
		} else {
			return 0;// 超时
		}
	},
	initButton : function() {
		var check = $("#user-list tbody tr td input[type=checkbox]:checked");
		if (check) {
			if (check.length == 0) {
				userList.removeBtn();
			} else {
				userList.removeBtn();
				$("#addRole").addClass("botton-active");
				$("#resetPwd").addClass("botton-active");
				$("#delUser").addClass("botton-active");
			}
		}
	},
	removeBtn : function() {
		$("#delUser").removeClass("botton-active");
		$("#addRole").removeClass("botton-active");
		$("#resetPwd").removeClass("botton-active");
	},
	resetPwd : function() {
		var check = $("#user-list tbody tr td input[type=checkbox]:checked");
		var ids = new Array();
		check.each(function() {
					ids.push($(this).val());
				});
		$.ajax({
					url : kpcas + '/user/resetPwd',
					type : 'POST',
					data : {
						"ids" : ids
					},
					dataType : 'json',
					success : function(data) {
						if (data.result == '0') {
							ssoindex.alert(data.msg);
							ssoindex.modal_close();
						} else {
							ssoindex.alert(data, msg);
						}
					},
					error : function() {
						ssoindex.alert("系统错误");
					}
				});
	},
	ableUser : function() {
		var check = $("#user-list tbody tr td input[type=checkbox]:checked");
		if (check) {
			if (check.length > 0) {
				var ids = new Array();
				check.each(function() {

					if ($(this).parent().parent("tr").attr("data-state") != '1') {
						ssoindex.alert("所选角色已是启用状态！");
						return;
					}
					ids.push($(this).val());
				});
				$.ajax({
							url : kpcas + '/userManage/ableUser',
							type : 'POST',
							data : {
								"flag" : "0",
								"ids" : ids
							},
							dataType : 'json',
							success : function() {
								ssoindex.alert("操作成功！");
								userList.reload();
							},
							error : function(data) {
								ssoindex.alert(data.msg);
							}
						});
			}
		}
	},
	reload : function() {
		userList.init(userList.pageNo, $('#userPaging select option:selected')
						.val());
	}
};

$(function() {
			userList.init(userList.pageNo, userList.pageSize);
			ssoindex.checkAll($("#user-list"), userList.initButton);
			$("#userPaging select").on("change", function() {
						var value = $('#userPaging select option:selected')
								.val();
						userList.pageSize = value;
						userList.pageNo = 1;
						userList.init(userList.pageNo, userList.pageSize);
					});
			$("#addUser").on("click", function() {
						if ($(this).hasClass("botton-active")) {
							var url = kpcas + '/base/addUser';
							ssoindex.modal(url, 690, 408, 75);
						}
					});
			$("#delUser").on("click", function() {
						if ($(this).hasClass("botton-active")) {
							ssoindex.confirm("确定删除该用户吗？", userList.delUser);
						}
					});
			$("#addRole").on("click", function() {
						if ($(this).hasClass("botton-active")) {
							var url = kpcas + '/base/addRole';
							ssoindex.modal(url, 700, 480, 50);
						}
					});
			$("#resetPwd").on("click", function() {
						if ($(this).hasClass("botton-active")) {
							var url = kpcas + '/base/resetPwd';
							ssoindex.modal(url, 350, 200, 150);
						}
					});
			$("#user-list tbody").on("change", "input[type=checkbox]",
					function() {
						userList.initButton();
					});
			$("#userSearchBtn").on("click", function() {
						userList.init(userList.pageNo, userList.pageSize);
					});
		});