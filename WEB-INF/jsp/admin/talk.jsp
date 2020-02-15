<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>通報されたチャットに対してのトーク</title>
</head>
<body>
<h1>通報されたチャットに対してのトーク</h1>
<a href="ReportChatListServlet">戻る</a>
<table border="1">
    <tr><th>検索ID</th><th>内容</th><th>時間</th></tr>
    <c:forEach var="tl" items="${talkList}">
        <tr>
            <td>${tl.search_id}<img src="data:image;base64,${tl.top_picture}" height="10%"></td>
            <td>${tl.content}</td>
            <td>${tl.mess_time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>