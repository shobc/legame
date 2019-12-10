<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>パスワード</title>
</head>
<body>
<h1>パスワード再発行</h1>
<form action="PassChangServlet" method="post">
    <input type ="hidden" value="${param.RandomCode}" name="Random">
    パスワード：<input type="password" name="pass"><br>
    パスワード(確認)：<input type="password" name="confirm"><br>
    <input type="submit" value="送信">
</form>
</body>
</html>
