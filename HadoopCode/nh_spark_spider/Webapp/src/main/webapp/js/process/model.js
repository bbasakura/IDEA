var proModel = {
	
	showModelView : function(url,width,height,top){
		$("#modal .modal-content").empty();
    	$("#modal").css("top",top+"px");
    	$("#modal .modal-dialog").css("width",width+"px");
    	$("#modal .modal-content").css("height",height+"px");
    	$("#modal .modal-content").load(url);
    	$("#modal").modal("show");
	},
	
	showModelView1 : function(url,width,height,top){
		$("#modal-editPass .modal-content").empty();
    	$("#modal-editPass").css("top",top+"px");
    	$("#modal-editPass .modal-dialog").css("width",width+"px");
    	$("#modal-editPass .modal-content").css("height",height+"px");
    	$("#modal-editPass .modal-content").load(url);
    	$("#modal-editPass").modal("show");
	},
	
	modal_close : function(){
    	$("#modal").modal("hide");
    	$("#modal-editPass").modal("hide");
    }
};