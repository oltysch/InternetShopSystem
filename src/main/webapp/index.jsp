<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="style.css"/>
</head>
<body>
<div id="header">
    <jsp:include page="header.jsp"/>
</div>
<div id="container">
    <div id="sidebar">
        <jsp:include page="left_sidebar.jsp"/>
    </div>
    <div id="content">
        <jsp:include page="content.jsp"/>
    </div>
</div>
<div id="footer">
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
