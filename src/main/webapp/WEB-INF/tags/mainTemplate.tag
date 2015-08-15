<%@ attribute name="user" required="true" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%--<a:basePage>--%>
<a:header user="${user}"/>
<div id="container">
    <a:sidebar/>
    <div id="content">
        <jsp:doBody/>
    </div>
</div>
<a:footer/>
<%--</a:basePage>--%>