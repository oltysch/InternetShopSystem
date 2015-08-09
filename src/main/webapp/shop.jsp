<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
${user.getLogin()}
<form action="${pageContext.request.contextPath}/new/shopcart"><input type="submit" value="посмотреть корзину"/></form>
<br>
<a href="${pageContext.request.contextPath}/new/logout">Выйти</a><br>

<c:forEach var="gun" items="${guns}" varStatus="iter">
    <form action="${pageContext.request.contextPath}/new/addToCart" method="post">
            ${gun}<input type="hidden" name="selectedGunId" value="${gun.getId()}"/>
        <input type="submit" name="submit" value="добавить в корзину"/><br>
    </form>
</c:forEach>
</body>
</html>
