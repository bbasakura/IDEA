
var newProcess = {
	//初始化
	init : function(){
		$("#preStep").hide();
		$("#finshBtn").hide();
		newProcess.addListen();
	},
	//点击退出
	exit : function(){
		var url = ctx + "/base/to/process/exitConfirm";
		$("#signOutModel .modal-content").empty();
    	$("#signOutModel").css("top",100+"px");
    	$("#signOutModel .modal-dialog").css("width",360+"px");
    	$("#signOutModel .modal-content").css("height",250+"px");
    	$("#signOutModel .modal-content").load(url);
    	$("#signOutModel").modal("show");
	},
	
	//完成后提示信息
	confirmMsg : function(context){
		$("#modalPrompt").css("top",100+"px");
    	$("#modalPrompt .modal-dialog").css("width",360+"px");
    	$("#modalPrompt .modal-content").css("height",250+"px");
		$("#modalPrompt .myRun-content").html(context);
    	$("#modalPrompt").modal("show");
	},
	
	//下一步
	nextStep : function(flag){
		if(flag=="1"){
			var flag;
			var sign=true;
			var processName = $("#processName").val();
			list = new Array();
			//通过回调information.js中的函数获取页面的值  !isNaN(list[i].arg0) 
			list = getAllInformation();
			//list = getinformation();
			if(processName!=""){
				var tishi = "";
				$("#tishi").html(tishi);
				if(list!=null){
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
						newProcess.showConStrategyView();
					}else if(false==sign && true==flag){
						newProcess.confirmMsg("规则参数只能是数字类型，请修改后再尝试");
						newProcess.showConRuleView();
					}else if(true==sign && false==flag){
						newProcess.confirmMsg("规则类型只能选一种并且不能为空，请修改后再尝试");
						newProcess.showConRuleView();
					}else{
						newProcess.confirmMsg("规则参数只能是数字类型、规则类型只能选一种并且不能为空，请修改后再尝试");
						newProcess.showConRuleView();
					}
				}
			}else{
				var tishi = "*流程名称不能为空。";
				$("#tishi").html(tishi);
			}
		}
		if(flag=="2"){
			var signo = true;
			var signc = true;
			var crawlerBlacklistThresholds = $("#crawlerBlacklistThresholds").val();
			var occBlacklistThresholds = $("#occBlacklistThresholds").val();
			if(crawlerBlacklistThresholds!="" ){
				if(isNaN(crawlerBlacklistThresholds) ){
					 signc=false;
				}else{
					 signc=true;
				}
			}
			if(occBlacklistThresholds!="" ){
				if(isNaN(occBlacklistThresholds) ){
					 signo=false;
				}else{
					 signo=true;
				}
			}
			if(signc==true && signo==true){
				newProcess.showConModelView();
			}else{
				newProcess.confirmMsg("阀值参数只能是数字类型");
				newProcess.showConStrategyView();
			}
		}
	},
	//上一步
	preStep : function(flag){
		if(flag=="1"){
			newProcess.showConRuleView();
		}
		if(flag=="2"){
			newProcess.showConStrategyView();
		}
		
	},
	addListen : function(){
		$("#realTime").click(function(){
			newProcess.showRealTime();
		});
		$("#quasiRealTime").click(function(){
			newProcess.showQuasiRealTime();
		});
		$("#offLine").click(function(){
			newProcess.showOffLine();
		});
	},
	showRealTime : function(){
		$("#realTime").addClass("on");
		$("#quasiRealTime").removeClass("on");
		$("#offLine").removeClass("on");
		$("#real_time").show();
		$("#quasi_real-time").hide();
		$("#off_line").hide();
		$("#wayInfo").val("0");
	},
	showQuasiRealTime : function(){
		$("#realTime").removeClass("on");
		$("#quasiRealTime").addClass("on");
		$("#offLine").removeClass("on");
		$("#real_time").hide();
		$("#quasi_real-time").show();
		$("#off_line").hide();
		$("#wayInfo").val("1");
	},
	showOffLine : function(){
		$("#realTime").removeClass("on");
		$("#quasiRealTime").removeClass("on");
		$("#offLine").addClass("on");
		$("#real_time").hide();
		$("#quasi_real-time").hide();
		$("#off_line").show();
		$("#wayInfo").val("2");
	},
	//显示配置规则页面
	showConRuleView : function(){
		$("#contentTwo").hide();
		$("#contentThree").hide();
		$("#contentOne").show();
		$("#conRule").addClass("on");
		$("#conModel").removeClass("on");
		$("#conStrategy").removeClass("on");
		
		$("#nextStep").show();
		$("#finshBtn").hide();
		$("#preStep").hide();
		$("#nextStep").attr("onclick","window.newProcess.nextStep(1)");
	},
	//显示配置策略页面
	showConStrategyView : function(){
		$("#contentOne").hide();
		$("#contentTwo").show();
		$("#contentThree").hide();
		$("#conRule").removeClass("on");
		$("#conModel").removeClass("on");
		$("#conStrategy").addClass("on");
		
		$("#preStep").show();
		$("#nextStep").show();
		$("#finshBtn").hide();
		$("#preStep").attr("onclick","window.newProcess.preStep(1)");
		$("#nextStep").attr("onclick","window.newProcess.nextStep(2)");
	},
	//显示配置模型页面
	showConModelView : function(){
		$("#contentOne").hide();
		$("#contentTwo").hide();
		$("#contentThree").show();
		$("#conStrategy").removeClass("on");
		$("#conRule").removeClass("on");
		$("#conModel").addClass("on");
		
		$("#preStep").show();
		$("#nextStep").hide();
		$("#finshBtn").show();
		
		$("#preStep").attr("onclick","window.newProcess.preStep(2)");
	},
	//选择模型算法
	selectType : function(res){
		if(res == '0') {
			document.getElementsByClassName('three-show')[0].style.display = 'block';
			document.getElementsByClassName('three-hide')[0].style.display = 'none';
		}else {
			document.getElementsByClassName('three-show')[0].style.display = 'none';
			document.getElementsByClassName('three-hide')[0].style.display = 'block';
		}
	},
	//点击完成
	finsh : function(){
		process = new Object();
		var processName = $("#processName").val();
		process.processName= $("#processName").val();
		list = new Array();
		//通过回调information.js中的函数获取页面的值
		list = getinformation();
		nhstrategy = new Object();
		nhstrategy.crawlerBlacklistThresholds = $("#crawlerBlacklistThresholds").val();
		nhstrategy.occBlacklistThresholds = $("#occBlacklistThresholds").val();
		nmodel = new Object();
		if($("#type-option option:selected").val()=="0"){
			nmodel.type = $("#type-option option:selected").val();
			nmodel.splitWay = $("#splitWay option:selected").val();
			nmodel.treeDepth = $("#treeDepth option:selected").text();
			nmodel.minTreeDepth = $("#minTreeDepth").val();
			nmodel.minInstanceNum = $("#minInstanceNum").val();
			var signh = true;
			if($("#minTreeDepth").val()!=""){
				if(isNaN($("#minTreeDepth").val())){
					signh = false;
				}
			}
			var signm = true;
			if($("#minInstanceNum").val()!=""){
				if(isNaN($("#minInstanceNum").val())){
					signm = false;
				}
			}
		}else{
			nmodel.type = $("#type-option option:selected").val();
			nmodel.clusterNum = $("#clusterNum").val();
			nmodel.iterNum = $("#iterNum option:selected").text();
			var singc = true;
			if($("#clusterNum").val()!=""){
				if(isNaN($("#clusterNum").val())){
					singc = false;
				}
			}
		}
		var signs = false;
		if($("#type-option option:selected").val()=="0"){
			if(signh==true && signm==true){
				signs = true;
			}
		}else{
			if(singc==true){
				signs = true;
			}
		}
		if(signs==true){
			$.ajax({
				url:ctx + '/process/saveProcess',
				type:'post',
				dateType:'json',
				data:{"jsonProcessInfo":JSON.stringify(process),"jsonRules":JSON.stringify(list),
				"jsonModel":JSON.stringify(nmodel),"jsonNhStrategy":JSON.stringify(nhstrategy)},
				success:function(data){
					if(data.code=="0"){
						proModel.modal_close();
						newProcess.info();
//						newProcess.confirmMsg(data.msg);
						
					}else{
//						newProcess.confirmMsg(data.msg);
					}
				}
			});
		}else{
			newProcess.confirmMsg("请输入数字！！！");
		}
		//防止对数据重复提交，对“完成”按钮做定时处理
		$("#finshBtn").each(function(){
			$(this).attr("disabled","disabled");
			setTimeout("$('#finshBtn').removeAttr('disabled')",3000);
		});
	},
	//渲染数据
	info :function(){
		var content='';
		var strategy = $("#form-control option:selected").val();
		var type = $("#form-group option:selected").val();
		var tactics='';
		var model='';
		$.ajax({
			url:ctx + '/process/findAllProcessInfo',
			type:'post',
			dataType:'json',
			data:{strategy:strategy,type:type},
			success:function(data){
				for(var i=0;i<data.listInfo.length;i++){
					if(data.listInfo[i].type==0){
						model="有监督学习";
					}else{
						model="无监督学习";
					}
					if(data.listInfo[i].status==0){
						img="ico-010.png";
					}else{
						img="ico-004.png";
					}
					content +='<tr>'
					+'<td>'
						+'<p>'+data.listInfo[i].processName+'</p>'
					+'</td>'
					+'<td>'+model+'</td>'
					+'<td>'+data.listInfo[i].createDate+'</td>'
					+'<td>'+data.listInfo[i].createPerson+'</td>'
					+'<td class="center">'
						+'<img onclick="window.runRule(\''+data.listInfo[i].id+'\',\''+data.listInfo[i].status+'\')" src="'+ctx+'/themes/default/images/'+img+'" data-toggle="modal" >'
						+'<img onclick="window.modify(\''+data.listInfo[i].id+'\')" src="'+ctx+'/themes/default/images/ico-005.png" data-toggle="modal" >'
						+'<img onclick="window.submitData(\''+data.listInfo[i].id+'\')" src="'+ctx+'/themes/default/images/ico-006.png" data-toggle="modal">'
					+'</td>'
				+'</tr>';
				}
				$('#content-body').html(content);
			}
		})
	}
};


$(function(){
	newProcess.init();
	newProcess.info();
});