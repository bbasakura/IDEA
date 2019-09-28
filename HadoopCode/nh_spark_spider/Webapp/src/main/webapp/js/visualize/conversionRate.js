var conversionRate = {
	 dateFrom : "" ,
	 
	 setDateFrom : function(date){
	 	this.dateFrom = date;
	 },
	 getDateFrom : function(){
	 	return this.dateFrom;
	 },
	
	//选择开始时间后触发
	selectTimeFrom : function(){
		//对开始时间重新set一个值（去除默认时间）
		conversionRate.setDateFrom($dp.cal.getDateStr("yyyy-MM-dd"));
		dateFrom = $("#chooseTimeFrom").val();
		var flightType = $("#percentConversion ").val();
		var userType = $("#userType").val();
		var querySpiderType = $("#reptileType").val();
		conversionRate.getDomesticInterConversionRate("domesticInterConversionRate",dateFrom,flightType);
		conversionRate.getUserConversionRate("userConversionRate",dateFrom,userType);
		conversionRate.getFlightQueryConversionRate("flightQueryConversionRate",dateFrom,querySpiderType);
	},
	
	//初始化
	init : function(){
		conversionRate.initParms();
	},
	//初始化参数
	initParms : function(){
		$("#chooseTimeFrom").attr("placeholder",getYesterdayDate());
		conversionRate.setDateFrom(getYesterdayDate());
		//回调加载数据函数
		conversionRate.loadData(conversionRate.getDateFrom());
	},
	
	//加载数据
	loadData : function(dataFrom){
		conversionRate.getDomesticInterConversionRate("domesticInterConversionRate",dataFrom);
		conversionRate.getUserConversionRate("userConversionRate",dataFrom);
		conversionRate.getFlightQueryConversionRate("flightQueryConversionRate",dataFrom);
	},
	//获取国内、国际转化率
	getDomesticInterConversionRate : function(id,dataFrom,flightType){
		var option = chartTemplate.createNewFunnel();
		var myCharts = echarts.init(document.getElementById(id));
		var flightType = $("#percentConversion ").val();
		var content = {
				"startDate":dataFrom,
				"flightType":flightType
			};
		/*var legend = ["查询", "下单","支付"];
		option.legend.setData(legend);*/
	 	var url = ctx +'/conversionRate/getNhDomesticInterConversionRate';
	 	kingpoint.postData(url,content,function(data){
	 		if(null!=data){
				rList = new Array();
				for(var i=0;i<data.length;i++){
					var typeName = "";
					if(data[i].stepType==0){
						typeName='查询';
					}else if(data[i].stepType==1){
						typeName='下单';
					}else if(data[i].stepType==2){
						typeName='支付';
					}
					var legend = ["查询", "下单","支付"];
					option.legend.setData(legend);
					info = new Object();
					info.value = data[i].conversionValue * 100;
					info.name = typeName;
					option.series[0].data.push(info);
				}
				myCharts.setOption(option);
			}
		});

	},
	//用户转化率
	getUserConversionRate: function(id,dataFrom,userType){
		var option = chartTemplate.createNewFunnel();
		var myCharts = echarts.init(document.getElementById(id));
		var userType = $("#userType").val();
		var content = {
			"startDate":dataFrom,
			"userType":userType
		};
	    var url = ctx +'/conversionRate/getNhUserConversionRate';
	 	kingpoint.postData(url,content,function(data){
	 		if(null!=data){
				rList = new Array();
				for(var i=0;i<data.length;i++){
					var typeName = "";
					if(data[i].stepType==0){
						typeName='查询';
					}else if(data[i].stepType==1){
						typeName='下单';
					}else if(data[i].stepType==2){
						typeName='支付';
					}
					var legendData = ["查询","下单","支付"];
					option.legend.data.push(legendData);
					info = new Object();
					info.value = data[i].conversionValue * 100;
					info.name = typeName;
					option.series[0].data.push(info);
				}
				myCharts.setOption(option);
			}
		});
	},
	//排除/未排除航班查询爬虫转化率
	getFlightQueryConversionRate : function(id,dataFrom){
		var option = chartTemplate.createNewFunnel();
		var myCharts = echarts.init(document.getElementById(id));
		var querySpiderType = $("#reptileType").val();
		var content = {
			"startDate":dataFrom,
			"querySpiderType":querySpiderType
		};
	    var url = ctx +'/conversionRate/getNhFlightQueryConversionRate';
	 	kingpoint.postData(url,content,function(data){
	 		if(null!=data){
				rList = new Array();
				for(var i=0;i<data.length;i++){
					var typeName = "";
					if(data[i].stepType==0){
						typeName='查询';
					}else if(data[i].stepType==1){
						typeName='下单';
					}else if(data[i].stepType==2){
						typeName='支付';
					}
					var legendData = ["查询","下单","支付"];
					option.legend.data.push(legendData);
					info = new Object();
					info.value = data[i].conversionValue * 100;
					info.name = typeName;
					option.series[0].data.push(info);
				}
				myCharts.setOption(option);
			}
		});
		myCharts.setOption(option);
	}
//	//排除/未排除恶意占座爬虫转化率
//	getOccConversionRate : function(id,dataFrom){
//		var option = chartTemplate.createNewFunnel();
//		var myCharts = echarts.init(document.getElementById(id));
//		myCharts.setOption(option);
//	}
};
function selectPercentConversion(){
	var objS = document.getElementById("percentConversion");
    var flightType = objS.options[objS.selectedIndex].value;
    conversionRate.getDomesticInterConversionRate("domesticInterConversionRate",conversionRate.dateFrom,flightType);
}
function selectUserType(){
	var objS = document.getElementById("userType");
    var userType = objS.options[objS.selectedIndex].value;
    conversionRate.getUserConversionRate("userConversionRate",conversionRate.dateFrom,userType);
}
function selectReptileType(){
	var objS = document.getElementById("reptileType");
    var querySpiderType = objS.options[objS.selectedIndex].value;
    conversionRate.getFlightQueryConversionRate("flightQueryConversionRate",conversionRate.dateFrom,querySpiderType)
}
$(function(){
	conversionRate.init();
});