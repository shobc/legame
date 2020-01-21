<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>チャット一覧</title>
</head>
<body>
<h1>チャット一覧</h1>
<table>
    <tr><td>トーク</td><td>画像</td><td>名前</td></tr>
    <c:forEach var="cl" items="${chatList}">
        <tr><td><a href="TalkPageServlet?chat_id=${cl.chat_id}">トーク</a></td><td style="width: 20%;height: 100%;"><img src="data:image;base64,${cl.top_picture}" height="20%" width="80%" style="border: solid;"></td><td>${cl.name}</td></tr>
    </c:forEach>
</table>
<a href="HomePageServlet">home</a>
</body>
</html>