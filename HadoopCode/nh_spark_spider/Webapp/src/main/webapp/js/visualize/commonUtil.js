var commonUtil = {
	//找到x轴对应的数据
	calculateValue : function(seriesType, xAxisData){
		var seriesData = [];
		if(seriesType.length!=0){
 			for(var j=0; j<xAxisData.length; j++){
				var temp = 0;
				for(var i=0; i<seriesType.length; i++){
	 				if(seriesType[i].recordTime.indexOf(xAxisData[j]) >= 0){
	 					if(seriesType[i].value != undefined){
	 						seriesData.push(seriesType[i].value);
	 					}else if(seriesType[i].flowValue != undefined){
	 						seriesData.push(seriesType[i].flowValue);
	 					}else if(seriesType[i].conversionRate != undefined){
	 						seriesData.push(seriesType[i].conversionRate);
	 					}
	 					break;
	 				}else{
	 					temp = temp + 1;
	 				}
	 			}
	 			//没有，设值为null
	 			if(temp==seriesType.length){
	 				seriesData.push(null);
	 			}
			}
 		 }
 		 
 		 return seriesData;
	}

}