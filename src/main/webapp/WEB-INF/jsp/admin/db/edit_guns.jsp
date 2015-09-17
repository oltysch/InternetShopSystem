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
        href="${pageContext.request.contextPath}/admin/guns"><fmt:message key="button.view.mode"/></a><br>
<table>
    <tr>
        <td><fmt:message key="db.guns.id"/></td>
        <td><fmt:message key="db.guns.uuid"/></td>
        <td><fmt:message key="db.guns.type"/></td>
        <td><fmt:message key="db.guns.model"/></td>
        <td><fmt:message key="db.guns.price"/></td>
        <td><fmt:message key="db.guns.origin"/></td>
        <td><fmt:message key="db.guns.description"/></td>
        <td><fmt:message key="db.guns.firing.range"/></td>
        <td><fmt:message key="db.guns.effective.firing.range"/></td>
        <td><fmt:message key="db.guns.cartridge.capacity"/></td>
        <td><fmt:message key="db.guns.caliber"/></td>
        <td><fmt:message key="db.guns.fire.rate"/></td>
    </tr>
    <c:choose>
        <c:when test="${not empty guns}">
            <c:forEach var="gun" items="${guns}" varStatus="iter">
                <tr>
                    <form action="${pageContext.request.contextPath}/admin/change_gun_db">
                        <td><label>${gun.id}</label></td>
                        <td><label>${gun.uuid}</label></td>
                        <td><select name="type">
                            <c:forEach var="current_type" items="${types}">
                                <option value="${current_type}" ${gun.type==current_type ? 'selected' : ''}><fmt:message
                                        key="${current_type}"/></option>
                            </c:forEach>
                        </select></td>

                        <td><input name="model" type="text" value="${gun.name}"/></td>
                        <td><input name="price" type="text" value="${gun.price}"/></td>
                        <td><input name="origin" type="text" value="${gun.origin}"/></td>
                        <td><input name="description" type="text" value="${gun.description}"/></td>
                        <td><input name="firingRange" type="text" value="${gun.firingRange}"/></td>
                        <td><input name="effectiveFiringRange" type="text" value="${gun.effectiveFiringRange}"/>
                        </td>
                        <td><input name="magazineCapacity" type="text" value="${gun.magazineCapacity}"/></td>
                        <td><input name="caliber" type="text" value="${gun.caliber}"/></td>
                        <td><input name="fireRate" type="text" value="${gun.fireRate}"/></td>
                        <td><input type="hidden" name="selectedProductUuid"
                                   value="${gun.uuid}"/>
                        <td><input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/change_gun_db"
                                   value="обновить"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/delete_gun"
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
        <form action="${pageContext.request.contextPath}/admin/create_gun">
            <td><label></label></td>
            <td><label></label></td>
            <td><select name="type">
                <option value="pistol"><fmt:message key="pistol"/></option>
                <option value="revolver"><fmt:message key="revolver"/></option>
                <option value="rifle"><fmt:message key="rifle"/></option>
                <option value="carbine"><fmt:message key="carbine"/></option>
                <option value="assault.rifle"><fmt:message key="assault.rifle"/></option>
                <option value="machine.pistol"><fmt:message key="machine.pistol"/></option>
                <option value="machine.gun"><fmt:message key="machine.gun"/></option>
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
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/create_gun"
                       value="добавить"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
