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
        href="${pageContext.request.contextPath}/admin/edit_bullets">редактировать</a><br>
<table>
    <tr>
        <td>ID</td>
        <td>UUID</td>
        <td>CALIBER</td>
        <td>NAME</td>
        <td>BULLET_TYPE</td>
        <td>PRICE</td>
        <td>QTY</td>
        <td>DESCRIPTION</td>
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
            <%--TODO make this display in table instead this--%>
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
