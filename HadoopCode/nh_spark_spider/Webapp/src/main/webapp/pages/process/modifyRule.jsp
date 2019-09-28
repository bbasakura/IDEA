<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
  	<script type="text/javascript">
  		var id = '${id}';
  	</script>
  	<script type="text/javascript" src="${ctx}/js/process/modifyRule.js"></script>
  	<script type="text/javascript" src="${ctx}/js/process/information.js"></script>
  	<script type="text/javascript" src="${ctx}/js/process/checkboxSelect.js"></script>
  </head>
  <body>
  <!-- <div class="modal" id="signOutModel" tabindex="-1" role="dialog" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
			
			</div>
		</div>
 </div> -->
  <jsp:include page="../model/exitModel.jsp"></jsp:include>
  <div id="myModify">
  	<div class="modal-body">
  		<div class="myModify-content-nav">
			<ul class="myModify-nav">			
				<li id="conRule" class="on">
					配置规则
					<p>
						<span>1</span>
					</p>
				</li>
				<li id="conStrategy">
					配置策略
					<p>
						<span>2</span>
					</p>
				</li>
				<li id="conModel">
					配置模型
					<p>
						<span>3</span>
					</p>
				</li>
			</ul>
		</div>
		<div id="contentOne" class="myModify-content-one">
			<p>流程名称<input type="text"  id="processName">
				<span style="color:red;" id="tishi"></span>
			</p>
			<div class="myModify-nav-li">
				<!-- id为wayInfo用于保存对应实时规则，准实时规则,离线规则-->
				<input id="wayInfo" type="checkbox" value="0" style="display: none;">
				<ul>
					<li class="on" id="realTime">
						<p>实时规则</p>
					</li>
					<li id="quasiRealTime">
						<p>准实时规则</p>
					</li>
					<li id="offLine">
						<p>离线规则</p>
					</li>
				</ul>
			</div>
			
			<div id="real_time" class="form-inline myModify-one-box">
				<p>反爬虫统计字段规则<span>（可多选）</span>：</p>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox0">
					</label>
					<span>5分钟内的IP访问量&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg00"></label>
					<select class="form-control fr" id="rtlastarg0">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox1">
					</label>
					<span>5分钟内的关键页面访问总量&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg01"></label>
					<select class="form-control fr" id="rtlastarg1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox2">
					</label>
					<span>5分钟内的UA出现次数统计&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg02"></label>
					<select class="form-control fr" id="rtlastarg2">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox3">
					</label>
					<span>5分钟内的关键页面最短访问间隔&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg03"></label>
					<select class="form-control fr" id="rtlastarg3">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox4">
					</label>
					<span>5分钟内小于最短访问间隔&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg04"></label>
					<span>&nbsp;&nbsp;的关键页面查询次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg24"></label>
					<select class="form-control fr" id="rtlastarg4">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox5">
					</label>
					<span>5分钟内关键页面的访问次数的Cookie数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg05"></label>
					<select class="form-control fr" id="rtlastarg5">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox6">
					</label>
					<span>5分钟内查询不同行程的次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg06"></label>
					<select class="form-control fr" id="rtlastarg6">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="checkbox7">
					</label>
					<span>5分钟内的IP段访问总量&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="rtarg07"></label>
					<select class="form-control fr" id="rtlastarg7">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
			</div>
			<div id="quasi_real-time" class="form-inline myModify-one-box" style="display: none;">
				<p>反爬虫统计字段规则：</p>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox0">
					</label>
					<span>1小时内的IP段访问量（前两位）<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg00"></label>
					<select class="form-control fr" id="qlastarg0">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<p>防占座统计字段规则：</p>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox1">
					</label>
					<span>1小时内IP段的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg01"></label>
					<select class="form-control fr" id="qlastarg1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox2">
					</label>
					<span>1小时内购票ID的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg02"></label>
					<select class="form-control fr" id="qlastarg2">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox3">
					</label>
					<span>1小时内联系人名的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg03"></label>
					<select class="form-control fr" id="qlastarg3">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox4">
					</label>
					<span>1小时内联系人手机号的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg04"></label>
					<select class="form-control fr" id="qlastarg4">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox5">
					</label>
					<span>1小时内IP段的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg05"></label>
					<select class="form-control fr" id="qlastarg5">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox6">
					</label>
					<span>1小时内购票ID的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg06"></label>
					<select class="form-control fr" id="qlastarg6">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox7">
					</label>
					<span>1小时内联系人名的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg07"></label>
					<select class="form-control fr" id="qlastarg7">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox8">
					</label>
					<span>1小时内联系人手机号的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg08"></label>
					<select class="form-control fr" id="qlastarg8">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox9">
					</label>
					<span>1小时内乘机人信息的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg09"></label>
					<select class="form-control fr" id="qlastarg9">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox10">
					</label>
					<span>1小时内联系人名&联系人手机号预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg010"></label>
					<select class="form-control fr" id="qlastarg10">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox11">
					</label>
					<span>1小时内联系人名&联系人手机号付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="qarg011"></label>
					<select class="form-control fr" id="qlastarg11">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="qcheckbox12">
					</label>
					<span>按用户名1小时内预定未支付&nbsp;&nbsp;</span>
					<select class="form-control fr" id="qlastarg12">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
			</div>
			<div id="off_line" class="form-inline myModify-one-box" style="display: none;">
				<p>反爬虫统计字段规则：</p>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox0">
					</label>
					<span>1天内的IP段访问量（前两位）<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg00"></label>
					<select class="form-control fr" id="olastarg0">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox1">
					</label>
					<span>一天内的访问总量&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg01"></label>
					<select class="form-control fr" id="olastarg1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox2">
					</label>
					<span>一天内的关键页面访问总量&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg02"></label>
					<select class="form-control fr" id="olastarg2">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox3">
					</label>
					<span>一天内的UA出现次数统计&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg03"></label>
					<select class="form-control fr" id="olastarg3">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox4">
					</label>
					<span>一天内的关键页面最短访问间隔&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg04"></label>
					<select class="form-control fr" id="olastarg4">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox5">
					</label>
					<span>一天内小于最短访问间隔&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg05"></label>
					<span>&nbsp;&nbsp;的关键页面查询次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg25"></label>
					<select class="form-control fr" id="olastarg5">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox6">
					</label>
					<span>一天内关键页面的访问次数的Cookie数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg06"></label>
					<select class="form-control fr" id="olastarg6">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox7">
					</label>
					<span>一天内查询不同行程的次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg07"></label>
					<select class="form-control fr" id="olastarg7">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<p>防占座统计字段规则：</p>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox8">
					</label>
					<span>一天内IP段的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg08"></label>
					<select class="form-control fr" id="olastarg8">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox9">
					</label>
					<span>一天内购票ID的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg09"></label>
					<select class="form-control fr" id="olastarg9">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox10">
					</label>
					<span>一天内联系人名的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg010"></label>
					<select class="form-control fr" id="olastarg10">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox11">
					</label>
					<span>一天内联系人手机号的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg011"></label>
					<select class="form-control fr" id="olastarg11">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox12">
					</label>
					<span>一天内乘机人信息的预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg012"></label>
					<select class="form-control fr" id="olastarg12">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox13">
					</label>
					<span>一天内IP段的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg013"></label>
					<select class="form-control fr" id="olastarg13">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox14">
					</label>
					<span>一天内购票ID的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg014"></label>
					<select class="form-control fr" id="olastarg14">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox15">
					</label>
					<span>一天内联系人名的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg015"></label>
					<select class="form-control fr" id="olastarg15">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox16">
					</label>
					<span>一天内联系人手机号的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg016"></label>
					<select class="form-control fr" id="olastarg16">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox17">
					</label>
					<span>一天内乘机人信息的付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg017"></label>
					<select class="form-control fr" id="olastarg17">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox18">
					</label>
					<span>一天内联系人名&联系人手机号预定次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg018"></label>
					<select class="form-control fr" id="olastarg18">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox19">
					</label>
					<span>一天内联系人名&联系人手机号付费次数&nbsp;&nbsp;<&nbsp;&nbsp;</span>
					<label><input type="text" name="" id="oarg019"></label>
					<select class="form-control fr" id="olastarg19">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="" id="ocheckbox20">
					</label>
					<span>按用户名1天内预定未支付</span>
					<select class="form-control fr" id="olastarg20">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</div>
			</div>
		</div>
		<div id="contentTwo" class="myModify-content-two" style="display: none;">
			<p>
				<label>
					<span>反爬虫规则：黑名单阀值&nbsp;>&nbsp;</span>
					<input type="text" id="crawlerBlacklistThresholds">
				</label>
			</p>
			<p>
				<label>
					<span>防占座规则：黑名单阀值&nbsp;>&nbsp;</span>
					<input type="text" id="occBlacklistThresholds">
				</label>
			</p>
		</div>
		<div id="contentThree" class="myModify-content-three" style="display: none;">
			<div class="form-inline">
				<div class="form-group">
					<span>选择算法：</span>
					<select class="form-control" id="type-option" onchange="window.modifyRule.selectType(this.options[this.options.selectedIndex].value)">
						<option value="0" selected>有监督学习</option>
						<option value="1">无监督学习</option>
					</select>
				</div>
				<div class="three-box">
					<p></p>
					<div class="three-show">
						<div class="form-group">
							<span>分裂方式：</span>
							<select class="form-control" id="splitWay">
								<option value="0">信息增益</option>
								<option value="1">信息增益率</option>
								<option value="2">基尼系数</option>
							</select>
						</div>
						<div class="form-group">
							<span>树的深度：</span>
							<select class="form-control"  id="treeDepth">
								<option>100</option>
							</select>
						</div>
						<div class="form-group">
							<span>最小数深：</span>
							<input type="text" name="" id="minTreeDepth">
						</div>
						<div class="form-group">
							<span>最小实例数：</span>
							<input type="text" name="" id="minInstanceNum">
						</div>
					</div>
					<div class="three-hide">
					<div class="form-group">
						<span>类中心数：</span>
						<label>
							<input type="text" name="" id="clusterNum">
						</label>
					</div>
					<div class="form-group">
						<span>迭代次数：</span>
						<select class="form-control" id="iterNum">
							<option>5</option>
						</select>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button id="preStep" type="button" class="btn btn-primary" onclick="window.modifyRule.preStep(1)">上一步</button> 
		<button id="nextStep" type="button" class="btn btn-primary" onclick="window.modifyRule.nextStep(1)">下一步</button>
		<button id="finshBtn" type="button" class="btn btn-primary" onclick="window.modifyRule.finsh()">完成</button> 
		<button id="outBtn" type="button" class="btn btn-default" onclick="window.modifyRule.exit()">退出</button>
	</div> 
		
  </div>
  </body>
</html>
