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
        href="${pageContext.request.contextPath}/admin/guns">режим просмотра</a><br>
<table>
    <tr>
        <td>ID</td>
        <td>UUID</td>
        <td>TYPE</td>
        <td>MODEL</td>
        <td>PRICE</td>
        <td>ORIGIN</td>
        <td>DESCRIPTION</td>
        <td>FIRING_RANGE</td>
        <td>EFFECTIVE_FIRING_RANGE</td>
        <td>MAGAZINE_CAPACITY</td>
        <td>CALIBER</td>
        <td>FIRE_RATE</td>
    </tr>
    <c:choose>
        <c:when test="${not empty guns}">
            <c:forEach var="gun" items="${guns}" varStatus="iter">
                <tr>
                    <form action="${pageContext.request.contextPath}/admin/changeGun">
                        <td><label>${gun.id}</label></td>
                        <td><label>${gun.uuid}</label></td>
                        <td><select name="type">
                            <c:forEach var="current_type" items="${types}">
                                <c:choose>
                                    <c:when test="${gun.type==current_type}">
                                        <option value="${current_type}" selected>${current_type}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${current_type}">${current_type}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select></td>

                        <td><input name="model" type="text" value="${gun.model}"/></td>
                        <td><input name="price" type="text" value="${gun.price}"/></td>
                        <td><input name="origin" type="text" value="${gun.origin}"/></td>
                        <td><input name="description" type="text" value="${gun.description}"/></td>
                        <td><input name="firingRange" type="text" value="${gun.firingRange}"/></td>
                        <td><input name="effectiveFiringRange" type="text" value="${gun.effectiveFiringRange}"/>
                        </td>
                        <td><input name="magazineCapacity" type="text" value="${gun.magazineCapacity}"/></td>
                        <td><input name="caliber" type="text" value="${gun.caliber}"/></td>
                        <td><input name="fireRate" type="text" value="${gun.fireRate}"/></td>
                        <td><input type="hidden" name="uuid"
                                   value="${gun.uuid}"/>
                        <td><input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/changeGun"
                                   value="обновить"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/deleteGun"
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
        <form action="${pageContext.request.contextPath}/admin/createGun">
            <td><label></label></td>
            <td><label></label></td>
            <td><select name="type">
                <option value="Pistol">Pistol</option>
                <option value="Revolver">Revolver</option>
                <option value="Rifle">Rifle</option>
                <option value="Carbine">Carbine</option>
                <option value="Assault Rifle">Assault Rifle</option>
                <option value="Machine Pistol">Machine Pistol</option>
                <option value="Machine Gun">Machine Gun</option>
            </select></td>
            <td><input name="model" type="text"/></td>
            <td><input name="price" type="text"/></td>
            <td><input name="origin" type="text"/></td>
            <td><input name="description" type="text"/></td>
            <td><input name="firingRange" type="text"/></td>
            <td><input name="effectiveFiringRange" type="text"/></td>
            <td><input name="magazineCapacity" type="text"/></td>
            <td><input name="caliber" type="text"/></td>
            <td><input name="fireRate" type="text"/></td>
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createGun"
                       value="добавить"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
