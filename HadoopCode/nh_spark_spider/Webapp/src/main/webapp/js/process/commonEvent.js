//删除数据
function submitData(id){
	var url = ctx + "/base/to/process/deleteRule?id="+id+"";
	proModel.showModelView(url,340,250,120);
};
//规则数据渲染
function view(id){
	var url = ctx + "/base/to/process/ruleView?id="+id+"";
	proModel.showModelView(url,690,408,75);
};
//流程信息修改
function modify(id){
	var url = ctx + "/base/to/process/modifyRule?id="+id+"";
	proModel.showModelView(url,780,570,75);
};

//运行流程
function runRule(id,status){
	var url = ctx + "/base/to/process/run?id="+id+"&status="+status+"";
	proModel.showModelView(url,350,250,75);
};

//提示信息
function commonConfirmMsg(context){
	$("#proModalPrompt").css("top",100+"px");
    	$("#proModalPrompt .modal-dialog").css("width",360+"px");
    	$("#proModalPrompt .modal-content").css("height",250+"px");
		$("#proModalPrompt .myRun-content").html(context);
    	$("#proModalPrompt").modal("show");
};