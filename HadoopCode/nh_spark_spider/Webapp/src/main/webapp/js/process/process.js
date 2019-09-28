var process = {
};

function proPromptSure(){
	$("#proModalPrompt").modal("hide");
}

function addListen(){
	//查询流程
	$("#queryProcess").click(function(){
		load();
	});
	//新建流程
	$("#newProcess").click(function(){
		var url = ctx + "/base/to/process/newProcess";
		proModel.showModelView(url,780,570,75);
	});
}
function addStatus(){
		if(status=="0"){
			$("#end").show();
			$("#start").hide();
		}else{
			$("#start").show();
			$("#end").hide();
		}
}
//渲染数据
function load(){
	var content='';
	var model='';
	var img='';
	$.ajax({
		url:ctx + '/process/findAllProcessInfo',
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.listInfo!=null){
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
					+'</td>'
				+'</tr>';
			}
		}
		$('#content-body').html(content);
	}

	});
};

$(function(){
	addListen();
	load();
	addStatus();
});