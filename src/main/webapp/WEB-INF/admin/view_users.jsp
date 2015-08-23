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
<a href="${pageContext.request.contextPath}/gunshop/">назад</a><br>
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
                        <td><input type="text" value="${user.login}"/></td>
                        <td><input type="email" value="${user.email}"/></td>
                        <td><select>
                                <%--TODO load from bd--%>
                            <option value="ADMIN" selected="${user.role == 'ADMIN'}">ADMIN</option>
                            <option value="USER" selected="${user.role == 'USER'}">USER</option>
                            <option value="GUEST" selected="${user.role == 'GUEST'}">GUEST</option>
                        </select></td>
                        <td><input type="text" value="${user.password}"/></td>
                        <td><input type="hidden" name="selectedUserUuid"
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
        <td></td>
        <td></td>
        <td><input type="text"/></td>
        <td><input type="email"/></td>
        <td><select>
            <%--TODO load from bd--%>
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
            <option value="GUEST">GUEST</option>
        </select></td>
        <td><input type="text"/></td>
        <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createUser"
                   value="добавить"/></td>
    </tr>
</table>
</body>
</html>
