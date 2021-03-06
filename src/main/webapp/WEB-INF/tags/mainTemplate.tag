<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>
<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>
<fmt:setBundle basename="i18n" scope="session"/>
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
            <c:redirect url="${pageContext.request.contextPath}/gunshop/"/>
        </c:otherwise>
    </c:choose>
</div>
<a:footer/>