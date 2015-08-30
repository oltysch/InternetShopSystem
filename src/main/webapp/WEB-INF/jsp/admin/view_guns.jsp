<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <title>Gun Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/../style.css"/>
    </title>
</head>
<body>
<a href="${pageContext.request.contextPath}/gunshop/">на главную</a> | <a
        href="${pageContext.request.contextPath}/admin/edit_guns">редактировать</a><br>
<table>
    <tr>
        <td>ID</td>
        <td>UUID</td>
        <td>TYPE</td>
        <td>MODEL</td>
        <td>PRICE</td>
        <td>ORIGIN</td>
        <td>DESCRIPTION</td>
        <td>FIRING_RANGE</td>
        <td>EFFECTIVE_FIRING_RANGE</td>
        <td>MAGAZINE_CAPACITY</td>
        <td>CALIBER</td>
        <td>FIRE_RATE</td>
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
                    <div>в базе данных записи отсутствуют</div>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
