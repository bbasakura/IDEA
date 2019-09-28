var Component = {
	loadComponent : function(component2,myChart,params){
		//将组件数据源中的使用参数的过滤属性替换成实际参数值
		var component = cloneUtil.clone(component2);
		try {
			replaceParams(component.datasource.condition.children,params);
		} catch(err) {
			
		}
		var url = proxyTicket(reportUrl + "/report/loadComponentData");
		kingpoint.postObject(url, component, function(data) {
			var type = component.type;
			//var myChart = option.getChartById(id);
			var chartOption = myChart.getOption();
			try {
				var dimensions = component.datasource.dimensions;
				var measures = component.datasource.measures;
				if(type == "bar" || type == "line") {//目前只支持单个维度
					chartOption.xAxis[0].data = data.dimensionValues[dimensions[0].column];
					chartOption.legend[0].data = [];
					for(var i = 0; i < measures.length; i++) {
						chartOption.legend[0].data[i] = measures[i].alias;
					}
				} else if(type == "pie") {
					chartOption.legend[0].data = data.dimensionValues[dimensions[0].column];
				}
				var series = chartOption.series;
				/*for(var i = 0; i < series.length; i++) {
					series[i].data = data.measureValues[series[i].name];
				}*/
				var measureValues = data.measureValues;
				var j = 0;
				for(var key in measureValues) {
					series[j].data = measureValues[key];
					j++;
				}
			} catch(err) {
				console.log(err);
			}
			myChart.setOption(chartOption);
			//option.put(id,myChart);
		});
	}
}
/**
 * 将参数替换成实际值
 * @param {} conditions
 * @param {} params
 */
var replaceParams = function(conditions, params) {
	if(conditions != undefined && conditions != null && conditions.length > 0) {
		for(var i = 0; i < conditions.length; i++) {
			if(conditions[i].type == "ENTITY" && conditions[i].useVar) {
				var paramName = conditions[i].value;
				if(params != undefined && params[paramName] != null) {
					conditions[i].value = params[paramName];
				} else {
					conditions[i].value = conditions[i].defaultValue;
				}
			} else {
				replaceParams(conditions[i].children,params);
			}
		}
	}
};