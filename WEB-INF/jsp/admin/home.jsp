<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>タイムライン</title>
</head>
<body>
<h1>タイムライン</h1>
<p>ログイン中のユーザー${sessionScope.aub.user_name}</p>
<a href="AdminHomePageServlet">すべてのタイムライン</a>
<a href="ReportTimeLineServlet">通報されたタイムライン</a>
<a href="ReportChatListServlet">通報されたチャット</a>
<a href="UserListServlet">ユーザー設定</a>
<a href="#">お店の申請許可</a>
<a href="LogoutServlet">ログアウト</a>
<table border="1">
    <tr><th>名前&写真</th><th>時間</th><th>投稿内容</th><th>写真</th></tr>
    <c:forEach var="tll" items="${timelineList}">
        <tr>
            <td>${tll.name}<img src="data:image;base64,${tll.top_picture}" height="10%"></td>
            <td>${tll.time}</td>
            <td>${tll.timeline_sentence}</td>
            <c:forEach var="tt" items="${tll.timeline_picutre}">
                <td><img src="data:image;base64,${tt.base64Image}" width="100%" height="10%"/></td>
            </c:forEach>
            <c:if test="${not empty tll.report_count}">
                <td><span>通報数${tll.report_count}</span></td>
            </c:if>
            <td><a href="DeleteTimeLineServlet?timeline_id=${tll.timeline_id}">削除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>