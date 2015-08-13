<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="style.css"/>
</head>
<body>
<c:choose>
    <c:when test="${not empty user}">
        <c:redirect url="${pageContext.request.contextPath}/gunshop/products"/>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/gunshop/login">Вход</a>
        <a href="${pageContext.request.contextPath}/gunshop/register">Регистрация</a>
    </c:otherwise>
</c:choose>
</body>
</html>
