<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-admin-top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/adminShop/login.css' />">
        <title>パスワード忘れた方</title>
        <style>
            .upload-img-btn {
                margin: 22px auto;
                padding: 15px;
                border-radius: 4px;
                text-align: center;
                display: block;
                color: rgba(5, 210, 5, 0.79);
                cursor: pointer;
            }
        </style>
    </c:param>
    <c:param name="content">
        <div class="header">
            <div>
                <a href="login"><img src="<c:url value='/image/LegamePay_logo.png' />" alt="legame" width="30%" height="80%"></a>
            </div>
        </div>
        <div class="header">
            <div>
                <h1>パスワードを変更する</h1>
            </div>
        </div>
        <div class="admin_shop_login">
            <div class="group">
                <form action="ChangePassWordServlet" method="post">
                    <input type="hidden" name="shop_admin_user_id" value="${requestScope.shop_admin_user_id}">
                    <div class="value_box">
                        <div class="value_inner">
                            <input class="insert" type="password" name="password" placeholder="パスワード入力" required>
                        </div>
                    </div>
                    <div class="value_box">
                        <div class="value_inner">
                            <input class="insert" type="password" name="confirm" placeholder="パスワード入力" required>
                        </div>
                    </div>
                    <div class="submit_box">
                        <div class="submit_inner">
                            <input class="btn" type="submit" value="送信">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </c:param>
</c:import>