<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>パスワード</title>
</head>
<body>
<h1>パスワード変更</h1>
<h2>${error}</h2>
<h2>${OK}</h2>
<form action="PassWordChangeServlet" method="post">
    古いパスワード:<input type="password" name="old_pass"><br>
    新しいパスワード：<input type="password" name="new_pass"><br>
    パスワード(確認)：<input type="password" name="confirm"><br>
    <input type="submit" value="送信">
</form>
</body>
</html>
