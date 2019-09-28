var ssoindex={
	account : '',
	permission : null,
    init : function(){
/*    	$.ajax({
    		url : kpcas+'/userInfo/getUserAndResInfo',
    		type : 'get',
    		dataType : 'json',
    		success : function(data){
    			data = FastJson.format(data);
    			if(data.result == '0'){
    				var re = data.data[0];
    				$("#user-welcome").text(re.uname+", 欢迎您");
    				ssoindex.account = re.aname;
    				ssoindex.permission = re.permission;
    				//渲染具有权限的功能点
    				$(".index-menu li div").each(function(){
    					ssoindex.liPermission($(this));
    				})
					//默认进入个人信息页面
    				var menu=$(".index-menu li:eq(0)").addClass("menu-active");
        			var url = kpcas+'/base/userInfoView';
        			$(".index-right").load(url);
    			}else if(data.result == '1'){
    				ssoindex.alert("查询不到用户信息！");
    			}else if(data.result == '99'){
    				ssoindex.alert("系统错误");
    			}
    		},
    		error : function(data){
    			ssoindex.alert("用户信息加载失败！");
    		}
    	})*/
    	//默认进入个人信息页面
//		var menu=$(".index-menu li:eq(0)").addClass("menu-active");
//		var url = kpcas+'/base/userInfoView';
//		$(".index-right").load(url);
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
    
    alert2 : function(content){
    	$("#modal-alert-editPass .content-body").html(content);
    	$("#modal-alert-editPass").modal("show");
    },
    
    modal_close : function(){
    	$("#modal").modal("hide");
    	//修改密码的弹框消失
    	$("#modal-editPass").modal("hide");
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
    		})
    		obj.find("thead tr th input[type=checkbox]").attr("checked",checkAll);
    	})
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
    	})
    },
    validation : function(p){
    	  var pattern=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    	  if(pattern.test(p)){
    	  	ssoindex.alert("您输入的信息具有特殊字符！");
    	  	return false;
    	  }
    	  return true;
    }/*,
    validationBefore : function(obj){
    	//防止重复提交
    	if(obj.flag){
    		obj.flag = false;
    	}else{
    		ssoindex.alert("正在处理中，请勿重复提交！");
    		return false;
    	}
    },
    validationAfter : function(obj){
    	obj = true;
    }*/
    /*,
    liPermission : function(obj){
    	var perNum = obj.attr("perNum");
    	if(ssoindex.permission){
    		for(var i in ssoindex.permission){
    			if(perNum == ssoindex.permission[i].perNum){
    				obj.parent("li").css("display","block");
    			}
    		}
    	}
    },
    buttonPermission : function(obj){
    	var perNum = obj.attr("perNum");
    	if(ssoindex.permission){
    		for(var i in ssoindex.permission){
    			if(ssoindex.permission[i].perNum && perNum == ssoindex.permission[i].perNum){
    				obj.css("display","inline-block");
    			}
    		}
    	}
    },
    initPermission : function(){
    	$(".content-botton-list1>button").each(function(){
			ssoindex.buttonPermission($(this));
		})
    }*/
}
$(function(){
//	ssoindex.init();
//    $(".index-menu li").on("click",function(){
//        var index = $(this).children().attr("menu-info");
//        var item=parseInt(index);
//        var url='';
//        switch (item){
//            case 1://个人信息
//                url=kpcas+'/base/userInfoView';
//                break;
//            case 2://用户列表
//                url=kpcas+'/base/userManageView';
//                break;
//            case 3://角色列表
//                url=kpcas+'/base/roleManageView';
//                break;
//            case 4://部门组织
//                url=kpcas+'/base/deptManageView';
//                break;
//            case 5://权限管理
//                url=kpcas+'/base/permissManageView';
//                break;
//            case 6://系统日志
//                url=kpcas+'/base/logManageView';
//                break;
//            default:
//                url='';
//                break;
//        }
//        $(".index-menu").find("li.menu-active").removeClass("menu-active");
//        $(this).addClass("menu-active");
//        $(".index-right").load(url);
//    })
})
