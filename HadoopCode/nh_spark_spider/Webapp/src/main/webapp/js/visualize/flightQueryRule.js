var flightQueryRule = {
	 dateFrom : "" ,
	 dateTo : "",
	 dateFromTo : [],
	 
	 setDateFrom : function(date){
	 	this.dateFrom = date;
	 },
	 getDateFrom : function(){
	 	return this.dateFrom;
	 },
	 setDateTo : function(date){
	 	this.dateTo = date;
	 },
	 getDateTo : function(){
	 	return this.dateTo;
	 },
	 setDateFromTo : function(date){
	 	this.dateFromTo = date;
	 },
	 getDateFromTo : function(){
	 	return this.dateFromTo;
	 },
	
	//选择开始时间后触发
	selectTimeFrom : function(){
		//对开始时间重新set一个值（去除默认时间）
		flightQueryRule.setDateFrom($dp.cal.getDateStr("yyyy-MM-dd"));
		//重新生成时间时间数组
		flightQueryRule.setDateFromTo(getSomeDates(flightQueryRule.getDateFrom(),flightQueryRule.getDateTo()));
		//回调加载数据函数
		flightQueryRule.loadData(flightQueryRule.getDateFrom(), flightQueryRule.getDateTo())
	},
	//选择结束时间后触发
	selectTimeTo : function(){
		//对结束时间重新set一个值（去除默认时间）
		flightQueryRule.setDateTo($dp.cal.getDateStr("yyyy-MM-dd"));
		//重新生成时间时间数组
		flightQueryRule.setDateFromTo(getSomeDates(flightQueryRule.getDateFrom(),flightQueryRule.getDateTo()));
		//回调加载数据函数
		flightQueryRule.loadData(flightQueryRule.getDateFrom(), flightQueryRule.getDateTo());
	},
	
	//初始化
	init : function(){
		flightQueryRule.initParms();
	},
	//初始化参数
	initParms : function(){
		$("#chooseTimeFrom").attr("placeholder",getWeekAgoDate());
		$("#chooseTimeTo").attr("placeholder",getNowDate());
		//获取当前时间
		flightQueryRule.setDateFrom(getWeekAgoDate());
		//获取一周前的时间
		flightQueryRule.setDateTo(getNowDate());
		//获取默认的七天时间
		flightQueryRule.setDateFromTo(getSomeDates(flightQueryRule.getDateFrom(),flightQueryRule.getDateTo()));
//		$("#chooseTimeFrom").attr("placeholder","2017-07-13");
//		$("#chooseTimeTo").attr("placeholder","2017-07-20");
//		flightQueryRule.setDateFrom("2017-07-13");
//		flightQueryRule.setDateTo("2017-07-20");
//		flightQueryRule.setDateFromTo(getSomeDates(flightQueryRule.getDateFrom(), flightQueryRule.getDateTo()));
		//回调加载数据函数
		flightQueryRule.loadData(flightQueryRule.getDateFrom(),flightQueryRule.getDateTo());
	},
	
	//加载数据
	loadData : function(dataFrom, dataTo){
		flightQueryRule.loadOneWay("oneWay-content",dataFrom,dataTo);
		flightQueryRule.loadTurnAround("turnaround-content",dataFrom,dataTo);
		flightQueryRule.loadCrawlerFavoriteFlight("favoriteFlight",dataFrom,dataTo);
	},
	
	//国内、国际单程查询爬取频次
	loadOneWay : function(id, dataFrom, dataTo){
		var option = chartTemplate.createNewLine();
		var myCharts = echarts.init(document.getElementById(id));
		var legend = ["国内", "国际"];
		option.title.setText("国内、国际单程查询爬取频次");
		option.series[0].setName("国内");
		option.series[1].setName("国际");
//		option.legend.setData(legend);
		option.removeSerie(2,3);
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var seriesData0 = [];
		var seriesData1 = [];
		var seriesType0 = [];
		var seriesType1 = [];
		var xAxisData = flightQueryRule.getDateFromTo();
		//调用ajax前对开始时间和结束时间做校验
		if(dataFrom > dataTo){
			alert("开始时间不能大于结束时间");
			return;
		}else{
			var content = {
				"startData":dataFrom,
				"endDate":dataTo
			};
		 	var url = ctx + '/filghtQuery/findAllOneWayByTime';
		 	kingpoint.postData(url,content,function(data){
		 		if(data!=null && data.length!=0){
		 			//对数据进行分类
					for(var i=0; i<data.length; i++){
						if(data[i].flghtType == 0){
							seriesType0.push(data[i]);
						}else if(data[i].flghtType == 1){
							seriesType1.push(data[i]);
						}
					}
			 		//找到x轴对应的数据 
			 		seriesData0 = commonUtil.calculateValue(seriesType0,xAxisData);
			 		seriesData1 = commonUtil.calculateValue(seriesType1,xAxisData);
			 	}
		 		option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData0);
				option.series[1].setData(seriesData1);
				myCharts.setOption(option);
		 	});
		}
	 	option.xAxis.setData(xAxisData);
		option.series[0].setData(seriesData0);
		option.series[1].setData(seriesData1);
	 	myCharts.setOption(option);
	},
	//国内、国际来回程查询爬取频次
	loadTurnAround:function(id, dataFrom, dataTo){
		var option = chartTemplate.createNewLine();
		var myCharts = echarts.init(document.getElementById(id));
		var legend = ["国内", "国际"];
		option.title.setText("国内、国际双程查询爬取频次");
		option.series[0].setName("国内");
		option.series[1].setName("国际");
//		option.legend.setData(legend);
		option.removeSerie(2,3);
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var seriesData0 = [];
		var seriesData1 = [];
		var seriesType0 = [];
		var seriesType1 = [];
		var xAxisData = flightQueryRule.getDateFromTo();
		//调用ajax前对开始时间和结束时间做校验
		if(dataFrom > dataTo){
			alert("开始时间不能大于结束时间");
			return;
		}else{
			var content = {
				"startData":dataFrom,
				"endDate":dataTo
			};
		 	var url = ctx + '/filghtQuery/findTurnAroundByTime';
		 	kingpoint.postData(url,content,function(data){
		 		if(data!=null && data.length!=0){
		 			//对数据进行分类
					for(var i=0; i<data.length; i++){
						if(data[i].flghtType == 0){
							seriesType0.push(data[i]);
						}else if(data[i].flghtType == 1){
							seriesType1.push(data[i]);
						}
					}
			 		//找到x轴对应的数据 
			 		seriesData0 = commonUtil.calculateValue(seriesType0,xAxisData);
			 		seriesData1 = commonUtil.calculateValue(seriesType1,xAxisData);
			 	}
		 		option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData0);
				option.series[1].setData(seriesData1);
				myCharts.setOption(option);
		 	});
		}
	 	option.xAxis.setData(xAxisData);
		option.series[0].setData(seriesData0);
		option.series[1].setData(seriesData1);
	 	myCharts.setOption(option);
	},
	
	loadCrawlerFavoriteFlight : function(id, dataFrom, dataTo){
		var option = chartTemplate.createNewBar();
		var myCharts = echarts.init(document.getElementById(id));
		option.title.setText("爬虫查询航线排行");
		option.series[0].setName("抓取频次");
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var xAxisData = [];
		var seriesData = [];
		var content = {"startData":dataFrom,"endDate":dataTo};
	 	var url = ctx + '/filghtQuery/getNhCrawlerQueryRoutesRank';
	 	kingpoint.postData(url,content,function(data){
	 		if(data!=null && data.length!=0){
	 			for(var i=0; i<data.length; i++){
	 				xAxisData.push(data[i].depairport+"-"+data[i].arrairport);
	 				seriesData.push(data[i].grapFrequency);
	 			}
	 			option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData);
				myCharts.setOption(option);
	 		}
	 	}),
	 	
	 	option.xAxis.setData(xAxisData);
		option.series[0].setData(seriesData);
		myCharts.setOption(option);
	}
	
};

$(function(){
	flightQueryRule.init();
});