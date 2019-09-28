var roleUser = {
	pageNo : 1,
	pageSize : 10,
	pageCount : 0,
	validation : {
		removeRoleUserFlag : true
	},
	init : function(pageNo, pageSize) {
		var check = $("#role-list tbody tr td input[type=checkbox]:checked");
		var ids = new Array();
		check.each(function() {
					ids.push($(this).val());
				});
		$.ajax({
			url : kpcas + '/role/roleUser',
			type : "POST",
			data : {
				"ids" : ids,
				"pageNo" : pageNo,
				"pageSize" : pageSize
			},
			dataType : "json",
			success : function(data) {
				data = FastJson.format(data);
				if (data.result == '0') {
					// i表示在data中的索引位置，n表示包含的信息的对象
					var table = '';
					$.each(data.list, function(i, n) {
								table += '<tr data-state="'
										+ n.state
										+ '" '
										+ (i % 2 == 0
												? "class=\"list-bg\""
												: "")
										+ '>'
										+ '<td>'
										+ n.refUserRoles[0].systemRole.name
										+ '</td><td>'
										+ n.name
										+ '</td><td>'
										+ n.user.name
										+ '</td>'
										+ '<td><a onclick="roleUser.removeRoleUser(\''
										+ n.refUserRoles[0].arId
										+ '\');">移除</a></td></tr>';
							});
					$("#roleUserList tbody").html(table);
					roleUser.pageCount = data.pageCount;
					$("#roleUserPage").createPage({
								pageCount : roleUser.pageCount,
								current : roleUser.pageNo,
								backFn : function(p) {
									roleUser.pageNo = p;
									roleUser.init(p, roleUser.pageSize);
								}
							});
				} else if (data.result == '1') {

				} else if (data.result == '99') {

				}
			},
			error : function(data) {
				ssoindex.alert(data.msg);
			}
		});
	},
	removeRoleUser : function(arId) {
		if (roleUser.validation.removeRoleUserFlag) {
			roleUser.validation.removeRoleUserFlag = false;
		} else {
			ssoindex.alert("正在处理中，请勿重复提交！");
			return false;
		}
		$.ajax({
				url : kpcas + '/role/removeRoleUser',
				type : 'POST',
				data : {
					"id" : arId
				},
				dataType : 'json',
				success : function(data) {
					if (data.result == '0') {
						ssoindex.alert(data.msg);
						roleUser.init(roleUser.pageNo, roleUser.pageSize);
					} else {
						ssoindex.alert(data.msg);
					}
				},
				error : function() {
					ssoindex.alert("操作失败！");
				},
				complete : function() {
					roleUser.validation.removeRoleUserFlag = true;
				}
			});
	}
};
$(function() {
	roleUser.init(roleUser.pageNo, roleUser.pageSize);
	ssoindex.checkAll($("#roleUserList"), null);
	$("#roleUserPaging select").on("change", function() {
		var value = $('#roleUserPaging select option:selected')
				.val();
		roleUser.pageSize = value;
		roleUser.pageNo = 1;
		roleUser.init(roleUser.pageNo, roleUser.pageSize);
	});
});