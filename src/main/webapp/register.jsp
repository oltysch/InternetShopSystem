<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/gunshop/register" method="post">
    <label>Регистрация</label><br>
    <label>Логин: </label><input name="login" type="text" placeholder="steve_jobs" required><br>
    <label>Электронная почта: </label><input name="email" type="email" placeholder="steve_jobs@apple.com" required><br>
    <label>password: </label><input name="password" type="password" placeholder="gagaga56" required><br>
    <button type="submit">Зарегистрироваться</button>
</form>
</body>
</html>
