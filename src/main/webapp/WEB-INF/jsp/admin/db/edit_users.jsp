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
        href="${pageContext.request.contextPath}/admin/users"><fmt:message key="button.view.mode"/></a><br>
<table>
    <tr>
        <td><fmt:message key="db.users.id"/></td>
        <td><fmt:message key="db.users.uuid"/></td>
        <td><fmt:message key="db.users.login"/></td>
        <td><fmt:message key="db.users.email"/></td>
        <td><fmt:message key="db.users.role"/></td>
        <td><fmt:message key="db.users.password"/></td>
        <td><fmt:message key="db.users.cash"/></td>
        <td><fmt:message key="db.users.banned"/></td>
    </tr>
    <c:choose>
        <c:when test="${not empty users}">
            <c:forEach var="user" items="${users}" varStatus="iter">
                <tr>
                    <form action="${pageContext.request.contextPath}/admin/change_user">
                        <td><label>${user.id}</label></td>
                        <td><label>${user.uuid}</label></td>
                        <td><input name="login" type="text" value="${user.login}"/></td>
                        <td><input name="email" type="email" value="${user.email}"/></td>
                        <td><select name="role">
                            <c:forEach var="current_role" items="${roles}">
                                <option value="${current_role}" ${user.role == current_role ? 'selected' : ''}>${current_role}</option>
                            </c:forEach>
                        </select></td>
                        <td><input name="password" type="text" value="${user.password}"/></td>
                        <td><input name="cash" type="text" value="${user.cash}"/></td>
                        <td><input name="banned" type="checkbox" value="${user.banned}"/></td>
                        <td><input type="hidden" name="uuid"
                                   value="${user.uuid}"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/change_user"
                                   value="обновить"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/delete_user"
                                   value="удалить"/>
                        </td>
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
    <tr>
        <form action="${pageContext.request.contextPath}/admin/create_user">
            <td></td>
            <td></td>
            <td><input name="login" type="text"/></td>
            <td><input name="email" type="email"/></td>
            <td><select name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER" selected>USER</option>
            </select></td>
            <td><input name="password" type="text"/></td>
            <td><input name="cash" type="text"/></td>
            <td><input name="banned" type="checkbox"/></td>
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/create_user"
                       value="добавить"/></td>
        </form>
    </tr>
</table>
</body>
</html>
