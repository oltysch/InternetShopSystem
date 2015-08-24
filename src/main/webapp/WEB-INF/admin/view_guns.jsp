<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <title>Gun Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/../style.css"/>
    </title>
</head>
<body>
<a:mainTemplate>
    <form>
        <input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createGun"
               value="добавить"/>
        <c:choose>
            <c:when test="${not empty guns}">
                <c:forEach var="gun" items="${guns}" varStatus="iter">

                    ${gun.name} ${gun.price}<input type="hidden" name="selectedUserUuid" value="${gun.uuid}"/>

                    <input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/changeGun"
                           value="изменить"/>
                    <input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/deleteGun"
                           value="удалить"/>
                    <br>

                </c:forEach>
            </c:when>
            <c:otherwise>
                &lt;%&ndash;TODO make this display in table instead this&ndash;%&gt;
                <div>в базе данных записи отсутствуют</div>
            </c:otherwise>
        </c:choose></form>
</a:mainTemplate>
</body>
</html>
--%>
