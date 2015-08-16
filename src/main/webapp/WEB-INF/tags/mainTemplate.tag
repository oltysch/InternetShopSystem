<%--<%@ attribute name="login" required="true" %>--%>
<%@ attribute name="shopcart" required="true" %>
<%@ attribute name="exit" required="true" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%--<a:basePage>--%>
<a:header shopcart="${shopcart}" exit="${exit}"/>
<div id="container">
    <a:sidebar/>
    <div id="content">
        <jsp:doBody/>
    </div>
</div>
<a:footer/>
<%--</a:basePage>--%>