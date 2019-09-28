<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="${ctx}/js/process/exitConfirm.js"></script>
  </head>
  
  <body>
  	<div id="mySignOut">
  	<div class="modal-header">
		<button type="button" class="close" onclick="window.exitConfirm.cancleExit()"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">提示</h4>
	</div>
	<div class="modal-body">
		<p class="mySignOut-content">
			<span>!</span> 退出后已修改的数据不会保存！
		</p>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-primary" onclick="window.exitConfirm.sureExit()">确认</button>
		<button type="button" class="btn btn-default" onclick="window.exitConfirm.cancleExit()">取消</button>
	</div>
	</div>
  </body>
</html>
