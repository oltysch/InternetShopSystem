<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
<a:mainTemplate greeting="adfsdfadf" name="${user}">
${user.getLogin()}
    <form action="${pageContext.request.contextPath}/gunshop/shopcart"><input type="submit" value="посмотреть корзину"/>
    </form>
<br>
    <a href="${pageContext.request.contextPath}/gunshop/logout">Выйти</a><br>

<c:forEach var="product" items="${products}" varStatus="iter">
    <form action="${pageContext.request.contextPath}/gunshop/addToCart" method="post">
            ${product.name}<input type="hidden" name="selectedProduct" value="${product.uuid}"/>
        <input type="hidden" name="productType" value="${product.getClass().getSimpleName()}"/>
        <input type="submit" name="submit" value="добавить в корзину"/><br>
    </form>
</c:forEach>
</a:mainTemplate>
</body>
</html>
