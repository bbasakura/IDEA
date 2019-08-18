<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";%>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>用户信息管理系统</title>

    <link rel='stylesheet' href='css/bootstrap.min.css'/>
    <script src='js/jquery-3.3.1.min.js'></script>
    <script src='js/bootstrap.min.js'></script>

    <script>
        function delContact(contactId) {
            // 当用户点击删除通讯录时，让用户确认一下，如果用户确认的话，我们再删除
            // 当用户确认时，flag为true
            // 当用户取消时，flag为false
            var flag = confirm("确认删除吗？");

            if (flag) {
                window.location = "del_by_id?id=" + contactId;
            }
        }
    </script>

    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>

<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
        </tr>
        <tr class="success">
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td>${ contact.id }</td>
                <td>${ contact.name }</td>
                <td>${ contact.gender == "m" ? "男" : "女" }</td>
                <td>${ contact.age }</td>
                <td>${ contact.birthplace }</td>
                <td>${ contact.mobile }</td>
                <td>${ contact.email }</td>
                <td>
                    <a class="btn btn-default btn-sm" href="update.jsp">修改</a>
                    <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="delContact(${contact.id})">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8" align="center" class="form-inline">

                <%-- 因为需要在页面当中，构造几个页码按钮，所以需要遍历 --%>
                <c:forEach begin="1" end="${pageCount}" var="pageItem">
                    <a class="btn btn-default <c:if test="${currentPage == pageItem}">btn-success</c:if>" href="query_contacts?currentPage=${pageItem}">${pageItem}</a>
                </c:forEach>

                <select class="form-control" onchange="changePageSize(this.value)">
                    <c:forEach begin="5" end="15" step="5" var="pageItem">
                        <option value="${pageItem}" <c:if test="${pageItem == pageSize}">selected</c:if>>${pageItem}条/页</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
