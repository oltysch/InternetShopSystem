<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="sidebar">
    <div class="products" type="list">
        <div class="top"><a href="${pageContext.request.contextPath}/gunshop/products?prType=guns"><fmt:message
                key="title.guns"/></a></div>
        <div class="points">
            <ul>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=pistol"><fmt:message
                        key="pistols"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=revolver"><fmt:message
                        key="revolvers"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=rifle"><fmt:message
                        key="rifles"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=carbine"><fmt:message
                        key="carbines"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=assault.rifle"><fmt:message
                        key="assault.rifles"/></a>
                <li>
                    <a href="${pageContext.request.contextPath}/gunshop/products?seltp=machine.pistol"><fmt:message
                            key="machine.pistols"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=machine.gun"><fmt:message
                        key="machine.guns"/></a>
            </ul>
        </div>
    </div>
    <div class="products" type="list">
        <div class="top"><a href="${pageContext.request.contextPath}/gunshop/products?prType=bullets"><fmt:message
                key="title.bullets"/></a></div>
        <div class="points">
            <ul>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=armor.piercing"><fmt:message
                        key="armor.piercing"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=ball"><fmt:message
                        key="ball"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=tracer"><fmt:message
                        key="tracer"/></a>
                <li><a href="${pageContext.request.contextPath}/gunshop/products?seltp=incendiary"><fmt:message
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