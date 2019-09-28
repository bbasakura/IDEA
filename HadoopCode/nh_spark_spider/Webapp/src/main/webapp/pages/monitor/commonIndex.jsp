<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript" src="${ctx}/js/monitor/commonIndex.js"></script>
	<link rel="stylesheet" href="${ctx}/themes/default/css/demo.css" /> 
	<style type="text/css">
        @font-face {font-family: "iconfont";
          src: url('${ctx}/themes/default/fonts/iconfont.eot'); /* IE9*/
          src: url('${ctx}/themes/default/fonts/iconfont.eot#iefix') format('embedded-opentype'), /* IE6-IE8 */
          url('${ctx}/themes/default/fonts/iconfont.woff') format('woff'), /* chrome, firefox */
          url('${ctx}/themes/default/fonts/iconfont.ttf') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
          url('${ctx}/themes/default/fonts/iconfont.svg#iconfont') format('svg'); /* iOS 4.1- */
        }

        .iconfont {
          font-family:"iconfont" !important;
          font-size:38px;
          color: #999;
          font-style:normal;
          -webkit-font-smoothing: antialiased;
          -webkit-text-stroke-width: 0.2px;
          -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body class="bj">
	<div class="Unified">
		<ul class="left-menu">
			<li  id="systemMonitor" class="active">
				<i class="icon iconfont">&#xe612;</i>
				<div class="name">系统监控</div>
			</li>
			<!-- <li id="ruleMonitor">
				<i class="icon iconfont">&#xe610;</i>
				<div class="name">规则监控</div>
			</li>
			<li id="modelMonitor" >
				<i class="icon iconfont">&#xe611;</i>
				<div class="name">模型监控</div>
			</li> -->
			<li id="propertyMonitor">
				<i class="icon iconfont">&#xe613;</i>
				<div class="name">性能监控</div>
			</li>
		</ul>
		<div id="rightContent" class="right-content">
			
		</div>
	</div>
</body>
</html>
