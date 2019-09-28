<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
  <script type="text/javascript">
  		function promptSure(){
  			$("#modalPrompt").modal("hide");
  		}
  </script>	
  <!-- 模态退出静态框 -->
  <body>
  	<div class="modal" id="signOutModel" tabindex="-1" role="dialog" >
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
			
			</div>
		</div>
 	</div>
 	
 	<!-- 运行流程模态框 -->
	<div class="modal prompt" id="modalPrompt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" aria-label="Close" onclick="promptSure()"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">
					<p class="myRun-content">
						<!-- <span></span>是否运行该流程？ -->
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"  onclick="promptSure()">确认</button>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
