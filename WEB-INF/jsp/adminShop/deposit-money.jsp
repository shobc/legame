<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-top-login-layout.jsp">
    <c:param name="head">
        <title>����</title>
        <style>
            .deposit{
                padding-top: 200px;
                text-align: center;
            }
        </style>
    </c:param>
    <c:param name="content">
        <div class="deposit">
            <h1>����</h1>
            <p>${money}</p>
            <form method="post" action="InputMoneyServlet">
                <input type="hidden" name="money" value="${money}"><br>
                <input type="hidden" name="RandomString" value="${RandomString}"><br>
                <input type="submit" value="�m��">
            </form>
        </div>
    </c:param>
</c:import>