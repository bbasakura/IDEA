//默认开始时间为一周前，结束时间为当前时间
var startDate = getWeekAgoDate();
var endDate = getNowDate();
//var startDate = "2017-07-01";
//var endDate = "2017-07-20";

var occRule = {
	pageNo : 1,
	pageSize : 20,
	pageCount : 0,
	
	selectTimeFrom:function(){
		startDate = $dp.cal.getDateStr("yyyy-MM-dd");
		var size = $("#selectPage option:selected").val();
		occRule.pageNo = 1;
		occRule.loadDetail(occRule.pageNo, size, startDate, endDate);
	},
	selectTimeTo:function(){
		endDate = $dp.cal.getDateStr("yyyy-MM-dd");
		var size = $("#selectPage option:selected").val();
		occRule.pageNo = 1;
		//occRule.pageSize
		occRule.loadDetail(occRule.pageNo, size, startDate, endDate);
	},
	init : function(){
		$("#chooseTimeFrom").attr("placeholder",getWeekAgoDate());
		$("#chooseTimeTo").attr("placeholder",getNowDate());
//		$("#chooseTimeFrom").attr("placeholder","2017-07-01");
//		$("#chooseTimeTo").attr("placeholder","2017-07-20");
		occRule.loadDetail(occRule.pageNo, occRule.pageSize, startDate, endDate);
	},
	//占座航线排行
	loadDetail : function(pageNo,pageSize,startDate, endDate){
		$.ajax({
			url:ctx + '/occRate/getNhIllegalOccFlightRank',
			type:'post',
			dataType:'json',
			data:{"pages":pageNo,"rows":pageSize,"startDate":startDate, "endDate":endDate},
			success:function(data){
				if(data.seataList != null){
					var content = '';
					fTos = new Array();
					frequencys = new Array();
					for(var i=0;i<data.seataList.length;i++){
						var ydtArray = (data.seataList[i].flightStartTime+"").split(" ")[0].split("-");
						var dataArray = (data.seataList[i].flightStartTime+"").split(" ")[1].split(":");
						var ydtString = ydtArray[0]+"-"+ydtArray[1]+"-"+ydtArray[2];
						var datatime = dataArray[0]+":"+dataArray[1]+":"+dataArray[2];
						var fTo="";
						var frequency = "";
						frequency = ""+data.seataList[i].frequency;
						fTo = data.seataList[i].depairport +"-"+data.seataList[i].arrairport;
						fTos.push(fTo);
						frequencys.push(frequency);
						content +='<tr>'
									+'<td>'+data.seataList[i].depairport+'</td>'
									+'<td>'+data.seataList[i].arrairport+'</td>'
									+'<td>'+data.seataList[i].flightCode+'</td>'
									+'<td>'+data.seataList[i].shipSpace+'</td>'
									+'<td>'+ydtString+'</td>'
									+'<td>'+datatime+'</td>'
									+'+</tr>';
					}
					var myChart = echarts.init(document.getElementById("zhuzhuang-content"));
					// 绘制图表
					myChart.setOption({
						color: ['#3398DB'],
					    title: { text: '占座航线排行' },
					    tooltip: {},
					    xAxis: {
					        data: fTos
					    },
					    yAxis: {
							name:'频次'					    
					    },
					    series: [{
					        
					        type: 'bar',
					        data: frequencys
					    }]
					});
					$("#detailInfo").html(content);
					occRule.pageCount = data.pageCount;
					$("#infoPage").createPage({
						pageCount: occRule.pageCount,
						current : occRule.pageNo,
						backFn : function(p){
							occRule.pageNo=p;
							occRule.loadDetail(occRule.pageNo,$("#selectPage option:selected").val(),startDate,endDate);
						}
					});
				}
			}
		}); 
	}
};

$(function(){
	occRule.loadDetail(occRule.pageNo,occRule.pageSize,startDate, endDate);
	//初始化
	occRule.init();
	//监听分页
	$("#paging select").on("change",function(){
		var size = $("#selectPage option:selected").val();
		occRule.pageNo = 1;
		occRule.loadDetail(occRule.pageNo,size,startDate, endDate);
	});
});