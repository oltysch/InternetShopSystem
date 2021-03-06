<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/main_style.css"/>
</head>
<body>
<a:mainTemplate>
    <a href="#" onclick="history.back()"><fmt:message key="button.back"/></a>
    <c:choose>
        <c:when test="${not empty products}">
            <c:forEach var="product" items="${products}" varStatus="iter">
                <a:productBlock product="${product}" buttonText="button.remove_from_cart" buttonType="remove"
                                showCount="true">
                </a:productBlock><br>
            </c:forEach>
            <div><fmt:message key="cart.total.price"/>: ${price} $</div>
            <form>
                <input type="submit" formaction="${pageContext.request.contextPath}/gunshop/paid_shopcart"
                       value="<fmt:message key="button.paid"/>">
                <input type="submit" formaction="${pageContext.request.contextPath}/gunshop/clear_shopcart"
                       value="<fmt:message key="button.clear"/>">
            </form>
        </c:when>
        <c:otherwise>
            <div><fmt:message key="cart.clear"/></div>
        </c:otherwise>
    </c:choose>
</a:mainTemplate>
</body>
</html>
