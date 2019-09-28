$(function() {
	$.ajax({
		url : kpcas + '/permission/getAllPermission',
		type : 'get',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data.result == '0') {
				var html = '';
				var da = data.perList;
				for (var o in da) {
					if (da[o].parentid == 0 || da[o].parentid == '0') {
						html += "<tr data-tt-id='" + da[o].id + "'>";
						html += "<td><input type='checkbox' id='check"
								+ da[o].id + "'/><label  for='check" + da[o].id
								+ "'></label><span>" + da[o].name
								+ "</span></td>";
						html += "<td>" + da[o].num + "</td>";
						html += "<td>" + da[o].url + "</td>";
						html += "<td>" + da[o].description + "</td>";
						html += "</tr>";
					} else {
						html += "<tr data-tt-id='" + da[o].id
								+ "' data-tt-parent-id='" + da[o].parentid
								+ "'>";
						html += "<td><input type='checkbox' id='check"
								+ da[o].id + "'/><label for='check" + da[o].id
								+ "'></label><span>" + da[o].name
								+ "</span></td>";
						html += "<td>" + da[o].num + "</td>";
						html += "<td>" + da[o].url + "</td>";
						html += "<td>" + da[o].description + "</td>";
						html += "</tr>";
					}
				}
				$("#treeTable tbody").append(html);
				// 以下为初始化表格样式
				var option = {
					expandable : true,
					expandLevel : 2
				};
				$('#treeTable').treetable(option);
				var id1 = $("#role-list tbody tr td input[type=checkbox]:checked")
						.val();
				$.ajax({
							url : kpcas + '/role/getRolePermission',
							type : 'POST',
							data : {
								"id" : id1
							},
							dataType : 'json',
							success : function(data) {
								if (data.result == '0') {
									$.each(data.perList, function(i, n) {
												$("#treeTable")
														.find("tr[data-tt-id='"
																+ n.id + "']")
														.find("td")
														.find("input[type=checkbox]")
														.attr("checked",
																"checked");
											});
								} else {
									ssoindex.alert(data.msg);
								}
							},
							error : function() {
								ssoindex.alert("系统错误！");
							}
						});
			} else if (data.result == '99') {
				ssoindex.alert(data.msg);
			}
		},
		error : function(data) {
			ssoindex.alert("操作失败！");
		}
	});

	$("#treeTable tbody").on("mousedown", "tr", function() {
				$(".selected").not(this).removeClass("selected");
				$(this).toggleClass("selected");
			});
	$("#treeTable tbody").on("click", "tr td input[type='checkbox']",
			function() {
				var checkFlag = $(this).attr("checked") || false;
				var ttId = $(this).parent('td').parent('tr').attr("data-tt-id");
				var childNode = $("#treeTable").treetable("getChildNode", ttId);
				if (childNode != null && childNode.length > 0) {
					for (var i = 0; i < childNode.length; i++) {
						$("#check" + childNode[i].id)
								.attr("checked", checkFlag);
					}
				}
				var parentNode = $("#treeTable").treetable("getParentNode",
						ttId);
				if (parentNode != null && parentNode.length > 0) {
					for (var i = 0; i < parentNode.length; i++) {
						$("#check" + parentNode[i].id).attr("checked",
								"checked");
					}
				}
			});
	ssoindex.checkAll($("#treeTable"), null);
	$("#setRoleSearch button").on("click", function() {
		var seaStr = $("#setRoleSearch input").val();
		seaStr = seaStr.replace(/\s+/g, "");
		if (seaStr) {
			$.ajax({
					url : kpcas + '/permission/searchPermission',
					type : 'get',
					dataType : 'json',
					data : {
						"seaStr" : seaStr
					},
					success : function(data) {
						if (data.result == '0') {
							$(".select-bg").removeClass("select-bg");
							$.each(data.ttIds, function(i, n) {
										$("#treeTable").treetable("reveal",
												n);
										$("#treeTable tr[data-tt-id=" + n
												+ "]")
												.addClass("select-bg");
									});
						} else if (data.result == '99') {
							ssoindex.alert(data.msg);
						}
					},
					error : function(data) {
						ssoindex.alert("操作失败！");
					}
				});
		}
	});

});