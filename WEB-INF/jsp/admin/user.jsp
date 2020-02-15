<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>登録ユーザー</title>
</head>
<body>
<h1>登録ユーザー</h1>
<p>ログイン中のユーザー${sessionScope.aub.user_name}</p>
<a href="AdminHomePageServlet">すべてのタイムライン</a>
<a href="ReportTimeLineServlet">通報されたタイムライン</a>
<a href="ReportChatListServlet">通報されたチャット</a>
<a href="UserListServlet">ユーザー設定</a>
<a href="#">お店の申請許可</a>
<a href="LogoutServlet">ログアウト</a>
<p>ユーザー検索</p>
<form action="SearchUserServlet" method="get">
    <input type="text" name="search_id">
    <input type="submit" value="検索">
</form>
<table border="1">
    <tr><th>写真</th><th>検索ID</th><th>判定</th></tr>
    <c:forEach var="ul" items="${userList}">
        <tr>
            <td><img src="data:image;base64,${ul.top_picture}" height="10%"></td>
            <td>${ul.search_id}</td>
            <c:choose>
                <c:when test = "${ul.stop_flag=='0'}">
                    <td><a href="ReleaseUserServlet?user_id=${ul.user_id}">解除</a></td>
                </c:when>
                <c:otherwise>
                    <td><a href="StopUserServlet?user_id=${ul.user_id}">停止</a></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>