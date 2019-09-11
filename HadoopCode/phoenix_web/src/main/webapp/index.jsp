<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8" />
		<title>用户画像展示平台</title>

		<style>
			
			body{
				width: 100%;
			    height: 710px;
			   	background: #320158;
			    padding-top: 70px;
			}
		
		    .yhhx {
		    	width: 800px;
		    	margin: 0 auto;
		    }
			table {
				border-spacing: 0;
				border-collapse: collapse;
			}			
			table td,table th {
				padding: 8px;
				border: 1px solid #ddd;
			}
			td {text-align:center;
				vertical-align:middle;
				}
			select {
				border-color: #ddd;
			    padding: 2px 5px;
			    color: #535050;
			}
			.table {
				width: 100%;
				max-width: 100%;
				border: 1px solid #ddd;
			}
			.btn-check {
				float: right;
			    padding: 6px 20px;
			    margin-top: 10px;
			    border: 0;
			    border-radius: 5px;
			    font-size: 16px;
			    background-color: #45aa6d;
			    color: #fff;
			    cursor: pointer;
			}
			.spark-m{
				text-align:center;
			}
			#queryResult{
				color:yellow;
			}
			
			#table-checked {
				display: none;
				margin-top: 10px;
			}
			.info{
				color: #0ff;
				font-size: 18px;
				text-align: center;

			}

			/*------------------------加载div样式----------------------------------------*/  
        .loading {  
            z-index: 1001;  
            vertical-align: middle;  
            text-align:center;  
            height:115px;  
            line-height:115px;  
        }  
        .spnContent {  
            vertical-align: 50%;  
            margin-left: 10px;  
            color: red;  
            font-size: 18px;  
            font-weight: bold;  
        }  
        .white_content {  
            display: none;  
            position: absolute;  
            border: 3px solid lightblue;  
            background-color: #CAE4F7;  
            z-index: 9999;  
            -moz-opacity: 0.5;  
            opacity: .50;  
            filter: alpha(opacity=50);  
            vertical-align: middle;  
            top:0px;  
            text-align:center;  
        }  
        .navPoint {  
            color: white;  
            cursor:pointer;  
            font-family: Webdings;  
            font-size: 9pt;  
        }
		.th_class{
			background-color:#00AAAA;
			color: #0000CD;
		}	
		</style>
		<link rel="stylesheet" type="text/css" href="css/miaov_style.css" />
		
	</head>

	<body>
		<div style="height:50px;"></div>	
		<div class="yhhx">
			<table class="table">
				
				<tbody>
					<tr>
						<td class="info">用户年龄</td>
						<td>
							<div class="controls">
								<select name="selectAge" id="selectAge">
									<option value="">无限制</option>
									<option value="0-20">0-20</option>
									<option value="20-30">20-30</option>
									<option value="30-40">30-40</option>
									<option value="40-50">40-50</option>
									<option value="50-60">50-60</option>
									<option value="60">60以上</option>
								</select>
							</div>
						</td>
						<td class="info">家里下单总数</td>
						<td>
							<div class="controls">
								<select name="selectHomeOrderCnt" id="selectHomeOrderCnt">
									<option value="">无限制</option>
									<option value="0-50">0-50</option>
									<option value="50-100">50-100</option>
									<option value="100">100以上</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td class="info">用户性别</td>
						<td>
							<div class="controls">
								<select name="selectSex" id="selectSex">
									<option value="">无限制</option>
									<option value="male">男</option>
									<option value="female">女</option>
								</select>
							</div>
						</td>
						<td class="info">近30天购买次数</td>
						<td>
							<div class="controls">
								<select name="selectMonth1OrderCnt" id="selectMonth1OrderCnt">
									<option value="">无限制</option>
									<option value="0-10">0-10</option>
									<option value="10-20">10-20</option>
									<option value="20">20以上</option>
								</select>
							</div>
						</td>
					</tr>
				    <tr>
						<td class="info">用户省份</td>
						<td>
							<div class="controls">
								<form class="form-inline">
							      <div data-toggle="distpicker">
							        <div class="form-group">							         
							          <select class="form-control" id="province">
							          </select>
							        </div>							       							        
							      </div>
							    </form>
							</div>
						</td>
						<td class="info">近30天购买金额</td>
						<td>
							<div class="controls">
								<select name="selectMonth1OrderAmt" id="selectMonth1OrderAmt">
									<option value="">无限制</option>
									<option value="0-1000">0-1000</option>
									<option value="1000-5000">1000-5000</option>
									<option value="5000">5000以上</option>
								</select>
							</div>
						</td>
					</tr>
				    <tr>
						<td class="info">用户收入</td>
						<td>
							<div class="controls">
								<select name="selectMonthlyMoney" id="selectMonthlyMoney">
									<option value="">无限制</option>
									<option value="0-5000">0-5000</option>
									<option value="5000-10000">5000-10000</option>
									<option value="10000">10000以上</option>
								</select>
							</div>
						</td>
						<td class="info">近30天PC端访问常用游览器</td>
						<td>
							<div class="controls">
								<select name="selectBrowserName" id="selectBrowserName">
									<option value="">无限制</option>
									<option value="Chrome">Chrome</option>
									<option value="Safari">Safari</option>
									<option value="IE">IE</option>
									<option value="Firefox">Firefox</option>
									<option value="Opera">Opera</option>
									<option value="360">360</option>
									
								</select>
							</div>
						</td>
					</tr>
				    <tr>
						<td class="info">会员积分</td>
						<td>
							<div class="controls">
								<select name="selectTotalMark" id="selectTotalMark">
									<option value="">无限制</option>
									<option value="0-1000">0-1000</option>
									<option value="1000-2000">1000-2000</option>
									<option value="2000">2000以上</option>
								</select>
							</div>
						</td>
						<td class="info">近30天8点-9点访问次数</td>
						<td>
							<div class="controls">
								<select name="selectMonth1Hour829Cnt" id="selectMonth1Hour829Cnt">
									<option value="">无限制</option>
									<option value="0-100">0-100</option>
									<option value="100-200">100-200</option>
									<option value="200">200以上</option>
								</select>
							</div>
						</td>
					</tr>
				    <tr>
						<td class="info">最大消费金额</td>
						<td>
							<div class="controls">
								<select name="selectMaxOrderAmt" id="selectMaxOrderAmt">
									<option value="">无限制</option>
									<option value="0-1000">0-1000</option>
									<option value="1000-2000">1000-2000</option>
									<option value="2000">2000以上</option>
								</select>
							</div>
						</td>
						<td class="info">近30天22点-23点访问次数</td>
						<td>
							<div class="controls">
								<select name="selectMonth1Hour22223Cnt" id="selectMonth1Hour22223Cnt">
									<option value="">无限制</option>
									<option value="0-100">0-100</option>
									<option value="100-200">100-200</option>
									<option value="200">200以上</option>
								</select>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		    
		    <button type="button" class="btn-check">查询</button>
		    <div style="clear: both;"></div>
		    <div id="table-checked">
		    	<table class="table" id="queryResult">
		    	<thead>
		    		<tr>
		    			<th class="th_class">用户编码</th>
			    		<th class="th_class">用户名</th>
			    		<th class="th_class">用户性别</th>
			    		<th class="th_class">用户年龄</th>
			    		<th class="th_class">月收入</th>
			    		<th class="th_class">会员积分</th>
		    		</tr>
		    	</thead>
		    	<tbody>
		    		<tr>
		    		</tr>
		    	</tbody>
		    	
		         </table>
		    </div>
		    
		</div>

		<div id="div1">
			<div class="spark-m">
				<img src="image/robot.png">
			</div>
			<a href="#">女性</a>
			<a href="#" class="red">居住地北京</a>
			<a href="#">80后白富美</a>
			<a href="#" class="yellow">奢侈品</a>
			<a href="#" class="blue">家有小孩</a>
			<a href="#">有车一族</a>
			<a href="#" class="red">夜猫子</a>
			<a href="#" class="yellow">动漫小说</a>
			<a href="#" class="yellow">喜欢IT男</a>
			<a href="#" class="red">钟情白色</a>
			<a href="#">家有宠物</a>
			<a href="#" class="red">SuperMan</a>
			<a href="#" class="blue">红酒</a>
			<a href="#">处女座</a>
			<a href="#" class="red">耐克跑鞋</a>
			<a href="#" class="blue">精通Spark</a>
			<a href="#">潮妈族</a>
			<a href="#" class="blue">购物达人</a>
			<a href="#" class="yellow">iphone</a>
	</div>
		
		<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/distpicker.data.js"></script>
		<script type="text/javascript" src="js/distpicker.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/miaov.js"></script>
	
<script type="text/javascript">
		//点击查询按钮
			$(function(){
				$(".btn-check").click(function(){
					$("#table-checked").css("display","block");
					var params="";
					//用戶年龄
					var user_age=$('#selectAge option:selected').val();
					params+="user_age="+user_age;
					//用户性别
					var user_sex=$("#selectSex option:selected").val();
					params+="&user_sex="+user_sex;
					//用户省份
					var province=$('#province option:selected').val();
					params+="&province="+province;
					//收入			
					var monthly_money=$('#selectMonthlyMoney option:selected').val();
					params+="&monthly_money="+monthly_money;
					//会员积分
					var total_mark=$('#selectTotalMark option:selected').val();
					params+="&total_mark="+total_mark;
					//最大消费金额
					var max_order_amt=$('#selectMaxOrderAmt option:selected').val();
					params+="&max_order_amt="+max_order_amt;
					//近30天购买次数（含退拒）
					var month1_order_cnt=$('#selectMonth1OrderCnt option:selected').val();
					params+="&month1_order_cnt="+month1_order_cnt;
					//近30天购买金额（含退拒）
					var month1_order_amt=$('#selectMonth1OrderAmt option:selected').val();
					params+="&month1_order_amt="+month1_order_amt;
					//家里下单总数
					var home_order_cnt=$('#selectHomeOrderCnt option:selected').val();
					params+="&home_order_cnt="+home_order_cnt;
					//近30天PC端访问最常用游览器
					var month1_pc_common_browser_name=$('#selectBrowserName option:selected').val();
					params+="&month1_pc_common_browser_name="+month1_pc_common_browser_name;
					//近30天8-9点访问次数(不分PC与APP)
					var month1_hour829_cnt=$('#selectMonth1Hour829Cnt option:selected').val();
					params+="&month1_hour829_cnt="+month1_hour829_cnt;
					//近30天22-23点访问次数(不分PC与APP)
					var month1_hour22223_cnt=$('#selectMonth1Hour22223Cnt option:selected').val();
					params+="&month1_hour22223_cnt="+month1_hour22223_cnt;
					queryUserList(params);
				})
			});
			
	//根据条件查询数据	
	function queryUserList(params) {
        $.ajax({
          url: "http://localhost:8080/spring/userList.action",
          type: "POST",
          dataType: "json",
          data:params,
          async: false,
          success: function(data) {
        	  $("#queryResult  tr:not(:first)").empty("");
        	  for (var i = 0; i < data.length; i++) {
        		  var row = "<tr><td>"+data[i].user_id+"</td><td>"+data[i].user_name+"</td>"+
        		  "<td>"+data[i].user_sex+"</td><td>"+data[i].user_age+"</td>"+
        		  "<td>"+data[i].monthly_money+"</td><td>"+data[i].total_mark+"</td></tr>";
        		   $("#queryResult").append(row);
			}
          },
          error: function() {
            alert("error");
            $("#queryResult  tr:not(:first)").empty("");
          }
        });
	}
		</script>
	</body>

</html>