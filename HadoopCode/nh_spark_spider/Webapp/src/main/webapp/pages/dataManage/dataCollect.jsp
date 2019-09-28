<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="${ctx}/js/dataManage/dataCollect.js"></script> 
  </head>
  
  <body>
	<article class="dataCollect">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>部署服务器</th>
						<th>当前活跃连接数</th>
						<th>最近三天采集数据量</th>
						<!-- <th width="200" style="text-align: center;">脚本开关阈值设置</th> -->
					</tr>
				</thead>
				<tbody id="datacollect">
					<tr>
						<td class="server-img">
							<p>服务器1</p>
						</td>
						<td>
							<p>1000</p>
						</td>
						<td>
							<p class="fontSize">
								<span>昨天：</span>
								<span>104879行</span>
							</p>
							<p class="fontSize">
								<span>前天：</span>
								<span>104879行</span>
							</p>
							<p class="fontSize">
								<span>前三天：</span>
								<span>104879行</span>
							</p>
						</td>
						<%-- <td class="operation-img">
							<img src="${ctx}/themes/default/images/ico-005.png" onclick="window.dataCollect.modify()" data-toggle="modal" data-target="#myModify">
						</td> --%>
					</tr>
					<tr>
						<td class="server-img">
							<p>服务器1</p>
						</td>
						<td>
							<p>1000</p>
						</td>
						<td>
							<p class="fontSize">
								<span>昨天：</span>
								<span>104879行</span>
							</p>
							<p class="fontSize">
								<span>前天：</span>
								<span>104879行</span>
							</p>
							<p class="fontSize">
								<span>前三天：</span>
								<span>104879行</span>
							</p>
						</td>
						<%-- <td class="operation-img">
							<img src="${ctx}/themes/default/images/ico-005.png">
						</td> --%>
					</tr>
					<tr>
						<td class="server-img">
							<p>服务器1</p>
						</td>
						<td>
							<p>1000</p>
						</td>
						<td>
							<p class="fontSize">
								<span>昨天：</span>
								<span>104879行</span>
							</p>
							<p class="fontSize">
								<span>前天：</span>
								<span>104879行</span>
							</p>
							<p class="fontSize">
								<span>前三天：</span>
								<span>104879行</span>
							</p>
						</td>
						<%-- <td class="operation-img">
							<img src="${ctx}/themes/default/images/ico-005.png">
						</td> --%>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 修改脚本 -->
		<div class="modal dataCollect-xg" id="dataCollectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					
				</div>
			</div>
		</div>
	</article>
  </body>
</html>
