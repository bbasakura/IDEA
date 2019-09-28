//获取高亮CheckBox的值
function getinformation(){
		list = new Array();
		if($("#wayInfo").val()=="0"){
			if($("#checkbox0").is(':checked')){
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg00").val();
				rule.score = $("#rtlastarg0 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg00").val();
				rule.score = $("#rtlastarg0 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox1").is(':checked')){
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg01").val();
				rule.score = $("#rtlastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg01").val();
				rule.score = $("#rtlastarg1 option:selected").text();
			}
			if($("#checkbox2").is(':checked')){
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg02").val();
				rule.score = $("#rtlastarg2 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg02").val();
				rule.score = $("#rtlastarg2 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox3").is(':checked')){
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg03").val();
				rule.score = $("#rtlastarg3 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg03").val();
				rule.score = $("#rtlastarg3 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox4").is(':checked')){
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg04").val();
				rule.arg1 = $("#rtarg24").val();
				rule.score = $("#rtlastarg4 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg04").val();
				rule.arg1 = $("#rtarg24").val();
				rule.score = $("#rtlastarg4 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox5").is(':checked')){
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg05").val();
				rule.score = $("#rtlastarg5 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg05").val();
				rule.score = $("#rtlastarg5 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox6").is(':checked')){
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg06").val();
				rule.score = $("#rtlastarg6 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg06").val();
				rule.score = $("#rtlastarg6 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox7").is(':checked')){
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg07").val();
				rule.score = $("#rtlastarg7 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg07").val();
				rule.score = $("#rtlastarg7 option:selected").text();
				list[list.length] = rule;
			}
		}
		if($("#wayInfo").val()=="1"){
			if($("#qcheckbox0").is(':checked')){
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#qarg00").val();
				rule.score = $("#qlastarg0 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#qarg00").val();
				rule.score = $("#qlastarg0 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox1").is(':checked')){
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg01").val();
				rule.score = $("#qlastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg01").val();
				rule.score = $("#qlastarg1 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox2").is(':checked')){
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg02").val();
				rule.score = $("#qlastarg2 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg02").val();
				rule.score = $("#qlastarg2 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox3").is(':checked')){
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg03").val();
				rule.score = $("#qlastarg3 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg03").val();
				rule.score = $("#qlastarg3 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox4").is(':checked')){
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg04").val();
				rule.score = $("#qlastarg4 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg04").val();
				rule.score = $("#qlastarg4 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox5").is(':checked')){
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg05").val();
				rule.score = $("#qlastarg5 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg05").val();
				rule.score = $("#qlastarg5 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox6").is(':checked')){
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg06").val();
				rule.score = $("#qlastarg6 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg06").val();
				rule.score = $("#qlastarg6 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox7").is(':checked')){
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg07").val();
				rule.score = $("#qlastarg7 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg07").val();
				rule.score = $("#qlastarg7 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox8").is(':checked')){
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg08").val();
				rule.score = $("#qlastarg8 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg08").val();
				rule.score = $("#qlastarg8 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox9").is(':checked')){
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg09").val();
				rule.score = $("#qlastarg9 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg09").val();
				rule.score = $("#qlastarg9 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox10").is(':checked')){
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg010").val();
				rule.score = $("#qlastarg10 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg010").val();
				rule.score = $("#qlastarg10 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox11").is(':checked')){
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg011").val();
				rule.score = $("#qlastarg11 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg011").val();
				rule.score = $("#qlastarg11 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox12").is(':checked')){
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = "";
				rule.score = $("#qlastarg12 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = "";
				rule.score = $("#qlastarg12 option:selected").text();
				list[list.length] = rule;
			}
		}
		if($("#wayInfo").val()=="2"){
			if($("#ocheckbox0").is(':checked')){
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg00").val();
				rule.score = $("#olastarg0 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg00").val();
				rule.score = $("#olastarg0 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox1").is(':checked')){
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg01").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg01").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox2").is(':checked')){
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg02").val();
				rule.score = $("#olastarg2 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg02").val();
				rule.score = $("#olastarg2 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox3").is(':checked')){
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg03").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg03").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox4").is(':checked')){
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg04").val();
				rule.score = $("#olastarg4 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg04").val();
				rule.score = $("#olastarg4 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox5").is(':checked')){
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg05").val();
				rule.arg1 = $("#oarg25").val();
				rule.score = $("#olastarg5 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg05").val();
				rule.arg1 = $("#oarg25").val();
				rule.score = $("#olastarg5 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox6").is(':checked')){
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg06").val();
				rule.score = $("#olastarg6 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg06").val();
				rule.score = $("#olastarg6 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox7").is(':checked')){
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg07").val();
				rule.score = $("#olastarg7 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg07").val();
				rule.score = $("#olastarg7 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox8").is(':checked')){
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg08").val();
				rule.score = $("#olastarg8 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg08").val();
				rule.score = $("#olastarg8 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox9").is(':checked')){
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg09").val();
				rule.score = $("#olastarg9 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg09").val();
				rule.score = $("#olastarg9 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox10").is(':checked')){
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg010").val();
				rule.score = $("#olastarg10 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg010").val();
				rule.score = $("#olastarg10 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox11").is(':checked')){
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg011").val();
				rule.score = $("#olastarg11 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg011").val();
				rule.score = $("#olastarg11 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox12").is(':checked')){
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg012").val();
				rule.score = $("#olastarg12 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg012").val();
				rule.score = $("#olastarg12 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox13").is(':checked')){
				rule = new Object();
				rule.ruleName = 13;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg013").val();
				rule.score = $("#olastarg13 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 13;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg013").val();
				rule.score = $("#olastarg13 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox14").is(':checked')){
				rule = new Object();
				rule.ruleName = 14;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg014").val();
				rule.score = $("#olastarg14 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 14;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg014").val();
				rule.score = $("#olastarg14 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox15").is(':checked')){
				rule = new Object();
				rule.ruleName = 15;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg015").val();
				rule.score = $("#olastarg15 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 15;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg015").val();
				rule.score = $("#olastarg15 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox16").is(':checked')){
				rule = new Object();
				rule.ruleName = 16;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg016").val();
				rule.score = $("#olastarg16 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 16;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg016").val();
				rule.score = $("#olastarg16 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox17").is(':checked')){
				rule = new Object();
				rule.ruleName = 17;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg017").val();
				rule.score = $("#olastarg17 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 17;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg017").val();
				rule.score = $("#olastarg17 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox18").is(':checked')){
				rule = new Object();
				rule.ruleName = 18;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg018").val();
				rule.score = $("#olastarg18 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 18;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg018").val();
				rule.score = $("#olastarg18 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox19").is(':checked')){
				rule = new Object();
				rule.ruleName = 19;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg019").val();
				rule.score = $("#olastarg19 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 19;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg019").val();
				rule.score = $("#olastarg19 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox20").is(':checked')){
				rule = new Object();
				rule.ruleName = 20;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = "";
				rule.score = $("#olastarg20 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 20;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = "";
				rule.score = $("#olastarg20 option:selected").text();
				list[list.length] = rule;
			}
			
		}
		return list
};
//获取所有CheckBox的值
function getAllInformation(){
		list = new Array();
			if($("#checkbox0").is(':checked')){
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg00").val();
				rule.score = $("#rtlastarg0 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg00").val();
				rule.score = $("#rtlastarg0 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox1").is(':checked')){
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg01").val();
				rule.score = $("#rtlastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg01").val();
				rule.score = $("#rtlastarg1 option:selected").text();
			}
			if($("#checkbox2").is(':checked')){
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg02").val();
				rule.score = $("#rtlastarg2 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg02").val();
				rule.score = $("#rtlastarg2 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox3").is(':checked')){
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg03").val();
				rule.score = $("#rtlastarg3 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg03").val();
				rule.score = $("#rtlastarg3 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox4").is(':checked')){
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg04").val();
				rule.arg1 = $("#rtarg24").val();
				rule.score = $("#rtlastarg4 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg04").val();
				rule.arg1 = $("#rtarg24").val();
				rule.score = $("#rtlastarg4 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox5").is(':checked')){
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg05").val();
				rule.score = $("#rtlastarg5 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg05").val();
				rule.score = $("#rtlastarg5 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox6").is(':checked')){
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg06").val();
				rule.score = $("#rtlastarg6 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg06").val();
				rule.score = $("#rtlastarg6 option:selected").text();
				list[list.length] = rule;
			}
			if($("#checkbox7").is(':checked')){
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 0;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg07").val();
				rule.score = $("#rtlastarg7 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 0;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#rtarg07").val();
				rule.score = $("#rtlastarg7 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox0").is(':checked')){
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#qarg00").val();
				rule.score = $("#qlastarg0 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 0;
				rule.arg0 = $("#qarg00").val();
				rule.score = $("#qlastarg0 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox1").is(':checked')){
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg01").val();
				rule.score = $("#qlastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg01").val();
				rule.score = $("#qlastarg1 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox2").is(':checked')){
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg02").val();
				rule.score = $("#qlastarg2 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg02").val();
				rule.score = $("#qlastarg2 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox3").is(':checked')){
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg03").val();
				rule.score = $("#qlastarg3 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg03").val();
				rule.score = $("#qlastarg3 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox4").is(':checked')){
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg04").val();
				rule.score = $("#qlastarg4 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg04").val();
				rule.score = $("#qlastarg4 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox5").is(':checked')){
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg05").val();
				rule.score = $("#qlastarg5 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg05").val();
				rule.score = $("#qlastarg5 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox6").is(':checked')){
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 1;
				rule.status= 0 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg06").val();
				rule.score = $("#qlastarg6 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 1;
				rule.status= 1 ;
				rule.crawlerType= 1;
				rule.arg0 = $("#qarg06").val();
				rule.score = $("#qlastarg6 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox7").is(':checked')){
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg07").val();
				rule.score = $("#qlastarg7 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg07").val();
				rule.score = $("#qlastarg7 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox8").is(':checked')){
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg08").val();
				rule.score = $("#qlastarg8 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg08").val();
				rule.score = $("#qlastarg8 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox9").is(':checked')){
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg09").val();
				rule.score = $("#qlastarg9 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg09").val();
				rule.score = $("#qlastarg9 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox10").is(':checked')){
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg010").val();
				rule.score = $("#qlastarg10 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg010").val();
				rule.score = $("#qlastarg10 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox11").is(':checked')){
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#qarg011").val();
				rule.score = $("#qlastarg11 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#qarg011").val();
				rule.score = $("#qlastarg11 option:selected").text();
				list[list.length] = rule;
			}
			if($("#qcheckbox12").is(':checked')){
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = "";
				rule.score = $("#qlastarg12 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 1;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = "";
				rule.score = $("#qlastarg12 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox0").is(':checked')){
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg00").val();
				rule.score = $("#olastarg0 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 0;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg00").val();
				rule.score = $("#olastarg0 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox1").is(':checked')){
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg01").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 1;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg01").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox2").is(':checked')){
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg02").val();
				rule.score = $("#olastarg2 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 2;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg02").val();
				rule.score = $("#olastarg2 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox3").is(':checked')){
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg03").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 3;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg03").val();
				rule.score = $("#olastarg1 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox4").is(':checked')){
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg04").val();
				rule.score = $("#olastarg4 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 4;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg04").val();
				rule.score = $("#olastarg4 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox5").is(':checked')){
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg05").val();
				rule.arg1 = $("#oarg25").val();
				rule.score = $("#olastarg5 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 5;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg05").val();
				rule.arg1 = $("#oarg25").val();
				rule.score = $("#olastarg5 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox6").is(':checked')){
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg06").val();
				rule.score = $("#olastarg6 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 6;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg06").val();
				rule.score = $("#olastarg6 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox7").is(':checked')){
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg07").val();
				rule.score = $("#olastarg7 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 7;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg07").val();
				rule.score = $("#olastarg7 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox8").is(':checked')){
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 0 ;
				rule.arg0 = $("#oarg08").val();
				rule.score = $("#olastarg8 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 8;
				rule.ruleType = 2;
				rule.crawlerType= 0;
				rule.status= 1 ;
				rule.arg0 = $("#oarg08").val();
				rule.score = $("#olastarg8 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox9").is(':checked')){
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg09").val();
				rule.score = $("#olastarg9 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 9;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg09").val();
				rule.score = $("#olastarg9 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox10").is(':checked')){
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg010").val();
				rule.score = $("#olastarg10 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 10;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg010").val();
				rule.score = $("#olastarg10 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox11").is(':checked')){
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg011").val();
				rule.score = $("#olastarg11 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 11;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg011").val();
				rule.score = $("#olastarg11 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox12").is(':checked')){
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg012").val();
				rule.score = $("#olastarg12 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 12;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg012").val();
				rule.score = $("#olastarg12 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox13").is(':checked')){
				rule = new Object();
				rule.ruleName = 13;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg013").val();
				rule.score = $("#olastarg13 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 13;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg013").val();
				rule.score = $("#olastarg13 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox14").is(':checked')){
				rule = new Object();
				rule.ruleName = 14;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg014").val();
				rule.score = $("#olastarg14 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 14;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg014").val();
				rule.score = $("#olastarg14 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox15").is(':checked')){
				rule = new Object();
				rule.ruleName = 15;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg015").val();
				rule.score = $("#olastarg15 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 15;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg015").val();
				rule.score = $("#olastarg15 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox16").is(':checked')){
				rule = new Object();
				rule.ruleName = 16;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg016").val();
				rule.score = $("#olastarg16 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 16;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg016").val();
				rule.score = $("#olastarg16 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox17").is(':checked')){
				rule = new Object();
				rule.ruleName = 17;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg017").val();
				rule.score = $("#olastarg17 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 17;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg017").val();
				rule.score = $("#olastarg17 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox18").is(':checked')){
				rule = new Object();
				rule.ruleName = 18;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg018").val();
				rule.score = $("#olastarg18 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 18;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg018").val();
				rule.score = $("#olastarg18 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox19").is(':checked')){
				rule = new Object();
				rule.ruleName = 19;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = $("#oarg019").val();
				rule.score = $("#olastarg19 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 19;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = $("#oarg019").val();
				rule.score = $("#olastarg19 option:selected").text();
				list[list.length] = rule;
			}
			if($("#ocheckbox20").is(':checked')){
				rule = new Object();
				rule.ruleName = 20;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 0 ;
				rule.arg0 = "";
				rule.score = $("#olastarg20 option:selected").text();
				list[list.length] = rule;
			}else{
				rule = new Object();
				rule.ruleName = 20;
				rule.ruleType = 2;
				rule.crawlerType= 1;
				rule.status= 1 ;
				rule.arg0 = "";
				rule.score = $("#olastarg20 option:selected").text();
				list[list.length] = rule;
			}
		return list
}