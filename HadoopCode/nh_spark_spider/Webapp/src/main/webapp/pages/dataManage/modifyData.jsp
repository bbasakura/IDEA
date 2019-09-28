<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	<script type="text/javascript">
  		var id = '${id}';
  	</script>
  	<script type="text/javascript" src="${ctx}/js/dataManage/modifyData.js"></script>
  </head>
  
  <body>
	<!-- 修改脚本 -->
	<div id="myModify">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">脚本开关阈值设置</h4>
		</div>
		<div class="modal-body">
			<p class="dataCollect-modal-body">阈值：<input type="text"></p>
		</div>
		<div class="modal-footer" style="border-top: 1px solid #e5e5e5;">
			<button type="submit" class="btn btn-primary">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
		</div>
	</div>
  </body>
</html>
