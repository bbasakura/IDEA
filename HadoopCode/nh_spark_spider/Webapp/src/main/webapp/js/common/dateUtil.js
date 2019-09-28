// 2017-07-17 时间格式转化成Date类型
function converDate(datestr){
	var temp = datestr.split("-");
	var date = new Date(temp[0],temp[1],temp[2]);
	return date;
}
//是否是闰年
function isLeapYear(iYear) {
	if (iYear % 4 == 0 && iYear % 100 != 0) {
		return true;
	} else {
		if (iYear % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
// 获取某段时间内的具体天数
function getSomeDates(dateFrom, dateTo){
	var date = [];
	startTime = converDate(dateFrom);
	endTime = converDate(dateTo);
	while((endTime.getTime()-startTime.getTime())>=0){
	  var year = startTime.getFullYear();
	  var month = startTime.getMonth().toString().length==1 ? "0"+startTime.getMonth().toString() : startTime.getMonth();
	  var day = startTime.getDate().toString().length==1 ? "0"+startTime.getDate() : startTime.getDate();
	  var a = year+"-"+month+"-"+day;
	  date.push(a);
	  switch(startTime.getMonth()){
	  	case 2 :
	  		if(isLeapYear(year)){	//闰年
	  			if(startTime.getDate()==29){
	  				startTime = new Date(startTime.getFullYear(),startTime.getMonth()+1,1);
	  			}else{
	  				startTime.setDate(startTime.getDate()+1);
	  			}
	  		}else{					
	  			if(startTime.getDate()==28){
	  				startTime = new Date(startTime.getFullYear(),startTime.getMonth()+1,1);
	  			}else{
	  				startTime.setDate(startTime.getDate()+1);
	  			}
	  		}
	  		break;
	  	case 4 :
	  	case 6 :
	  	case 9 :
	  	case 11 : 
	  		if(startTime.getDate()==30){
	  			startTime = new Date(startTime.getFullYear(),startTime.getMonth()+1,1);
	  		}else{
	  			startTime.setDate(startTime.getDate()+1);
	  		}
	  		break;
	  	default:
	  		startTime.setDate(startTime.getDate()+1);
	  }
	}
	return date;
}
// 获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
// 获取当前的日期时间 格式“yyyy-MM-dd”
function getNowDate() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
// 获取当前的日期一周前的日期 格式“yyyy-MM-dd”
function getWeekAgoDate() {
    var now = new Date();
	var date = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
	var year = date.getFullYear();
	var month = date.getMonth() + 1 ;
	var day = date.getDate() ;
	if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    
	var weekAgoDate = year + '-' + month + '-' + day; 
	return weekAgoDate;
}

// 获取昨天的时间值
function getYesterdayDate() {
    var now = new Date();
	var date = new Date(now.getTime() - 1 * 24 * 3600 * 1000);
	var year = date.getFullYear();
	var month = date.getMonth() + 1 ;
	var day = date.getDate() ;
	if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    
	var yesterdayDate = year + '-' + month + '-' + day; 
	return yesterdayDate;
}