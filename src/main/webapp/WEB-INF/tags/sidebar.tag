<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="sidebar">
    <div class="products" type="list">
        <div class="top"><a href="${pageContext.request.contextPath}/gunshop/products?prType=guns"><fmt:message
                key="title.guns"/></a></div>
        <div class="points">
            <ul>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Pistol"><fmt:message
                        key="pistols"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Revolver"><fmt:message
                        key="revolvers"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Rifle"><fmt:message
                        key="rifles"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Carbine"><fmt:message
                        key="carbines"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Assault Rifle"><fmt:message
                        key="assault.rifles"/></a>
                <li>
                    <a href="${pageContext.request.contextPath}/gunshop/products?seltp=Machine Pistol"><fmt:message
                            key="machine.pistols"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Machine Gun"><fmt:message
                        key="machine.guns"/></a>
            </ul>
        </div>
    </div>
    <div class="products" type="list">
        <div class="top"><a href="${pageContext.request.contextPath}/gunshop/products?prType=bullets"><fmt:message
                key="title.bullets"/></a></div>
        <div class="points">
            <ul>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Armor-piercing"><fmt:message
                        key="armor.piercing"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Ball"><fmt:message
                        key="ball"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Tracer"><fmt:message
                        key="tracer"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=Incendiary"><fmt:message
                        key="incendiary"/></a>
            </ul>
        </div>
    </div>
    <c:if test="${not empty user && user.role == 'ADMIN'}">
        <div class="admin" type="list">
            <div class="top"><fmt:message key="title.admin.block"/></div>
            <div class="points">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/users"><fmt:message
                            key="admin.edit.users"/></a>
                    <li><a href="${pageContext.request.contextPath}/admin/guns"><fmt:message key="admin.edit.guns"/></a>
                    <li><a href="${pageContext.request.contextPath}/admin/bullets"><fmt:message
                            key="admin.edit.bullets"/></a>
                </ul>
            </div>
        </div>
    </c:if>
</div>