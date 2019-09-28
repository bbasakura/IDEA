var viewReport = {
	init : function() {
		// 初始化layout布局组件
		var resizeMain = false;
		$(this.element).visualLayout({
					resizeMain : 's',
					maxLayout : true
				});
	},
	params : null,//全局参数，供过滤条件使用，实现行权限
	view : function() {
		this.init();
		var ele = this.element;
		var url = proxyTicket(reportUrl + "/report/loadReport");
		$.ajax({
			url : url,
			type : "POST",
			dataType : "json",
			data : {
				id : this.reportId
			},
			success : function(data) {
				if (data.status == "success") {
					var report = data.result;
					// 设置布局
					$(ele).visualLayout("setData",
							JSON.parse(report.layout));

					var comps = report.components;
					for (var i = 0; i < comps.length; i++) {
						var comp = comps[i];
						var layoutId = comp.layoutId;
						var type = comp.type;
						if (type == "text") {
							var textDiv = $("<DIV>");
							$(ele).find("#" + layoutId).html(comp.chartOpts);
							continue;
						} else if (type == "table") {
							var chartOpts = JSON.parse(comp.chartOpts,
									function(k, v) {
										if (typeof(v) != "undefined"
												&& v != null) {
											if (v.indexOf
													&& v.indexOf('function') > -1) {
												return eval("(function(){return "
														+ v + " })()");
											}
											return v;
										} else {
											return v;
										}
									});
							comp.chartOpts = chartOpts;

							// 初始化表格
							$(ele).find("#" + layoutId).kingpointTable({
										data : comp.chartOpts.data,
										option : comp.chartOpts,
										id : layoutId
									});
						} else {
							var myChart = echarts.init($(ele).find("#" + layoutId)[0]);
							myChart.setOption(JSON.parse(comp.chartOpts));
							Component.loadComponent(comp, myChart, viewReport.params);
						}
					}
				} else {
					openModal("viewTip", "提示", report.result);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				console.log("XMLHttpRequest",XMLHttpRequest);
				console.log("textStatus",textStatus);
				console.log("errorThrown",errorThrown);
			}
		});
	}
};

/**
 * 渲染报表方法
 * reportId:图表的id
 */
$.fn.renderReport = function(reportId, params) {
	viewReport.reportId = reportId;
	viewReport.element = $(this);
	if(params != undefined && params != null) {
		$.extend(viewReport, {params:params});
	}
	viewReport.view();
};