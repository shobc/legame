<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理者ログインページ</title>
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/admin/login.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/css/admin/login.css' />">
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
</head>
<body>
<div class="image">
    <img src="<c:url value='/image/log.PNG' />" height="10%" width="30%">
</div>
    <div class="group">
        <form action="AdminHomePageServlet" method="post">
        <div class="value_box">
            <div class="value_inner">
                <input class="insert" type="text" name="name" placeholder="ユーザー名" required>
            </div>
        </div>
        <div class="value_box">
            <div class="value_inner">
                <input class="insert" type="email" name="mail" placeholder="メールアドレス" required>
            </div>
        </div>
        <div class="value_box">
            <div class="value_inner">
                <input class="insert" type="password" name="pass" placeholder="パスワードを入力" required>
            </div>
        </div>
        <div class="submit_box">
            <div class="submit_inner">
                <input class="btn" type="submit" value="ログイン">
            </div>
        </div>
        </form>
    </div>
</body>
</html>