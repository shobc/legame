<%@page pageEncoding="Windows-31J"
    contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>���O�C��</title>
</head>
<body>
<p>${requestScope.error}</p>
<c:if test="${requestScope.target==null}" var="target"/>
<h1>���O�C��</h1>
<c:if test="${target}">
<form action="/legame/profile" method="post">
</c:if>
<c:if test="${!target}">
<form action="/legame${requestScope.target}" method="post">
</c:if>
<%--<form action="/legame${requestScope.target}" method="post">--%>
���[���A�h���X�F<input type="email" name="mail"><br>
  �p�X���[�h�F<input type="password" name="pass"><br><br>
  <input type="submit" value="���O�C��">
</form>
<a href="registerAccount">����o�^</a>
</body>
</html>
