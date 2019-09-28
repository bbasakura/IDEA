<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
  		var id = '${id}';
  	</script>
  	<script type="text/javascript" src="${ctx}/js/process/ruleView.js"></script>
  </head>
  
  <body>
    <!-- 预览规则模态框 -->
    <div id="myPreview">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">查看规则</h4>
	</div>
	<div class="modal-body">
		<table class="table">
			<thead>
				<tr>
					<th>规则名称</th>
					<th>类型</th>
				</tr>
			</thead>
			<tbody id="content-table">
				<!-- <tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr><tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<select>
								<option>1小时</option>
								<option>一天</option>
							</select>
							<label>内的IP段访问量（前两位）<&nbsp;</label>
							<input type="text">
						</div>
					</td>
					<td>
						<span>防爬虫</span>
					</td>
				</tr> -->
				
			</tbody>
		</table>
	</div>
	</div>
  </body>
</html>
