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
        href="${pageContext.request.contextPath}/admin/edit_users">редактировать</a><br>
<table>
    <tr>
        <td>ID</td>
        <td>UUID</td>
        <td>LOGIN</td>
        <td>EMAIL</td>
        <td>ROLE</td>
        <td>PASSWORD</td>
    </tr>
    <c:choose>
        <c:when test="${not empty users}">
            <c:forEach var="user" items="${users}" varStatus="iter">
                <tr>
                    <form>
                        <td><label>${user.id}</label></td>
                        <td><label>${user.uuid}</label></td>
                        <td><label>${user.login}</label></td>
                        <td><label>${user.email}</label></td>
                        <td><label>${user.role}</label></td>
                        <td><label>${user.password}</label></td>
                    </form>
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
