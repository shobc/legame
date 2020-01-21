<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>ユーザー検索IDを変更</title>
</head>
<body>
<h1>ユーザー検索ID</h1>
<table>
    <form action="ChangeSearchIdServlet" method="post">
        <input type="text" name="search_id" value="${sessionScope.ub.search_id}">
        <input type="submit" value="変更">
    </form>
</table>
</body>
</html>