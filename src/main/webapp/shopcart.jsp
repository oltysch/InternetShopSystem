<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"/>
</head>
<body>
<a:mainTemplate>
    <a href="${pageContext.request.contextPath}/gunshop/products">назад</a>
    <c:choose>
        <c:when test="${not empty user.getShopcart()}">
            <c:forEach var="product" items="${user.shopcart}" varStatus="iter">
                <form action="${pageContext.request.contextPath}/gunshop/removeFromCart" method="post">
                        ${product.name}<input type="hidden" name="selectedProductUuid" value="${product.uuid}"/>
                    <input type="submit" name="submit" value="убрать из корзины"/><br>
                </form>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div>Ваша корзина пуста</div>
        </c:otherwise>
    </c:choose>
</a:mainTemplate>
</body>
</html>
