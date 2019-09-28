<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  		var id = '${id}';
  	</script>
  	<script type="text/javascript" src="${ctx}/js/process/deleteRule.js"></script>
  </head>
  
  <body>
  	<div id="myDelete">
  	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">提示</h4>
	</div>
	<div class="modal-body">
		<p class="myDelete-content">
			<span>!</span> 是否删除该流程？
		</p>
	</div>
	<div class="modal-footer">
		<button type="button" onclick="deleteRule.deleteprocess()" class="btn btn-primary" data-dismiss="modal">确认</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
	</div>
  </body>
</html>
