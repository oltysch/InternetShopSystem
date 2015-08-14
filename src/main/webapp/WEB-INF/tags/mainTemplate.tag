<%@ attribute name="greeting" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%--<a:basePage>--%>
<a:header/>
<div id="container">
    <a:sidebar/>
    <div id="content">
        <jsp:doBody/>
    </div>
</div>
<a:footer/>
<%--</a:basePage>--%>