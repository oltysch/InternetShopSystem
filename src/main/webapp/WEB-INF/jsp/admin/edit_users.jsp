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
        href="${pageContext.request.contextPath}/admin/users">режим просмотра</a><br>
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
                        <td><input name="login" type="text" value="${user.login}"/></td>
                        <td><input name="email" type="email" value="${user.email}"/></td>
                        <td><select name="role">
                            <c:forEach var="current_role" items="${roles}">
                                <c:choose>
                                    <c:when test="${user.role==current_role}">
                                        <option value="${current_role}" selected>${current_role}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${current_role}">${current_role}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select></td>
                        <td><input name="password" type="text" value="${user.password}"/></td>
                        <td><input type="hidden" name="uuid"
                                   value="${user.uuid}"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/changeUser"
                                   value="обновить"/>
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
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/deleteUser"
                                   value="удалить"/>
                        </td>
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
    <tr>
        <form>
            <td></td>
            <td></td>
            <td><input name="login" type="text"/></td>
            <td><input name="email" type="email"/></td>
            <td><select name="role">
                <%--TODO load from bd--%>
                <option value="ADMIN">ADMIN</option>
                <option value="USER" selected>USER</option>
                <option value="GUEST">GUEST</option>
            </select></td>
            <td><input name="password" type="text"/></td>
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createUser"
                       value="добавить"/></td>
        </form>
    </tr>
</table>
</body>
</html>
