<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>
        <title>Gun Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/main_style.css"/>
    </title>
</head>
<body>
<a href="${pageContext.request.contextPath}/gunshop/"><fmt:message key="button.goto.main"/></a> | <a
        href="${pageContext.request.contextPath}/admin/bullets"><fmt:message key="button.view.mode"/></a><br>
<table>
    <tr>
        <td><fmt:message key="db.bullets.id"/></td>
        <td><fmt:message key="db.bullets.uuid"/></td>
        <td><fmt:message key="db.bullets.caliber"/></td>
        <td><fmt:message key="db.bullets.name"/></td>
        <td><fmt:message key="db.bullets.type"/></td>
        <td><fmt:message key="db.bullets.price"/></td>
        <td><fmt:message key="db.bullets.qty"/></td>
        <td><fmt:message key="db.bullets.description"/></td>
    </tr>
    <c:choose>
        <c:when test="${not empty bullets}">
            <c:forEach var="bullet" items="${bullets}" varStatus="iter">
                <tr>
                    <form action="${pageContext.request.contextPath}/admin/change_bullet_db">
                        <td><label>${bullet.id}</label></td>
                        <td><label>${bullet.uuid}</label></td>
                        <td><input name="caliber" type="text" value="${bullet.caliber}"/></td>
                        <td><input name="name" type="text" value="${bullet.name}"/></td>

                        <td><select name="type">
                            <c:forEach var="current_type" items="${types}">
                                <option value="${current_type}" ${bullet.type==current_type ? 'selected' : ''}>
                                    <fmt:message key="${current_type}"/></option>
                            </c:forEach>
                        </select></td>

                        <td><input name="price" type="text" value="${bullet.price}"/></td>
                        <td><input name="qty" type="text" value="${bullet.qty}"/></td>
                        <td><input name="description" type="text" value="${bullet.description}"/></td>
                        <td><input type="hidden" name="selectedProductUuid"
                                   value="${bullet.uuid}"/>
                        <td><input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/change_bullet_db"
                                   value="<fmt:message key="button.refresh"/>"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/delete_bullet"
                                   value="<fmt:message key="button.remove"/>"/>
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
        <form action="${pageContext.request.contextPath}/admin/create_bullet">
            <td><label></label></td>
            <td><label></label></td>
            <td><input name="caliber" type="text"/></td>
            <td><input name="name" type="text"/></td>

            <td><select name="type">
                <option value="armor.piercing"><fmt:message key="armor.piercing"/></option>
                <option value="ball"><fmt:message key="ball"/></option>
                <option value="tracer"><fmt:message key="tracer"/></option>
                <option value="incendiary"><fmt:message key="incendiary"/></option>
            </select></td>

            <td><input name="price" type="text"/></td>
            <td><input name="qty" type="text"/></td>
            <td><input name="description" type="text"/></td>
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/create_bullet"
                       value="добавить"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
