<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body>
  	<div id="myResult">
  	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">爬虫用户识别结果</h4>
	</div>
	<div class="modal-body">
		<p class="myResult-title">拟合率：<span>90%</span></p>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>用户ID</th>
						<th>算法识别</th>
						<th>规则识别</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>001</td>
						<td>1</td>
						<td>1</td>
					</tr>
					<tr>
						<td>002</td>
						<td>0</td>
						<td>1</td>
					</tr>
				</tbody>
			</table>
		</div>
		<p class="myResult-text">（1表示是，0表示否）</p>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-primary" data-dismiss="modal">退出</button>
	</div>
	</div>
  </body>
</html>
