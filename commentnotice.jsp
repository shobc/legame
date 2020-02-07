<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>コメント通知</title>
</head>
<body>
<h1>コメント通知</h1>
<table>
    <c:forEach var="cn" items="${commentNotice}">
        <tr>
            <td><a href="CommentSearchServlet?timeline_id=${cn.timeline_id}">投稿を見る</a></td>
            <td>${cn.name}さんからコメントがありました</td>
            <td>${cn.timeline_time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>