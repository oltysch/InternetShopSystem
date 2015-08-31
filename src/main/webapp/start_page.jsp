<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${lang == 'ru'}}">
    <fmt:setLocale value='ru' scope="session"/>
</c:if>
<c:if test="${lang == 'en'}}">
    <fmt:setLocale value='en' scope="session"/>
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
        <span class="right">
            <c:if test="${lang == 'ru'}"><a>ru</a></c:if>
            <c:if test="${lang != 'ru'}"><a href="?lang=ru">ru</a></c:if>
            <c:if test="${lang == 'en'}"><a>en</a></c:if>
            <c:if test="${lang != 'en'}"><a href="?lang=en">en</a></c:if>
        </span>
            <div class="clr"></div>
        </div>
        <div id="login-form">
            <h1><fmt:message key="title.authorization"/></h1>
            <fieldset>
                <form>
                    <input type="submit" value=
                        <fmt:message key="button.enter"/> formaction="${pageContext.request.contextPath}/gunshop/login"><br>
                    <input type="submit" value=
                        <fmt:message
                                key="button.register"/> formaction="${pageContext.request.contextPath}/gunshop/register">
                    <footer class="clearfix">
                    </footer>
                </form>
            </fieldset>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>