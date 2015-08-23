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
                    <form>
                        <td><label>${gun.id}</label></td>
                        <td><label>${gun.uuid}</label></td>
                        <td><select name="type">
                                <%--TODO load from bd--%>
                            <c:choose>
                                <c:when test="${gun.type=='Pistol'}">
                                    <option value="Pistol" selected>Pistol</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Pistol">Pistol</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gun.type=='Revolver'}">
                                    <option value="Revolver" selected>Revolver</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Revolver">Revolver</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gun.type=='Rifle'}">
                                    <option value="Rifle" selected>Rifle</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Rifle">Rifle</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gun.type=='Carbine'}">
                                    <option value="Carbine" selected>Carbine</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Carbine">Carbine</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gun.type=='Assault Rifle'}">
                                    <option value="Assault Rifle" selected>Assault Rifle</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Assault Rifle">Assault Rifle</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gun.type=='Machine Pistol'}">
                                    <option value="Machine Pistol" selected>Machine Pistol</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Machine Pistol">Machine Pistol</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gun.type=='Machine Gun'}">
                                    <option value="Machine Gun" selected>Machine Gun</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Machine Gun">Machine Gun</option>
                                </c:otherwise>
                            </c:choose>

                        </select></td>

                        <td><input name="model" type="text" value="${gun.model}"/></td>
                        <td><input name="price" type="number" value="${gun.price}"/></td>
                        <td><input name="origin" type="text" value="${gun.origin}"/></td>
                        <td><input name="description" type="text" value="${gun.description}"/></td>
                        <td><input name="firingRange" type="number" value="${gun.ttc.firingRange}"/></td>
                        <td><input name="effectiveFiringRange" type="number" value="${gun.ttc.effectiveFiringRange}"/>
                        </td>
                        <td><input name="magazineCapacity" type="number" value="${gun.ttc.magazineCapacity}"/></td>
                        <td><input name="caliber" type="text" value="${gun.ttc.caliber}"/></td>
                        <td><input name="fireRate" type="number" value="${gun.ttc.fireRate}"/></td>
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
        <form>
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
            <td><input name="price" type="number"/></td>
            <td><input name="origin" type="text"/>
                value="${gun.description}"/>
            </td>
            <td><input name="description" type="text"/></td>
            <td><input name="firingRange" type="number"/></td>
            <td><input name="effectiveFiringRange" type="number"/></td>
            <td><input name="magazineCapacity" type="number"/></td>
            <td><input name="caliber" type="text"/></td>
            <td><input name="fireRate" type="number"/></td>
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createGun"
                       value="добавить"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
