<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript" src="${ctx}/js/visualize/stableInfluence.js"></script>
  </head>
  
  <body>
		<!-- <div  class="SubjectBox dataVisualization-one">
			<div class="date">
				<p>
					<input id="chooseTimeFrom" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){stableInfluence.selectTimeFrom();}, readOnly:true,maxDate:'#F{$dp.$D(\'chooseTimeTo\') ||\'%y-%M-%d\'}' })">
					<span></span>
					<samp>至</samp>
					<input id="chooseTimeTo" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){stableInfluence.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})">
					<span></span>
				</p>
			</div>
			<div class="box-centent">
				<ul>
					<li>
						<div id = "nh-flow-info"></div>
					</li>
					<li>
						<div id = "crawler-flow-rate"></div>
					</li>
				</ul>
			</div>
		</div> -->
		
		<div class="SubjectBox dataVisualization-one">
			<div class="date">
				<p>
					<samp style="width: 70px;text-align: left;">选择时间</samp>
					<input id="chooseTimeFrom" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){stableInfluence.selectTimeFrom();}, readOnly:true,maxDate:'#F{$dp.$D(\'chooseTimeTo\') ||\'%y-%M-%d\'}' })">
					<span></span>
					<samp>至</samp>
					<input id="chooseTimeTo" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){stableInfluence.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})">
					<span></span>
				</p>
			</div>
			<div class="box-centent">
				<div id = "nh-flow-info" class="oneBox-one"></div>
				<div id = "crawler-flow-rate" class="oneBox-two"></div>
			</div>
		</div>
  </body>
</html>
