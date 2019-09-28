<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript" src="${ctx}/js/visualize/conversionRate.js"></script>
  </head>
  
  <body>
	  <div id="subjectBoxDataVisual" class="SubjectBox dataVisualization-one">
		<div class="date">
			<p>
				<samp style="width: 70px;text-align: left;">选择时间</samp>
				<input id="chooseTimeFrom" value="" type="text" class="chooseTime" onFocus="WdatePicker({onpicked:function(){conversionRate.selectTimeFrom();}, readOnly:true})">
				<!-- <samp>至</samp>
				<input id="chooseTimeTo" value="" type="text" class="chooseTime" onFocus="WdatePicker({onpicked:function(){conversionRate.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})"> 
				-->
			</p>
		</div>
		<div id="boxCentent" class="box-centent dataVisualization-select">
			<ul>
				<li>
					<p>
						<span>类别</span>
						<select id="percentConversion"  onchange="selectPercentConversion()">
							<option value="0">国内转化率</option>
							<option value="1">国际转化率</option>
						</select>
					</p>
					<div id="domesticInterConversionRate" ></div>
				</li>
				<li>
				<p>
						<span>类别</span>
						<select id="userType" onchange="selectUserType()">
							<option value="0">全部用户</option>
							<option value="1">普通用户</option>
							<option value="2">代购用户</option>
						</select>
					</p>
					<div id="userConversionRate" ></div>
				</li>
				<li >
				<p>
						<span>类别</span>
						<select id="reptileType" onchange="selectReptileType()">
							<option value="0">排除爬虫</option>
							<option value="1">未排除爬虫</option>
						</select>
					</p>
					<div id="flightQueryConversionRate" ></div>
				</li>
				<!-- <li>
				<p>
						<span>类别</span>
						<select>
							<option>类别一</option>
							<option>类别二</option>
						</select>
					</p>
					<div id="occConversionRate" ></div>
				</li> -->
			</ul>
		</div>
	  </div> 
	  
  </body>
</html>
