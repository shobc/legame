<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>�p�X���[�h</title>
</head>
<body>
<h1>�p�X���[�h�Ĕ��s</h1>
<form action="PassChangServlet" method="post">
    <input type ="hidden" value="${param.RandomCode}" name="Random">
    �p�X���[�h�F<input type="password" name="pass"><br>
    �p�X���[�h(�m�F)�F<input type="password" name="confirm"><br>
    <input type="submit" value="���M">
</form>
</body>
</html>
