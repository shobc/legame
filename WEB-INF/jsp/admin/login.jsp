<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�Ǘ��҃��O�C���y�[�W</title>
</head>
<body>
<h1>�Ǘ��҃��O�C���y�[�W</h1>
    <form action="AdminHomePageServlet" method="post">
        ���[�U�[���F<input type="text" name="name" required><br>
        ���[���A�h���X�F<input type="email" name="mail" required><br>
        �p�X���[�h�F<input type="password" name="pass" required><br><br>
        <input type="submit" value="���O�C��">
    </form>
</body>
</html>