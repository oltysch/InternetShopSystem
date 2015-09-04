<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="product" required="true" type="com.epam.ot.entity.ProductBlock" %>
<%@ attribute name="buttonText" required="true" %>
<%@ attribute name="buttonType" required="true" %>
<%@ attribute name="showCount" type="java.lang.Boolean" %>
<form>
    <input type="hidden" name="selectedProductUuid" value="${product.uuid}"/>
    <input type="hidden" name="selectedProductType" value="${product.productType}"/>

    <div class="product_block">
        <div class="name">
            <c:if test="${showCount == true}">
                <span><input type="number" name="newCount" value="${cart.getProductCount(product.uuid)}">
                    <input type="submit" value="<fmt:message key='button.refresh'/>"
                           formaction="${pageContext.request.contextPath}/gunshop/shopcart/change_product_count">
                 x</span>
            </c:if>
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
    <input type="submit" name="submit" value="<fmt:message key='${buttonText}'/>"
           formaction="${pageContext.request.contextPath}/gunshop/${buttonType == 'remove' ? 'remove_from_cart' : 'add_to_cart'}"
           formmethod="post"/>
    <c:if test="${not empty user && user.role == 'ADMIN'}">
        <input type="submit" value="<fmt:message key='button.edit'/>"
               formaction="${pageContext.request.contextPath}/admin/product_editor"/>
    </c:if>
</form>