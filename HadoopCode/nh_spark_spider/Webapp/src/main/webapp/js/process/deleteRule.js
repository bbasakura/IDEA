var deleteRule = {
	//关闭离开
	exit : function(){
		var url = ctx + "/base/to/process/exitConfirm";
		$("#signOutModel .modal-content").empty();
    	$("#signOutModel").css("top",60+"px");
    	$("#signOutModel .modal-dialog").css("width",300+"px");
    	$("#signOutModel .modal-content").css("height",180+"px");
    	$("#signOutModel .modal-content").load(url);
    	$("#signOutModel").modal("show");
	},
	//删除数据
	deleteprocess:function(){
		$.ajax({
			url:ctx+'/process/deleteProcess',
			type:'post',
			dataType:'json',
			data:{id:id},
			success:function(data){
				if(data.code==1){
					proModel.modal_close();
					deleteRule.load();
				}else{
					alert(data.msg);
				}
			}
		})
	},
	//渲染数据
	load :function(){
		var content='';
		var strategy = $("#form-control option:selected").val();
		var type = $("#form-group option:selected").val();
		var tactics='';
		var model='';
		$.ajax({
			url:ctx + '/process/findAllProcessInfo',
			type:'post',
			dataType:'json',
			data:{strategy:strategy,type:type},
			success:function(data){
				for(var i=0;i<data.listInfo.length;i++){
					if(data.listInfo[i].type==0){
						model="有监督学习";
					}else{
						model="无监督学习";
					}
					if(data.listInfo[i].status==0){
						img="ico-010.png";
					}else{
						img="ico-004.png";
					}
					content +='<tr>'
					+'<td>'
						+'<p>'+data.listInfo[i].processName+'</p>'
					+'</td>'
					+'<td>'+model+'</td>'
					+'<td>'+data.listInfo[i].createDate+'</td>'
					+'<td>'+data.listInfo[i].createPerson+'</td>'
					+'<td class="center">'
						+'<img onclick="window.runRule(\''+data.listInfo[i].id+'\',\''+data.listInfo[i].status+'\')" src="'+ctx+'/themes/default/images/'+img+'" data-toggle="modal" >'
						+'<img onclick="window.modify(\''+data.listInfo[i].id+'\')" src="'+ctx+'/themes/default/images/ico-005.png" data-toggle="modal" >'
						+'<img onclick="window.submitData(\''+data.listInfo[i].id+'\')" src="'+ctx+'/themes/default/images/ico-006.png" data-toggle="modal">'
						//+'<img onclick="window.resultShow(\''+data.listInfo[i].id+'\')" src="'+ctx+'/themes/default/images/ico-0007.png" data-toggle="modal" >'
					+'</td>'
				+'</tr>';
				}
				$('#content-body').html(content);
			}
		})
	},
	//删除数据
	submitData:function (id){
		var url = ctx + "/base/to/process/deleteRule?id="+id+"";
		proModel.showModelView(url,300,180,80);
	},
	//规则数据渲染
	ruleView:function(id){
		var url = ctx + "/base/to/process/ruleView?id="+id+"";
			proModel.showModelView(url,690,408,75);
	},
	//流程信息修改
	modifyRule:function(id){
		var url = ctx + "/base/to/process/modifyRule?id="+id+"";
			proModel.showModelView(url,800,500,75);
	},
	//显示识别率
	resultShow:function(id){
		var url = ctx + "/base/to/process/resultShow?id="+id+"";
			proModel.showModelView(url,600,380,75);
	},
	//运行流程
	runRule:function(id){
		var url = ctx + "/base/to/process/run?id="+id+"";
			proModel.showModelView(url,300,180,75);
	}
}