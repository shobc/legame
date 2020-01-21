<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>名前を変更</title>
</head>
<body>
<h1>名前</h1>
<table>
    <form action="ChangeNameServlet" method="post">
        <input type="text" name="name" value="${sessionScope.ub.name}">
        <input type="submit" value="変更">
    </form>
</table>
</body>
</html>