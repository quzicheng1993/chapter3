<%--
  Created by IntelliJ IDEA.
  User: zicheng
  Date: 2018/8/28
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>客户列表</h1>
    <table>
        <tr>
            <th>客户姓名</th>
            <th>性别</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th>操作</th>
        </tr>

        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.sex}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td>
                    <a href="${BASE}/customer/edit?id=${customer.id}">编译</a>
                    <a href="${BASE}/customer/delete?id=${customer.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
