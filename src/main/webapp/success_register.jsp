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
<div>Поздравляем ${user.getLogin()}, теперь вы зарегистрированы. <a
        href="${pageContext.request.contextPath}/new/shop">Продолжить</a></div>
</body>
</html>
