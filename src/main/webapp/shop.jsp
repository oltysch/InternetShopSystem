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

<c:forEach var="product" items="${products}" varStatus="iter">
    <form action="${pageContext.request.contextPath}/new/addToCart" method="post">
            ${product.getName()}<input type="hidden" name="selectedProduct" value="${product.getUuid()}"/>
        <input type="hidden" name="productType" value="${product.getClass().getSimpleName()}"/>
        <input type="submit" name="submit" value="добавить в корзину"/><br>
    </form>
</c:forEach>
</body>
</html>
