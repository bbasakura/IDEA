<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  		<script type="text/javascript" src="${ctx}/js/monitor/systemMonitorIndex.js"></script>
  </head>
  
  <body>
			<div class="content-top">
				<div id="contentLeft1" class="content-top-left">
					<%-- <img style="width: 100%;" src="${ctx}/themes/default/images/9.png" /> --%>
				</div>
				<div class="content-top-right">
					<ul id="crawler-curday-info"  class="content-top-right-top">
						<!-- <li style="background: #d0e4f4;text-align: center;line-height: 40px;margin-bottom: 7px;">2017年9月28日</li>
						<li>当日流量：<span class="lanse">2033万</span></li>
						<li style="margin-bottom: 7px;">当日识别爬虫：<span class="lanse">3452</span></li>
						<li style="margin-bottom: 8px;">积累识别爬虫：<span class="lanse">222123345</span></li> -->
					</ul>
					<div class="content-Title" style="text-align: center;padding-right: 15px;margin-top: 20px; background: #fff;margin-left: 20px;padding-bottom: 5px;border: 1px solid #ddd;">
					    <span class="content-Title-span">系统功能运行情况</span>
					    <div style="line-height: 40px;">
					    	<span class="lyanse">运行正常</span><span class="hyanse">运行异常</span>
					    </div>
					    <table id="content-monitor">
					    	 <tr id="firstModel">
					    		<td style="background:#58aea5;" id="dataHandl">数据处理模块</td>
					    		<td style="background:gray;" id="process">流程管理模块</td>
					    	</tr>
					    	<tr id="secondModel">
					    		<td style="background:gray;" id="visualization">数据可视化模块</td>
					    		<td style="background:gray;" id="strategy">策略管理模块</td>
					    	</tr>
					    	<tr>
					    		<td style="background:gray;" id="machineStudy">机器学习模块</td>
					    		<td style="background:gray;" id="rule">规则管理模块</td>
					    	</tr>
					    </table>
				    </div>
				</div>
			</div>
			<div id = "flow-transmit" class="content-bottom">
				<%-- <img style="width: 100%;" src="${ctx}/themes/default/images/10.png" /> --%>
			</div>
  </body>
</html>