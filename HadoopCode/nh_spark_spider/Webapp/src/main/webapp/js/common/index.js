var index = {
	
	//初始化, 默认加载系统监控界面
	init : function(){
		$("#content").html("");
		var url = ctx + "/base/to/monitor/commonIndex";
		$("#content").load(url);
	},
	
	addListen : function(){
		//首页
		$("#indexFlage").click(function(){
			index.highlighted("indexFlage");
			$("#content").html("");
			var url = ctx + "/base/to/monitor/commonIndex";
			$("#content").load(url);
		});
		//用户管理
		$("#userManage").click(function(){
			index.loadUserManage();
		});
		//角色管理
		$("#roleManage").click(function(){
			index.loadRoleManage();
		});
		//权限管理
		$("#persManage").click(function(){
			index.loadPersManage();
		});
		//数据采集
		$("#dataCollect").click(function(){
			index.loadDataCollect();
		});
		//数据处理
		$("#dataHandle").click(function(){
			index.loadDataHandle();
		});
		//数据可视化
		$("#dataVisualize").click(function(){
			index.loadDataVisual();
		});
		//流程管理
		$("#processManage").click(function(){
			index.loadProcessManage();
		});
		//修改密码
		$("#mofityPassword").click(function(){
			index.loadMofityPassword();
		});
	},
	
	//加载用户管理页面
	loadUserManage : function(){
		index.highlighted("sysManage");
		var url = ctx + "/base/to/manage/userManage";
		index.loadPage(url);
	},
	
	//加载角色管理页面
	loadRoleManage : function(){
		index.highlighted("sysManage");
		var url = ctx + "/base/to/manage/roleManage";
		index.loadPage(url);
	},
	
	//加载权限管理页面
	loadPersManage : function(){
		index.highlighted("sysManage");
		var url = ctx + "/base/to/manage/permissManage";
		index.loadPage(url);
	},
	
	//加载数据采集页面
	loadDataCollect : function(){
		index.highlighted("dataManage");
		var url = ctx + "/base/to/dataManage/dataCollect";
		index.loadPage(url);
	},
	
	//加载数据处理页面
	loadDataHandle : function(){
		index.highlighted("dataManage");
		var url = ctx + "/base/to/dataManage/dataHandle";
		index.loadPage(url);
	},
	
	//加载数据可视化页面
	loadDataVisual : function(){
		index.highlighted("dataVisualize");
		var url = ctx + "/base/to/visualize/visualize";
		index.loadPage(url);
	},
	
	//加载流程管理页面
	loadProcessManage : function(){
		index.highlighted("processManage");
		var url = ctx + "/process/processMain";
		index.loadPage(url);
	},
	//加载修改密码模态弹出框
	loadMofityPassword: function(){
		var url = ctx + "/base/to/manage/editPwd";
		proModel.showModelView1(url,500,300,100);
	},
	//加载页面
	loadPage : function(url){
		$("#content").html("");
		$("#content").load(url);
	},
	
	//高亮事件
	highlighted : function(id){
		$("#indexFlage").removeClass("active");
		$("#dataManage").removeClass("active");
		$("#dataVisualize").removeClass("active");
		$("#processManage").removeClass("active");
		$("#sysManage").removeClass("active");
		$("#" + id).addClass("active");
	}

	
};

$(function(){
	index.init();
	index.addListen();
});

