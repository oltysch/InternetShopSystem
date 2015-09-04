<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Gun Shop</title>
    <link rel="stylesheet" href=${pageContext.request.contextPath}"/main_style.css"/>
</head>
<body>
<a:mainTemplate>
    <a href="#" onclick="history.back()"><fmt:message key="button.back"/></a><br>
    <form>
        <div class="view_product">
            <div class="name">${product.name}</div>

            <div class="characteristics">
                <c:forEach var="characteristic" items="${product.characteristics}" varStatus="iter">
                    <span class="left">
                    <c:if test="${not empty characteristic.name}">
                        <fmt:message key="${characteristic.name}"/>:
                        </span><span class="right">${characteristic.value}</span><br>
                    </c:if>
                </c:forEach>
            </div>
            <div class="description">
                    ${product.fullDescription}
            </div>
        </div>
        <input type="hidden" name="selectedProductUuid" value="${product.uuid}"/>
        <input type="hidden" name="selectedProductType" value="${product.productType}"/>

        <div class="price">
            <input type="submit" name="submit" value="<fmt:message key='button.add_to_cart'/>"
                   formaction="${pageContext.request.contextPath}/gunshop/add_to_cart" formmethod="post"/>
                ${product.price} $
        </div>
    </form>
</a:mainTemplate>
</body>
</html>
