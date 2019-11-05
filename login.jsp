<%@page pageEncoding="Windows-31J"
    contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>ログイン</title>
</head>
<body>
<p>${requestScope.error}</p>
<c:if test="${requestScope.target==null}" var="target"/>
<h1>ログイン</h1>
<c:if test="${target}">
<form action="/legame/profile" method="post">
</c:if>
<c:if test="${!target}">
<form action="/legame${requestScope.target}" method="post">
</c:if>
<%--<form action="/legame${requestScope.target}" method="post">--%>
メールアドレス：<input type="email" name="mail"><br>
  パスワード：<input type="password" name="pass"><br><br>
  <input type="submit" value="ログイン">
</form>
<a href="registerAccount">会員登録</a>
</body>
</html>
