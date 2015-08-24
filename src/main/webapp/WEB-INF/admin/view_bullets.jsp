<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <title>Gun Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/../style.css"/>
    </title>
</head>
<body>
<a:mainTemplate>
    <form>
        <input type="submit" name="submit" formaction="${pageContext.request.contextPath}/admin/createBullet"
               value="добавить"/>
        <c:choose>
            <c:when test="${not empty bullets}">
                <c:forEach var="bullet" items="${bullets}" varStatus="iter">

                    ${bullet.name} ${bullet.price}<input type="hidden" name="selectedBulletUuid"
                                                         value="${bullet.uuid}"/>

                    <input type="submit" name="submit"
                           formaction="${pageContext.request.contextPath}/admin/changeBullet"
                           value="изменить"/>
                    <input type="submit" name="submit"
                           formaction="${pageContext.request.contextPath}/admin/deleteBullet"
                           value="удалить"/>
                    <br>

                </c:forEach>
            </c:when>
            <c:otherwise>
                &lt;%&ndash;TODO make this display in table instead this&ndash;%&gt;
                <div>в базе данных записи отсутствуют</div>
            </c:otherwise>
        </c:choose>
    </form>
</a:mainTemplate>
</body>
</html>
--%>
