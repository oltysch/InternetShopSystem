<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>
<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/authorization_style.css"/>
</head>
<body>
<div class="top">
    <a href="${pageContext.request.contextPath}/"><fmt:message key="button.back"/> </a>
        <span class="right">

        </span>

    <div class="clr"></div>
</div>
<div id="login-form">
    <h1><fmt:message key="title.registration"/></h1>
    <fieldset>
        <form action="${pageContext.request.contextPath}/gunshop/register" method="post">
            <input name="login" type="text" required placeholder="<fmt:message key="placeholder.enter.login" />">
            <input name="email" type="email" required placeholder="<fmt:message key="placeholder.enter.email" />">
            <input name="password" type="password" required
                   placeholder="<fmt:message key="placeholder.enter.password" />">
            <input type="submit" value="<fmt:message key="button.register" />">

            <c:if test="${not empty registerError}">
                <div style="color: red"><fmt:message key="${registerError}"/></div>
            </c:if>
            <footer class="clearfix">
            </footer>
        </form>
    </fieldset>
</div>
</body>
</html>