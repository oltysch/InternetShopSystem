<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<%--<%@ attribute name="shopcart" required="true" %>
<%@ attribute name="exit" required="true" %>--%>
<div id="header">
    <div class="left">
        <a href="${pageContext.request.contextPath}/gunshop/">Gun Shop</a>
    </div>
    <div class="mid">
        +7 (7212) 12-34-56<br>
        +7 777 123-45-67
    </div>
    <div class="right">
        ${user.login}<br>
        <a href="${pageContext.request.contextPath}/gunshop/shopcart"><fmt:message key="shopcart"/></a><br>

            <div>cash ${user.cash} $</div>
        <a href="${pageContext.request.contextPath}/gunshop/logout">exit</a>
    </div>
</div>