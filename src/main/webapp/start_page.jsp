<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="language" value="${pageContext.request.locale}"/>--%>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href=${pageContext.request.contextPath}"/authorization-style.css"/>
</head>
<body>
<c:choose>
    <c:when test="${not empty user}">
        <c:redirect url="${pageContext.request.contextPath}/gunshop/products"/>
    </c:when>
    <c:otherwise>
        <div class="top">
            <br>
        <span class="right">
        </span>

            <div class="clr"></div>
        </div>
        <div id="login-form">
            <h1>Авторизация на сайте</h1>
            <fieldset>
                <form>
                    <input class="selector" type="submit" value=
                        <fmt:message key="button.enter"/>
                           formaction="${pageContext.request.contextPath}/gunshop/login"><br>
                    <input class="selector" type="submit" value=
                        <fmt:message key="button.register"/>
                           formaction="${pageContext.request.contextPath}/gunshop/register">
                    <footer class="clearfix">
                    </footer>
                </form>
            </fieldset>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
