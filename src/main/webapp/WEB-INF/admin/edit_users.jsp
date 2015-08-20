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
    <c:choose>
        <c:when test="${not empty users}">
            <c:forEach var="user" items="${users}" varStatus="iter">
                <form>
                        ${user.login} ${user.role}<input type="hidden" name="selectedUserUuid" value="${user.uuid}"/>
                    <input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/changeUser"
                           value="изменить"/>
                    <c:if test="${user.role == 'ADMIN'}">
                        <input type="submit" name="submit"
                               formaction="${pageContext.request.contextPath}/admin/makeUser"
                               value="убрать права админа"/>
                    </c:if>
                    <c:if test="${user.role == 'USER'}">
                        <input type="submit" name="submit"
                               formaction="${pageContext.request.contextPath}/admin/makeAdmin"
                               value="дать права админа"/>
                    </c:if>
                    <input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/deleteUser"
                           value="удалить"/>
                    <br>
                </form>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div>Список пользователей пуст</div>
        </c:otherwise>
    </c:choose>
</a:mainTemplate>
</body>
</html>
