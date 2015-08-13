<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.08.2015
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
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
        <%--TODO change to register--%>
        <div style="color: red">${loginError}</div>
    </form>
</body>
</html>
