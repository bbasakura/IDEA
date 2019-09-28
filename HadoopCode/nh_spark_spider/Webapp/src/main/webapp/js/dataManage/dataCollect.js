var dataCollect = {
		
	//初始化
	init : function(){
		dataCollect.loadServer();
	},
	
	loadServer : function(){
		$.ajax({
			url : ctx + '/dataCollect/getDataCollect',
			type : 'get',
			dataType : 'json',
			success : function(data){
				var dataCollectHtml = '';
				for(var i = 0;i<data.length;i++){
					
					dataCollectHtml += '<tr><td class="server-img"><p>'+data[i].serverName+'</p></td>'+
					'<td><p>'+data[i].activeNum+'</p></td>'+
					'<td><p class="fontSize"><span>昨天：</span><span>'+data[i].yesterdayNum+'行</span></p>'+
					'<p class="fontSize"><span>前天：</span><span>'+data[i].beforeYesterdayNum+'行</span></p>'+
					'<p class="fontSize"><span>前三天：</span><span>'+data[i].lastThreeDaysNum+'行</span></p></td></tr>';
					//+
					'<td class="operation-img"><img src="${ctx}/themes/default/images/ico-005.png" onclick="window.dataCollect.modify()" data-toggle="modal" data-target="#myModify"></td>'
					
				}
				$('#datacollect').html(dataCollectHtml);
				
			},
			error : function(data){
				alert("系统错误");
			}
		});
	},
	//修改
	modify : function(id){
		var url = ctx + "/base/to/dataManage/modifyData?id="+id;
		dataCollect.showModelView(url,580,300,100);
	},
	//显示模态框
	showModelView : function(url,width,height,top){
		$("#dataCollectModal .modal-content").empty();
    	$("#dataCollectModal").css("top",top+"px");
    	$("#dataCollectModal .modal-dialog").css("width",width+"px");
    	$("#dataCollectModal .modal-content").css("height",height+"px");
    	$("#dataCollectModal .modal-content").load(url);
    	$("#dataCollectModal").modal("show");
	}
};

$(function(){
	dataCollect.init();
});

