var modifyData = {
	//关闭离开
	exit : function(){
		var url = ctx + "/base/to/dataManage/exitConfirm";
		$("#dataCollectSignOutModal .modal-content").empty();
    	$("#dataCollectSignOutModal").css("top",60+"px");
    	$("#dataCollectSignOutModal .modal-dialog").css("width",400+"px");
    	$("#dataCollectSignOutModal .modal-content").css("height",250+"px");
    	$("#dataCollectSignOutModal .modal-content").load(url);
    	$("#dataCollectSignOutModal").modal("show");
	},
	
	//保存数据
	save : function(){
		
		var newScript = $("#data-collect").val();
		newScript = encodeURIComponent(newScript);
		$.ajax({
			
			url : ctx + '/dataCollect/modifyData',
			type : 'get',
			dataType : 'json',
			data : "id="+id+"&newScript="+newScript,
			success : function(data){
				if(data.result == '修改成功'){
					alert('数据保存成功！');
					proModel.modal_close();
					modifyData.load();
				}
			},
			error : function(data){
				ssoindex.alert("系统错误");
			}
		});
	},
	
	//初始化
	init : function(){
		modifyData.loadScriptText();
	},
	
	//加载脚本文本
	loadScriptText : function(){
			$.ajax({
			url : ctx + '/dataCollect/getScript',
			type : 'get',
			dataType : 'json',
			data : "id="+id,
			success : function(data){
				
				var modifyDataText = data.script;
				
				$("#data-collect").text(modifyDataText);
			},
			error : function(data){
				alert("系统错误");
			}
		});
	},
	
	//load数据
	load : function(){
		$.ajax({
			url : ctx + '/dataCollect/getDataCollect',
			type : 'get',
			dataType : 'json',
			success : function(data){
				var dataCollectHtml = '';
				for(var i = 0;i<data.length;i++){
					dataCollectHtml += '<tr id = '+data[i].id+'><td><textarea readonly="readonly">'+data[i].script+'</textarea></td><td class="server-img"><img src='+ctx+'/themes/default/images/ico-009.png>'+
					'<p>'+data[i].serverName+'</p></td><td><p class="fontSize"><span>昨天：</span><span>'+data[i].yesterdayNum+'行</span></p>'+
					'<p class="fontSize"><span>前天：</span><span>'+data[i].beforeYesterdayNum+'行</span></p>'+
					'<p class="fontSize"><span>前三天：</span><span>'+data[i].threeDayNum+'行</span></p></td>'+
					'<td class="operation-img"><img src="'+ctx+'/themes/default/images/ico-005.png" onclick="window.dataCollect.modify(\''+data[i].id+'\')"><img src="'+ctx+'/themes/default/images/ico-006.png" class="delete" onclick="window.dataCollect.delData(\''+data[i].id+'\')" ></td></tr>';
				}
				$('#datacollect').html(dataCollectHtml);
				
			},
			error : function(data){
				alert("系统错误");
			}
		});
	}
	
};

$(function(){
//	modifyData.init();
});