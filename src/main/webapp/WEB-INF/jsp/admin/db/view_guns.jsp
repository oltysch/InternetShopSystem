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
        href="${pageContext.request.contextPath}/admin/edit_guns"><fmt:message key="button.edit"/></a><br>
<table>
    <tr>
        <td><fmt:message key="db.guns.id"/></td>
        <td><fmt:message key="db.guns.uuid"/></td>
        <td><fmt:message key="db.guns.type"/></td>
        <td><fmt:message key="db.guns.model"/></td>
        <td><fmt:message key="db.guns.price"/></td>
        <td><fmt:message key="db.guns.origin"/></td>
        <td><fmt:message key="db.guns.description"/></td>
        <td><fmt:message key="db.guns.firing.range"/></td>
        <td><fmt:message key="db.guns.effective.firing.range"/></td>
        <td><fmt:message key="db.guns.cartridge.capacity"/></td>
        <td><fmt:message key="db.guns.caliber"/></td>
        <td><fmt:message key="db.guns.fire.rate"/></td>
    </tr>
    <c:choose>
        <c:when test="${not empty guns}">
            <c:forEach var="gun" items="${guns}" varStatus="iter">
                <tr>
                    <form>
                        <td><label>${gun.id}</label></td>
                        <td><label>${gun.uuid}</label></td>
                        <td><label>${gun.type}</label></td>
                        <td><label>${gun.name}</label></td>
                        <td><label>${gun.price}</label></td>
                        <td><label>${gun.origin}</label></td>
                        <td><label>${gun.description}</label></td>
                        <td><label>${gun.firingRange}</label></td>
                        <td><label>${gun.effectiveFiringRange}</label></td>
                        <td><label>${gun.magazineCapacity}</label></td>
                        <td><label>${gun.caliber}</label></td>
                        <td><label>${gun.fireRate}</label></td>
                    </form>
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
