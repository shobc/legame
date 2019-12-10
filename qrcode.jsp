<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>QRで決済</h1>
<img src="QRImage/${picURI}.png" height="50%" width="25%">
<a href="QRCodeServlet">QRコードを更新する</a>
</body>
</html>