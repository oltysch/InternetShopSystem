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
                    <form>
                        <td><label>${bullet.id}</label></td>
                        <td><label>${bullet.uuid}</label></td>
                        <td><input name="caliber" type="text" value="${bullet.caliber}"/></td>
                        <td><input name="name" type="text" value="${bullet.name}"/></td>

                        <td><select name="type">
                                <%--TODO load from bd--%>
                            <c:choose>
                                <c:when test="${bullet.type=='Armor-piercing'}">
                                    <option value="Armor-piercing" selected>Armor-piercing</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Armor-piercing">Armor-piercing</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${bullet.type=='Ball'}">
                                    <option value="Ball" selected>Ball</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Ball">Ball</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${bullet.type=='Tracer'}">
                                    <option value="Tracer" selected>Tracer</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Tracer">Tracer</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${bullet.type=='Incendiary'}">
                                    <option value="Incendiary" selected>Incendiary</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Incendiary">Incendiary</option>
                                </c:otherwise>
                            </c:choose>
                        </select></td>

                        <td><input name="price" type="number" value="${bullet.price}"/></td>
                        <td><input name="qty" type="number" value="${bullet.qty}"/></td>
                        <td><input name="description" type="text" value="${bullet.description}"/></td>
                        <td><input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/changeBullet"
                                   value="обновить"/>
                            <input type="submit" name="submit"
                                   formaction="${pageContext.request.contextPath}/admin/deleteBullet"
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
            <td><input name="caliber" type="text"/></td>
            <td><input name="name" type="text"/></td>

            <td><select name="type">
                <option value="Armor-piercing">Armor-piercing</option>
                <option value="Ball">Ball</option>
                <option value="Tracer">Tracer</option>
                <option value="Incendiary">Incendiary</option>
            </select></td>

            <td><input name="price" type="number"/></td>
            <td><input name="qty" type="number"/></td>
            <td><input name="description" type="text"/></td>
            <td><input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createBullet"
                       value="добавить"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
