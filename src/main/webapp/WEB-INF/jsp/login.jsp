<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>
<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>
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
            <a href="${pageContext.request.contextPath}/"><fmt:message key="button.back"/></a>
        <span class="right">

        </span>
            <div class="clr"></div>
        </div>
        <div id="login-form">
            <h1><fmt:message key="title.authorization"/></h1>
            <fieldset>
                <form action="${pageContext.request.contextPath}/gunshop/login" method="post">
                    <input name="login" type="text" required placeholder="<fmt:message key="placeholder.login" />"
                           value="${login}">
                    <input name="password" type="password" required
                           placeholder="<fmt:message key="placeholder.password" />">
                    <input type="submit" value="<fmt:message key="button.enter2" />">
                    <footer class="clearfix">
                        <c:if test="${not empty registerError}">
                            <div style="color: red"><fmt:message key="${loginError}"/></div>
                        </c:if>
                    </footer>
                </form>
            </fieldset>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>