<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>プロフィール設定</title>
</head>
<body>
<a href="HomePageServlet">home</a>
<h1>プロフィール設定</h1>
<table>
    <tr><td>name:</td><td><a href="changename">${sessionScope.ub.name}</a></td></tr>
    <tr><td>search_id:</td><td><a href="changesearchid">${sessionScope.ub.search_id}</a></td></tr>
    <tr><td>single_word:</td><td><a href="changesingleword">${sessionScope.ub.single_word}</a></td></tr>
    <tr><td>top_picture:</td><td><img src="data:image;base64,${sessionScope.ub.top_picture}" style="height: 200px;width: 200px;"></td></tr>
    <tr><td><a href="changepassword">パスワード変更</a></td></tr>
</table>
${pageContext.request.servletPath}
</body>
</html>