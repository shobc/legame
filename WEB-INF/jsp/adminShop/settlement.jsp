<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-top-login-layout.jsp">
    <c:param name="head">
        <title>���σy�[�W</title>
        <style>
            .settlement{
                padding-top: 200px;
                text-align: center;
            }
        </style>
    </c:param>
    <c:param name="content">
        <div class="settlement">
            <form action="SettlementServlet" method="post">
                ���Ɏg�����H<input type="text" name="history"><br>
                ������H<input type="number" name="price"><br>
                <input type="hidden" name="qrcode" value="${param.RandomString}"><br>
                <input type="submit" value="����"><br>
            </form>
        </div>
    </c:param>
</c:import>