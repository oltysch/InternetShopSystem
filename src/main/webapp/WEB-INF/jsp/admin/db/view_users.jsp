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
        href="${pageContext.request.contextPath}/admin/edit_users"><fmt:message key="button.edit.mode"/></a><br>
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
                    <form>
                        <td><label>${user.id}</label></td>
                        <td><label>${user.uuid}</label></td>
                        <td><label>${user.login}</label></td>
                        <td><label>${user.email}</label></td>
                        <td><label>${user.role}</label></td>
                        <td><label>*******</label></td>
                        <td><label>${user.cash}</label></td>
                        <td><label>${user.banned}</label></td>
                        <td>
                            <input type="hidden" name="uuid"
                                   value="${user.uuid}"/>
                            <c:if test="${user.role == 'ADMIN'}">
                                <input type="submit" name="submit"
                                       formaction="${pageContext.request.contextPath}/admin/make_user"
                                       value="<fmt:message key="button.make.user"/>"/>
                            </c:if>
                            <c:if test="${user.role == 'USER'}">
                                <input type="submit" name="submit"
                                       formaction="${pageContext.request.contextPath}/admin/make_admin"
                                       value="<fmt:message key="button.make.admin"/>"/>
                            </c:if>
                            <c:if test="${user.banned}">
                                <input type="submit" name="submit"
                                       formaction="${pageContext.request.contextPath}/admin/unban_user"
                                       value="<fmt:message key="button.unban.user"/>"/>
                            </c:if>
                            <c:if test="${!user.banned}">
                                <input type="submit" name="submit"
                                       formaction="${pageContext.request.contextPath}/admin/ban_user"
                                       value="<fmt:message key="button.ban.user"/>"/>
                            </c:if>
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
</table>
</body>
</html>
