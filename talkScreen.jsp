<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>トーク一覧</title>
</head>
<body>
<h1><a href="ChatPageServlet">チャット一覧ページに戻る</a>トーク一覧</h1>
<table border="1">
    <tr><td>名前</td><td>画像</td><td>時間</td><td>内容</td></tr>
    <c:forEach var="tl" items="${talkList}">
        <tr><td>${tl.name}</td>
            <td style="width: 20%;height: 100%;"><img src="${tl.top_picture}" height="20%" width="80%" style="border: solid;"></td>
        <td>${tl.mess_time}</td>
        <td>${tl.content}</td></tr>
    </c:forEach>
</table>
<form action="RegisterTalkServlet" method="post">
    <input type="hidden" name="chat_id" value="${requestScope.chat_id}">
    <input type="text" name="content"><br>
    <input type="file" name="contentPicture"><br>
    <input type="submit" value="送信"><br>
</form>
</body>
</html>