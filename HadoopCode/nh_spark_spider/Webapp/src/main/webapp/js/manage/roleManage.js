var role = {
	pageNo : 1,
	pageSize : 10,
	pageCount : 0,
	validation : {
		newRoleFlag : true,
		editRoleFlag : true,
		delRoleFlag : true
	},
	init : function(pageNo,pageSize){
		var seaStr = $("#roleSearch").val();
		$.ajax({
            url : kpcas+'/role/getAllRole',
            type : "get",
            data : {"seaStr" : seaStr,"pageNo":pageNo,"pageSize":pageSize},
            contentType : "application/json;charset=utf-8",
            dataType : "json",
            success : function(data){
            	if(data.result == '0'){
            		//i表示在data中的索引位置，n表示包含的信息的对象
            		var table = '';
                	$.each(data.roleList,function(i,n){
                		table += '<tr data-state="'+n.state+'" '+(i%2 == 0? "class=\"list-bg\"" : "")+'><td><input type="checkbox" id="checkRole'+i+'" name="checkRole" value="'+n.id+'"/><label for="checkRole'+i+'"></label></td>'
                			  +'<td>'+n.name+'</td><td>'+n.cnname+'</td><td>'+n.description+'</td>'
                			  +'<td>'+n.createtime+'</td></tr>';
                	});
                	$("#role-list tbody").html(table);
                	role.pageCount = data.pageCount;
                	// 刷新按钮样式
					role.initButton();
					$("#rolePage").createPage({
						pageCount : role.pageCount,
						current : role.pageNo,
						backFn : function(p) {
							role.pageNo = p;
							role.init(p, role.pageSize);
						}
					});
					$("#role-list thead tr th input[type=checkbox]").prop("checked",false);
            	}else if(data.result == '99'){
            		ssoindex.alert("系统错误");
            	}
            },
            error : function(data){
            	ssoindex.alert("操作失败");
            }
		});
	},
	reload : function(){
		role.init(role.pageNo,$('.content-page select option:selected').val());
	},
	newRole : function(){
		var name = $("#name").val();
		var cnname = $("#cnname").val();
		var description = $("#description").val();
		name = name.replace(/\s+/g,"");
		cnname = cnname.replace(/\s+/g,"");
		description = description.replace(/\s+/g,"");
		if(!name||!cnname){
			ssoindex.alert("请完整填写信息！");
			return;
		}
		
		if(name.length > 64 || /[`~!@#$%^&*()_+<>?:"{}？“”‘’、【】\\\,.\/;'[\]]/im.test(name)){
			ssoindex.alert("角色名称应为1-64位字符，不包含特殊符号！");
			return false;
		}
		if(!/[\u4e00-\u9fa5]{1,10}/.test(cnname)){
			ssoindex.alert("中文名称应为1-10位中文字符！");
			return false;
		}
		if (role.validation.newRoleFlag) {
			role.validation.newRoleFlag = false;
		} else {
			ssoindex.alert("正在处理中，请勿重复提交！");
			return false;
		}
		$.ajax({
			url : kpcas+'/role/newRole',
			type : 'post',
			data : {"name":name,"cnname":cnname,"description":description},
			dataType : 'json',
			success : function(data){
				if(data.result == '0'){
					ssoindex.alert("操作成功！");
					role.reload();
				}else if(data.result == '99'){
					ssoindex.alert(data.msg);
				}
				ssoindex.modal_close();
			},
			error : function(data){
				ssoindex.alert("操作失败！");
			},
			complete : function(){
				role.validation.newRoleFlag = true;
			}
		});
	},
	editRole : function(){
		var name = $("#name").val();
		var cnname = $("#cnname").val();
		var description = $("#description").val();
		name = name.replace(/\s+/g,"");
		cnname = cnname.replace(/\s+/g,"");
		description = description.replace(/\s+/g,"");
		if(!name||!cnname){
			ssoindex.alert("请完整填写信息");
			return;
		}

		if(name.length > 64 || /[`~!@#$%^&*()_+<>?:"{}？“”‘’、【】\\\,.\/;'[\]]/im.test(name)){
			ssoindex.alert("角色名称应为1-64位字符，不包含特殊符号！");
			return false;
		}
		if(!/[\u4e00-\u9fa5]{1,10}/.test(cnname)){
			ssoindex.alert("中文名称应为1-10位中文字符！");
			return false;
		}
		var roleId = $("#roleId").val();
		if (role.validation.editRoleFlag) {
			role.validation.editRoleFlag = false;
		} else {
			ssoindex.alert("正在处理中，请勿重复提交！");
			return false;
		}
		$.ajax({
			url : kpcas+'/role/editRole',
			type : 'post',
			data : {"id":roleId,"name":name,"cnname":cnname,"description":description},
			dataType : 'json',
			success : function(data){
				if(data.result == '0'){
					ssoindex.alert("操作成功！");
					ssoindex.modal_close();
					role.reload();
				}else if(data.result == '99'){
					ssoindex.alert(data.msg);
				}
			},
			error : function(data){
				ssoindex.alert("操作失败！");
			},
			complete : function(){
				role.validation.editRoleFlag = true;
			}
		});
	},
	initButton : function(){
		var check = $("#role-list tbody tr td input[type=checkbox]:checked");
		if(check){
			if(check.length == 0){
				role.removeBtn();
			}else if(check.length == 1){
				role.removeBtn();
				$("#editRole").addClass("botton-active");
				$("#setRole").addClass("botton-active");
				$("#delRole").addClass("botton-active");
				$("#ableRole").addClass("botton-active");
				$("#disableRole").addClass("botton-active");
				$("#roleUser").addClass("botton-active");
			}else if(check.length > 1){
				role.removeBtn();
				$("#delRole").addClass("botton-active");
				$("#ableRole").addClass("botton-active");
				$("#disableRole").addClass("botton-active");
				$("#roleUser").addClass("botton-active");
			}
		}
	},
	removeBtn : function(){
		$("#editRole").removeClass("botton-active");
		$("#setRole").removeClass("botton-active");
		$("#delRole").removeClass("botton-active");
		$("#ableRole").removeClass("botton-active");
		$("#disableRole").removeClass("botton-active");
		$("#roleUser").removeClass("botton-active");
	},
	delRole : function(){
		var check = $("#role-list tbody tr td input[type=checkbox]:checked");
		if(check){
			if(check.length > 0){
				var ids = new Array();
				check.each(function(){
					ids.push($(this).val());
				});
				if (role.validation.delRoleFlag) {
					role.validation.delRoleFlag = false;
				} else {
					ssoindex.alert("正在处理中，请勿重复提交！");
					return false;
				}
				$.ajax({
					url : kpcas+'/role/delRole',
					type : 'POST',
					data : {"ids":ids},
					dataType : 'json',
					success : function(){
						$("#modal-confirm").modal("hide");
						ssoindex.alert("操作成功！");
						role.reload();
					},
					error : function(data){
						ssoindex.alert(data.msg);
					},
					complete : function(){
						role.validation.delRoleFlag = true;
					}
				});
			}
		}
	},
	ableRole : function(){
		var check = $("#role-list tbody tr td input[type=checkbox]:checked");
			if(check){
				if(check.length > 0){
					var ids = new Array();
					check.each(function(){
						if($(this).parent().parent("tr").attr("data-state") != '1'){
							ssoindex.alert("所选角色已是启用状态！");
							return;
						}
						ids.push($(this).val());
					});
					$.ajax({
						url : kpcas + '/roleManage/able',
						type : 'POST',
						data : {"type":"0","ids":ids},
						dataType : 'json',
						success : function() {
							ssoindex.alert("操作成功！");
							role.reload();
						},
						error : function() {

						}
					});
				}
			}
	},
	disableRole : function(){
		var check = $("#role-list tbody tr td input[type=checkbox]:checked");
			if(check){
				if(check.length > 0){
					var ids = new Array();
					check.each(function(){
						if($(this).parent().parent("tr").attr("data-state") != '0'){
							ssoindex.alert("所选角色已是禁用状态！");
							return;
						}
						ids.push($(this).val());
					});
					$.ajax({
						url : kpcas + '/roleManage/able',
						type : 'POST',
						data : {"type":"1","ids":ids},
						dataType : 'json',
						success : function(data) {
							ssoindex.alert("操作成功！");
							role.reload();
						},
						error : function() {

						}
					});
				}
			}
	},
	setRole : function(){
		var check = $("#treeTable tbody tr td input[type=checkbox]:checked");
		var ids = new Array();
		if(check){
			check.each(function(){
				var parentid = $(this).parent("td").parent("tr").attr("data-tt-id");
				ids.push(parentid);
			});
			var roleId = $("#role-list tbody tr td input[type=checkbox]:checked").val();
			$.ajax({
				url : kpcas+'/role/saveRolePermission',
				type : 'POST',
				dataType : 'json',
				data : {"ids":ids,"roleId":roleId},
				success : function(data){
					if(data.result == '0'){
						ssoindex.alert("操作成功！");
						ssoindex.modal_close();
					}else if(data.result == '99'){
						ssoindex.alert(data.msg);
					}
					
				},
				error : function(){
					ssoindex.alert("操作失败！");
				}
			});
		}	
	}
	
};
$(function(){
	role.init(role.pageNo,role.pageSize);
	ssoindex.checkAll($("#role-list"),role.initButton);
	$("#rolePaging select").on("change",function(){
		var value = $('.content-page select option:selected').val();
		role.pageSize = value;
		role.pageNo = 1;
		role.init(role.pageNo,role.pageSize);
	});
	$("#newRole").on("click",function(){
		if($(this).hasClass("botton-active")){
			var url = kpcas+'/base/newRole';
			ssoindex.modal(url,500,300,150);
		}
	});
	$("#editRole").on("click",function(){
		if($(this).hasClass("botton-active")){
			var url = kpcas+'/base/editRole';
			ssoindex.modal(url,500,300,150);
		}
	});
	//权限分配
	$("#setRole").on("click",function(){
		if($(this).hasClass("botton-active")){
			var url = kpcas+'/base/setRole';
			ssoindex.modal(url,850,550,50);
		}
	});
	$("#delRole").on("click",function(){
		if($(this).hasClass("botton-active")){
			ssoindex.confirm("确定删除所选角色吗？",role.delRole);
		}
	});
	$("#roleUser").on("click",function(){
		if($(this).hasClass("botton-active")){
			var url = kpcas+'/base/roleUser';
			ssoindex.modal(url,800,450,70);
		}
	});
	$("#role-list tbody").on("change","input[type=checkbox]",function(){
		role.initButton();
	});
	$("#roleSearchBtn").on("click",function(){
		role.init(role.pageNo,role.pageSize);
	});
});