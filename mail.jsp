<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>メールアドレス</title>
</head>
<body>
<p>${error}</p>
<h1>メールアドレス入力</h1>
<form action="ReminderPassServlet" method="post">
    メールアドレス：<input type="mail" name="mail"><br>
    <input type="submit" value="送信">
</form>
</body>
</html>
