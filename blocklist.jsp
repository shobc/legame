<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>ブロックリスト</h1>
<table>
    <tr>
        <td>picture</td><td>search_id</td><td>name</td>
    </tr>
    <c:forEach var="bl" items="${blocklist}">
        <tr><td><img src="${bl.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${bl.name}</td>
            <td><a href="ReleaseFriendServlet?friend_id=${bl.user_id}">解除</a></td>
            <td><a href="DeleteFriendServlet?friend_id=${bl.user_id}">削除</a></td></tr>
    </c:forEach>
</table>
<a href="NewFriendListServlet">友達リストに戻る</a>
</body>
</html>