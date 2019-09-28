var confirmResult = {
	//取消退出
	cancleExit : function(){
		$("#signOutModel").modal("hide");
	}
};

$(function(){
	$("#signOutModel .mySignOut-content").html("");
	if(status==0){
		$("#signOutModel .mySignOut-content").html("保存成功");
	}else{
		$("#signOutModel .mySignOut-content").html("系统原因，保存失败");
	}
});