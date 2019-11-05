<%@page pageEncoding="Windows-31J"
    contentType="text/html; charset=Windows-31J"%>

<html>
<head>
<title>登録</title>
</head>
<body>
<h1>登録</h1>
<form action="RegisterAccountServlet" method="post">
  メールアドレス：<input type="email" name="mail"><br>
  <p>${requestScope.error}</p>
  パスワード：<input type="password" name="pass"><br>
  パスワード：<input type="password" name="pass1"><br>
<input type="submit" value="登録"><br>
</form>
</body>
</html>
