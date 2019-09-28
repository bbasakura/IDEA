<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <script type="text/javascript" src="${ctx}/js/monitor/modelMonitorIndex.js"></script>
     <script type="text/javascript" src="${ctx}/js/monitor/commonUtil.js"></script>
  </head>
  
  <body>
   <%--  <div class="content-bottom width">
		<div class="content-Title">
			反爬
			<select>
			    <option>实时</option>
			    <option>准实时</option>
			    <option>离线</option>
		    </select>
		    规则
		    <select>
			    <option>5分钟内的访问总量</option>
			    <option>5分钟内的关键页面访问总量</option>
			    <option>5分钟内的关键页面最短访问间隔</option>
			    <option>5分钟内小于最短访问间隔的关键页面查询次数 </option>
			    <option>5分钟内关键页面的访问次数的Cookie数</option>
			    <option>5分钟内查询不同行程的次数</option>
		    </select>
		</div>
		<div class="content-top-img">
			<img src="${ctx}/themes/default/images/u36.png" />
			<span>5分钟</span>
		</div>
	</div>
	<div class="content-bottom width">
		<div class="content-Title">
			防占座
			<select>
			    <option>准实时</option>
			    <option>离线</option>
		    </select>
		    规则
		    <select>
			    <option>一天内IP段的预定次数</option>
			    <option>一天内购票ID的预定次数</option>
			    <option>一天内联系人名的预定次数</option>
			    <option>一天内联系人手机号的预定次数</option>
			    <option>一天内乘机人信息的预定次数</option>
			    <option>一天内IP段的付费次数</option>
			    <option>一天内购票ID的付费次数</option>
			    <option>一天内联系人名的付费次数</option>
			    <option>一天内联系人手机号的付费次数</option>
			    <option>一天内乘机人信息的付费次数</option>
			    <option>一天内联系人名&联系人手机号预定次数</option>
			    <option>一天内联系人名&联系人手机号付费次数</option>
			    <option>按用户名 一天内预定未支付  </option>
		    </select>
		</div>
		<div class="content-top-img">
			<img src="${ctx}/themes/default/images/u36.png" />
			<span>5分钟</span>
		</div>
	</div> --%>
	<!-- <div class="right-content"> -->
		<div class="content-bottom width">
			<div class="font">算法模型准确率</div>
				<div class="content-Title" style="text-align: center;">
					实时性
					<select id="timeType" onchange="selectTimeType()">
					    <option value="0">实时</option>
					    <option value="1">准实时</option>
					    <option value="2">离线</option>
				    </select>
				    流程
				    <select id="flowType" onchange="selectFlowType()">
					    <!-- <option>第一套</option>
					    <option>第二套</option>
					    <option>第三套</option>
					    <option>第四套 </option>
					    <option>第五套</option>
					    <option>第六套</option> -->
				    </select>
				</div>
				<div class="content-top-img" id="flowTransmit">
					<%-- <img style="width: 100%;" src="${ctx}/themes/default/images/13.png"/> --%>
				</div>
			</div>
	<!-- </div> -->
  </body>
</html>
