<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-admin-top-layout.jsp">
    <c:param name="head">
        <script src="<c:url value='/js/adminShop/shop-admin-top-layout.js' />"></script>
        <link rel="stylesheet" href="<c:url value='/css/adminShop/shop-top-login-layout.css' />">
        ${param.head}
    </c:param>
    <c:param name="content">
        <div id="hamburger">
            <div id="nav-open"><span></span></div>
        </div>
        <div class="header">
            <div>
                <img src="<c:url value='/image/LegamePay_logo.png' />" class="logo" alt="legame" width="30%" height="80%">
            </div>
        </div>
        <div id="nav-content">
            <div class="hamburger-top"></div>
            <li class="category-title user">ログインユーザ:${sessionScope.saub.user_name}</li>
            <ul class="category">
                <li class="category-title"><a href="ShopAdminHomeServlet">ホーム</a></li>
                <li class="category-title"><a href="CancelListServlet">返金リスト</a></li>
                <li class="category-title logout"><img src="<c:url value='/image/logout.png' />" id="logoutImage" height="3%" width="9%"><a href="LogoutServlet">ログアウト</a></li>
                <li class="category-title" style="text-align: center"><a href="RemoveAccountServlet">アカウント削除</a></li>
            </ul>
        </div>
        ${param.content}
    </c:param>
</c:import>