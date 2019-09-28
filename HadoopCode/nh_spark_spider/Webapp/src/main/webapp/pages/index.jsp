<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>传智播客反爬虫监控平台</title>
		<jsp:include page="common/common.jsp" />
		<script type="text/javascript" src="${ctx}/js/common/index.js"></script>
		<script type="text/javascript">
			$.ajaxSetup ({ 
			    cache: false //关闭AJAX相应的缓存 
			}); 
		</script>
	</head>
	<body class="bj">
		<jsp:include page="common/top.jsp"></jsp:include>
		<div id="content">
		</div>
		
	</body>
</html>
