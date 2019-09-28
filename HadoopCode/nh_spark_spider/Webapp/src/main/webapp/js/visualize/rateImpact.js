var rateImpact = {
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
		rateImpact.setDateFrom($dp.cal.getDateStr("yyyy-MM-dd"));
		// 重新生成时间时间数组
		rateImpact.setDateFromTo(getSomeDates(rateImpact.getDateFrom(), rateImpact.getDateTo()));
		// 回调加载数据函数
		rateImpact.loadData(rateImpact.getDateFrom(), rateImpact.getDateTo());
	},
	// 选择结束时间后触发
	selectTimeTo : function() {
		// 对结束时间重新set一个值（去除默认时间）
		rateImpact.setDateTo($dp.cal.getDateStr("yyyy-MM-dd"));
		// 重新生成时间时间数组
		rateImpact.setDateFromTo(getSomeDates(rateImpact.getDateFrom(), rateImpact.getDateTo()));
		// 回调加载数据函数
		rateImpact.loadData(rateImpact.getDateFrom(), rateImpact.getDateTo());
	},

	// 初始化
	init : function() {
		rateImpact.initParms();
	},
	// 初始化参数
	initParms : function() {
		$("#chooseTimeFrom").attr("placeholder",getWeekAgoDate());
		$("#chooseTimeTo").attr("placeholder",getNowDate());
		// 获取当前时间
		rateImpact.setDateFrom(getWeekAgoDate());
		// 获取一周前的时间
		rateImpact.setDateTo(getNowDate());
		// 获取默认的七天时间
		rateImpact.setDateFromTo(getSomeDates(rateImpact.getDateFrom(), rateImpact.getDateTo()));
		// 回调加载数据函数
		rateImpact.loadData(rateImpact.getDateFrom(), rateImpact.getDateTo());
	},

	// 加载数据
	loadData : function(dataFrom, dataTo) {
		rateImpact.loadFlowQueryRate("nh-flow-query-rate", dataFrom, dataTo);
		rateImpact.loadCrawlerFlowRate("crawler-flow-rate", dataFrom, dataTo);
	},

	// 加载查定比
	loadFlowQueryRate : function(id,dataFrom, dataTo) {
		var option = chartTemplate.createNewLine();
		var myCharts = echarts.init(document.getElementById(id));
		var legend = ["全部流量", "正常用户","查询用户","","占座用户","代购用户"];
		option.title.setText("查定比");
		option.series[0].setName("全部流量");
		option.series[1].setName("正常用户");
		option.series[2].setName("查询用户");
		option.series[3].setName("占座用户");
		option.series[4].setName("代购用户");
		option.legend.setData(legend);
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var seriesData0 = [];
		var seriesData1 = [];
		var seriesData2 = [];
		var seriesData3 = [];
		var seriesData4 = [];
		var seriesType0 = [];
		var seriesType1 = [];
		var seriesType2 = [];
		var seriesType3 = [];
		var seriesType4 = [];
		var xAxisData = rateImpact.getDateFromTo();
		//调用ajax前对开始时间和结束时间做校验
		if(dataFrom > dataTo){
			alert("开始时间不能大于结束时间");
			return;
		}else{
			var content = {
				"startDate":dataFrom,
				"endDate":dataTo
			};
		 	var url = ctx + '/flowQueryRate/getFlowQueryRate';
		 	kingpoint.postData(url,content,function(data){
		 		if(data!=null && data.length!=0){
		 			//对数据进行分类
					for(var i=0; i<data.length; i++){
						if(data[i].userType == 0){
							seriesType0.push(data[i]);
						}else if(data[i].userType == 1){
							seriesType1.push(data[i]);
						}else if(data[i].userType == 2){
							seriesType2.push(data[i]);
						}else if(data[i].userType == 3){
							seriesType3.push(data[i]);
						}else if(data[i].userType == 4){
							seriesType4.push(data[i]);
						}
					}
			 		//找到x轴对应的数据 
			 		seriesData0 = commonUtil.calculateValue(seriesType0,xAxisData);
			 		seriesData1 = commonUtil.calculateValue(seriesType1,xAxisData);
			 		seriesData2 = commonUtil.calculateValue(seriesType2,xAxisData);
			 		seriesData3 = commonUtil.calculateValue(seriesType3,xAxisData);
			 		seriesData4 = commonUtil.calculateValue(seriesType4,xAxisData);
			 	}
		 		option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData0);
				option.series[1].setData(seriesData1);
				option.series[2].setData(seriesData2);
				option.series[3].setData(seriesData3);
				option.series[4].setData(seriesData4);
				myCharts.setOption(option);
		 	});
		}
	 	option.xAxis.setData(xAxisData);
		option.series[0].setData(seriesData0);
		option.series[1].setData(seriesData1);
		option.series[2].setData(seriesData2);
		option.series[3].setData(seriesData3);
		option.series[4].setData(seriesData4);
	 	myCharts.setOption(option);
	},
	// 加载四类用户
	loadCrawlerFlowRate : function(id,dataFrom, dataTo) {
		var option = chartTemplate.createNewLine();
		var myCharts = echarts.init(document.getElementById(id));
		var legend = ["正常用户","查询用户","占座用户","代购用户"];
		option.title.setText("四类用户");
		option.series[0].setName("正常用户");
		option.series[1].setName("查询用户");
		option.series[2].setName("占座用户");
		option.series[3].setName("代购用户");
		option.legend.setData(legend);
		option.removeSerie(4,1);
		option.toolbox.feature.saveAsImage.setName(option.title.getText() + getNowFormatDate());
		
		var seriesData0 = [];
		var seriesData1 = [];
		var seriesData2 = [];
		var seriesData3 = [];
		var seriesType0 = [];
		var seriesType1 = [];
		var seriesType2 = [];
		var seriesType3 = [];
		var xAxisData = rateImpact.getDateFromTo();
		//调用ajax前对开始时间和结束时间做校验
		if(dataFrom > dataTo){
			alert("开始时间不能大于结束时间");
			return;
		}else{
			var content = {
				"startDate":dataFrom,
				"endDate":dataTo
			};
		 	var url = ctx + '/flowQueryRate/getNhFourFlowNum';
		 	kingpoint.postData(url,content,function(data){
		 		if(data!=null && data.length!=0){
		 			//对数据进行分类
					for(var i=0; i<data.length; i++){
						if(data[i].type == 0){
							seriesType0.push(data[i]);
						}else if(data[i].type == 1){
							seriesType1.push(data[i]);
						}else if(data[i].type == 2){
							seriesType2.push(data[i]);
						}else if(data[i].type == 3){
							seriesType3.push(data[i]);
						}
					}
			 		//找到x轴对应的数据 
			 		seriesData0 = commonUtil.calculateValue(seriesType0,xAxisData);
			 		seriesData1 = commonUtil.calculateValue(seriesType1,xAxisData);
			 		seriesData2 = commonUtil.calculateValue(seriesType2,xAxisData);
			 		seriesData3 = commonUtil.calculateValue(seriesType3,xAxisData);
			 	}
		 		option.xAxis.setData(xAxisData);
				option.series[0].setData(seriesData0);
				option.series[1].setData(seriesData1);
				option.series[2].setData(seriesData2);
				option.series[3].setData(seriesData3);
				myCharts.setOption(option);
		 	});
		}
	 	option.xAxis.setData(xAxisData);
		option.series[0].setData(seriesData0);
		option.series[1].setData(seriesData1);
		option.series[2].setData(seriesData2);
		option.series[3].setData(seriesData3);
	 	myCharts.setOption(option);
	}
};

$(function() {
	rateImpact.init();
});