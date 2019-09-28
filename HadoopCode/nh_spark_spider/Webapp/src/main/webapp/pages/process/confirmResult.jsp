<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		var status = '${status}';
  	</script>
  	<script type="text/javascript" src="${ctx}/js/process/confirmResult.js"></script> 
  </head>
  
  <body>
  	<div id="mySignOut">
  	<div class="modal-header">
		<button type="button" class="close" onclick="window.confirmResult.cancleExit()"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">提示</h4>
	</div>
	<div class="modal-body">
		<p class="mySignOut-content">
			<!-- <span></span> 保存成功 -->
		</p>
	</div>
	<div class="modal-footer">
		<button id="sureExit" type="button" class="btn btn-primary" onclick="window.confirmResult.cancleExit()">确认</button>
	</div>
	</div>
  </body>
</html>
