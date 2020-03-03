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
            input[type=text]{
                width: 100%;
                height: 50px;
            }
            input[type=number]{
                width: 100%;
                height: 50px;
            }
            input[type=submit]{
                width: 100%;
                height: 50px;
                background-color: #FFFFFF;
                color: black;
                border: none;
                font-size: 30px;
            }
        </style>
    </c:param>
    <c:param name="content">
        <div class="settlement">
            <form action="SettlementServlet" method="post">
                <input type="text" name="history" placeholder="���Ɏg����?"><br>
                <input type="number" min="0" name="price" placeholder="������"><br>
                <input type="hidden" name="qrcode" value="${param.RandomString}"><br>
                <input type="submit" value="����"><br>
            </form>
        </div>
    </c:param>
</c:import>