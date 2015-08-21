<%--<%@ attribute name="login" required="true" %>--%>
<%--<%@ attribute name="shopcart" required="true" %>
<%@ attribute name="exit" required="true" %>--%>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<a:basePage>--%>
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
            <c:redirect url="/gunshop/"/>
        </c:otherwise>
    </c:choose>
</div>
<a:footer/>