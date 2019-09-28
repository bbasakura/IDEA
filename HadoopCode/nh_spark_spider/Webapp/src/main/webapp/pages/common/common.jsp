<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	if(request.getSession().getAttribute("ctx") == null) {
		request.getSession().setAttribute("ctx", request.getContextPath());
		String path =request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
	}
	
 %>

<script type="text/javascript" src="${ctx}/plugins/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/manage/jquery.page.js"></script> 
<script type="text/javascript" src="${ctx}/plugins/bootstrap_v3.3.5/js/bootstrap.min.js"></script>  
<script type="text/javascript" src="${ctx}/plugins/echarts3/echarts.min.js"></script> 
<script type="text/javascript" src="${ctx}/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/common/chartTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/common/dateUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/common/security.js"></script>
<script type="text/javascript" src="${ctx}/js/common/cookieUtil.js"></script>
<script type="text/javascript" src="${ctx}/plugins/KingReport/js/ajaxUtil.js"></script>
<link rel="stylesheet" href="${ctx}/plugins/bootstrap_v3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/themes/default/css/aviation-comm.css" />
<link rel="stylesheet" href="${ctx}/themes/default/css/aviation-oym.css" />
<script type="text/javascript" src="${ctx}/js/manage/index.js"></script>
<script type="text/javascript" src="${ctx}/js/process/model.js"></script>


<script type="text/javascript">
	var ctx = "${ctx}";
	var account = '${sessionScope._account}';
	var kpcas = ctx;
</script>


