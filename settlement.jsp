<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>決済ページ</title>
</head>
<body>
    <form action="SettlementServlet" method="post">
        何に使った？<input type="text" name="history"><br>
        いくら？<input type="number" name="price"><br>
        <input type="hidden" name="qrcode" value="${param.RandomString}"><br>
        <input type="submit" value="決済"><br>
    </form>
</body>
</html>