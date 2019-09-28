var addRole = {
	pageNo : 1,
	pageSize : 10,
	pageCount : 0,
	init : function(pageNo,pageSize){
		var check = $("#user-list tbody tr td input[type=checkbox]:checked");
		var seaStr = $("#addRoleSearch input").val();
		$.ajax({
			url : kpcas+'/role/getAllRole',
            type : "GET",
            data : {"seaStr" : seaStr,"pageNo":pageNo,"pageSize":pageSize},
            dataType : "json",
            success : function(data){
            	if(data.result == '0'){
            		//i表示在data中的索引位置，n表示包含的信息的对象
            		var table = '';
            		$.each(data.roleList,function(i,n){
                		table += '<tr data-state="'+n.state+'" '+(i%2 == 0? "class=\"list-bg\"" : "")+'><td><input type="checkbox" id="checkAddRole'+i+'" name="checkAddRole" value="'+n.id+'"/><label for="checkAddRole'+i+'"></label></td>'
                			  +'<td>'+n.name+'</td><td>'+n.cnname+'</td><td>'+n.description+'</td>';
                	});
                	$("#add-role tbody").html(table);
                	addRole.pageCount = data.pageCount;
					$("#addRolePage").createPage({
						pageCount : addRole.pageCount,
						current : addRole.pageNo,
						backFn : function(p) {
							addRole.pageNo = p;
							addRole.init(p, addRole.pageSize);
						}
					});
					$("#add-role thead tr th input[type=checkbox]").prop("checked",false);
					
					//如果勾选仅有一个人，则获取该用户的角色，并在配置页面上打钩
					if(check && check.length == 1){
						var id = check.val();
						$.ajax({
							url : kpcas+'/role/getRoleByUser',
							type : "GET",
							data : {"id":id},
							dataType : "json",
							success : function(data){
								if(data.result == '0'){
									if(data.roleList){
										$.each(data.roleList,function(i,n){
											$("#add-role tbody tr td").find("input[value="+n+"]").attr("checked","checked");
										});
									}
								}else{
									ssoindex.alert(data.msg);
								}
							},
							error : function(data){
								ssoindex.alert("操作失败");
							}
						});
					}
            	}else if(data.result == '99'){
            		ssoindex.alert("系统错误");
            	}
            },
            error : function(data){
            	ssoindex.alert("操作失败");
            }
		});
	}
};

$(function(){
	addRole.init(addRole.pageNo,addRole.pageSize);
	ssoindex.checkAll($("#add-role"),null);
	$("#addRolePaging select").on("change",function(){
		var value = $('#addRolePaging select option:selected').val();
		addRole.pageSize = value;
		addRole.pageNo = 1;
		addRole.init(addRole.pageNo,addRole.pageSize);
	});
	$("#addRoleSearchBtn").on("click",function(){
		addRole.init(addRole.pageNo,addRole.pageSize);
	});
});