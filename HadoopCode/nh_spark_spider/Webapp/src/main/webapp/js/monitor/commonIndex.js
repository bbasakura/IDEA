var commonIndex = {
	
	init : function(){
		$("#rightContent").html("");
		var url = ctx + "/base/to/monitor/systemMonitorIndex";
		$("#rightContent").load(url);
		//自动刷新的标识
		refreshSystemMonitorFlag = null;
		refreshPerformanceMonitorFlag = null;
		refreshRuleMonitorFlag = null;
	},
	
	addListen : function(){
		//点击系统监控
		$("#systemMonitor").click(function(){
			commonIndex.loadSystemMonitor();
		});
		//点击规则监控
		$("#ruleMonitor").click(function(){
			commonIndex.loadRuleMonitor();
		});
		//点击模型监控
		$("#modelMonitor").click(function(){
			commonIndex.loadModelMonitor();
		});
		//点击属性监控
		$("#propertyMonitor").click(function(){
			commonIndex.loadPropertyMonitor();
		});
		
	},
	//加载系统监控
	loadSystemMonitor : function(){
		commonIndex.changeClass("#systemMonitor");
		var url = ctx + "/base/to/monitor/systemMonitorIndex";
		commonIndex.changeContext(url);
	},
	//加载规则监控
	loadRuleMonitor : function(){
		commonIndex.changeClass("#ruleMonitor");
		var url = ctx + "/base/to/monitor/ruleMonitorIndex";
		commonIndex.changeContext(url);
	},
	//加载模型监控
	loadModelMonitor: function(){
		commonIndex.changeClass("#modelMonitor");
		var url = ctx + "/base/to/monitor/modelMonitorIndex";
		commonIndex.changeContext(url);
	},
	//加载性能监控
	loadPropertyMonitor : function(){
		commonIndex.changeClass("#propertyMonitor");
		var url = ctx + "/base/to/monitor/propertyMonitorIndex";
		commonIndex.changeContext(url);
	},
	//改变点击后，选中按钮的样式
	changeClass : function(id){
		var active = $(".active");
		active.removeClass("active");
		$(id).addClass("active");
		commonIndex.highlighted();
	},
	//导航高亮事件
	highlighted : function(){
		$("#dataManage").removeClass("active");
		$("#dataVisualize").removeClass("active");
		$("#processManage").removeClass("active");
		$("#sysManage").removeClass("active");
		$("#indexFlage").addClass("active");
	},
	//改变显示内容
	changeContext : function(url){
		$("#rightContent").html("");
		$("#rightContent").load(url);
	}
};

$(function(){
	commonIndex.init();
	commonIndex.addListen();
});
