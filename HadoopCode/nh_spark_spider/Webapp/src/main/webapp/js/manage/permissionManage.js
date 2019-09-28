var permission = {
	validation : {
		newPermissFlag : true,
		editPermissFlag : true,
		delPermissFlag : true
	},
	init : function(){
		var seaStr = $("#setRoleSearch input").val();
		$.ajax({
			url : kpcas+'/permission/getAllPermission',
			type : 'get',
			dataType : 'json',
			data : {"seaStr" : seaStr},
			success : function(data){
				if (data.result == '0') {
					var html = '';
					var da = data.perList;
					for (var o in da) {
						if (da[o].parentid == 0||da[o].parentid == '0') {
							html += "<tr data-tt-id='" + da[o].id + "' "+(o%2 == 0? "class=\"list-bg\"" : "")+">";
							html += "<td><input type='checkbox' id='check"
									+ da[o].id + "'/><label  for='check"
									+ da[o].id + "'></label><span>"
									+ da[o].name + "</span></td>";
							html += "<td>" + da[o].num + "</td>";
							html += "<td>" + da[o].url + "</td>";
							html += "<td>" + da[o].description + "</td>";
							html += "</tr>";
						} else {
							html += "<tr data-tt-id='" + da[o].id
									+ "' data-tt-parent-id='" + da[o].parentid
									+ "'"+(o%2 == 0? "class=\"list-bg\"" : "")+">";
							html += "<td><input type='checkbox' id='check"
									+ da[o].id + "'/><label for='check"
									+ da[o].id + "'></label><span>"
									+ da[o].name + "</span></td>";
							html += "<td>" + da[o].num + "</td>";
							html += "<td>" + da[o].url + "</td>";
							html += "<td>" + da[o].description + "</td>";
							html += "</tr>";
						}
					}
					$("#treeTable-permiss tbody").html(html);
					//以下为初始化表格样式
					var option = {
						expandable : true,
						expandLevel : 2
					};
					$('#treeTable-permiss').treetable(option);
				}else if(data.result == '99'){
					ssoindex.alert(data.msg);
				}
			},
			error : function(data){
				ssoindex.alert("操作失败！");
			}
		});
	},
	newPermiss : function(){
		var check = $("#treeTable-permiss tbody tr td input[type=checkbox]:checked");
		if(check){
				var parentid = check.parent("td").parent("tr").attr("data-tt-id");
				if(!parentid){
					parentid = '';
				}
				var name = $("#name").val();
				var number = $("#number").val();
				var url = $("#url").val();
				var description = $("#description").val();
				
				name = name.replace(/\s+/g,"");
				number = number.replace(/\s+/g, "");
				if (!name) {
					ssoindex.alert("请输入权限名称！");
					return;
				}
				
				if (!number) {
					ssoindex.alert("请输入权限编号！");
					return;
				}

				if(name.length > 64 || /[`~!@#$%^&*()_+<>?:"{}？“”‘’、【】\\\,.\/;'[\]]/im.test(name)){
					ssoindex.alert("权限名称应为1-64位字符，不包含特殊符号！");
					return false;
				}
				if (permission.validation.newPermissFlag) {
					permission.validation.newPermissFlag = false;
				} else {
					ssoindex.alert("正在处理中，请勿重复提交！");
					return false;
				}
				$.ajax({
					url : kpcas+'/permission/newPermission',
					type : 'post',
					dataType : 'json',
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					data : {"parentid":parentid,"name":name,"number":number,"url":url,"desc":description},
					success : function(data){
						if(data.result == '0'){
							ssoindex.alert(data.msg);
							ssoindex.modal_close();
							permission.reload();
						}else{
							ssoindex.alert(data.msg);
						}
					},
					error : function(){
						ssoindex.alert("操作失败");
					},
					complete : function(){
						permission.validation.newPermissFlag = true;
					}
				});
			
		}
	},
	editPermiss : function(){
		var check = $("#treeTable-permiss tbody tr td input[type=checkbox]:checked");
		if(check){
			if(check.length == 1){
				var parentid = check.parent("td").parent("tr").attr("data-tt-id");
				var name = $("#name").val();
				var number = $("#number").val();
				var url = $("#url").val();
				var description = $("#description").val();
				name = name.replace(/\s+/g,"");
				number = number.replace(/\s+/g, "");
				if (!name) {
					ssoindex.alert("请输入权限名称！");
					return;
				}
				if(name.length > 64 || /[`~!@#$%^&*()_+<>?:"{}？“”‘’、【】\\\,.\/;'[\]]/im.test(name)){
					ssoindex.alert("权限名称应为1-64位字符，不包含特殊符号！");
					return false;
				}
				if (!number) {
					ssoindex.alert("请输入权限编码！");
					return;
				}
				if (permission.validation.editPermissFlag) {
					permission.validation.editPermissFlag = false;
				} else {
					ssoindex.alert("正在处理中，请勿重复提交！");
					return false;
				}
				$.ajax({
					url : kpcas+'/permission/modifyPermission',
					type : 'POST',
					dataType : 'json',
					data : {"id":parentid,"name":name,"number":number,"url":url,"desc":description},
					success : function(data){
						if(data.result == '0'){
							ssoindex.alert(data.msg);
							ssoindex.modal_close();
							permission.reload();
						}else{
							ssoindex.alert(data.msg);
						}
					},
					error : function(){
						ssoindex.alert("操作失败");
					},
					complete : function(){
						permission.validation.editPermissFlag = true;
					}
				});
			}else{
				ssoindex.alert("请勾选一个权限进行操作！");
				return;
			}
		}
	},
	delPermiss : function(){
		var check = $("#treeTable-permiss tbody tr td input[type=checkbox]:checked");
		if(check){
			if(check.length > 0){
				var ids = new Array();
				check.each(function(){
					var id = $(this).parent("td").parent("tr").attr("data-tt-id");
					ids.push(id);
				});
				if (permission.validation.delPermissFlag) {
					permission.validation.delPermissFlag = false;
				} else {
					ssoindex.alert("正在处理中，请勿重复提交！");
					return false;
				}
				$.ajax({
					url : kpcas+'/permission/delPermission',
					type : 'POST',
					data : {"ids":ids},
					dataType : 'json',
					success : function(data){
						if(data.result == '0'){
							ssoindex.alert(data.msg);
							permission.reload();
							$("#modal-confirm").modal("hide");
						}else{
							ssoindex.alert(data.msg);
						}
					},
					error : function(){
						ssoindex.alert("操作失败");
					},
					complete : function(){
						permission.validation.delPermissFlag = true;
					}
				});
			}else{
				ssoindex.alert("请勾选一个权限进行操作！");
				return;
			}
		}
	},
	initButton : function(){
		var check = $("#treeTable-permiss tbody tr td input[type=checkbox]:checked");
		if(check){
			if(check.length == 0){
				permission.removeBtn();
			}else if(check.length == 1){
				permission.removeBtn();
				$("#editPermiss").addClass("botton-active");
				$("#delPermiss").addClass("botton-active");
			}else if(check.length > 1){
				permission.removeBtn();
				$("#delPermiss").addClass("botton-active");
			}
		}
	},
	removeBtn : function(){
		$("#editPermiss").removeClass("botton-active");
		$("#delPermiss").removeClass("botton-active");
	},
	reload : function(){
		var url = kpcas+'/base/permissManage';
		$("#permissManageBody").html('');
		$("#permissManageBody").load(url);
	}
	
};
$(function(){
	permission.init();
    
	$("#treeTable-permiss tbody").on("mousedown", "tr", function() {
		$(".selected").not(this).removeClass("selected");
		$(this).toggleClass("selected");
	});
	$("#permissionSearch button").on("click",function(){
		var seaStr = $("#permissionSearch input").val();
		seaStr=seaStr.replace(/\s+/g,"");
		if(seaStr){
			$.ajax({
				url : kpcas+'/permission/searchPermission',
				type : 'get',
				dataType : 'json',
				data : {"seaStr" : seaStr},
				success : function(data){
					if(data.result == '0'){
						$(".select-bg").removeClass("select-bg");
						$.each(data.ttIds,function(i,n){
							$("#treeTable-permiss").treetable("reveal",n);
							$("#treeTable-permiss tr[data-tt-id="+n+"]").addClass("select-bg");
						});
					}else if(data.result == '99'){
						ssoindex.alert(data.msg);
					}
				},
				error : function(data){
					ssoindex.alert("操作失败！");
				}
			});
		}else{
			permission.reload();
		}
	});
	$("#newPermiss").on("click",function(){
		var url = kpcas+'/base/addPermiss';
		ssoindex.modal(url,500,350,150);
	});
	$("#editPermiss").on("click",function(){
		if($(this).hasClass("botton-active")){
			var url = kpcas+'/base/editPermiss';
			ssoindex.modal(url,500,350,150);
		}
	});
	$("#delPermiss").on("click",function(){
		if($(this).hasClass("botton-active")){
			ssoindex.confirm("确定删除该权限？",permission.delPermiss);
		}
	});
	$("#treeTable-permiss tbody").on("change","input[type=checkbox]",function(){
		permission.initButton();
	});
});