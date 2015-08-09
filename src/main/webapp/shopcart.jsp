<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
<c:forEach var="gun" items="${user.getShopcart()}" varStatus="iter">
  <form action="${pageContext.request.contextPath}/new/removeFromCart" method="post">
      ${gun}<input type="hidden" name="selectedGunId" value="${gun.getId()}"/>
    <input type="submit" name="submit" value="убрать из корзины"/><br>
  </form>
</c:forEach>
<a href="${pageContext.request.contextPath}/new/shop">назад</a>
</body>
</html>
