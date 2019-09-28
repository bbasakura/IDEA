<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script type="text/javascript" src="${ctx}/js/process/commonEvent.js"></script>
	<script type="text/javascript" src="${ctx}/js/process/process.js"></script> 
	<script type="text/javascript" src="${ctx}/js/process/checkboxSelect.js"></script> 
	<style type="text/css">
		canvas {
			border: 1px solid #ddd;
		}
	</style>
	<jsp:include page="model.jsp"></jsp:include>
    
  <body> 
	<article class="dataVisualization process" style="background-color: #F7F8FC;">
		<div class="btn-new" data-toggle="modal" data-target="#myModify">
			<p id="newProcess"><span>+</span>新建流程</p>
		</div>
		<div class="table-box">
			<table class="table">
				<thead>
					<tr>
						<th>流程名称</th>
						<th>模型名称</th>
						<th>创建时间</th>
						<th>创建人</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody id="content-body">
					<%-- <tr>
						<td>
							<p>流程1</p>
						</td>
						<td>有监督学习</td>
						<td>2017-03-25&nbsp;10:00:00</td>
						<td>系统管理员</td>
						<td class="center">
							<img src="${ctx}/themes/default/images/ico-004.png" data-img="1" onclick="myRun(this)">
							<img src="${ctx}/themes/default/images/ico-005.png" data-toggle="modal" data-target="#myModify" onclick="one(this)">
							<img src="${ctx}/themes/default/images/ico-006.png" data-toggle="modal" data-target="#myDelete">
						</td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		
	</article>
  </body> 
</html>
