var ssoindex={
	account : '',
	permission : null,
    init : function(){
    },
    modal : function(url,width,height,top){
    	$("#modal .modal-content").empty();
    	$("#modal").css("top",top+"px");
    	$("#modal .modal-dialog").css("width",width+"px");
    	$("#modal .modal-content").css("height",height+"px");
    	$("#modal .modal-content").load(url);
    	$("#modal").modal("show");
    },
    confirm : function(content,opt){
    	$("#modal-confirm .content-body").html(content);
    	$("#modal-confirm").modal("show");
    	if(opt){
    		$("#confirm").on("click",opt);
    	}
    },
    alert : function(content){
    	$("#modal-alert .content-body").html(content);
    	$("#modal-alert").modal("show");
    },
    modal_close : function(){
    	$("#modal").modal("hide");
    },
    checkAll : function(obj,opt){//opt为所在的table对象,opt为执行函数
    	obj.find("thead tr th input[type=checkbox]").on("click",function(){
    		var checkFlag = $(this).attr("checked")||false;
    		obj.find("tbody tr td input[type=checkbox]").each(function(){
    			$(this).attr("checked",checkFlag);
    		});
    		if(opt!=null){
    			opt();
    		}
    	});
    	obj.find("tbody tr td input[type=checkbox]").on("click",function(){
    		var checkAll = true;
    		obj.find("tbody tr td input[type=checkbox]").each(function(){
    			if($(this).attr("checked") == false||!$(this).attr("checked")){
    				checkAll = false;
    				return false;
    			}
    		});
    		obj.find("thead tr th input[type=checkbox]").attr("checked",checkAll);
    	});
    },
    inputAlert : function(obj){
    	obj.blur(function(){
    		var n = obj.parent('td');
    		if(obj.val() == ""){	
    			if(n.find('span').length == 0){
    				n.append('<span style="color:red;font-size:12px;">必填项</span>');
    			}
    		}else{
    			n.find('span').remove();
    		}
    	});
    },
    validation : function(p){
    	  var pattern=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    	  if(pattern.test(p)){
    	  	ssoindex.alert("您输入的信息具有特殊字符！");
    	  	return false;
    	  }
    	  return true;
    }
};
$(function(){
});
