var stableInfluence = {
	dateFrom : "",
	dateTo : "",
	dateFromTo : [],

	setDateFrom : function(date) {
		this.dateFrom = date;
	},
	getDateFrom : function() {
		return this.dateFrom;
	},
	setDateTo : function(date) {
		this.dateTo = date;
	},
	getDateTo : function() {
		return this.dateTo;
	},
	setDateFromTo : function(date) {
		this.dateFromTo = date;
	},
	getDateFromTo : function() {
		return this.dateFromTo;
	},

	// 选择开始时间后触发
	selectTimeFrom : function() {
		// 对开始时间重新set一个值（去除默认时间）
		stableInfluence.setDateFrom($dp.cal.getDateStr("yyyy-MM-dd"));
		// 重新生成时间时间数组
		stableInfluence.setDateFromTo(getSomeDates(stableInfluence.getDateFrom(), stableInfluence.getDateTo()));
		// 回调加载数据函数
		stableInfluence.loadData(stableInfluence.getDateFrom(), stableInfluence.getDateTo());
	},
	// 选择结束时间后触发
	selectTimeTo : function() {
		// 对结束时间重新set一个值（去除默认时间）
		stableInfluence.setDateTo($dp.cal.getDateStr("yyyy-MM-dd"));
		// 重新生成时间时间数组
		stableInfluence.setDateFromTo(getSomeDates(stableInfluence.getDateFrom(), stableInfluence.getDateTo()));
		// 回调加载数据函数
		stableInfluence.loadData(stableInfluence.getDateFrom(), stableInfluence.getDateTo());
	},

	// 初始化
	init : function() {
		stableInfluence.initParms();
	},
	// 初始化参数
	initParms : function() {
		$("#chooseTimeFrom").attr("placeholder",getWeekAgoDate());
		$("#chooseTimeTo").attr("placeholder",getNowDate());
		// 获取当前时间
		stableInfluence.setDateFrom(getWeekAgoDate());
		// 获取一周前的时间
		stableInfluence.setDateTo(getNowDate());
		// 获取默认的七天时间
		stableInfluence.setDateFromTo(getSomeDates(stableInfluence.getDateFrom(), stableInfluence.getDateTo()));
		// 回调加载数据函数
		stableInfluence.loadData(stableInfluence.getDateFrom(), stableInfluence.getDateTo());
	},

	// 加载数据
	loadData : function(dataFrom, dataTo) {
		stableInfluence.getNhFlowInfo("nh-flow-info", dataFrom, dataTo);
		stableInfluence.loadCrawlerFlowRate("crawler-flow-rate", dataFrom, dataTo);
	},

	// 加载流量情况
	getNhFlowInfo : function(id,dataFrom, dataTo) {
		var option = chartTemplate.createNewLine();
		var myCharts = echarts.init(document.getElementById(id));
		var legend = ["全部流量", "爬虫流量"];
		option.title.setText("流量情况");
		option.series[0].setName("全部流量");
		option.series[1].setName("爬虫流量");
		option.legend.setData(legend);
		option.removeSerie(2,3);
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var seriesData0 = [];
		var seriesData1 = [];
		var seriesType0 = [];
		var seriesType1 = [];
		var xAxisData = stableInfluence.getDateFromTo();
		//调用ajax前对开始时间和结束时间做校验
		if(dataFrom > dataTo){
			alert("开始时间不能大于结束时间");
			return;
		}else{
			var content = {
				"startDate":dataFrom,
				"endDate":dataTo
			};
		 	var url = ctx + '/nhFlowInfo/getNhFlowInfo';
		 	kingpoint.postData(url,content,function(data){
		 		if(data!=null && data.length!=0){
		 			//对数据进行分类
					for(var i=0; i<data.length; i++){
						if(data[i].flowType == 0){
							seriesType0.push(data[i]);
						}else if(data[i].flowType == 1){
							seriesType1.push(data[i]);
						}
						console.log(data[i].recordTime);
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
	// 加载流量比情况
	loadCrawlerFlowRate : function(id,dataFrom, dataTo) {
		var option = chartTemplate.createNewLine();
		var myCharts = echarts.init(document.getElementById(id));
		var legend = ["全部流量", "爬虫流量"];
		option.title.setText("流量比");
		option.series[0].setName("全部流量");
		option.series[1].setName("爬虫流量");
		option.legend.setData(legend);
		option.removeSerie(2,3);
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var seriesData0 = [];
		var seriesData1 = [];
		var seriesType0 = [];
		var seriesType1 = [];
		var xAxisData = stableInfluence.getDateFromTo();
		//调用ajax前对开始时间和结束时间做校验
		if(dataFrom > dataTo){
			alert("开始时间不能大于结束时间");
			return;
		}else{
			var content = {
				"startDate":dataFrom,
				"endDate":dataTo
			};
		 	var url = ctx + '/nhFlowInfo/getNhFlowInfoRate';
		 	kingpoint.postData(url,content,function(data){
		 		if(data!=null && data.length!=0){
		 			//对数据进行分类
					for(var i=0; i<data.length; i++){
						if(data[i].flowType == 0){
							seriesType0.push(data[i]);
						}else if(data[i].flowType == 1){
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
	}
};

$(function(){
	stableInfluence.init();
});