<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>
<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <fmt:message key="register.congratulation1"/> ${login},
    <fmt:message key="register.congratulation2"/>
    <form action="${pageContext.request.contextPath}/gunshop/login" method="post">
        <input name="login" type="hidden" value="${login}"><br>
        <input name="password" type="hidden" value="${password}"/><br>
        <button type="submit"><fmt:message key="button.continue"/></button>
        <br>
        <div style="color: red">${loginError}</div>
    </form>
</body>
</html>
