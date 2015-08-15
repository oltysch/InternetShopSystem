<%@ attribute name="user" required="true" %>
<div id="header">
    <div class="left">

    </div>
    <div class="mid">

    </div>
    <div class="right">
        <c:choose>
            <c:when test="${not empty user}">

            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </div>
</div>