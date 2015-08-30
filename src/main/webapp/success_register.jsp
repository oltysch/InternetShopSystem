<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>Поздравляем ${login}, теперь вы зарегистрированы.
    <form action="${pageContext.request.contextPath}/gunshop/login" method="post">
        <input name="login" type="hidden" value="${login}"><br>
        <input name="password" type="hidden" value="${password}"/><br>
        <button type="submit">Продолжить</button>
        <br>
        <div style="color: red">${loginError}</div>
    </form>
</body>
</html>
