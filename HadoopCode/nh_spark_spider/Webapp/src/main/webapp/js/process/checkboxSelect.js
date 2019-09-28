function checkboxSelectInfo(){
	var flag = false ;
	//判断实时规则的勾选状态
	var rtFlag = false;
	$("#real_time input:checkbox").each(function(){
		if($(this).is(':checked')){
			rtFlag = true;
		}
	});
	//判断准实时规则的勾选状态
	var qrtFlag = false;
	$("#quasi_real-time input:checkbox").each(function(){
		if($(this).is(':checked')){
			qrtFlag = true;
		}
	});
	//判断离线规则的勾选状态
	var olFlag = false;
	$("#off_line input:checkbox").each(function(){
		if($(this).is(':checked')){
			olFlag = true;
		}
	});
	//对 rtFlag、olFlag、qrtFlag三个标识符判断 只有一个为true通过
	if((true==rtFlag && false==qrtFlag && false==olFlag) || (false==rtFlag && true==qrtFlag && false==olFlag)
		|| (false==rtFlag && false==qrtFlag && true==olFlag)){
			flag=true;
	}
	return flag;
}