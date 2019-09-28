<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String userCnName = (String) request.getSession().getAttribute("_user_cnname");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<div class="top">
	<div class="top-logo">传智播客反爬虫</div>
	<div class="top-user">
		<div class="top-user1">
			<div class="user1">
				<img src="${ctx}/themes/default/images/user-portrait.png" />
			</div>
			<div class="user1-accounts" title=""><%=userCnName%></div>
			<div class="user1-Triangle"></div>
			<div style="" class="none">
				<div class="user1-Triangle1"></div>
				<a id="mofityPassword" href="#">修改密码</a> <a
					href="${ctx}/auth/logout">退出</a>
			</div>
		</div>
	</div>
	<ul class="dwei">
		<li class="dwei-li">
			<a class="active" id="indexFlage" href="${ctx}/pages/index.jsp">首页</a>
		</li>
		<li class="dwei-li"><a id="dataManage">数据管理</a>
			<ul class="dwei-yinc">
				<li><a id="dataCollect" href="#">数据采集</a></li>
				<li><a id="dataHandle" href="#">数据处理</a></li>
			</ul></li>
		<li class="dwei-li"><a id="dataVisualize" href="#">数据可视化</a></li>
		<li class="dwei-li"><a id="processManage" href="#">流程管理</a></li>
		<li class="dwei-li"><a id="sysManage">系统管理</a>
			<ul class="dwei-yinc">
				<li><a id="userManage">用户管理</a></li>
				<li><a id="roleManage">角色管理</a></li>
				<li><a id="persManage">权限管理</a></li>
			</ul></li>
	</ul>
	</div>

	<jsp:include page="../model/model.jsp"></jsp:include>

</html>
