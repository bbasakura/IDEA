var modifyRule = {
	ruleType : "",
	// 初始化
	init : function() {
		$("#preStep").hide();
		$("#finshBtn").hide();
		modifyRule.addListen();
	},
	// 点击退出
	exit : function() {
		var url = ctx + "/base/to/process/exitConfirm";
		$("#signOutModel .modal-content").empty();
		$("#signOutModel").css("top", 100 + "px");
		$("#signOutModel .modal-dialog").css("width", 320 + "px");
		$("#signOutModel .modal-content").css("height", 250 + "px");
		$("#signOutModel .modal-content").load(url);
		$("#signOutModel").modal("show");
	},
	// 完成后提示信息
	confirmMsg : function(context) {
		$("#modalPrompt").css("top", 100 + "px");
		$("#modalPrompt .modal-dialog").css("width", 360 + "px");
		$("#modalPrompt .modal-content").css("height", 250 + "px");
		$("#modalPrompt .myRun-content").html(context);
		$("#modalPrompt").modal("show");
	},
	// 下一步 modifyRule.showConStrategyView();
	nextStep : function(flag) {
		if (flag == "1") {
			var sign = true;
			var flag;
			var processName = $("#processName").val();
			list = new Array();
			// 通过回调information.js中的函数获取页面的值 !isNaN(list[i].arg0)
			list = getAllInformation();
			if (processName != "") {
				var tishi = "";
				$("#tishi").html(tishi);
				if (list != null) {
					//对规则信息是否为数字类型进行判断
					for (var i = 0; i < list.length; i++) {
						var arg0 = "";
						var arg1 = "";
						arg0 = list[i].arg0;
						arg1 = list[i].arg1;
						if(undefined == arg1){
							if(arg0!="" ){
								if(isNaN(list[i].arg0)){
									sign=false;
									break;
								}else{
									sign=true;
								}
							}
						}else{
							if (arg0 != "" && arg1 != "") {
								if (!isNaN(arg0) && !isNaN(arg1)) {
									sign = true;
								} else {
									sign = false;
									break;
								}
							}else if(arg0 != ""  && arg1 == ""){
								if(isNaN(arg0)){
									sign = false;
									break;
								}else{
									sign = true;
								}
							}else if(arg0 == ""  && arg1 != ""){
								if(isNaN(arg1)){
									sign = false;
									break;
								}else{
									sign = true;
								}
							}
						}
					}
					//通过回调checkboxSelect.js中的函数获取布尔值做判断
					flag = checkboxSelectInfo();
					if(true==sign && true==flag){
						modifyRule.showConStrategyView();
					}else if(false==sign && true==flag){
						modifyRule.confirmMsg("规则参数只能是数字类型，请修改后再尝试");
						modifyRule.showConRuleView();
					}else if(true==sign && false==flag){
						modifyRule.confirmMsg("规则类型只能选一种并且不能为空，请修改后再尝试");
						modifyRule.showConRuleView();
					}else{
						modifyRule.confirmMsg("规则参数只能是数字类型、规则类型只能选一种并且不能为空，请修改后再尝试");
						modifyRule.showConRuleView();
					}
				}
			} else {
				var tishi = "*流程名称不能为空。";
				$("#tishi").html(tishi);
			}
		}
		if (flag == "2") {
			var signo = true;
			var signc = true;
			var crawlerBlacklistThresholds = $("#crawlerBlacklistThresholds")
					.val();
			var occBlacklistThresholds = $("#occBlacklistThresholds").val();
			if (crawlerBlacklistThresholds != "") {
				if (isNaN(crawlerBlacklistThresholds)) {
					signc = false;
				} else {
					signc = true;
				}
			}
			if (occBlacklistThresholds != "") {
				if (isNaN(occBlacklistThresholds)) {
					signo = false;
				} else {
					signo = true;
				}
			}
			if (signc == true && signo == true) {
				modifyRule.showConModelView();
			} else {
				modifyRule.confirmMsg("阀值参数只能是数字类型");
				//modifyRule.showConRuleView();
			}
		}
	},
	// 上一步
	preStep : function(flag) {
		if (flag == "1") {
			modifyRule.showConRuleView();
		}
		if (flag == "2") {
			modifyRule.showConStrategyView();
		}

	},
	addListen : function() {
		$("#realTime").click(function() {
					modifyRule.showRealTime();
				});
		$("#quasiRealTime").click(function() {
					modifyRule.showQuasiRealTime();
				});
		$("#offLine").click(function() {
					modifyRule.showOffLine();
				});
	},
	showRealTime : function() {
		$("#realTime").addClass("on");
		$("#quasiRealTime").removeClass("on");
		$("#offLine").removeClass("on");
		$("#real_time").show();
		$("#quasi_real-time").hide();
		$("#off_line").hide();
		$("#wayInfo").val("0");
	},
	showQuasiRealTime : function() {
		$("#realTime").removeClass("on");
		$("#quasiRealTime").addClass("on");
		$("#offLine").removeClass("on");
		$("#real_time").hide();
		$("#quasi_real-time").show();
		$("#off_line").hide();
		$("#wayInfo").val("1");
	},
	showOffLine : function() {
		$("#realTime").removeClass("on");
		$("#quasiRealTime").removeClass("on");
		$("#offLine").addClass("on");
		$("#real_time").hide();
		$("#quasi_real-time").hide();
		$("#off_line").show();
		$("#wayInfo").val("2");
	},
	// 显示配置规则页面
	showConRuleView : function() {
		$("#contentTwo").hide();
		$("#contentThree").hide();
		$("#contentOne").show();
		$("#conRule").addClass("on");
		$("#conModel").removeClass("on");
		$("#conStrategy").removeClass("on");

		$("#nextStep").show();
		$("#finshBtn").hide();
		$("#preStep").hide();
		$("#nextStep").attr("onclick", "window.modifyRule.nextStep(1)");
	},
	// 显示配置策略页面
	showConStrategyView : function() {
		$("#contentOne").hide();
		$("#contentTwo").show();
		$("#contentThree").hide();
		$("#conRule").removeClass("on");
		$("#conModel").removeClass("on");
		$("#conStrategy").addClass("on");

		$("#preStep").show();
		$("#nextStep").show();
		$("#finshBtn").hide();
		$("#preStep").attr("onclick", "window.modifyRule.preStep(1)");
		$("#nextStep").attr("onclick", "window.modifyRule.nextStep(2)");

	},
	// 显示配置模型页面
	showConModelView : function() {
		$("#contentOne").hide();
		$("#contentTwo").hide();
		$("#contentThree").show();
		$("#conStrategy").removeClass("on");
		$("#conRule").removeClass("on");
		$("#conModel").addClass("on");

		$("#preStep").show();
		$("#nextStep").hide();
		$("#finshBtn").show();

		$("#preStep").attr("onclick", "window.modifyRule.preStep(2)");
	},
	// 选择模型算法
	selectType : function(res) {
		if (res == '0') {
			document.getElementsByClassName('three-show')[0].style.display = 'block';
			document.getElementsByClassName('three-hide')[0].style.display = 'none';
		} else {
			document.getElementsByClassName('three-show')[0].style.display = 'none';
			document.getElementsByClassName('three-hide')[0].style.display = 'block';
		}
	},
	// 点击完成
	finsh : function() {
		process = new Object();
		var processName = $("#processName").val();
		process.processName = $("#processName").val();
		process.id = id;
		list = new Array();
		list = getinformation();
		nhstrategy = new Object();
		nhstrategy.crawlerBlacklistThresholds = $("#crawlerBlacklistThresholds")
				.val();
		nhstrategy.occBlacklistThresholds = $("#occBlacklistThresholds").val();
		nmodel = new Object();
		if ($("#type-option option:selected").val() == "0") {
			nmodel.type = $("#type-option option:selected").val();
			nmodel.splitWay = $("#splitWay option:selected").val();
			nmodel.treeDepth = $("#treeDepth option:selected").text();
			nmodel.minTreeDepth = $("#minTreeDepth").val();
			nmodel.minInstanceNum = $("#minInstanceNum").val();
			var signh = true;
			if ($("#minTreeDepth").val() != "") {
				if (isNaN($("#minTreeDepth").val())) {
					signh = false;
				}
			}
			var signm = true;
			if ($("#minInstanceNum").val() != "") {
				if (isNaN($("#minInstanceNum").val())) {
					signm = false;
				}
			}
		} else {
			nmodel.type = $("#type-option option:selected").val();
			nmodel.clusterNum = $("#clusterNum").val();
			nmodel.iterNum = $("#iterNum option:selected").text();
			var singc = true;
			if ($("#clusterNum").val() != "") {
				if (isNaN($("#clusterNum").val())) {
					singc = false;
				}
			}
		}
		var signs = false;
		if ($("#type-option option:selected").val() == "0") {
			if (signh == true && signm == true) {
				signs = true;
			}
		} else {
			if (singc == true) {
				signs = true;
			}
		}
		if (signs == true) {
			$.ajax({
						url : ctx + '/process/updateProcess',
						type : 'post',
						dateType : 'json',
						data : {
							"jsonProcessInfo" : JSON.stringify(process),
							"jsonRules" : JSON.stringify(list),
							"jsonModel" : JSON.stringify(nmodel),
							"jsonNhStrategy" : JSON.stringify(nhstrategy)
						},
						success : function(data) {
							if (data.code == "0") {
								proModel.modal_close();
								modifyRule.load();
//								alert(data.msg);
								commonConfirmMsg(data.msg);
							} else {
//								alert(data.msg);
								commonConfirmMsg(data.msg);
							}
						}
					});
		} else {
			modifyRule.confirmMsg("请输入数字");
		}
	},
	// 根据回显流程信息
	info : function() {
		content = "";
		$.ajax({
			url : ctx + '/process/getNhProcessInfoById',
			type : 'post',
			dateType : 'json',
			data : {
				id : id
			},
			success : function(data) {
				ruleType = data.ruleType;
				if (data.ruleType == "0") {
					$("#realTime").addClass("on");
					$("#quasiRealTime").removeClass("on");
					$("#offLine").removeClass("on");
					$("#real_time").show();
					$("#quasi_real-time").hide();
					$("#off_line").hide();
					$("#wayInfo").val("0");
					// 对实时规则数据渲染
					if (data.nhProcessInfo.nhRules != null) {
						for (var i = 0; i < data.nhProcessInfo.nhRules.length; i++) {
							if (data.nhProcessInfo.nhRules[i].ruleName == "0") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox0").prop("checked", true);
								}
								$("#rtarg00")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg0 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "1") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox1").prop("checked", true);
								}
								$("#rtarg01")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg1 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");

							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "2") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox2").prop("checked", true);
								}
								$("#rtarg02")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg2 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "3") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox3").prop("checked", true);
								}
								$("#rtarg03")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg3 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "4") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox4").prop("checked", true);
								}
								$("#rtarg04")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtarg24")
										.val(data.nhProcessInfo.nhRules[i].arg1);
								$("#rtlastarg4 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "5") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox5").prop("checked", true);
								}
								$("#rtarg05")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg5 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "6") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox6").prop("checked", true);
								}
								$("#rtarg06")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg6 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "7") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#checkbox7").prop("checked", true);
								}
								$("#rtarg07")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#rtlastarg7 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
						}
					}
				}
				if (data.ruleType == "1") {
					$("#realTime").removeClass("on");
					$("#quasiRealTime").addClass("on");
					$("#offLine").removeClass("on");
					$("#real_time").hide();
					$("#quasi_real-time").show();
					$("#off_line").hide();
					$("#wayInfo").val("1");
					// 对准实时规则数据渲染
					if (data.nhProcessInfo.nhRules != null) {
						for (var i = 0; i < data.nhProcessInfo.nhRules.length; i++) {
							if (data.nhProcessInfo.nhRules[i].ruleName == "0") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox0").prop("checked", true);
								}
								$("#qarg00")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg0 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "1") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox1").prop("checked", true);
								}
								$("#qarg01")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg1 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "2") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox2").prop("checked", true);
								}
								$("#qarg02")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg2 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");

							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "3") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox3").prop("checked", true);
								}
								$("#qarg03")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg3 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "4") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox4").prop("checked", true);
								}
								$("#qarg04")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg4 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "5") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox5").prop("checked", true);
								}
								$("#qarg05")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg5 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "6") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox6").prop("checked", true);
								}
								$("#qarg06")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg6 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "7") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox7").prop("checked", true);
								}
								$("#qarg07")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg7 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "8") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox8").prop("checked", true);
								}
								$("#qarg08")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg8 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "9") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox9").prop("checked", true);
								}
								$("#qarg09")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg9 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "10") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox10").prop("checked", true);
								}
								$("#qarg010")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg10 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "11") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox11").prop("checked", true);
								}
								$("#qarg011")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#qlastarg11 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "12") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#qcheckbox12").prop("checked", true);
								}
								$("#qlastarg12 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
						}
					}
				}
				if (data.ruleType == "2") {
					$("#realTime").removeClass("on");
					$("#quasiRealTime").removeClass("on");
					$("#offLine").addClass("on");
					$("#real_time").hide();
					$("#quasi_real-time").hide();
					$("#off_line").show();
					$("#wayInfo").val("2");
					// 对离线规则数据渲染
					if (data.nhProcessInfo.nhRules != null) {
						for (var i = 0; i < data.nhProcessInfo.nhRules.length; i++) {
							if (data.nhProcessInfo.nhRules[i].ruleName == "0") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox0").prop("checked", true);
								}
								$("#oarg00")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg0 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "1") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox1").prop("checked", true);
								}
								$("#oarg01")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg1 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "2") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox2").prop("checked", true);
								}
								$("#oarg02")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg2 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");

							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "3") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox3").prop("checked", true);
								}
								$("#oarg03")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg3 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "4") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox4").prop("checked", true);
								}
								$("#oarg04")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg4 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "5") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox5").prop("checked", true);
								}
								$("#oarg05")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#oarg25")
										.val(data.nhProcessInfo.nhRules[i].arg1);
								$("#olastarg5 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "6") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox6").prop("checked", true);
								}
								$("#oarg06")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg6 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "7") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox7").prop("checked", true);
								}
								$("#oarg07")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg7 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "8") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox8").prop("checked", true);
								}
								$("#oarg08")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg8 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "9") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox9").prop("checked", true);
								}
								$("#oarg09")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg9 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "10") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox10").prop("checked", true);
								}
								$("#oarg010")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg10 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "11") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox11").prop("checked", true);
								}
								$("#oarg011")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg11 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "12") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox12").prop("checked", true);
								}
								$("#oarg012")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg12 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "13") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox13").prop("checked", true);
								}
								$("#oarg013")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg13 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "14") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox14").prop("checked", true);
								}
								$("#oarg014")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg14 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "15") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox15").prop("checked", true);
								}
								$("#oarg015")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg15 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "16") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox16").prop("checked", true);
								}
								$("#oarg016")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg16 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "17") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox17").prop("checked", true);
								}
								$("#oarg017")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg17 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "18") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox18").prop("checked", true);
								}
								$("#oarg018")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg18 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "19") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox19").prop("checked", true);
								}
								$("#oarg019")
										.val(data.nhProcessInfo.nhRules[i].arg0);
								$("#olastarg19 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
							if (data.nhProcessInfo.nhRules[i].ruleName == "20") {
								if (data.nhProcessInfo.nhRules[i].status == 0) {
									$("#ocheckbox20").prop("checked", true);
								}
								$("#olastarg20 option[value='"
										+ data.nhProcessInfo.nhRules[i].score
										+ "']").attr("selected", "selected");
							}
						}
					}
				}
				// 对流程信息数据渲染
				$("#processName").val(data.nhProcessInfo.processName);
				// 对模型数据渲染
				modifyRule.selectType(data.nhProcessInfo.nhModel.type);
				if (data.nhProcessInfo.nhModel.type == "0") {
					$("#type-option option[value='"
							+ data.nhProcessInfo.nhModel.type + "']").attr(
							"selected", "selected");
					$("#splitWay option[value='"
							+ data.nhProcessInfo.nhModel.splitWay + "']").attr(
							"selected", "selected");
					$("#treeDepth option:contains('"
							+ data.nhProcessInfo.nhModel.treeDepth + "')")
							.attr("selected", true);
					$("#minTreeDepth")
							.val(data.nhProcessInfo.nhModel.minTreeDepth);
					$("#minInstanceNum")
							.val(data.nhProcessInfo.nhModel.minInstanceNum);
				} else {
					$("#type-option option[value='"
							+ data.nhProcessInfo.nhModel.type + "']").attr(
							"selected", "selected");
					$("#clusterNum").val(data.nhProcessInfo.nhModel.clusterNum);
					$("#iterNum option[text='"
							+ data.nhProcessInfo.nhModel.iterNum + "']").attr(
							"selected", "selected");
				}
				// 对策略信息数据渲染
				$("#crawlerBlacklistThresholds")
						.val(data.nhProcessInfo.nhStrategy.crawlerBlacklistThresholds);
				$("#occBlacklistThresholds")
						.val(data.nhProcessInfo.nhStrategy.occBlacklistThresholds);
			}
		});
	},
	// 渲染数据
	load : function() {
		var content = '';
		var model = '';
		var img = '';
		$.ajax({
			url : ctx + '/process/findAllProcessInfo',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.listInfo.length; i++) {
					if (data.listInfo[i].type == 0) {
						model = "有监督学习";
					} else {
						model = "无监督学习";
					}
					if (data.listInfo[i].status == 0) {
						img = "ico-010.png";
					} else {
						img = "ico-004.png";
					}
					content += '<tr>'
							+ '<td>'
							+ '<p>'
							+ data.listInfo[i].processName
							+ '</p>'
							+ '</td>'
							+ '<td>'
							+ model
							+ '</td>'
							+ '<td>'
							+ data.listInfo[i].createDate
							+ '</td>'
							+ '<td>'
							+ data.listInfo[i].createPerson
							+ '</td>'
							+ '<td class="center">'
							+ '<img onclick="window.runRule(\''
							+ data.listInfo[i].id
							+ '\',\''
							+ data.listInfo[i].status
							+ '\')" src="'
							+ ctx
							+ '/themes/default/images/'
							+ img
							+ '" data-toggle="modal" >'
							+ '<img onclick="window.modify(\''
							+ data.listInfo[i].id
							+ '\')" src="'
							+ ctx
							+ '/themes/default/images/ico-005.png" data-toggle="modal" >'
							+ '<img onclick="window.submitData(\''
							+ data.listInfo[i].id
							+ '\')" src="'
							+ ctx
							+ '/themes/default/images/ico-006.png" data-toggle="modal">'
							+ '</td>' + '</tr>';
				}
				$('#content-body').html(content);
			}
		});
	}

};

$(function() {
			modifyRule.init();
			modifyRule.info();
			modifyRule.load();
		});