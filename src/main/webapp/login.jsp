<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/new/login" method="post">
    <label>Логин: </label><input name="login" type="text" placeholder="steve_jobs" value="${login}"><br>
    <label>Пароль: </label><input name="password" type="password" placeholder="gagaga53"/><br>
    <button type="submit">Войти</button><br>
    <div style="color: red">${loginError}</div>
</form>
</body>
</html>
