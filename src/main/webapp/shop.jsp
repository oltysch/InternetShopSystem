<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Gun Shop</title>
    <%--TODO make normal css style--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"/>
</head>
<body>
<a:mainTemplate>
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
