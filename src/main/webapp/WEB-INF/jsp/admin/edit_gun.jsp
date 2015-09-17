<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/main_style.css"/>
</head>
<body>
<a:mainTemplate>
    <a href="#" onclick="history.back()"><fmt:message key="button.back"/></a>

    <form action="${pageContext.request.contextPath}/admin/change_gun">
        <label><fmt:message key="gun.model"/>:
            <input name="model" type="text" value="${product.name}"/>
        </label><br>
        <label><fmt:message key="gun.type"/>
            <select name="type">
                <c:forEach var="current_type" items="${types}">
                    <option value="${current_type}" ${product.type==current_type ? 'selected' : ''}>${current_type}</option>
                </c:forEach>
            </select>
        </label><br>

        <label><fmt:message key="price"/>:
            <input name="price" type="text" value="${product.price}"/>
        </label><br>
        <label><fmt:message key="gun.origin"/>:
            <input name="origin" type="text" value="${product.origin}"/>
        </label><br>
        <label><fmt:message key="description"/>:
            <input name="description" type="text" value="${product.description}"/>
        </label><br>
        <label><fmt:message key="gun.firingRange"/>:
            <input name="firingRange" type="text" value="${product.firingRange}"/>
        </label><br>
        <label><fmt:message key="gun.effectiveFiringRange"/>:
            <input name="effectiveFiringRange" type="text" value="${product.effectiveFiringRange}"/>
        </label><br>
        <label><fmt:message key="gun.magazineCapacity"/>:
            <input name="magazineCapacity" type="text" value="${product.magazineCapacity}"/>
        </label><br>
        <label><fmt:message key="gun.caliber"/>:
            <input name="caliber" type="text" value="${product.caliber}"/>
        </label><br>
        <label><fmt:message key="gun.fireRate"/>:
            <input name="fireRate" type="text" value="${product.fireRate}"/>
        </label><br>
        <input type="hidden" name="selectedProductUuid"
               value="${product.uuid}"/>
        <input type="hidden" name="selectedProductType"
               value="${product.toBlock().productType}"/>
        <input type="submit" name="submit"
               formaction="${pageContext.request.contextPath}/admin/change_gun"
               value="<fmt:message key="button.refresh"/>"/>
    </form>
</a:mainTemplate>
</body>
</html>
