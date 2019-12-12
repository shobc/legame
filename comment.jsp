<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>タイムライン</title>
</head>
<body>
<h1>選択されたタイムライン</h1>
<table border="1">
<tr><th>名前&写真</th><th>時間</th><th>コメント</th></tr>
    <tr>
        <td><a href="${tlb.user_id}">${tlb.name}<img src="${tlb.top_picture}"></a></td>
        <td>${tlb.timeline_time}</td>
        <td>${tlb.timeline_sentence}</td>
    </tr>
</table>
<h1>コメント一覧</h1>
<table border="1">
    <tr><th>名前&写真</th><th>時間</th><th>コメント</th></tr>
    <c:forEach var="ca" items="${commentArray}">
        <tr>
            <td><a href="${ca.user_id}">${ca.name}<img src="${ca.top_picture}"></a></td>
            <td>${ca.comment_time}</td>
            <td>${ca.comment_sentence}</td>
        </tr>
    </c:forEach>
</table>
<form action="CommentAddServlet" method="post">
    <input type="hidden" value="${tlb.timeline_id}" name="timeline_id">
    <input type="text" name="comment"><br>
    <input type="submit" value="コメント">
</form>
</body>
</html>