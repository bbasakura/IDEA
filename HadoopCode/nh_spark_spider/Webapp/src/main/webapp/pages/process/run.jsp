<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
  		var id = '${id}';
  		var status='${status}';
  	</script>
  	<script type="text/javascript" src="${ctx}/js/process/run.js"></script>
  	<script type="text/javascript" src="${ctx}/js/process/process.js"></script>
  </head>
  
  <body>
  	<div id="myRun">
  	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">提示</h4>
	</div>
	<div class="modal-body">
		<p class="myRun-content" id="start"> 
			 <span>!</span> 是否运行该流程？
		</p>
		<p class="myRun-content" id="end"> 
			 <span>!</span> 是否暂停该流程？
		</p>
	</div>
	<div class="modal-footer">
		<button id="finshBtn" type="button" class="btn btn-primary" onclick="window.run.finsh()">确认</button>
		<button id="outBtn" type="button" class="btn btn-default" data-dismiss="modal" onclick="window.run.exit()">取消</button>
	</div>
	</div>
  </body>
</html>
