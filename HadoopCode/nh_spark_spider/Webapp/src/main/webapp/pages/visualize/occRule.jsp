<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="${ctx}/themes/sso/css/index.css"/>
	<script type="text/javascript" src="${ctx}/js/visualize/occRule.js"></script>
  </head>
  
  <body>
	<!-- <div class="SubjectBox dataVisualization-two">
		<div class="date">
			<p>
				<input id="chooseTimeFrom" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){occRule.selectTimeFrom();}, readOnly:true,maxDate:'#F{$dp.$D(\'chooseTimeTo\') ||\'%y-%M-%d\'}' })">
				<span></span>
				<samp>至</samp>
				<input id="chooseTimeTo" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){occRule.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})">
				<span></span>
			</p>
		</div>
		<div class="box-centent">
			<div id="zhuzhuang-content"></div>
			<div style="border: none;">
			<table>
				<thead>
					<tr>
						<th>始发地</th>
						<th>目的地</th>
						<th>航班号</th>
						<th>舱位</th>
						<th>日期</th>
						<th>起飞时间</th>
					</tr>
				</thead>
				<tbody id="detailInfo">
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
						<td>6</td>
					</tr>
				</tbody>
			</table>
			<div class="cx">
				<div class="footer">
					<div class="pull-left mr-eps" id="paging">
						<select id="selectPage">
							<option value="20">20条/页</option>
							<option value="50">50条/页</option>
						</select>
					</div>                       
					<ul id="fye" class="pagination">
						<li>
							<a class="disabled">«</a>
						</li>
						<li class="active">
							<a class="current">1</a>
						</li>
						<li>
							<a href="javascript:;" class="tcdNumber">2</a>
						</li>
						<li>
							<a href="javascript:;" class="tcdNumber">3</a>
						</li>
						<li>
							<a href="javascript:;" class="nextPage">»</a>
						</li>
						<li>
							<input class="page-go" placeholder="跳转至">
						</li>
						<li>
							<a class="page-btn" href="#">GO</a>
						</li>
					</ul>
					<div id="infoPage" class="tcdPageCode"></div>
				</div>
			</div>
			</div>
		</div>
	</div> -->
	
	<div class="SubjectBox dataVisualization-two">
		<div class="date">
			<p>
				<samp style="width: 70px;text-align: left;">选择时间</samp>
				<input id="chooseTimeFrom" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){occRule.selectTimeFrom();}, readOnly:true,maxDate:'#F{$dp.$D(\'chooseTimeTo\') ||\'%y-%M-%d\'}' })">
				<span></span>
				<samp>至</samp>
				<input id="chooseTimeTo" value="" type="text" placeholder="选择时间" class="chooseTime" onFocus="WdatePicker({onpicked:function(){occRule.selectTimeTo();}, readOnly:true,minDate:'#F{$dp.$D(\'chooseTimeFrom\')}',maxDate:'%y-%M-%d'})">
				<span></span>
			</p>
		</div>
		<div class="box-centent">
			<div id="zhuzhuang-content"></div>
			<div class="dataVisualization-table">
				<table>
					<thead>
						<tr>
							<th>始发地</th>
							<th>目的地</th>
							<th>航班号</th>
							<th>舱位</th>
							<th>日期</th>
							<th>起飞时间</th>
						</tr>
					</thead>
					<tbody  id="detailInfo">
						<!-- <tr>
							<td>兰州</td>
							<td>北京</td>
							<td>CS800</td>
							<td>经济舱</td>
							<td>2017-08-15</td>
							<td>15:00:00</td>
						</tr>
						<tr>
							<td>乌鲁木齐</td>
							<td>哈尔滨</td>
							<td>CS8506</td>
							<td>经济舱</td>
							<td>2017-08-15</td>
							<td>15:00:00</td>
						</tr>
						<tr>
							<td>兰州</td>
							<td>北京</td>
							<td>CS800</td>
							<td>经济舱</td>
							<td>2017-08-15</td>
							<td>15:00:00</td>
						</tr>
						<tr>
							<td>乌鲁木齐</td>
							<td>哈尔滨</td>
							<td>CS8506</td>
							<td>经济舱</td>
							<td>2017-08-15</td>
							<td>15:00:00</td>
						</tr> -->
					</tbody>
				</table>
				<div class="cx">
					<div class="footer">
						<div class="pull-left mr-eps" id="paging">
							<select id="selectPage">
								<option value="10">10条/页</option>
								<option value="20">20条/页</option>
							</select>
						</div>                       
						<!-- <ul id="fye" class="pagination">
							<li>
								<a class="disabled">«</a>
							</li>
							<li class="active">
								<a class="current">1</a>
							</li>
							<li>
								<a href="javascript:;" class="tcdNumber">2</a>
							</li>
							<li>
								<a href="javascript:;" class="tcdNumber">3</a>
							</li>
							<li>
								<a href="javascript:;" class="nextPage">»</a>
							</li>
							<li>
								<input class="page-go" placeholder="跳转至">
							</li>
							<li>
								<a class="page-btn" href="#">GO</a>
							</li>
						</ul> -->
						<div id="infoPage" class="tcdPageCode"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
