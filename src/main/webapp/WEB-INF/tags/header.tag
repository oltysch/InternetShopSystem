<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:choose>
            <c:when test="${not empty user}">
                ${user.login}<br>                                <%--TODO check russian encodings--%>
                <a href="${pageContext.request.contextPath}/gunshop/shopcart">shopcart</a><br>
                <a href="${pageContext.request.contextPath}/gunshop/logout">exit</a>
            </c:when>
            <c:otherwise>
                <c:redirect url="/gunshop/"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>