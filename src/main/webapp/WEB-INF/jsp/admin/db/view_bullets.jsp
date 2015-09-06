<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>
        <title>Gun Shop</title>
        <link rel="stylesheet" href="../../../../main_style.css"/>
    </title>
</head>
<body>
<a href="${pageContext.request.contextPath}/gunshop/"><fmt:message key="button.goto.main"/></a> | <a
        href="${pageContext.request.contextPath}/admin/edit_bullets"><fmt:message key="button.edit"/></a><br>
<table>
    <tr>
        <td><fmt:message key="db.bullets.id"/></td>
        <td><fmt:message key="db.bullets.uuid"/></td>
        <td><fmt:message key="db.bullets.caliber"/></td>
        <td><fmt:message key="db.bullets.name"/></td>
        <td><fmt:message key="db.bullets.type"/></td>
        <td><fmt:message key="db.bullets.price"/></td>
        <td><fmt:message key="db.bullets.qty"/></td>
        <td><fmt:message key="db.bullets.description"/></td>
    </tr>
    <c:choose>
        <c:when test="${not empty bullets}">
            <c:forEach var="bullet" items="${bullets}" varStatus="iter">
                <tr>
                    <td><label>${bullet.id}</label></td>
                    <td><label>${bullet.uuid}</label></td>
                    <td><label>${bullet.caliber}</label></td>
                    <td><label>${bullet.name}</label></td>
                    <td><label>${bullet.type}</label></td>
                    <td><label>${bullet.price}</label></td>
                    <td><label>${bullet.qty}</label></td>
                    <td><label>${bullet.description}</label></td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td>
                    <div><fmt:message key="message.db.clear"/></div>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
