<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  		<script type="text/javascript" src="${ctx}/js/monitor/ruleMonitorIndex.js"></script>
  </head>
  
  <body>
<%-- 	<div class="content-bottom" style="margin-bottom: -11px;">
		<select>
			<option>实时</option>
			<option>准实时</option>
			<option>离线</option>
		</select>
		<select>
			<option>第一套</option>
			<option>第二套</option>
			<option>第三套</option>
		</select>
	</div>
	<div id="content_bottom" class="content-bottom">
		<img src="${ctx}/themes/default/images/u26.png" />
	</div> --%>
		<!-- <div class="right-content"> -->
			<div class="content-bottom width">
				<div class="font">反爬</div>
				<div class="content-Title" style="text-align: center;">
					实时性
					<select id="timeType" onchange="selectTimeType()">
					    <option value="0">实时</option>
					    <option value="1">准实时</option>
					    <option value="2">离线</option>
				    </select>
				    规则
				    <select class="kdu332" id="ruleType" onchange="selectRuleType()">
					    <option value="0">单位时间内的IP段访问量(前两位)</option>
					    <option value="1">基于IP的统计</option>
					    <option value="2">单位时间内的关键页面访问总量</option>
					    <option value="3">单位时间内的UA出现次数统计</option>
					    <option value="4">单位时间内的关键页面最短访问间隔 </option>
					    <option value="5">单位时间内关键页面的访问次数的Cookie数少于X（自设）</option>
					    <option value="6">单位时间内查询不同行程的次数</option>
				    </select>
				</div>
				<div class="content-top-img" class="content-bottom" id="anticLimb">
					<%-- <img style="width: 100%;" src="${ctx}/themes/default/images/11.png" /> --%>
				</div>
			</div>
			<div class="content-bottom width">
				<div class="font">防占座</div>
				<div class="content-Title" style="text-align: center;">
					实时性
					<select id="timeTypeSeat" onchange="selectTimetypeSeat()">
					    <option value="0">实时</option>
					    <option value="1">准实时</option>
					    <option value="2">离线</option>
				    </select>
				    规则
				    <select class="kdu332" id="ruleTypeSeat" onchange="selectRuleTypeSeat()">
					    <option value="0">单位时间内IP段的预定未支付次数</option>
					    <option value="1">单位时间内购票ID的预定未支付次数</option>
					    <option value="2">单位时间内联系人名的预定未支付次数</option>
					    <option value="3">单位时间内联系人手机号的预定未支付次数</option>
					    <option value="4">单位时间内乘机人信息的预定未支付次数</option>
					    <option value="5">单位时间内IP段的付费次数</option>
					    <option value="6">单位时间内购票ID的付费次数</option>
					    <option value="7">单位时间内联系人名的付费次数</option>
					    <option value="8">单位时间内联系人手机号的付费次数</option>
					    <option value="9">单位时间内乘机人信息的付费次数</option>
					    <option value="10">单位时间内IP段的取消次数</option>
					    <option value="11">单位时间内购票ID的取消次数</option>
					    <option value="12">单位时间内联系人名的取消次数</option>
					    <option value="13">单位时间内联系人手机号的取消次数</option>
					    <option value="14">单位时间内乘机人信息的取消次数</option>
					    <option value="15">单位时间内联系人名&联系人手机号预定未支付次数</option>
					    <option value="16">单位时间内联系人名&联系人手机号付费次数</option>
				    </select>
				</div>
				<div class="content-top-img" id="saveSeat">
					<%-- <img style="width: 100%;" src="${ctx}/themes/default/images/12.png" /> --%>
				</div>
			</div>
		<!-- </div> -->
  </body>
</html>
