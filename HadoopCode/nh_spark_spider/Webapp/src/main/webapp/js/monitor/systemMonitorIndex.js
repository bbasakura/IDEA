var systemMonitorIndex = {
	sumSourceCount:"",
	setSumSourceCount:function(sumSourceCount){
		this.sumSourceCount=sumSourceCount;
	},
	getSumSourceCount:function(){
		return this.sumSourceCount;
	},
	//初始化
	init : function(){
		systemMonitorIndex.loadSystemFunctionInfo();
		systemMonitorIndex.loadFlows();
		systemMonitorIndex.loadDiscernCondition();
		systemMonitorIndex.loadFlowTransmit();
		//refreshSystemMonitorFlag在 commonIndex.js定义
		if(null == refreshSystemMonitorFlag){
			setInterval(systemMonitorIndex.loadFlows,60000);  			//定时60s刷新一次
			setInterval(systemMonitorIndex.loadFlowTransmit,60000); 	//定时60s刷新一次
			refreshSystemMonitorFlag = "HAVE_REFRESH";
		}
	},
	//系统功能运行情况
	loadSystemFunctionInfo:function(){
		var content ='';
		$.ajax({
			url : ctx +'/systemMonitoring/getSystemFunctionInfo',
			type : 'get',
			dataType : 'json',
			success:function(data){
				if(data.sign == 1){
					//异常css style="background:#f94325;"
					document.getElementById('dataHandl').style.background="#f94325";
				}else if(data.sign==0){
					//正常css style="background:#58aea5;"
					document.getElementById('dataHandl').style.background="#58aea5";
				}
			}
		});
	},
	//加载爬虫识别情况
	loadDiscernCondition : function(){
		var ydtString='';
		$.ajax({
			url : ctx +'/systemMonitoring/getNhCrawlerCurdayInfoByDate',
			type : 'post',
			dataType : 'json',
			success:function(data){
				var myDate = new Date();
				if(0==(myDate.getDate()-1)){
					myDate.setTime(myDate.getTime()-24*60*60*1000);
					ydtString = myDate.getFullYear()+"年" + (myDate.getMonth()+1) + "月" + myDate.getDate()+"日";
				}else{
					ydtString=myDate.getFullYear()+"年"+(myDate.getMonth()+1)+"月"+(myDate.getDate()-1)+"日";
				}
				crawlerCurdayInfoHtml = '<li>'+ydtString+'</li>';
				if(0 == data.code){
					var crawlerCurdayInfoHtml = '';
						if(null!=data.flownum){
							crawlerCurdayInfoHtml+='<li>当日流量：<span class="lanse">'+data.flownum+'</span></li>';
						}else{
							crawlerCurdayInfoHtml+='<li>当日流量：<span class="lanse">暂无数据</span></li>';
						}
						if(null!=data.spiderVO.numspider){
							crawlerCurdayInfoHtml+='<li>当日识别爬虫：<span class="lanse">'+data.spiderVO.numspider+'</span></li>';
						}else{
							crawlerCurdayInfoHtml+='<li>当日识别爬虫：<span class="lanse">暂无数据</span></li>';
						}
						if(null!=data.spiderVO.sumspider){
							crawlerCurdayInfoHtml+='<li>累计识别爬虫：<span class="lanse">'+data.spiderVO.sumspider+'</span></li>';
						}else{
							crawlerCurdayInfoHtml+='<li>累计识别爬虫：<span class="lanse">暂无数据</span></li>';
						}
					$("#crawler-curday-info").html(crawlerCurdayInfoHtml);
				}else if(1 == data.code){
					crawlerCurdayInfoHtml+='<li>当日流量：<span class="lanse">暂无数据</span></li>';
					crawlerCurdayInfoHtml+='<li>当日识别爬虫：<span class="lanse">暂无数据</span></li>';
					crawlerCurdayInfoHtml+='<li>累计识别爬虫：<span class="lanse">暂无数据</span></li>';
					$("#crawler-curday-info").html(crawlerCurdayInfoHtml);
				}
			}
		})
	},
	//加载各链路流量转发情况
	loadFlowTransmit : function(){
		var worldMapContainer = document.getElementById('flow-transmit');
		//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
		var resizeWorldMapContainer = function () {
		    worldMapContainer.style.height = 520 +'px';
		};
		//设置容器高宽
		resizeWorldMapContainer();
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('flow-transmit'));
        // 指定图表的配置项和数据
        var option = {
        	    color: ['#3398DB'],
        	    tooltip : {
        	        trigger: 'axis',
        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	        }
        	    },
        	    title: {
	          		text: '各链路流量转发情况',
	          		x: 'center'
	       		},
        	    grid: {
        	        left: '3%',
        	        right: '4%',
        	        bottom: '3%',
        	        containLabel: true
        	    },
        	    xAxis : [
        	        {
        	        	name:'链路',
        	            type : 'category',
        	            data : [],
//        	            data : ['1#', '2#', '3#', '4#', '5#', '6#', '7#', '8#', '9#', '10#'],
        	             //设置一组数据
				        setData : function(typeData){
				        	this.data = typeData;
				        }
        	        }
        	    ],
        	    yAxis : [
        	        {
        	        	name:'转发量',
        	            type : 'value'
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'流量转发情况',
        	            type:'bar',
        	            barWidth: '40%',
        	            data : [],
//        	            data:[1000, 5200, 2000, 3340, 3900, 3300, 2200, 1230, 3450, 220],
        	            setData : function(_data){
				  	  		this.data = _data;
				  		}	
        	        }
        	    ]
        	};
        var url = ctx + '/systemMonitoring/getRealTimeLinkTraffic';
	  	kingpoint.postData(url,null,function(data){
		  	if(data!=null){
		  		option.series[0].setData(data.value);
		  		option.xAxis[0].setData(data.key);
			  	myChart.setOption(option, true);
		  	}
	  	});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	},
	loadFlows : function(){
		var sum = "";
		var flowContainer = document.getElementById('contentLeft1');
		//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
		var resizeflowContainer = function () {
		    flowContainer.style.height = 505 +'px';
		};
		//设置容器高宽
		resizeflowContainer();
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(flowContainer);
		var timeData = [];
//		var timeData = ['3:00', '6:00', '9:00', '12:00', '15:00', '18:00','21:00','24:00','3:00'];
		option = {
		      title: {
		          text: '实时流量转发情况',
		          x: 'center'
		      },
		      tooltip: {
		          trigger: 'axis',
		
		          axisPointer: {
		              animation: false
		          }
		      },
		      legend: {
		          data: ['流量'],
		          x: 'left'
		      },
		      toolbox: {
		          feature: {
		              saveAsImage: {}
		          }
		      },
		      axisPointer: {
		          link: {
		              xAxisIndex: 'all'
		          }
		      },
		      grid: [{
		          left: 40,
		          right: 40
		      }, {
		          left: 40,
		          right: 40
		      }],
		      xAxis: [{
		          type: 'category',
		          boundaryGap: false,
		          axisLine: {
		              onZero: true
		          },
		          data: timeData,
		          setData : function(_data){
				  	  this.data = _data;
				  }	
		      }],
		         grid: { 		// 控制图的大小，调整下面这些值就可以，
	              x: 60,
	              x2: 50,
	              y2: 100		// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
   				 },
		      yAxis: [{
			      interval:0,  
	              rotate:0,		//旋转角度，为0表示水平  
	              margin:8,
		          type: 'value'
		      }],
		      series: [{
		          name: '数值',
		          type: 'line',
		          smooth: true,
		          symbol: 'circle',
		          symbolSize: 9,
		          showSymbol: false,
		          lineStyle: {
		              normal: {
		                  color: '#48D1CC',
		                  width: 2
		              }
		          },
		          data: [],
//		          data: [13, 52, 23, 22, 52, 32, 123, 25, 73],
				  setData : function(_data){
				  	  this.data = _data;
				  }	
		      }]
		  };
		$.ajax({
	  		url : ctx + '/systemMonitoring/getRealTimeTraffic',
			type:'post',
			dataType:'json',
			async:false, 
			success:function(data){
				sum = data.sum;
			  	if(data != null){
			  		option.xAxis[0].setData(data.time);
				  	option.series[0].setData(data.value);
				  	myChart.setOption(option, true);
			  	}
			}
	  });
	  myChart.setOption(option, true);
	  //用于使chart自适应高度和宽度
	  window.onresize = function () {
	     //重置容器高宽
	     resizeflowContainer();
	     myChart.resize();
	  };
//	   systemMonitorIndex.setSumSourceCount(sum);
//	   sumSourceCount=sum;
	}
};

$(function(){
	systemMonitorIndex.init();
});