var run = {
	init : function(){
		run.load();
		run.addListen();
	},
	//点击退出
	exit : function(){
		var url = ctx + "/base/to/process/exitConfirm";
		$("#signOutModel .modal-content").empty();
    	$("#signOutModel").css("top",100+"px");
    	$("#signOutModel .modal-dialog").css("width",300+"px");
    	$("#signOutModel .modal-content").css("height",180+"px");
    	$("#signOutModel .modal-content").load(url);
    	$("#signOutModel").modal("show");
	},
	addListen : function(){
		if(status=="0"){
			$("#end").show()
			$("#start").hide();
		}else{
			$("#start").show();
			$("#end").hide();
		}
	},
	finsh: function(){
		var content = "";
		$.ajax({
			url : ctx + '/process/updateStatusbyId',
			type:'post',
			dataType:'json',
			data:{"id":id},
			success:function(data){
				if(data.code=="1"){
					proModel.modal_close();
					run.load();
				}else{
					alert(data.msg);
				}
			}
		});
	},
		//渲染数据
	load :function(){
		var content='';
		var model='';
		var img='';
		$.ajax({
			url:ctx + '/process/findAllProcessInfo',
			type:'post',
			dataType:'json',
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
	}
	
};
$(function(){
	run.init();
	run.load();
});