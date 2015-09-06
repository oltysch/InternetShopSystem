<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="header">
    <div class="left">
        <a href="${pageContext.request.contextPath}/gunshop/">Gun Shop</a>
    </div>
    <div class="mid">
        +7 (7212) 12-34-56<br>
        +7 777 123-45-67
    </div>
    <div class="right">
        <a href="${lang != 'ru' ? '?lang=ru' : ''}">ru</a>/
        <a href="${lang != 'en' ? '?lang=en' : ''}">en</a>
        ${user.login}<br>
        <a href="${pageContext.request.contextPath}/gunshop/shopcart"><fmt:message key="shopcart"/></a><br>

        <div><fmt:message key="user.cash"/> ${user.cash} $</div>
        <a href="${pageContext.request.contextPath}/gunshop/logout"><fmt:message key="button.logout"/></a>
    </div>
</div>