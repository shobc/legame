<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>�p�X���[�h</title>
</head>
<body>
<h1>�p�X���[�h�ύX</h1>
<h2>${error}</h2>
<h2>${OK}</h2>
<form action="PassWordChangeServlet" method="post">
    �Â��p�X���[�h:<input type="password" name="old_pass"><br>
    �V�����p�X���[�h�F<input type="password" name="new_pass"><br>
    �p�X���[�h(�m�F)�F<input type="password" name="confirm"><br>
    <input type="submit" value="���M">
</form>
</body>
</html>
