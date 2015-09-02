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
    <a href="${pageContext.request.contextPath}/gunshop/products">назад</a>
    <c:choose>
        <c:when test="${not empty products}">
            <c:forEach var="product" items="${products}" varStatus="iter">
                <form action="${pageContext.request.contextPath}/gunshop/removeFromCart" method="post">
                        ${product.name} x ${cart.getProductCount(product.uuid)}<input type="hidden"
                                                                                      name="selectedProductUuid"
                                                                                      value="${product.uuid}"/>
                    <input type="submit" name="submit" value="убрать из корзины"/><br>
                </form>
            </c:forEach>
            <div>${price}</div>
            <form>
                <input type="submit" formaction="${pageContext.request.contextPath}/gunshop/paidShopcart"
                       value="Оплатить">
                <input type="submit" formaction="${pageContext.request.contextPath}/gunshop/clearShopcart"
                       value="Очистить">
            </form>
        </c:when>
        <c:otherwise>
            <div>Ваша корзина пуста</div>
        </c:otherwise>
    </c:choose>
</a:mainTemplate>
</body>
</html>
