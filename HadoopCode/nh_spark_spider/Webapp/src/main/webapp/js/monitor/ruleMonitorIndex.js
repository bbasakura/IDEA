
var ruleMonitorIndex = {
		
	//初始化
	init : function(){	
		ruleMonitorIndex.getRuleMonitor();
		ruleMonitorIndex.getRuleSeat();
		ruleMonitorIndex.getRuleMonitor();
		if(null == refreshRuleMonitorFlag){
			setInterval(ruleMonitorIndex.getRuleMonitor,5000);
			refreshRuleMonitorFlag = "HAVE_REFRESH";
		}
	},
	//规则监控 反爬
	getRuleMonitor : function(timeType,ruleType){
		var option = chartTemplate.createNewBar();
		var lineContainer = document.getElementById('anticLimb');
		//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
		var resizeLineContainer = function () {
		    lineContainer.style.height = 500 +'px';
		};
		//设置容器高宽
		resizeLineContainer();
		// 基于准备好的dom，初始化echarts实例
		var myCharts = echarts.init(document.getElementById('anticLimb'));
		var xAxisData = new Array();
		var seriesData = new Array();
		var ruleType = $("#ruleType").val();
		var timeType = $("#timeType").val();
		var content = {
			"timeType":timeType,
			"ruleType":ruleType
		};
		var yname = "数量";
		var xname = "次数";
		option.yAxis.setName(yname);
		option.xAxis.setName(xname);
		var url = ctx + '/ruleMonitor/getRulemonitorAntispider';
		kingpoint.postData(url,content,function(data){
			if(data!=null && data.length!=0){
				for(var i=0; i<data.length; i++){
					seriesData.push(data[i].numy);
					xAxisData.push(data[i].numx);
				}
				option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData);
			 	myCharts.setOption(option);
			 	//用于使chart自适应高度和宽度
				window.onresize = function () {
				    //重置容器高宽
				    resizeLineContainer();
				    myCharts.resize();
				};
			}
		});
	},
	getRuleSeat:function(ruleType,timeType){
		var option = chartTemplate.createNewBar();
		var lineContainer = document.getElementById('saveSeat');
		//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
		var resizeLineContainer = function () {
		    lineContainer.style.height = 500 +'px';
		};
		//设置容器高宽
		resizeLineContainer();
		// 基于准备好的dom，初始化echarts实例
		var myCharts = echarts.init(document.getElementById('saveSeat'));
		var xAxisData = new Array();
		var seriesData = new Array();
		var yname = "数量";
		var xname = "次数";
		option.yAxis.setName(yname);
		option.xAxis.setName(xname);
		var ruleType = $("#ruleTypeSeat").val();
		var timeType = $("#timeTypeSeat").val();
		var content = {
			"timeType":timeType,
			"ruleType":ruleType
		};
		var url = ctx + '/ruleMonitor/getNhRulemonitorAntioccupy';
		kingpoint.postData(url,content,function(data){
			if(data!=null && data.length!=0){
				for(var i=0; i<data.length; i++){
					seriesData.push(data[i].numy);
					xAxisData.push(data[i].numx);
				}
				option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData);
			 	myCharts.setOption(option);
			 	//用于使chart自适应高度和宽度
				window.onresize = function () {
				    //重置容器高宽
				    resizeLineContainer();
				    myCharts.resize();
				};
			}
		});
	}
};
//反爬 实时性
function selectTimeType(){
	var objS = document.getElementById("timeType");
    var timeType = objS.options[objS.selectedIndex].value;
    var ruleType = $("#ruleType ").val();
    ruleMonitorIndex.getRuleMonitor(timeType,ruleType);
};
//反爬 规则
function selectRuleType(){
	var objS = document.getElementById("ruleType");
    var ruleType = objS.options[objS.selectedIndex].value;
    var timeType = $("#timeType ").val();
    ruleMonitorIndex.getRuleMonitor(timeType,ruleType);
}
//占座 实时性
function selectTimetypeSeat(){
	var objS = document.getElementById("timeTypeSeat");
    var timeType = objS.options[objS.selectedIndex].value;
    var ruleType = $("#ruleTypeSeat ").val();
    ruleMonitorIndex.getRuleSeat(timeType,ruleType);
}
//占座 规则
function selectRuleTypeSeat(){
	var objS = document.getElementById("ruleTypeSeat");
    var ruleType = objS.options[objS.selectedIndex].value;
    var timeType = $("#timeTypeSeat ").val();	
    ruleMonitorIndex.getRuleSeat(ruleType,timeType);
}
$(function(){
	ruleMonitorIndex.init();
});