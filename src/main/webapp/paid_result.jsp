<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/main_style.css"/>
</head>
<body>
<a:mainTemplate>
    <div>${paidResult}</div>
    <br>
    <a href="${pageContext.request.contextPath}/gunshop/shopcart">продолжить</a>
</a:mainTemplate>
</body>
</html>
