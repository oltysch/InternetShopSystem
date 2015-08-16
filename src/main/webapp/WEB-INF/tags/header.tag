<%@ attribute name="shopcart" required="true" %>
<%@ attribute name="exit" required="true" %>
<div id="header">
    <div class="left">

    </div>
    <div class="mid">

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