var performanceMonitorIndex = {
	
	init : function(){
		performanceMonitorIndex.loadAnalysisSpeed();
		
		if(null == refreshPerformanceMonitorFlag){
			setInterval(performanceMonitorIndex.loadAnalysisSpeed,60000);
			refreshPerformanceMonitorFlag = "HAVE_REFRESH";
		}
		performanceMonitorIndex.loadServerStatus();
	},
	//加载数据分析速度
	loadAnalysisSpeed :function(){
		var content ="";
		//数据分析
		$.ajax({
			url:ctx +'/performance/getAllSystemDataAnalysis',
			type:'get',
			dataType:'json',
			success:function(data){
				 var con1 = "";
				 	//将date类型的时间xxxx-xx-xx转换成xxxx年xx月xx日
			 		var time='';
			 		var myDate = new Date();
					var ydtString=myDate.getFullYear()+"年"+(myDate.getMonth()+1)+"月";
					time='数据为'+ydtString+'平均值';
					//$('#time').html(time);
						if(null!=data.realtimeSpeed){
							con1 += "<span class='kdu'>实时：<span class='lse'>" + data.realtimeSpeed+" 毫秒</span> ";
						}else{
							con1 += "<span class='kdu'>实时：<span class='lse'>0 毫秒</span> ";
						}
				 		//实时仪表盘
				 		var worldMapContainer = document.getElementById('realTime');
						//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
						var resizeWorldMapContainer = function () {
						    worldMapContainer.style.width = 32 +'%';
						    var i = $('#realTime').width();
						    worldMapContainer.style.height = i+'px';
						};
						//设置容器高宽
						resizeWorldMapContainer();
						// 基于准备好的dom，初始化echarts实例
						realTime = new Object();
						if(null!=data.realtimeSpeed){
							realTime.value=data.realtimeSpeed;
						}else{
							realTime.value=0;	
						}
						realTime.name = 'Millisecond';
						var myChart = echarts.init(worldMapContainer);
						var optionRTime = chartTemplate.createNewGauge();
						optionRTime.series[0].setName("实时");
						optionRTime.series[0].setData(realTime);
						optionRTime.series[0].setMin(0);
						optionRTime.series[0].setMax(25);
						myChart.setOption(optionRTime, true);
						//用于使chart自适应高度和宽度
						window.onresize = function () {
						    //重置容器高宽
						    resizeWorldMapContainer();
						    myChart.resize();
						};
						//半实时仪表盘
						if(null!=data.nonreadtime){
							con1 += " &nbsp;&nbsp;准实时：<span class='lse'>"+ data.nonreadtime+" 毫秒</span> " ;
						}else{
							con1 += " &nbsp;&nbsp;准实时：<span class='lse'> 0 毫秒</span> " ;
						}
				 		var worldMapContainer = document.getElementById('semiRealTime');
						//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
						var resizeWorldMapContainer = function () {
						    worldMapContainer.style.width = '32%';
						    var i = $('#semiRealTime').width();
						    worldMapContainer.style.height = i+'px';
						};
						//设置容器高宽
						resizeWorldMapContainer();
						sRealTime = new Object();
						if(null!=data.nonreadtime){
							sRealTime.value = data.nonreadtime;
						}else{
							sRealTime.value = 0;						
						}
						sRealTime.name = 'Millisecond';
						// 基于准备好的dom，初始化echarts实例
						var myChart = echarts.init(worldMapContainer);
						var optionSRTime =  chartTemplate.createNewGauge();
						optionSRTime.series[0].setName("准实时");
						optionSRTime.series[0].setData(sRealTime);
						optionSRTime.series[0].setMin(0);
						optionSRTime.series[0].setMax(55);
						myChart.setOption(optionSRTime, true);
						//用于使chart自适应高度和宽度
						window.onresize = function () {
						    //重置容器高宽
						    resizeWorldMapContainer();
						    myChart.resize();
						};
						//离线仪表盘
						if(null!=data.offlineSpeed){
							con1 += " &nbsp;&nbsp;离线：<span class='lse'>" + data.offlineSpeed + " 毫秒</span></span>"  ;
						}else{
							con1 += " &nbsp;&nbsp;离线：<span class='lse'>0 毫秒</span></span>"  ;
						}
				 		var worldMapContainer = document.getElementById('offLine');
						//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
						var resizeWorldMapContainer = function () {
						    worldMapContainer.style.width = '32%';
						    var i = $('#offLine').width();
						    worldMapContainer.style.height = i+'px';
						};
						//设置容器高宽
						resizeWorldMapContainer();
						oLine = new Object();
						if(null!=data.offlineSpeed){
							oLine.value = data.offlineSpeed;
						}else{
							oLine.value = 0;
						}
						oLine.name = 'Millisecond';
						// 基于准备好的dom，初始化echarts实例
						var myChart = echarts.init(worldMapContainer);
						var optionOFine = chartTemplate.createNewGauge();
						optionOFine.series[0].setMin(0);
						optionOFine.series[0].setMax(165);
						optionOFine.series[0].setName("离线");
						optionOFine.series[0].setData(oLine);
						myChart.setOption(optionOFine, true);
						//用于使chart自适应高度和宽度
						window.onresize = function () {
						    //重置容器高宽
						    resizeWorldMapContainer();
						    myChart.resize();
						};
				 con1 +='<div style="padding-right: 97px;padding-bottom: 20px;" class="content-Title-right">' 
				 		+'数据分析速度标准：实时 5分钟内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 准实时 10分钟内'
						+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 离线 30分钟内'
					+'</div>';
				 content = con1 ;
				 $('#content-title').html(content);
			}
		});
	},
	//加载服务器运行状态
	loadServerStatus : function(){
		//服务器状态
		var server='';
		$.ajax({
			url:ctx +'/performance/getAllServerStatusInfo',
			type:'post',
			dateType:'json',
			success:function(data){
				if(data.serverStatusInfolist!=null){
					for(var i=0;i<data.serverStatusInfolist.length;i++){
						server +='<tr>'
									+'<td class="padding-left27px"><span class="glyphicon glyphicon-tasks"><img  src="'+ctx+'/themes/default/images/ico-09.PNG"/></span></td>'
									+'<td>'+data.serverStatusInfolist[i].name+'</td>'
									+'<td>CPU占用：<span style="color: #469f45;">'+data.serverStatusInfolist[i].cpuRate+'%</span></td>'
									+'<td>内存占用：<span style="color: #fe2224;">'+data.serverStatusInfolist[i].memoryRate+'%&nbsp;&nbsp;&nbsp;'+data.serverStatusInfolist[i].memoryInfo+'</span></td>'
									+'<td>已用磁盘空间：<span style="color: #2e30fe;">'+data.serverStatusInfolist[i].diskRate+'%&nbsp;&nbsp;&nbsp;'+data.serverStatusInfolist[i].diskInfo+'</span></td>'
									+'<td>备注：'+(data.serverStatusInfolist[i].remarke == null ? "":data.serverStatusInfolist[i].remarke)+'</td>'
								+'</tr>';
						$("#server").html(server);		
					}
				}
			}
		});
	}
};

$(function(){
	performanceMonitorIndex.init();
});