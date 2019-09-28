var dataHandle = {
	
	init : function(){
		dataHandle.addListen();
		dataHandle.displayInputs();
		dataHandle.showJsonView();
		$('.dataHandle-content-one>ul>li>p').click(function(){
			if($(this).hasClass('on')){
				$(this).removeClass('on');
			}else {
				$(this).addClass('on');
			}
		});
		$('.dataHandle-content-one li>div').click(function(){
			$(this).parents('.dataHandle-content-one').find('li>div').removeClass('on');
			$(this).addClass('on');
		});
	},
	
	addListen : function(){
		$("#inland").click(function(){
			dataHandle.showDomesticOneWay();
		});
		$("#international").click(function(){
			dataHandle.showInterOneWay();
		});
		$("#applicationJson").click(function(){
			$("#jsonOrXmlDataType").val("0");
			dataHandle.showJsonView();
		});
		$("#formJson").click(function(){
			$("#jsonOrXmlDataType").val("1");
			dataHandle.showJsonView();
		});
		$("#textXml").click(function(){
			$("#jsonOrXmlDataType").val("2");
			$("#url").removeClass(" label_input_width");
			dataHandle.showXmlView();
		});
		$("#formXml").click(function(){
			$("#jsonOrXmlDataType").val("3");
			$("#url").addClass(" label_input_width");
			dataHandle.showXmlView();
		});
		
		
		
		$("#dataEdit").click(function(){
			$("#dataEdit").hide();
			$("#dataSure").show();
			$("#dataCancel").show();
			dataHandle.playInputs();
		});
	},
	
	//删除已选中的
	removeSelected : function(){
		$(".dataHandle-content-one li>div").parents('.dataHandle-content-one').find('li>div').removeClass('on');
	},
	
	//查询
	query : function(){
		$("#query").addClass("on");
		$("#book").removeClass("on");
		$("#bookOrQuery").val("0");
		dataHandle.showDomesticOneWay();
	},
	//预定
	book : function(){
		$("#book").addClass("on");
		$("#query").removeClass("on");
		$("#bookOrQuery").val("1");
		dataHandle.showDomesticOneWay();
	},
	//国内
	showDomesticOneWay : function(){
		$("#inland").addClass("on");
		$("#international").removeClass("on");
		$("#wayInfo").val("0");
		$("#jsonOrXmlDataType").val("0");
		dataHandle.jsonClickEvent();
	},
	//国际
	showInterOneWay : function(){
		$("#international").addClass("on");
		$("#inland").removeClass("on");
		$("#wayInfo").val("1");
		$("#jsonOrXmlDataType").val("0");
		dataHandle.jsonClickEvent();
	},
	//点击事件
	jsonClickEvent : function(){
		$("#xml").removeClass("checkbox on");
		$("#form").removeClass("checkbox on");
		$("#get").removeClass("checkbox on");
		$("#xml").addClass("checkbox");
		$("#form").addClass("checkbox");
		$("#get").addClass("checkbox");
		$("#json").addClass("checkbox on");
		dataHandle.showJsonView();
	},
	xmlClickEvent : function(){
		$("#json").removeClass("checkbox on");
		$("#form").removeClass("checkbox on");
		$("#get").removeClass("checkbox on");
		$("#json").addClass("checkbox");
		$("#form").addClass("checkbox");
		$("#get").addClass("checkbox");
		$("#xml").addClass("checkbox on");
		dataHandle.showXmlView();
	},
	formClickEvent : function(){
		$("#json").removeClass("checkbox on");
		$("#xml").removeClass("checkbox on");
		$("#get").removeClass("checkbox on");
		$("#json").addClass("checkbox");
		$("#xml").addClass("checkbox");
		$("#get").addClass("checkbox");
		$("#form").addClass("checkbox on");
		$("#url").removeClass(" label_input_width");
		$("#formKey").hide();
		dataHandle.showFormView();
	},
	getClickEvent : function(){
		$("#json").removeClass("checkbox on");
		$("#xml").removeClass("checkbox on");
		$("#form").removeClass("checkbox on");
		$("#json").addClass("checkbox");
		$("#xml").addClass("checkbox");
		$("#form").addClass("checkbox");
		$("#get").addClass("checkbox on");
		$("#url").removeClass(" label_input_width");
		$("#formKey").hide();
		dataHandle.showGetView();
	},
	
	//显示json页面
	showJsonView : function(){
		
		if($("#jsonOrXmlDataType").val() == 0){
			dataHandle.removeSelected();
			$("#url").removeClass(" label_input_width");
			$("#applicationJson").addClass("on");
			$("#formKey").hide();
		}else if($("#jsonOrXmlDataType").val() == 1){
			$("#url").addClass(" label_input_width");
			$("#formKeyName").text("Json键名称");
			$("#formKey").show();
		}
		
		if($("#bookOrQuery").val() == 0){
			
			$("#book_psgType").hide();
			$("#book_bookUserId").hide();
			$("#book_idCard").hide();
			$("#book_contractName").hide();
			$("#book_idType").hide();
			$("#form_dataField").hide();
			$("#book_psgFirName").hide();
			$("#book_psgName").hide();
			$("#book_contractPhone").hide();
			$("#book_cabin").hide();
			$("#query_adultNum").show();
			$("#query_childNum").show();
			$("#query_infantNum").show();
			$("#query_country").show();
			$("#query_travelType").show();
			if($("#wayInfo").val() == 1){
				$("#book_psgFirName").show();
			}else{
				$("#book_psgFirName").hide();
			}
			
		}else if($("#bookOrQuery").val() == 1){
			$("#book_psgType").show();
			$("#book_bookUserId").show();
			$("#book_idCard").show();
			$("#book_contractName").show();
			$("#book_idType").show();
			$("#form_dataField").show();
			$("#book_psgFirName").show();
			$("#book_psgName").show();
			$("#book_contractPhone").show();
			$("#book_cabin").show();
			$("#query_adultNum").hide();
			$("#query_childNum").hide();
			$("#query_infantNum").hide();
			if($("#wayInfo").val() == 1 ){
				$("#query_country").show();
				$("#query_travelType").show();
			}else{
				$("#query_country").hide();
				$("#query_travelType").hide();
			}
			$("#book_psgFirName").show();
			
		}
		
		$("#dataSure").hide();
		$("#dataCancel").hide();
		$("#dataEdit").show();
		dataHandle.load();
		dataHandle.displayInputs();
	},
	//显示xml页面
	showXmlView : function(){
		
		if($("#jsonOrXmlDataType").val() == 2){
			$("#url").removeClass(" label_input_width");
			$("#formKey").hide();
		}else if($("#jsonOrXmlDataType").val() == 3){
			$("#url").addClass(" label_input_width");
			$("#formKeyName").text("Xml键名称");
			$("#formKey").show();
		}
		
		
		if($("#bookOrQuery").val() == 0){
			
			$("#book_psgType").hide();
			$("#book_bookUserId").hide();
			$("#book_idCard").hide();
			$("#book_contractName").hide();
			$("#book_idType").hide();
			$("#form_dataField").hide();
			$("#book_psgFirName").hide();
			$("#book_psgName").hide();
			$("#book_contractPhone").hide();
			$("#book_cabin").hide();
			$("#query_adultNum").show();
			$("#query_childNum").show();
			$("#query_infantNum").show();
			$("#query_country").show();
			$("#query_travelType").show();
			if($("#wayInfo").val() == 1){
				$("#book_psgFirName").show();
			}else{
				$("#book_psgFirName").hide();
			}
			
		}else if($("#bookOrQuery").val() == 1){
			
			$("#book_psgType").show();
			$("#book_bookUserId").show();
			$("#book_idCard").show();
			$("#book_contractName").show();
			$("#book_idType").show();
			$("#form_dataField").show();
			$("#book_psgFirName").show();
			$("#book_psgName").show();
			$("#book_contractPhone").show();
			$("#book_cabin").show();
			$("#query_adultNum").hide();
			$("#query_childNum").hide();
			$("#query_infantNum").hide();
			if($("#wayInfo").val() == 1 ){
				$("#query_country").show();
				$("#query_travelType").show();
			}else{
				$("#query_country").hide();
				$("#query_travelType").hide();
			}
			$("#book_psgFirName").show();
			
		}
		
		$("#dataSure").hide();
		$("#dataCancel").hide();
		$("#dataEdit").show();
		dataHandle.load();
		dataHandle.displayInputs();
	},
	//显示form页面
	showFormView : function(){
		
		$("#jsonOrXmlDataType").val("4");

		if($("#bookOrQuery").val() == 0){
			
			$("#book_psgType").hide();
			$("#book_bookUserId").hide();
			$("#book_idCard").hide();
			$("#book_contractName").hide();
			$("#book_idType").hide();
			$("#form_dataField").hide();
			$("#book_psgFirName").hide();
			$("#book_psgName").hide();
			$("#book_contractPhone").hide();
			$("#book_cabin").hide();
			$("#query_adultNum").show();
			$("#query_childNum").show();
			$("#query_infantNum").show();
			$("#query_country").show();
			$("#query_travelType").show();
			if($("#wayInfo").val() == 1 ){
				$("#book_psgFirName").show();
			}else{
				$("#book_psgFirName").hide();
			}
			
		}else if($("#bookOrQuery").val() == 1){
			
			$("#book_psgType").show();
			$("#book_bookUserId").show();
			$("#book_idCard").show();
			$("#book_contractName").show();
			$("#book_idType").show();
			$("#form_dataField").show();
			$("#book_psgFirName").show();
			$("#book_psgName").show();
			$("#book_contractPhone").show();
			$("#book_cabin").show();
			$("#query_adultNum").hide();
			$("#query_childNum").hide();
			$("#query_infantNum").hide();
			if($("#wayInfo").val() == 1 ){
				$("#query_country").show();
				$("#query_travelType").show();
			}else{
				$("#query_country").hide();
				$("#query_travelType").hide();
			}
			$("#book_psgFirName").show();
			
		}
		
		$("#dataSure").hide();
		$("#dataCancel").hide();
		$("#dataEdit").show();
		dataHandle.load();
		dataHandle.displayInputs();
	},
	//显示get页面
	showGetView : function(){
		
		$("#jsonOrXmlDataType").val("5");
		
		if($("#bookOrQuery").val() == 0){
			
			$("#book_psgType").hide();
			$("#book_bookUserId").hide();
			$("#book_idCard").hide();
			$("#book_contractName").hide();
			$("#book_idType").hide();
			$("#form_dataField").hide();
			$("#book_psgFirName").hide();
			$("#book_psgName").hide();
			$("#book_contractPhone").hide();
			$("#book_cabin").hide();
			$("#query_adultNum").show();
			$("#query_childNum").show();
			$("#query_infantNum").show();
			$("#query_country").show();
			$("#query_travelType").show();
			if($("#wayInfo").val() == 1){
				$("#book_psgFirName").show();
			}else{
				$("#book_psgFirName").hide();
			}
			
		}else if($("#bookOrQuery").val() == 1){
			
			$("#book_psgType").show();
			$("#book_bookUserId").show();
			$("#book_idCard").show();
			$("#book_contractName").show();
			$("#book_idType").show();
			$("#form_dataField").show();
			$("#book_psgFirName").show();
			$("#book_psgName").show();
			$("#book_contractPhone").show();
			$("#book_cabin").show();
			$("#query_adultNum").hide();
			$("#query_childNum").hide();
			$("#query_infantNum").hide();
			if($("#wayInfo").val() == 1){
				$("#query_country").show();
				$("#query_travelType").show();
			}else{
				$("#query_country").hide();
				$("#query_travelType").hide();
			}
			$("#book_psgFirName").show();
			
		}
		
		$("#dataSure").hide();
		$("#dataCancel").hide();
		$("#dataEdit").show();
		dataHandle.load();
		dataHandle.displayInputs();
	},
	//input变成不可编辑状态
	displayInputs : function(){
		var inputs = $("input[type='text']");
		var selects = $("select");
		for(var i =0; i<inputs.length; i++){
			$(inputs[i]).attr("disabled","true");
		}
		for(var i = 0;i<selects.length;i++){
			$(selects[i]).attr("disabled","true");
			$(selects[i]).css('background-color', 'rgb(235, 235, 228)');
		}
	},
	//input变成编辑状态
	playInputs : function(){
		var inputs = $("input[type='text']");
		var selects = $("select");
		for(var i =0; i<inputs.length; i++){
			$(inputs[i]).removeAttr("disabled");
		}
		for(var i = 0;i<selects.length;i++){
			$(selects[i]).removeAttr("disabled");
			$(selects[i]).css('background-color', '#fff');
		}
	},
	
	//填充数据
	paddingData : function(dataType,content,formDataField){
		if(dataType == 0){
			content.isJson = "true";
			content.isApplicationJson = "true";
			content.isNormalGet = "false";
			content.isNormalForm = "false";
			content.isTextXml = "false";
			content.isXml = "false";
		}else if(dataType == 1){
			content.isJson = "true";
			content.isApplicationJson = "false";
			content.isNormalGet = "false";
			content.isNormalForm = "false";
			content.isTextXml = "false";
			content.isXml = "false";
			content.formDataField = formDataField;
		}else if (dataType == 2){
			content.isJson = "false";
			content.isApplicationJson = "false";
			content.isNormalGet = "false";
			content.isNormalForm = "false";
			content.isTextXml = "true";
			content.isXml = "true";
		}else if (dataType == 3){
			content.isJson = "false";
			content.isApplicationJson = "false";
			content.isNormalGet = "false";
			content.isNormalForm = "true";
			content.isTextXml = "false";
			content.isXml = "true";
			content.formDataField = formDataField;
		}else if (dataType == 4){
			content.isJson = "false";
			content.isApplicationJson = "false";
			content.isNormalGet = "false";
			content.isNormalForm = "true";
			content.isTextXml = "false";
			content.isXml = "false";
		}else if (dataType == 5){
			content.isJson = "false";
			content.isApplicationJson = "false";
			content.isNormalGet = "true";
			content.isNormalForm = "false";
			content.isTextXml = "false";
			content.isXml = "false";
		}
		
		return content;
	},
	
	
	saveOrUpdate : function(){
		
		dataHandle.displayInputs();
		
		//解析匹配规则
		var requestMethod = $("#requestMethod").val();
		//航班类型
		var flightType = $("#wayInfo").val();
		//操作类型
		var behaviorType = $("#bookOrQuery").val();
		//数据类型
		var dataType = $("#jsonOrXmlDataType").val();
		//解析规则url
		var requestMatchExpression = $("#requestMatchExpression").val();
		//json或xml数据在form表单数据中的key值
		var formDataField = $("#formDataField").val();
		//购票人id -会员
		var bookBookUserId = $("#bookBookUserId").val();
		//购票人id -非会员
//		var bookBookUnUserId = $("#bookBookUnUserId").val();
		//乘机人名
		var bookPsgName = $("#bookPsgName").val();
		//乘机人类型
		var bookPsgType = $("#bookPsgType").val();
		//证件类型
		var bookIdType = $("#bookIdType").val();
		//乘机人证件号
		var bookIdCard = $("#bookIdCard").val();
		//联系人名
		var bookContractName = $("#bookContractName").val();
		//始发地
		var depCity = $("#depCity").val();
		//联系人手机号
		var bookContractPhone = $("#bookContractPhone").val();
		//目的地-预订
		var arrCity = $("#arrCity").val();
		//起飞时间-预订
		var flightDate = $("#flightDate").val();
		//舱位级别
		var bookCabin = $("#bookCabin").val();
		//成人乘机人数
		var queryAdultNum = $("#queryAdultNum").val();
		//儿童乘机人数
		var queryChildNum = $("#queryChildNum").val();
		//婴儿乘机人数
		var queryInfantNum = $("#queryInfantNum").val();
		//国际-国家
		var queryCountry = $("#queryCountry").val();
		//国际-是否往返
		var queryTravelType = $("#queryTravelType").val();
		//国际-乘机人姓名中的姓
		var bookPsgFirName = $("#bookPsgFirName").val();
		
		var url = ctx + '/datahandle/saveOrUpdateData';
		var content = {};
		
		if(behaviorType == 0){
			content.flightType = flightType;
			content.behaviorType = behaviorType;
			if(dataHandle.dataLong("requestMatchExpression")!=false){
				content.requestMatchExpression = requestMatchExpression;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("requestMethod")!=false){
				content.requestMethod = requestMethod;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("depCity")!=false){
				content.queryDepCity = depCity;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("arrCity")!=false){
				content.queryArrCity = arrCity;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("flightDate")!=false){
				content.queryFlightDate = flightDate;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("queryAdultNum")!=false){
				content.queryAdultNum = queryAdultNum;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("queryChildNum")!=false){
				content.queryChildNum = queryChildNum;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("queryInfantNum")!=false){
				content.queryInfantNum = queryInfantNum;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("queryTravelType")!=false){
				content.queryTravelType = queryTravelType;
			}else{
				dataHandle.playInputs();
				return false;
			}
			
			if(dataHandle.dataLong("queryCountry")!=false){
				content.queryCountry = queryCountry;
			}else{
				dataHandle.playInputs();
				return false;
			}
			content.dataType = dataType;
			if(flightType == 1){
				content.bookPsgFirName = bookPsgFirName;
				if(dataHandle.dataLong("bookPsgFirName")!=false){
					content.bookPsgFirName = bookPsgFirName;
				}else{
					dataHandle.playInputs();
					return false;
				}
			}
			content = dataHandle.paddingData(dataType, content, formDataField);
		}else if(behaviorType == 1){
			content.flightType = flightType;
			content.behaviorType = behaviorType;
			if(dataHandle.dataLong("requestMatchExpression")!=false){
				content.requestMatchExpression = requestMatchExpression;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookBookUserId")!=false){
				content.bookBookUserId = bookBookUserId;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookPsgName")!=false){
				content.bookPsgName = bookPsgName;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookPsgType")!=false){
				content.bookPsgType = bookPsgType;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookIdType")!=false){
				content.bookIdType = bookIdType;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookIdCard")!=false){
				content.bookIdCard = bookIdCard;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookContractName")!=false){
				content.bookContractName = bookContractName;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("depCity")!=false){
				content.bookDepCity = depCity;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookContractPhone")!=false){
				content.bookContractPhone = bookContractPhone;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("arrCity")!=false){
				content.bookArrCity = arrCity;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("flightDate")!=false){
				content.bookFlightDate = flightDate;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookCabin")!=false){
				content.bookCabin = bookCabin;
			}else{
				dataHandle.playInputs();
				return false;
			}
			if(dataHandle.dataLong("bookPsgFirName")!=false){
				content.bookPsgFirName = bookPsgFirName;
			}else{
				dataHandle.playInputs();
				return false;
			}
			content.bookBookUnUserId = "";
			content.requestMethod = requestMethod;
			content.dataType = dataType;
			if (flightType == 1){
				if(dataHandle.dataLong("queryCountry")!=false){
					content.queryCountry = queryCountry;
				}else{
					dataHandle.playInputs();
					return false;
				}
				if(dataHandle.dataLong("queryTravelType")!=false){
					content.queryTravelType = queryTravelType;
				}else{
					dataHandle.playInputs();
					return false;
				}
			}
			content = dataHandle.paddingData(dataType, content, formDataField);
		}

		dataHandle.callAjax(url,content);
	},
	
	//判断数据是否过长
	dataLong : function(id){
		if($("#"+id).val().length > 64){
			alert($($($("#"+id).parent()).prev()).text()+"数据过长");
			return false;
		}
	},
	
	//调用ajax保存数据
	callAjax : function(url,content){
		kingpoint.postData(url,content,function(data){
			if(data.result == '0'){
				alert("修改成功!");
				dataHandle.displayInputs();
				$("#dataSure").hide();
				$("#dataCancel").hide();
				$("#dataEdit").show();
			}else if (data.result == '1'){
				alert("系统错误");
				dataHandle.load();
				dataHandle.displayInputs();
				$("#dataSure").hide();
				$("#dataCancel").hide();
				$("#dataEdit").show();
			}
		});
	},
	
	//用户取消编辑
	cancel : function(){
		dataHandle.displayInputs();
		$("#dataSure").hide();
		$("#dataCancel").hide();
		$("#dataEdit").show();
		dataHandle.load();
	},
	
	load : function(){
		
		var bookOrQuery = $("#bookOrQuery").val();
		var flightType = $("#wayInfo").val();
		var jsonOrXmlDataType = $("#jsonOrXmlDataType").val();
		$.ajax({	
			url : ctx + '/datahandle/getAnalyzerule',
			type : 'post',
			dataType : 'json',
			data : 'behaviorType='+bookOrQuery+'&flightType='+flightType+'&dataType='+jsonOrXmlDataType,
			success : function(data){
				
				//判断加载什么类型的页面
				if(bookOrQuery == '0'){
					
					$("#requestMatchExpression").val(data.requestMatchExpression);
					$("#requestMethod").val(data.requestMethod);
					$("#depCity").val(data.queryDepCity);
					$("#arrCity").val(data.queryArrCity);
					$("#flightDate").val(data.queryFlightDate);
					$("#queryAdultNum").val(data.queryAdultNum);
					$("#queryChildNum").val(data.queryChildNum);
					$("#queryInfantNum").val(data.queryInfantNum);
					$("#queryTravelType").val(data.queryTravelType);
					$("#queryCountry").val(data.queryCountry);
					
					if(flightType == 1){
						$("#bookPsgFirName").val(data.bookPsgFirName);
					}
					if(jsonOrXmlDataType == 1 || jsonOrXmlDataType == 3){
						$("#formDataField").val(data.formDataField);
					}
					
					
				}else if (bookOrQuery == '1'){
					
					$("#requestMatchExpression").val(data.requestMatchExpression);
					$("#bookBookUserId").val(data.bookBookUserId);
					$("#bookPsgName").val(data.bookPsgName);
					$("#bookPsgType").val(data.bookPsgType);
					$("#bookIdType").val(data.bookIdType);
					$("#bookIdCard").val(data.bookIdCard);
					$("#bookContractName").val(data.bookContractName);
					$("#depCity").val(data.bookDepCity);
					$("#bookContractPhone").val(data.bookContractPhone);
					$("#arrCity").val(data.bookArrCity);
					$("#flightDate").val(data.bookFlightDate);
					$("#bookCabin").val(data.bookCabin);
					$("#bookPsgFirName").val(data.bookPsgFirName);
					$("#requestMethod").val(data.requestMethod);
					
					if(flightType == 1){
						$("#queryCountry").val(data.queryCountry);
						$("#queryTravelType").val(data.queryTravelType);
					}
					if(jsonOrXmlDataType == 1 || jsonOrXmlDataType == 3){
						$("#formDataField").val(data.formDataField);
					}
					
				}
			},
			error : function(data){
				alert("系统错误");
			}
		});
	}
};

$(function(){
	// dataHandle.load();
	dataHandle.init();
});