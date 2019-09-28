<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  		<script type="text/javascript" src="${ctx}/js/visualize/identifyAnalysis.js"></script>
  </head>
  
  <body>
		<!-- <div  class="SubjectBox dataVisualization-one">
			<div class="date">
				<p>
					<input id="chooseTimeFrom" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){identifyAnalysis.selectTimeFrom();}, readOnly:true,maxDate:'#F{$dp.$D(\'chooseTimeTo\') ||\'%y-%M-%d\'}' })">
					<span></span>
					<samp>至</samp>
					<input id="chooseTimeTo" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){identifyAnalysis.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})">
					<span></span>
				</p>
			</div>
			<div class="box-centent">
				<ul>
					<li>
						<div id="agencyCustomerAnalysis"></div>
					</li>
					<li>
						<div ></div>
					</li>
				</ul>
			</div>
		</div> -->
		
		<div class="SubjectBox dataVisualization-one">
			<div class="date">
				<p>
					<samp style="width: 70px;text-align: left;">选择时间</samp>
					<input id="chooseTimeFrom" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){identifyAnalysis.selectTimeFrom();}, readOnly:true,maxDate:'#F{$dp.$D(\'chooseTimeTo\') ||\'%y-%M-%d\'}' })">
					<span></span>
					<samp>至</samp>
					<input id="chooseTimeTo" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){identifyAnalysis.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})">
					<span></span>
				</p>
			</div>
			<div class="box-centent">
				<div id="agencyCustomerAnalysis" class="oneBox-one"></div>
			</div>
		</div>
  </body>
</html>
