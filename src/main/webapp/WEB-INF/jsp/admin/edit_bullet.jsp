<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href=${pageContext.request.contextPath}"/main_style.css"/>
</head>
<body>
<a:mainTemplate>
    <form action="${pageContext.request.contextPath}/admin/change_bullet">
        <input name="caliber" type="text" value="${bullet.caliber}"/><br>
        <input name="name" type="text" value="${bullet.name}"/><br>

        <select name="type">
            <c:forEach var="current_type" items="${types}">
                <c:choose>
                    <c:when test="${bullet.type==current_type}">
                        <option value="${current_type}" selected>${current_type}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${current_type}">${current_type}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select><br>

        <input name="price" type="text" value="${bullet.price}"/><br>
        <input name="qty" type="text" value="${bullet.qty}"/><br>
        <input name="description" type="text" value="${bullet.description}"/><br>
        <input type="hidden" name="uuid"
               value="${bullet.uuid}"/>
        <input type="submit" name="submit"
               formaction="${pageContext.request.contextPath}/admin/change_bullet"
               value="обновить"/>
        <input type="submit" name="submit"
               formaction="${pageContext.request.contextPath}/admin/delete_bullet"
               value="удалить"/>
    </form>
</a:mainTemplate>
</body>
</html>
