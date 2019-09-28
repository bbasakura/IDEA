
var chartTemplate = { 
	//创建饼图
	createNewPie : function(){
		var pieOption = {
			
			color : ['#91c7ae','#d48265','#61a0a8', '#c23531','#2f4554' ,'#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			layoutId : "",	
		    title : {
		        text: '近一周爬虫占流量比情况',
		        x:'center',
		        top:'3%',
		        //设置标题
		        setText : function(titleText){
		        	this.text = titleText;
		        },
		        //设置标题
		        getText : function(){
		        	return this.text;
		        }
		    },
		    toolbox: {
	        	feature: {
	            	saveAsImage: {}
	        	}
	    	},
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: [],
		        //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		    },
		    series : [{
	        	name: '转化率',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '55%'],
	            data:[],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            },
	            //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		    }],
		    //设置颜色
		    setColor : function(colors){
		    	this.color = colors;
		    }
			
		};
		
		return pieOption;
	},
	//创建柱图
	createNewBar : function(){
		var barOption = {
			layoutId : "",	
			color: ['#3398DB'],
			title: {
	        	text: '',
	        	subtext: '',
	//        	x: 'center',
	        	//设置标题
		        setText : function(titleText){
		        	this.text = titleText;
		        },
		        setSubtext : function(titleSubtext){
		        	this.subtext = titleSubtext;
		        },
		        //设置标题
		        getText : function(){
		        	return this.text;
		        }
	    	},
	    	
		    tooltip: {
		    	trigger: 'axis',
		        axisPointer : {            	 // 坐标轴指示器，坐标轴触发有效
	//	            type : 'shadow'          // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    
		    toolbox: {
	        	feature: {
	            	saveAsImage: {
	            		name : "",
	            		setName : function(imageName){
	            			this.name = imageName;
	            		}
	            	}
	        	}
		    },
		    legend: {
		        data:[],
		        
		        //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		    },
		    xAxis: {
		        data: [],
		        name:'',
		        setName : function(sname){
		        	this.name = sname;
		        },
		        //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		    },
		    yAxis: {
		        type: 'value',
		        name:'',
		        setName : function(name){
		        	this.name = name;
		        }
//		    	min : 0,
//		    	max : 1 
		    },
		    series: [
		     {
		        name: '',
		        type: 'bar',
		        data: [],
		        
		        itemStyle:{
					normal:{
//						color:function(d){return "#" + Math.floor(Math.random()*(256*256*256-1)).toString(16);},
//	//					color : '',
//						//设置柱形颜色
//						setColor : function(ncolor){
//							this.color = ncolor;
//						}
					}
				},
		        
		        setName : function(sname){
		        	this.name = sname;
		        },
		        //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		     }
		    ],
		    //设置颜色
		    setColor : function(colors){
		    	this.color = colors;
		    }
		};
		
		return barOption;
	},
	
	//创建折线图
	createNewLine : function(){
		var lineOption = {
			layoutId : "",	
		    title: {
		    	
		        text: '',
	        	subtext: '',
//	        	x: 'center',
	        	//设置标题
		        setText : function(titleText){
		        	this.text = titleText;
		        },
		        //设置标题
		        getText : function(){
		        	return this.text;
		        },
		        setSubtext : function(titleSubtext){
		        	this.subtext = titleSubtext;
		        },
		        //设置显示位置
		        setX : function(x){
		        	this.x = x;
		        }
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:[],
		        
		        //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		    },
		    toolbox: {
	        	feature: {
	            	saveAsImage: {
	            		name : "",
	            		setName : function(imageName){
	            			this.name = imageName;
	            		}
	            	}
	        	}
		    },
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data: [],
		        name:'',
		        //设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        },
		        setName : function(name){
		            	this.name = name;
		        }
		    },
		    yAxis: {
		        type: 'value',
		    	min : null,
				max : null,
		    	name:'',
				setType : function(type){
					this.type = type;
				},
		    	setMin : function(mindata){
		        	this.min = mindata;
		        },
		        setMax : function(maxdata){
		        	this.max = maxdata;
		        },
		        setName : function(name){
		            	this.name = name;
		        }
		        // axisLabel: {
		        //     formatter: '{value} °C'
		        // }
		    },
		    series: [
		        {
		            name:'',
		            type:'line',
		            data:[],
		            
		            setName : function(name){
		            	this.name = name;
		            },
		            setType : function(type){
						this.type = type;
					},
					//设置一组数据
			        setData : function(typeData){
			        	this.data = typeData;
			        },
			        //插入单个数据
			        insertData : function(typeData){
			        	this.data.push(typeData);
			        },
			        //移除所有数据
			        removeData : function(){
			        	this.data = [];
			        }
		        },
		        {
		            name:'',
		            type:'line',
		            data:[],
		            
		            setName : function(name){
		            	this.name = name;
		            },
		            setType : function(type){
						this.type = type;
					},
					
					//设置一组数据
			        setData : function(typeData){
			        	this.data = typeData;
			        },
			        //插入单个数据
			        insertData : function(typeData){
			        	this.data.push(typeData);
			        },
			        //移除所有数据
			        removeData : function(){
			        	this.data = [];
			        }
		        },
		        {
		            name:'',
		            type:'line',
		            data:[],
		            
		            setName : function(name){
		            	this.name = name;
		            },
		            setType : function(type){
						this.type = type;
					},
					//设置一组数据
			        setData : function(typeData){
			        	this.data = typeData;
			        },
			        //插入单个数据
			        insertData : function(typeData){
			        	this.data.push(typeData);
			        },
			        //移除所有数据
			        removeData : function(){
			        	this.data = [];
			        }
		        },
		        {
		            name:'',
		            type:'line',
		            data:[],
		            
		            setName : function(name){
		            	this.name = name;
		            },
		            setType : function(type){
						this.type = type;
					},
					
					//设置一组数据
			        setData : function(typeData){
			        	this.data = typeData;
			        },
			        //插入单个数据
			        insertData : function(typeData){
			        	this.data.push(typeData);
			        },
			        //移除所有数据
			        removeData : function(){
			        	this.data = [];
			        }
		        },
		        {
		            name:'',
		            type:'line',
		            data:[],
		            
		            setName : function(name){
		            	this.name = name;
		            },
		            setType : function(type){
						this.type = type;
					},
					//设置一组数据
			        setData : function(typeData){
			        	this.data = typeData;
			        },
			        //插入单个数据
			        insertData : function(typeData){
			        	this.data.push(typeData);
			        },
			        //移除所有数据
			        removeData : function(){
			        	this.data = [];
			        }
		        }
		    ],
		    
		    //移除数据
	        removeSerie : function(i,j){
	        	this.series.splice(i,j);
	        }
			
		};
		return lineOption;
	},
	//创建漏斗图
	createNewFunnel : function(){
		var funnelOption = {
			title: {
		        text: '',
		        subtext: '',
		        //设置标题
		        setText : function(titleText){
		        	this.text = titleText;
		        },
		        //设置标题
		        getText : function(){
		        	return this.text;
		        },
		        setSubtext : function(titleSubtext){
		        	this.subtext = titleSubtext;
		        }
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    legend: {
		        //data: ['查询','下单','支付'],
		    	data:[],
		    	//设置一组数据
		        setData : function(typeData){
		        	this.data = typeData;
		        },
		        //插入单个数据
		        insertData : function(typeData){
		        	this.data.push(typeData);
		        },
		        //移除所有数据
		        removeData : function(){
		        	this.data = [];
		        }
		        
		    },
		    calculable: true,
		    series: [
		        {
		            name:'转化率',
		            type:'funnel',
		            left: '10%',
		            top: 60,
		            //x2: 80,
		            bottom: 60,
		            width: '80%',
		            // height: {totalHeight} - y - y2,
		            min: 0,
		            max: 100,
		            minSize: '0%',
		            maxSize: '100%',
		            sort: 'descending',
		            gap: 2,
		            label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                },
		                emphasis: {
		                    textStyle: {
		                        fontSize: 20
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    length: 10,
		                    lineStyle: {
		                        width: 1,
		                        type: 'solid'
		                    }
		                }
		            },
		            itemStyle: {
		                normal: {
		                    borderColor: '#fff',
		                    borderWidth: 1
		                }
		            },
//		            data: [
//		                {value: 80, name: '下单'},
//		                {value: 100, name: '查询'},
//		                {value: 60, name: '支付'}
//		            ],
		            data:[],
		            //设置一组数据
			        setData : function(typeData){
			        	this.data = typeData;
			        },
			        //插入单个数据
			        insertData : function(typeData){
			        	this.data.push(typeData);
			        },
			        //移除所有数据
			        removeData : function(){
			        	this.data = [];
			        }
		        }
		    ]
		};
		return 	funnelOption;
	},	
	//创建仪表盘
	createNewGauge:function(){
		var gaugeOption = {
			  	 tooltip : {
						    formatter: "{a} <br/>{b} : {c}"
						  },
			  series: [
						  {
						      name: '仪表盘',
						      setName : function(name){
		            				this.name = name;
		           			  },
						      type: 'gauge',
						      min:0,
						      setMin: function(min){
		            				this.min = min;
		           			  },
            			      max:25,
            			      setMax: function(max){
		            				this.max = max;
		           			  },
            				  radius: '100%',
						      detail: {formatter:'{value}'},
						      axisLine: {            // 坐标轴线
							      lineStyle: {       // 属性lineStyle控制线条样式
							                   width: 6
							                }
							            },
							           pointer: {
							           width:4
							       },
							     axisTick: {            // 坐标轴小标记
								        length: 15,        // 属性length控制线长
								        lineStyle: {       // 属性lineStyle控制线条样式
								        color: 'auto'
								     }
								  },
								splitLine: {           // 分隔线
							         length: 20,         // 属性length控制线长
							         lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
							         color: 'auto'
							       }
							    },
							    title : {
							    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							    fontWeight: 'bolder',
							    fontSize: 10,
							    fontStyle: 'italic'
							}
						 },
					 data: [{value:20 , name: 'Millisecond'}],
					 setData:function(data){
					 	this.data=data
					 }
				 }
			]
		}
		return gaugeOption;
	}
};
