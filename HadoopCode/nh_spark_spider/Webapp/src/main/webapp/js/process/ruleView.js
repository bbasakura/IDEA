var ruleView ={
	init:function(){
		var content = "";
		var type = "";
		$.ajax({
			url:ctx + '/process/findNhRuleById',
			type:'get',
			dateType:'json',
			data:{id:id},
			success:function(data){
				if(data.ruleList!=null){
					for(var i=0;i<data.ruleList.length;i++){
					if(data.ruleList[i].type=="0"){
						type="反爬虫";
					}else if(data.ruleList[i].type=="1"){
						type="防占座";
					}else{
						type="代购识别";
					}
					if(data.ruleList[i].sign=="1"){
					content += '<tr>'
									+'<td>'
										+'<div>'
											+'<select>'
												+'<option>'+data.ruleList[i].arg0+'</option>'
											+'</select>'
											+'<span>'+data.ruleList[i].sInfo+'&nbsp;&nbsp;</span>'
											+'<label><input type="text" name="'+data.ruleList[i].arg1+'" value="'+data.ruleList[i].arg1+'"></label>'
											+'<span>&nbsp;&nbsp;'+data.ruleList[i].sInfo1+'&nbsp;&nbsp;&nbsp;&nbsp;</span>'
											+'<label><input type="text" name="'+data.ruleList[i].arg2+'" value="'+data.ruleList[i].arg2+'"></label>'
										+'</div>'
									+'</td>'
									+'<td>'
										+'<span>'+type+'</span>'
									+'</td>'
								+'</tr>';
					}else if(data.ruleList[i].sign=="2"){
						content += '<tr>'
									+'<td>'
										+'<div>'
											+'<label>'+data.ruleList[i].sInfo+'&nbsp;</label>'
											+'<input type="text" name="'+data.ruleList[i].arg1+'" value="'+data.ruleList[i].arg1+'">'
											+'<span>&nbsp;&nbsp;'+data.ruleList[i].sInfo1+'&nbsp;&nbsp;</span>'
										+'</div>'
									+'</td>'
									+'<td>'
										+'<span>'+type+'</span>'
									+'</td>'
								+'</tr>';
					}else if(data.ruleList[i].sign=="3"){
						content += '<tr>'
									+'<td>'
										+'<div>'
											+'<select>'
												+'<option>'+data.ruleList[i].arg0+'</option>'
											+'</select>'
											+'<label>'+data.ruleList[i].sInfo+'&nbsp;</label>'
										+'</div>'
									+'</td>'
									+'<td>'
										+'<span>'+type+'</span>'
									+'</td>'
								+'</tr>';
					}else{
						content += '<tr>'
									+'<td>'
										+'<div>'
											+'<select>'
												+'<option>'+data.ruleList[i].arg0+'</option>'
											+'</select>'
											+'<label>'+data.ruleList[i].sInfo+'&nbsp;</label>'
											+'<input type="text" name="'+data.ruleList[i].arg1+'" value="'+data.ruleList[i].arg1+'">'
										+'</div>'
									+'</td>'
									+'<td>'
										+'<span>'+type+'</span>'
									+'</td>'
								+'</tr>';
					}
				}
				}
				$("#content-table").html(content);
			},
			error : function(data){
				alert("系统错误");
			}
		});
	}
};
$(function(){
	ruleView.init();
});