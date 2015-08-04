<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
${login}<br>
<a href="${pageContext.request.contextPath}/new/logout">Выйти</a><br>
${guns.get(0)}<br>
${guns.get(1)}
</body>
</html>
