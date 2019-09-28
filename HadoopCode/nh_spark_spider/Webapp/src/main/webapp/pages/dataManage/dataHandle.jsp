<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 		<script type="text/javascript" src="${ctx}/js/dataManage/dataHandle.js"></script> 
  </head>
  
  <body>
	<article class="dataHandle">
		<div class="dataHandle-header">
			<input id="bookOrQuery" type="checkbox" value="0" style="display: none;">
			<ul>
				<li id = "query" onclick="dataHandle.query()" class="on">
					<p>查询</p>
				</li>
				<li  id = "book" onclick="dataHandle.book()">
					<p>预定</p>
				</li>
			</ul>
		</div>
		<div class="dataHandle-list">
			<input id="wayInfo" type="checkbox" value="0" style="display: none;">
			<ul>
				<li id="inland"  class="on">
					<p>国内</p>
				</li>
				<li id="international">
					<p>国际</p>
				</li>
			</ul>
		</div>
		<div class="dataHandle-content">
			
			<div class="dataHandle-content-one">
			<input id="jsonOrXmlDataType" value="0" type="checkbox" style="display: none;"> 
				<ul>
					<li>
						<p>数据类型：JSON</p>
						<ul>
							<li>
								<div id = "applicationJson" class="on">Application / JSON</div>
							</li>
							<li>
								<div id = "formJson" >Form表单中JSON</div>
							</li>
						</ul>
					</li>
					<li>
						<p class="on">数据类型：XML</p>
						<ul>
							<li>
								<div id = "textXml">Text / XML</div>
							</li>
							<li>
								<div id = "formXml">Form表单中XML</div>
							</li>
						</ul>
					</li>
					<li>
						<div onclick= "dataHandle.formClickEvent()">数据类型：Form</div>
					</li>
					<li>
						<div onclick= "dataHandle.getClickEvent()">数据类型：Get</div>
					</li>
				</ul>
			</div>
			<div class="dataHandle-content-two">
				<form id="content" class="form-horizontal">
					<div id= "url" class="checkbox theFirst" style="float: left;">
						<label>解析规则url匹配的正则表达式</label>
						<label>
							<input id="requestMatchExpression" type="text">
						</label>
					</div>
					<div id = "formKey" style="float: left;">
							<label id = "formKeyName" style="width: 110px;text-align: right;font-weight: 400;">hi</label>
							<label>
								<input id="formDataField" type="text" style="margin-left: 10px;padding: 0 10px;height: 38px;border: 1px solid #ddd;">
							</label>
						</div>
					<div style="clear: both;border-bottom: 2px dashed #e6e6e6;margin-bottom: 20px;"></div>
					<div class="dataHandle-checkbox">
						<div id="query_adultNum" class="checkbox">
							<label>成人乘机人数</label>
							<label>
								<input id="queryAdultNum" type="text">
							</label>
						</div>
						<div id="query_childNum" class="checkbox">
							<label>儿童乘机人数</label>
							<label>
								<input id="queryChildNum" type="text">
							</label>
						</div>
						<div id="query_infantNum" class="checkbox">
							<label>婴儿乘机人数</label>
							<label>
								<input id="queryInfantNum" type="text">
							</label>
						</div>
						<div id="book_contractPhone" class="checkbox">
							<label>联系人手机号</label>
							<label>
								<input id="bookContractPhone" type="text">
							</label>
						</div>
						<div id="book_psgName" class="checkbox">
							<label>乘机人名</label>
							<label>
								<input id="bookPsgName" type="text">
							</label>
						</div>
						<div id="query_travelType" class="checkbox">
							<label>是否往返</label>
							<label>
								<input id="queryTravelType" type="text">
							</label>
						</div>
						<div id="book_idCard" class="checkbox">
							<label>乘机人证件号</label>
							<label>
								<input id="bookIdCard" type="text">
							</label>
						</div>
						<div id="book_idType" class="checkbox">
							<label>证件类型</label>
							<label>
								<input id="bookIdType" type="text">
							</label>
						</div>
						<div id="query_country" class="checkbox">
							<label>国家</label>
							<label>
								<input id="queryCountry" type="text">
							</label>
						</div>
						<div id="book_bookUserId" class="checkbox">
							<label>购票人ID</label>
							<label>
								<input id="bookBookUserId" type="text">
							</label>
						</div>
						<div id="book_cabin" class="checkbox">
							<label>舱位级别</label>
							<label>
								<input id="bookCabin" type="text">
							</label>
						</div>
						<div id="book_psgType" class="checkbox">
							<label>乘机人类型</label>
							<label>
								<input id="bookPsgType" type="text">
							</label>
						</div>
						
					</div>
					<div class="dataHandle-checkbox dataHandle-checkbox-media">
						<div id="book_contractName" class="checkbox">
							<label>联系人名</label>
							<label>
								<input id="bookContractName" type="text">
							</label>
						</div>
						<div id="book_psgFirName" class="checkbox">
							<label>乘机人的姓</label>
							<label>
								<input id="bookPsgFirName" type="text">
							</label>
						</div>
						<div class="checkbox">
							<label>始发地</label>
							<label>
								<input id="depCity" type="text">
							</label>
						</div>
						<div class="checkbox">
							<label>目的地</label>
							<label>
								<input id="arrCity" type="text">
							</label>
						</div>
						<div class="checkbox">
							<label>起飞时间</label>
							<label>
								<input id="flightDate" type="text">
							</label>
						</div>
						<div id="request_method" class="checkbox">
							<label>请求方法</label>
							<label>
								<select id = "requestMethod">
									<option value = "post">post</option>
									<option value = "get">get</option>
								</select>
							</label>
						</div>
					</div>
				</form>
				<div style="clear: both;"></div>
				<div class="dataHandle-btn">
					<button id="dataEdit" type="button" class="btn btn-primary">编辑</button>
					<button id="dataSure" type="button" class="btn btn-primary" style="display: none;" onclick = "dataHandle.saveOrUpdate()">确认</button>
					<button id="dataCancel" type="button" class="btn btn-default" style="display: none;" onclick = "dataHandle.cancel()">取消</button>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
	</article>
  </body>
</html>
