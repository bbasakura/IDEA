<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <script type="text/javascript" src="${ctx}/js/monitor/performanceMonitorIndex.js"></script> 
  </head>
  
  <body>
	<!-- <div class="right-content"> -->
			<div class="content-bottom">
				<div class="content-Title" >
					<span class="content-Title-span">数据分析速度</span>
					<span class="content-Title-right" id="time"></span>
				</div>
				<div style="overflow: hidden;" id="dashBoard">
					<div class="content-Title-left" id="realTime">
						<%-- <img src="${ctx}/themes/default/images/1.jpg" /> --%>
					</div>
					<div class="content-Title-left" id="semiRealTime">
						<%-- <img src="${ctx}/themes/default/images/2.jpg"/> --%>
					</div>
					<div class="content-Title-left" id="offLine">
						<%-- <img src="${ctx}/themes/default/images/3.jpg" /> --%>
					</div>
				</div>
				<div class="content-Title" id="content-title">
					<!-- <span class="kdu" >实时：<span class="lse">3分44秒</span> 半实时：<span class="lse">7分35秒</span> 离线：<span class="lse">27分43秒</span></span>
					<div style="padding-right: 97px;padding-bottom: 20px;" class="content-Title-right">
						数据分析速度标准：实时 5分钟内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 半实时 10分钟内
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 离线30分钟内
					</div> -->
				</div>
			</div>
			<%-- <div class="content-bottom">
				<div class="content-Title">
					<span class="content-Title-span">服务器状态</span>
				</div>
				<table class="content-bottom-table" id="server">
					<tr>
						<td class="padding-left27px"><span class="tub"><img  src="${ctx}/themes/default/images/ico-09.PNG"/></span></td>
						<td>服务器#1</td>
						<td>CPU占用：<span style="color: #469f45;">19%</span></td>
						<td>内存占用：<span style="color: #fe2224;">90%&nbsp;&nbsp;115.2/128G</span></td>
						<td>可用磁盘空间：<span style="color: #2e30fe;">70%&nbsp;&nbsp;420/600G</span></td>
						<td>备注：Spark服务</td>
					</tr>
					<tr>
						<td class="padding-left27px"><span class="tub"><img src="${ctx}/themes/default/images/ico-09.PNG"/></span></td>
						<td>服务器#1</td>
						<td>CPU占用：<span style="color: #469f45;">19%</span></td>
						<td>内存占用：<span style="color: #fe2224;">90%&nbsp;&nbsp;115.2/128G</span></td>
						<td>可用磁盘空间：<span style="color: #2e30fe;">70%&nbsp;&nbsp;420/600G</span></td>
						<td>备注：Spark服务</td>
					</tr>
					<tr>
						<td class="padding-left27px"><span class="tub"><img src="${ctx}/themes/default/images/ico-09.PNG"/></span></td>
						<td>服务器#1</td>
						<td>CPU占用：<span style="color: #469f45;">19%</span></td>
						<td>内存占用：<span style="color: #fe2224;">90%&nbsp;&nbsp;115.2/128G</span></td>
						<td>可用磁盘空间：<span style="color: #2e30fe;">70%&nbsp;&nbsp;420/600G</span></td>
						<td>备注：Spark服务</td>
					</tr>
					<tr>
						<td class="padding-left27px"><span class="tub"><img src="${ctx}/themes/default/images/ico-09.PNG"/></span></td>
						<td>服务器#1</td>
						<td>CPU占用：<span style="color: #469f45;">19%</span></td>
						<td>内存占用：<span style="color: #fe2224;">90%&nbsp;&nbsp;115.2/128G</span></td>
						<td>可用磁盘空间：<span style="color: #2e30fe;">70%&nbsp;&nbsp;420/600G</span></td>
						<td>备注：Spark服务</td>
					</tr>
				</table>
			</div> --%>
		<!-- </div> -->
  </body>
</html>
