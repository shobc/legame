<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-top-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/adminShop/home.css' />">
        <script src="<c:url value='/js/adminShop/sold-list.js' />"></script>
        <title>ÉzÅ[ÉÄ</title>
    </c:param>
    <c:param name="content">
        <div class="caption">
            <h2>${caption}</h2>
        </div>
        <div  class="date_search">
            <form method="post" action="${uri}">
                <div class="selectdiv">
                    <div>
                        <select onchange="update()" name="year" id="year"></select>
                    </div>
                    <div>
                        <select name="mon" id="mon"></select>
                    </div>
                    <input type="submit" class="submit_box" value="åàíË">
                </div>
            </form>
        </div>
        <div>
        </div>
        <div class="content">
            <table cellpadding="1px 10px">
                <tr><th>Ç®ã‡</th><th>óöó</th><th>éûä‘</th><th>${reverseMoney}</th></tr>
                <c:forEach var="pl" items="${propertyList}">
                        <tr>
                            <td>${pl.money}</td>
                            <td class="history">${pl.history}</td>
                            <td>${pl.date}</td>
                            <c:if test="${pl.cancel_flag==0}">
                                <td><a href="CancelItemServlet?property_id=${pl.property_id}">ï‘ã‡</a></td>
                            </c:if>
                        </tr>
                </c:forEach>
            </table>
        </div>
    </c:param>
</c:import>