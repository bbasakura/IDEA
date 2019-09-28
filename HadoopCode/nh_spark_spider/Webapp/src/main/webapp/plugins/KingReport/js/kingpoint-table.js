var tempOption;
var tempData;
var isPaging = false; // 是否分页
var prePageCount = 10; // 每页行数
var serialNumber = false; // 是否显示序号
var isOrdering = false; // 是否排序
var columnArray;

$.fn.extend({
	kingpointTable : function(model) {
		// 加载基础数据
		var option = model.option;
		var data = model.data;
		var id = model.id;
		tempOption = option;
		tempData = data;
		isPaging = option.base.paging.show;
		prePageCount = option.base.paging.count;
		serialNumber = option.base.serialNumber;
		isOrdering = option.base.isOrdering;
		columnArray = orderDisplayColumn(option.column.displayColumn);
		// 增加外部容器
		addContainer($(this), id);
		// 展示标题部分
		 addTitle($("#" + id + "-tb-container"), option.title);
		 renderTitle(option.title, id);

		// 加入表格
		addTable($("#" + id + "-tb-container"), id);
		// 展示表头
		addHeader(option.column.displayColumn, id);
		renderHeader(option.header, id);
		// 加载数据
		loadData(data, option.column.displayColumn, id);
		renderBody(option.body, id);

		// 展示隐藏序列号
		toggleSerialNumber(option.base.serialNumber, id);
		
		//填充色
		renderFullingColor(option.base.fullingColor,id);

		// 添加底部分页
		addFooter(option.base.paging,id);
		
		if(option.highLight.isHighLight){
			addHighLight(option.highLight,id);
		}
	}
});

//展示所有高亮项
function addHighLight(highLight,id){
	updateRangeHighLight(highLight.getNumberColumn(),id);
	updateEqualHighLight(highLight.getTextColumn(),id);
	updateDateHighLight(highLight.getDateColumn(),id);
}

// 增加表格外部容器
function addContainer(obj, id) {
	var htmlText = "<div id='" + id
			+ "-tb-container' class='king-table' style='height:100%;overflow-y:auto'></div>";
	$(obj).html(htmlText);
}

// 获取标题展现的HTML
function addTitle(object, title) {
	var titleHtml = "";
	if (title.text != null) {
		if (isNotEmpty(title.link)) {
			titleHtml = "<div class='kp-title-container'><a href=" + title.link
					+ ">" + title.text + "</a></div>";
		} else {
			titleHtml = "<div class='kp-title-container'>" + option.title.text
					+ "</div>";
		}
	}
	object.append(titleHtml);
}

// 更新标题文字
function updateTitleText(title, id) {
	var titleHtml = "";
	if (isNotEmpty(title.text)) {
		$("#" + id + "-tb-container").find(".kp-title-container").show();
		if (isNotEmpty(title.link)) {
			titleHtml = "<a href=" + title.link + ">" + title.text + "</a>";
		} else {
			titleHtml = title.text;
		}
		if(!isNotEmpty($("#" + id + "-tb-container").find(".kp-title-container"))){
			addTitle($("#" + id + "-tb-container"), title);
		}
		$("#" + id + "-tb-container").find(".kp-title-container").html(titleHtml);
		renderTitle(title, id);
	}else{
		$("#" + id + "-tb-container").find(".kp-title-container").hide();
	}
}

// 渲染标题
function renderTitle(title, id) {
	if (isNotEmpty(title.position)) {
		$("#" + id + "-tb-container").find('.kp-title-container').css(
				'text-align', title.position);
	}

	if (isNotEmpty(title.font.color)) {
		$("#" + id + "-tb-container").find('.kp-title-container').css('color',
				title.font.color);
		$("#" + id + "-tb-container").find('.kp-title-container a').css(
				'color', title.font.color);
	}

	renderBorderAndBg('#' + id + '-tb-container .kp-title-container',
			title.appearance);
	renderFont('#' + id + '-tb-container .kp-title-container', title.font);
}

// 判断是否为空
function isNotEmpty(testString) {
	if (testString == null || testString == ""
			|| typeof (testString) == "undefined") {
		return false;
	} else {
		return true;
	}
}

// 渲染边框和背景
function renderBorderAndBg(selector, appearance) {
	if (isNotEmpty(appearance.background)) {
		/* if(!isNotEmpty($(selector).css('background'))){ */
		$(selector).css('background', appearance.background);
		/* } */
	}
	renderBorder(selector, appearance.border);
}

// 渲染边框
function renderBorder(selector, border) {
	if (isNotEmpty(border.color) & isNotEmpty(border.type)
			& isNotEmpty(border.size)) {
		$(selector).css('border-color', border.color);
		$(selector).css('border-style', border.type);
		$(selector).css('border-width', border.size);
	}
}

// 渲染字体
function renderFont(selector, font) {
	if (isNotEmpty(font.color)) {
		$(selector).css('color', font.color);
	}
	if (isNotEmpty(font.size)) {
		$(selector).css('font-size', font.size);
	}
	if (isNotEmpty(font.style)) {
		$(selector).css('font-style', font.style);
	}
	if (isNotEmpty(font.thickness)) {
		$(selector).css('font-weight', font.thickness);
	}
	if (isNotEmpty(font.textAlignH)) {
		$(selector).css('text-align', font.textAlignH);
	}
	if (isNotEmpty(font.textAlignV)) {
		$(selector).css('vertical-align', font.textAlignV);
	}
}

// 增加表格到容器内
function addTable(objcet, id) {
	objcet
			.append("<table id='"
					+ id
					+ "-kp-table' class='kp-table' border=1><thead></thead><tbody></tbody><footer></footer></table>");
}

// 添加表头
function addHeader(displayColumn, id) {
	console.log("addHeader=>displayColumn",displayColumn);
	var appendString = "<tr>";

	var column = orderDisplayColumn(displayColumn);

	for (var i = 0; i < column.length; i++) {
		// 排序为true，表头加入双箭头
		// 暂时引用bootstrap的图标
		appendString += "<th column="+column[i].name+">"
				+ column[i].alias
				+ "<div class='kp-ordering' order='desc'><span class='glyphicon glyphicon-chevron-up up'></span><span class='glyphicon glyphicon-chevron-down down'></span></div></th>";
	}
	appendString += "</tr>";
	$('#' + id + '-kp-table thead').html(appendString);
	toggleOrder(isOrdering, id);
}

// 显示隐藏排序
function toggleOrder(flag, id) {
	if (flag) {
		$("#" + id + "-kp-table").find(".up").show();
		$("#" + id + "-kp-table").find(".down").show();
	} else {
		$("#" + id + "-kp-table").find(".up").hide();
		$("#" + id + "-kp-table").find(".down").hide();
	}
}

// 渲染表头
function renderHeader(header, id) {
	if (isOrdering) {
		var fontSize = (header.font.size).substring(0,
				(header.font.size.length - 2));
		$("#" + id + "-kp-table").find('.kp-ordering').css('font-size',
				(fontSize / 2) + 'px');
	}
	renderBorderAndBg('#' + id + '-kp-table thead tr', header.appearance);
	renderFont('#' + id + '-kp-table thead tr th', header.font);
}

// 更新行行景色
function updateFullingColor(fullingColor, id) {
	if (isNotEmpty(fullingColor)) { // 当配置文件的fullingColor为true并且是否高亮为false时渲染行变色
		var trs = $('#' + id + '-kp-table tbody').children("tr");
		for (var i = 0; i < trs.length; i++) {
			var fullingColorIndex = i % fullingColor.length;
			$(trs[i]).css('background-color', fullingColor[fullingColorIndex]);
		}
	}
}

/*
 * fullingColor:填充颜色数组
 * id：VisualLayout Id
 * flag:是否展示填充色
 */
function toggleFullingColor(fullingColor,id,flag){
	if(flag){
		updateFullingColor(fullingColor, id);
	}else{
		
	}
}

// 遍历表数据
/*
 * data:将要展示的数据 displayColumn：表头 id:VisualLayout Id
 */
function loadData(data, displayColumn, id) {
	$('#' + id + '-kp-table').children('tbody').children().remove();
	appendString = "";
	if (isNotEmpty(data)) {
		for (var i = 0; i < data.length; i++) {
			// 渲染行背景色
			var fullingColor = tempOption.base.fullingColor;
			if (isNotEmpty(fullingColor) && (!tempOption.highLight.isHighLight)) { // 当配置文件的fullingColor为true并且是否高亮为false时渲染行变色
				var fullingColorIndex = i % fullingColor.length;
				appendString += "<tr style='background:"
						+ fullingColor[fullingColorIndex] + "'>";
			} else {
				appendString += "<tr>";
			}

			// 加载数据
			$.each(displayColumn,function(index,item){
				var field = displayColumn[index].name;
				appendString += "<td>" + data[i][field] + "</td>";
			});

			appendString += "</tr>";
		}
	} else {
		appendString = "加载内容为空";
	}

	$("#" + id + "-kp-table tbody").html(appendString);
}

// 隐藏或显示高亮单元格
function toggleHighLight(flag, id) {
	if (flag) {
		var hs = $("#" + id + "-kp-table").find(".highLight");
		for (var i = 0; i < hs.length; i++) {
			$(hs[i]).css("background-color", $(hs[i]).attr("highLight"));
		}
	} else {
		$("#" + id + "-kp-table").find(".highLight")
				.css("background-color", "");
	}
}

// 更新范围高亮
function updateRangeHighLight(rangeText, id) {
	var rangeTextHL = $("#" + id + "-kp-table tbody").find(".rangeText");
	if (isNotEmpty(rangeTextHL)) {
		$(rangeTextHL).css("background-color", "");
		$(rangeTextHL).removeClass("rangeText");
	}
	if (isNotEmpty(rangeText)) {
		var trs = $("#" + id + "-kp-table tbody").find("tr");
		$.each(rangeText, function(i, obj) {
			var resultIndex = getColumnIndex(obj.column, id);
			if (resultIndex != -1) {
				$.each(trs, function(index, item) {
					var tds = $(item).find("td");
					var targetValue = $(tds[resultIndex]).text();
					if (parseInt(targetValue) >= parseInt(obj.condition.min) && parseInt(targetValue) <= parseInt(obj.condition.max)) {
						$(tds[resultIndex]).css("background-color", obj.color);
						$(tds[resultIndex])
								.attr("class", "highLight rangeText");
						$(tds[resultIndex]).attr("highLight",obj.color);
					}
				});
			}
		});
	}else{
		$(rangeTextHL).css("background-color", "");
		$(rangeTextHL).removeClass("rangeText");
	}
}

// 更新等值高亮
function updateEqualHighLight(equalText, id) {
	var equalTextHL = $("#" + id + "-kp-table tbody").find(".equalText");
	if (equalTextHL.length > 0) {
		$(equalTextHL).css("background-color", "");
		$(equalTextHL).removeClass("equalText");
	}
	if (isNotEmpty(equalText)) {
		var trs = $("#" + id + "-kp-table tbody").find("tr");
		$.each(equalText, function(i, obj) {
			var resultIndex = getColumnIndex(obj.column, id);
			if (resultIndex != -1) {
				$.each(trs, function(index, item) {
					var tds = $(item).find("td");
					var targetValue = $(tds[resultIndex]).text();
					if (obj.condition.filter == "equal") {
						if (targetValue == obj.condition.text) {
							$(tds[resultIndex]).css("background-color",
									obj.color);
							$(tds[resultIndex]).attr("class",
									"highLight equalText");
							$(tds[resultIndex]).attr("highLight",obj.color);
						}
					} else if (obj.condition.filter == "contain") {
						if (targetValue.indexOf(obj.condition.text) >= 0) {
							$(tds[resultIndex]).css("background-color",
									obj.color);
							$(tds[resultIndex]).attr("class",
									"highLight equalText");
							$(tds[resultIndex]).attr("highLight",obj.color);
						}
					}
				});
			}
		});
	} else {
		$(equalTextHL).css("background-color", "");
		$(equalTextHL).removeClass("equalText");
	}
}

// 更新时间高亮
function updateDateHighLight(dateText, id) {
	var dateTextHL = $("#" + id + "-kp-table tbody").find(".dateText");
	if (isNotEmpty(dateTextHL)) {
		$(dateTextHL).css("background-color", "");
		$(dateTextHL).removeClass("dateText");
	}
	if (isNotEmpty(dateText)) {
		var trs = $("#" + id + "-kp-table tbody").find("tr");
		$.each(dateText, function(i, obj) {
			var resultIndex = getColumnIndex(obj.column, id);
			if (resultIndex != -1) {
				$.each(trs, function(index, item) {
					var tds = $(item).find("td");
					var targetValue = $(tds[resultIndex]).text();
					var targetDate=new Date(targetValue);
					var startDate=new Date(obj.condition.start);
					var endDate=new Date(obj.condition.end);
					if (targetDate>=startDate&&targetDate<=endDate) {
						$(tds[resultIndex]).css("background-color", obj.color);
						$(tds[resultIndex])
								.attr("class", "highLight dateText");
						$(tds[resultIndex]).attr("highLight",obj.color);
					}
				});
			}
		});
	} else {
		$(dateTextHL).css("background-color", "");
		$(dateTextHL).removeClass("dateText");
	}
}

// 通过表头来寻找传入的参数所属哪个下标
function getColumnIndex(column, id) {
	var result = -1;
	if (isNotEmpty(column)) {
		var ths = $("#" + id + "-kp-table thead").find("tr>th");
		$.each(ths, function(index, item) {
			if ($(item).text() == column) {
				result = index;
			}
		});
	}
	return result;
}

// 获取高亮单元格的HTML展示文本
function getBlock(row, field) {
	var rangeFilterObject = getRangeFilterObject(field); // 范围过滤条件Object
	var equalFilterObject = getEqualFilterObject(field); // 获取等值条件Object
	if (isNotEmpty(rangeFilterObject)) {
		if (row[field] >= rangeFilterObject.min
				&& row[field] <= rangeFilterObject.max) {
			appendString += "<td class='highLight'style='background-color:"
					+ rangeFilterObject.color + "' highLight='"
					+ rangeFilterObject.color + "'>" + row[field] + "</td>";
		} else {
			appendString += "<td>" + row[field] + "</td>";
		}
	} else if (isNotEmpty(equalFilterObject)) {
		if (equalFilterObject.text == row[field]) {
			appendString += "<td class='highLight' style='background-color:"
					+ equalFilterObject.color + "' highLight='"
					+ equalFilterObject.color + "'>" + row[field] + "</td>";
		} else {
			appendString += "<td>" + row[field] + "</td>";
		}
	} else {
		appendString += "<td>" + row[field] + "</td>";
	}
}

// 获取范围过滤高亮模型
function getRangeFilterObject(field) {
	var rangeFilter = tempOption.highLight.rangeText;
	if (isNotEmpty(rangeFilter)) {
		for (var r = 0; r < rangeFilter.length; r++) {
			if (rangeFilter[r].column == field) {
				return rangeFilter[r];
			}
		}
	}
}

// 获取等值过滤高亮模型
function getEqualFilterObject(field) {
	var equalFilter = tempOption.highLight.equalText;
	if (isNotEmpty(equalFilter)) {
		for (var e = 0; e < equalFilter.length; e++) {
			if (equalFilter[e].column == field) {
				return equalFilter[e];
			}
		}
	}
}

// 渲染表身的行
function renderBody(body) {
	if (isNotEmpty(tempOption.base.fullingColor)) {
		renderBorder('.kp-table tbody tr', tempOption.body.appearance.border);
	} else {
		renderBorderAndBg('.kp-table tbody tr', body.appearance);
	}
	renderFont('.kp-table tbody tr td', body.font);
}

// 整理列显示顺序
function orderDisplayColumn(displayColumn) {
	var result = new Array();
	for (var i = 0; i < displayColumn.length; i++) {
		var obj = {};
		if (isNotEmpty(displayColumn[i].index)) {
			for (var j = 0; j < displayColumn.length; j++) {
				if (displayColumn[j].index == i) {
					obj.name=displayColumn[j].name;
					obj.alias = displayColumn[j].alias;
					continue;
				}
			}
		} else {
			obj.name=displayColumn[i].name;
			obj.alias = displayColumn[i].alias;
		}
		result.push(obj);
	}
	return result;
}

// 文字排序
function characterSort(data, column, type) {
	if (type == 'asc') {
		data.sort(function(a, b) {
			return (a[column] + "").localeCompare((b[column] + ""));
		});
	} else if (type == 'desc') {
		data.sort(function(a, b) {
			return (b[column] + "").localeCompare((a[column] + ""));
		});
	}
	return data;
}

// 排序
function dataSort(data, column, type) {
	resultData = characterSort(data, column, type);
	return resultData;
}

// 改变排序状态
function changeOrderRender(obj, type) {
	if (type == 'desc') {
		$(obj).attr('order', 'asc');
		$(obj).children('.down').hide();
		$(obj).children('.up').show();
	} else if (type == 'asc') {
		$(obj).attr('order', 'desc');
		$(obj).children('.up').hide();
		$(obj).children('.down').show();
	}
}

// 加载分页
function addFooter(paging,id) {
	if (isNotEmpty(paging)) {
		var footer = '<div class="tb-page-container tcdPageCode"></div>';
		$('#'+id+'-tb-container').append(footer);
	}
}

//更新分页信息
/*function updateFooterPaging(total,pageIndex,id){
	var footerText='<ul class="pagination pull-left" style="margin: 0px;">'
		+ '<li><a href="#">&laquo;第一页</a></li>';
		
		+ '<li><a href="#">11</a></li>'
		+ '<li><a href="#">12</a></li>'
		+ '<li class="active"><a href="#">13</a></li>'
		+ '<li><a href="#">14</a></li>'
		+ '<li><a href="#">15</a></li>'
		var pageLiText=""
		footerText+= '<li class="disabled"><a href="#">最后一页&raquo;</a></li>'
		+ '</ul>'
		+ '<div class="fye">'
		+ '<samp>到第</samp>'
		+ '<input type="number">'
		+ '<samp>页</samp>'
		+ '<button type="button" title="button" value="button" >确定</button>'
		+ '<samp>共</samp>' + '<samp>120</samp>' + '<samp>条</samp>'
		+ '</div>';
	$('#'+id+'-tb-container').find(".tb-page-container").html(footerText);
}
*/
function getPageLiText(pageIndex,size,total){
	if(pageIndex>total||pageIndex<1){
		return "";
	}
	
	var left=pageIndex-size;
	var right=pageIndex+size;
	
	if(left<0){
		if(right<=total){
			right=right-left;
			if(right>total){
				right=total-pageIndex;
			}
		}else{
			right=total-pageIndex;
		}
	}
	
	if(right>total){
		
	}
	
	
	
	
}

// 显示与隐藏分页
function togglePageContainer(flag,id) {
	flag ? $("#"+id+"-tb-container").find(".tb-page-container").show() : $("#"+id+"-tb-container").find(".tb-page-container").hide();
}

// 隐藏于显示序号
function toggleSerialNumber(flag, id) {
	if (flag) {
		// 查找表头是否有序列号元素，没有则添加
		ths = $("#" + id + "-kp-table thead>tr").find("th");
		var thsn = $(ths[0]).attr("sn");
		if (thsn != "sn-" + id) {
			var th = $(ths[0]).clone();
			$(th[0]).text("序号");
			$(th[0]).attr("sn", "sn-" + id);
			$("#" + id + "-kp-table thead>tr").prepend(th[0]);
		}
		// 查找表身是否有序列号原素，没有则添加
		var trs = $("#" + id + "-kp-table tbody>tr");
		$.each(trs, function(index, item) {
			var tds = $(item).find("td");
			var tbsn = $(tds[0]).attr("sn");
			if (tbsn != "sn-" + id) {
				var td = $(tds[0]).clone();
				$(td[0]).attr("sn", "sn-" + id);
				$(td[0]).text(index + 1);
				if(isNotEmpty($(td[0]).attr("highLight"))){
					$(td[0]).css("background-color","");
				}
				$(item).prepend(td[0]);
			}
		});
	} else {
		// 查找表头是否存在排序原素，存在则删除
		var ths = $("#" + id + "-kp-table thead>tr").find("th");
		if ($(ths[0]).attr("sn") == "sn-" + id) {
			$(ths[0]).remove();
		}
		// 查找表身是否存在排序原素，存在则删除
		var trs = $("#" + id + "-kp-table tbody>tr");
		$.each(trs, function(index, item) {
			var tds = $(item).find("td");
			if ($(tds[0]).attr("sn") == "sn-" + id) {
				$(tds[0]).remove();
			}
		});
	}
}

//开关行背景色间隔渲染
function renderFullingColor(fullingColor,id){
	var trs = $("#" + id + "-kp-table tbody").find("tr");
	$.each(trs,function(index,item){
		var fullingColorIndex = index % fullingColor.length;
		$(item).css("background-color",fullingColor[fullingColorIndex]);
	});
}

// ---------------------------添加表格事件---------------------------//

// 单元格点击事件
$('tr').on('click', 'td', function() {
	var text = $(this).innerHTML;
//	alert(text);
});
