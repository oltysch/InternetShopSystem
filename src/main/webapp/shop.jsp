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
    <c:forEach var="product" items="${products}" varStatus="iter">
        <form>
            <input type="hidden" name="selectedProductUuid" value="${product.uuid}"/>
            <input type="hidden" name="selectedProductType" value="${product.productType}"/>

            <div class="product_block">
                <div class="name">
                    <a href="${pageContext.request.contextPath}/gunshop/view_product?selectedProductUuid=${product.uuid}&selectedProductType=${product.productType}">
                            ${product.name}
                    </a>
                    <span class="price"> ${product.price} $</span>
                </div>
                <br>
        <span class="description">
                ${product.shortDescription}
        </span><br>
            </div>
            <input type="submit" name="submit" value="<fmt:message key='button.add_to_cart'/>"
                   formaction="${pageContext.request.contextPath}/gunshop/add_to_cart" formmethod="post"/>
            <c:if test="${not empty user && user.role == 'ADMIN'}">
                <input type="submit" value="<fmt:message key='button.edit'/>"
                       formaction="${pageContext.request.contextPath}/admin/product_editor"/>
            </c:if>
        </form>
    </c:forEach>
</a:mainTemplate>
</body>
</html>
