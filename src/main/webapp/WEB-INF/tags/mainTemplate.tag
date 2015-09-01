<%--<%@ attribute name="login" required="true" %>--%>
<%--<%@ attribute name="shopcart" required="true" %>
<%@ attribute name="exit" required="true" %>--%>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${lang == 'ru'}}">
    <fmt:setLocale value='ru' scope="session"/>
</c:if>
<c:if test="${lang == 'en'}}">
    <fmt:setLocale value='en' scope="session"/>
</c:if>
<fmt:setBundle basename="i18n"/>
<a:header/>
<div id="container">
    <a:sidebar/>
    <c:choose>
        <c:when test="${not empty user}">
            <div id="content">
                <jsp:doBody/>
            </div>
        </c:when>
        <c:otherwise>
            ${request.setAttribute("redirect_url", request.request.requestDispatcherPath)}
            ${request.}
            <c:redirect url="/gunshop/login"/>
        </c:otherwise>
    </c:choose>
</div>
<a:footer/>