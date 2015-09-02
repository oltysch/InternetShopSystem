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
        href="${pageContext.request.contextPath}/admin/bullets">режим просмотра</a><br>
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
                    <form action="${pageContext.request.contextPath}/admin/change_bullet">
                        <td><label>${bullet.id}</label></td>
                        <td><label>${bullet.uuid}</label></td>
                        <td><input name="caliber" type="text" value="${bullet.caliber}"/></td>
                        <td><input name="name" type="text" value="${bullet.name}"/></td>

                        <td><select name="type">
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
                        </select></td>

                        <td><input name="price" type="text" value="${bullet.price}"/></td>
                        <td><input name="qty" type="text" value="${bullet.qty}"/></td>
                        <td><input name="description" type="text" value="${bullet.description}"/></td>
                        <td><input type="hidden" name="uuid"
                                   value="${bullet.uuid}"/>
                        <td><input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/change_bullet"
                                   value="обновить"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/delete_bullet"
                                   value="удалить"/>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td>
                    <div>в базе данных записи отсутствуют</div>
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
                <option value="Armor-piercing">Armor-piercing</option>
                <option value="Ball">Ball</option>
                <option value="Tracer">Tracer</option>
                <option value="Incendiary">Incendiary</option>
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
