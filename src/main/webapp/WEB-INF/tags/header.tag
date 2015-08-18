<%@ attribute name="shopcart" required="true" %>
<%@ attribute name="exit" required="true" %>
<div id="header">
    <div class="left">
        Gun Shop
    </div>
    <div class="mid">
        +7 (7212) 12-34-56<br>
        +7 777 123-45-67
    </div>
    <div class="right">
        <c:choose>
            <c:when test="${not empty user}">
                ${user.login}<br>                                <%--TODO check russian encodins--%>
                <a href="${pageContext.request.contextPath}/gunshop/shopcart">${shopcart}</a><br>
                <a href="${pageContext.request.contextPath}/gunshop/logout">${exit}</a>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </div>
</div>