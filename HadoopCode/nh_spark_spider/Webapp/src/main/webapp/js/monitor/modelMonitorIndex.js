var functionMonitorIndex = {
		init:function(){
			functionMonitorIndex.loadProcessNums();
			functionMonitorIndex.loadFlowTransmit();
		},
	loadProcessNums:function(){
		var content = "";
		var url = ctx + '/modelMonitor/getAllNhProcessNums';
		kingpoint.postData(url,null,function(data){
			if(data!=null && data.length!=0){
				for(var i=0; i<data.length; i++){
					content +='<option value="'+data[i].processNum+'">'+data[i].processName+'</option>';
				}
				$("#flowType").html(content);
				functionMonitorIndex.loadFlowTransmit();
			}
		});
	},	
	//算法模型准确率
	loadFlowTransmit : function(timeType,flowType){
		var option = chartTemplate.createNewLine();
		var worldMapContainer = document.getElementById('flowTransmit');
		var resizeLineContainer = function () {
		    worldMapContainer.style.height = 505 +'px';
		};
		var timeType = $("#timeType").val();
		var flowType = $("#flowType").val();
		var content = {
			"timeType":timeType,
			"flowType":flowType
		};
		//设置容器高宽
		resizeLineContainer();
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('flowTransmit'));
        var xAxisData = new Array();
        var arrTime = new Array();
        var legendData = ["有监督学习","无监督学习"];
        var seriesData0 = new Array();
        var seriesData1 = new Array();
        var seriesType0 = new Array();
        var seriseType1 = new Array();
        var name0 = "有监督学习";
        var name1 = "无监督学习";
        var yname = "准确率";
        var xname = "日期";
        option.yAxis.setName(yname);
        option.xAxis.setName(xname);
        var url = ctx + '/modelMonitor/getNhModelmonitorAccuracy';
        option.removeSerie(2,3);
        option.legend.setData(legendData);
        kingpoint.postData(url,content,function(data){
			if(data!=null && data.length!=0){
				for(var i=0; i<data.length; i++){
					xAxisData.push(data[i].date);
					if(data[i].modeltype==0){
						seriesType0.push(data[i]);
					}else if(data[i].modeltype==1){
						seriseType1.push(data[i]);
					}
				}
				if(null!=xAxisData){
					for(var j=0;j<xAxisData.length;j++){
						if(arrTime.indexOf(xAxisData[j])==-1){
							arrTime.push(xAxisData[j]);
						}
					}
				}
				seriesData0 = commonUtil.calculateValue(seriesType0,arrTime);
			 	seriesData1 = commonUtil.calculateValue(seriseType1,arrTime);
				option.xAxis.setData(arrTime);
				option.series[0].setData(seriesData0);
				option.series[0].setName(name0);
				option.series[1].setData(seriesData1);
				option.series[1].setName(name1);
			 	myChart.setOption(option);
			 	//用于使chart自适应高度和宽度
				window.onresize = function () {
				    //重置容器高宽
				    resizeLineContainer();
				    myCharts.resize();
				};
			}
		});
	},
};
//实时性选择触发
function selectFlowType(){
	var objS = document.getElementById("flowType");
    var flowType = objS.options[objS.selectedIndex].value;
    var timeType = $("#timeType").val();
    functionMonitorIndex.loadFlowTransmit(timeType,flowType);
};
//流程选择触发
function selectTimeType(){
	var objS = document.getElementById("timeType");
    var timeType = objS.options[objS.selectedIndex].value;
    var flowType = $("#flowType").val();
    functionMonitorIndex.loadFlowTransmit(timeType,flowType);
}
$(function(){
	functionMonitorIndex.init();
//	functionMonitorIndex.loadFlows();
//	setInterval('functionMonitorIndex.loadFlows()', 5000);
});