<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理者ログインページ</title>
</head>
<body>
<h1>管理者ログインページ</h1>
    <form action="AdminHomePageServlet" method="post">
        ユーザー名：<input type="text" name="name" required><br>
        メールアドレス：<input type="email" name="mail" required><br>
        パスワード：<input type="password" name="pass" required><br><br>
        <input type="submit" value="ログイン">
    </form>
</body>
</html>