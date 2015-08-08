<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
${login}
<form action="${pageContext.request.contextPath}/new/shopcart"><input type="submit" value="show cart"/></form>
<br>
<a href="${pageContext.request.contextPath}/new/logout">Выйти</a><br>

<form action="${pageContext.request.contextPath}/new/addToCart" method="post">
    <c:forEach var="gun" items="${guns}" varStatus="iter">
        ${gun}<input type="hidden" name="gunId" value="${gun.getId()}"/>
        <input type="submit" name="submit" value="add to cart"/><br>
    </c:forEach>
</form>
</body>
</html>
