var commonUtil = {
	//找到x轴对应的数据
	calculateValue : function(seriesType, xAxisData){
		var seriesData = [];
		if(seriesType.length!=0){
 			for(var j=0; j<xAxisData.length; j++){
				var temp = 0;
				for(var i=0; i<seriesType.length; i++){
	 				if(seriesType[i].date.indexOf(xAxisData[j]) >= 0){
	 					if(seriesType[i].accuracy != undefined){
	 						seriesData.push(seriesType[i].accuracy);
	 					}else if(seriesType[i].flowtype != undefined){
	 						seriesData.push(seriesType[i].flowtype);
	 					}else if(seriesType[i].modeltype != undefined){
	 						seriesData.push(seriesType[i].timetype);
	 					}else if(seriesType[i].timetype != undefined)
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