
var visualize = {
	//初始化
	init : function(){
		visualize.initPage();
		visualize.conversionRate();
		visualize.flightQueryRule();
		visualize.occRule();
		visualize.stableInfluence();
		visualize.rateImpact();
		visualize.identifyAnalysis();
	},
	//初始化默认界面
	initPage : function(){
		visualize.selectOn("conversionRate");
		var url = ctx + "/base/to/visualize/conversionRate";
		visualize.loadPage(url);
	},
	//转换率
	conversionRate : function(){
		$("#conversionRate").click(function(){
			visualize.selectOn("conversionRate");
			var url = ctx + "/base/to/visualize/conversionRate";
			visualize.loadPage(url);
		});
	},
	//航班查询爬取规律
	flightQueryRule : function(){
		$("#flightQueryRule").click(function(){
			visualize.selectOn("flightQueryRule");
			var url = ctx + "/base/to/visualize/flightQueryRule";
			visualize.loadPage(url);
		});
	},
	//占座规律
	occRule : function(){
		$("#occRule").click(function(){
			visualize.selectOn("occRule");
			var url = ctx + "/base/to/visualize/occRule";
			visualize.loadPage(url);
		});
	},
	//爬虫对查定比影响
	rateImpact : function(){
		$("#rateImpact").click(function(){
			visualize.selectOn("rateImpact");
			var url = ctx + "/base/to/visualize/rateImpact";
			visualize.loadPage(url);
		});
	},
	//爬虫对系统稳定性影响
	stableInfluence : function(){
		$("#stableInfluence").click(function(){
			visualize.selectOn("stableInfluence");
			var url = ctx + "/base/to/visualize/stableInfluence";
			visualize.loadPage(url);
		});
	},
	//代购识别分析
	identifyAnalysis : function(){
		$("#identifyAnalysis").click(function(){
			visualize.selectOn("identifyAnalysis");
			var url = ctx + "/base/to/visualize/identifyAnalysis";
			visualize.loadPage(url);
		});
	},
	//加载页面
	loadPage : function(url){
		$("#subjectBoxDataVisual").html("");
		$("#subjectBoxDataVisual").load(url);
	},
	//选中某个选项，突出显示
	selectOn : function(id){
		$('#navDataVisual').find('li').each(function() {
            $(this).removeClass("on");
        });
        $('#'+ id).addClass("on");
	}
	
};

$(function(){
	visualize.init();
});