<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-admin-top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/adminShop/login.css' />">
        <title>�m�F�y�[�W</title>
        <style>
            .confirm{
                margin-top: 200px;
                text-align: center;
            }
        </style>
    </c:param>
    <c:param name="content">
        <div class="header">
            <div>
                <img src="<c:url value='/image/LegamePay_logo.png' />" alt="legame" width="30%" height="80%">
            </div>
        </div>
        <div class="confirm">
            <h1>${message}</h1>
            <a href="login">���O�C���y�[�W�ɖ߂�</a>
        </div>
    </c:param>
</c:import>