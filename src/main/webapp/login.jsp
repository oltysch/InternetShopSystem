<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${lang == 'ru'}}">
    <fmt:setLocale value='ru' scope="session"/>
</c:if>
<c:if test="${lang == 'en'}}">
    <fmt:setLocale value='en'/>
</c:if>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/authorization_style.css"/>
</head>
<body>
<c:choose>
    <c:when test="${not empty user}">
        <c:redirect url="${pageContext.request.contextPath}/gunshop/products"/>
    </c:when>
    <c:otherwise>
        <div class="top">
            <a href="${pageContext.request.contextPath}/">Назад</a>
        <span class="right">

        </span>
            <div class="clr"></div>
        </div>
        <div id="login-form">
            <h1>Авторизация на сайте</h1>
            <fieldset>
                <form action="${pageContext.request.contextPath}/gunshop/login" method="post">
                    <input name="login" type="text" required placeholder="Логин" value="${login}">
                    <input name="password" type="password" required placeholder="Пароль">
                    <input type="submit" value="ВОЙТИ">
                    <footer class="clearfix">
                        <div style="color: red">${loginError}</div>
                    </footer>
                </form>
            </fieldset>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>