<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>通報されたチャット一覧</title>
</head>
<body>
<h1>通報されたチャット一覧</h1>
<p>ログイン中のユーザー${sessionScope.aub.user_name}</p>
<a href="AdminHomePageServlet">すべてのタイムライン</a>
<a href="ReportTimeLineServlet">通報されたタイムライン</a>
<a href="ReportChatListServlet">通報されたチャット</a>
<a href="UserListServlet">ユーザー設定</a>
<a href="#">お店の申請許可</a>
<a href="LogoutServlet">ログアウト</a>
<table border="1">
    <tr><th>検索ID</th><th>時間</th></tr>
    <c:forEach var="cl" items="${chatList}">
        <tr>
            <td>${cl.name}<img src="data:image;base64,${cl.top_picture}" height="10%"></td>
            <td><a href="TalkPageServlet?chat_id=${cl.chat_id}">トークを見る</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>