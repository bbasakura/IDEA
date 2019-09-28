<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${ctx}/js/visualize/visualize.js"></script>
<script type="text/javascript" src="${ctx}/js/visualize/commonUtil.js"></script>
</head>

<body>
	<article class="dataVisualization">
	<div class="dataVisualization-box">
		<div class="dataVisualization-nav">
			<ul id="navDataVisual" class="nav">
				<li id="conversionRate" class="on">
					<p>转换率</p>
				</li>
				<li id="flightQueryRule">
					<p>航班查询爬取规律</p>
				</li>
				<li id="occRule">
					<p>占座规律</p>
				</li>
				<li id="rateImpact">
					<p>爬虫对查定比影响</p>
				</li>
				<li id="stableInfluence">
					<p>爬虫对系统稳定性影响</p>
				</li>
				<li id="identifyAnalysis">
					<p>代购识别分析</p>
				</li>
			</ul>
		</div>
		<div id="subjectBoxDataVisual"></div>

	</div>
	</article>

</body>
</html>
