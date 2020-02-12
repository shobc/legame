<%@page pageEncoding="windows-31j"
         contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="HomePageServlet">プロフィールに戻る</a>
<a href="ShowFriendQRServlet">QR表示</a>
    <h1>検索</h1>
    <p><a href="BlockUserListServlet">ブロックリスト</a></p>
    <form action="FriendSearchServlet" method="post">
        <input type="search" name="id" placeholder="IDを入力" required>
        <input type="submit" value="検索">
    </form>
    <table>
        <tr>
            <td>picture</td><td>search_id</td><td>name</td><td>single_word</td>
        </tr>
        <tr><c:if test="${not empty requestScope.fb}">
            <td><img src="data:image;base64,${requestScope.fb.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${requestScope.fb.search_id}</td>
            <td>${requestScope.fb.name}</td>
            <td>${requestScope.fb.single_word}</td>
            <td><a href="FriendAddServlet?friend_id=${requestScope.fb.user_id}">追加</a></td>
        </c:if>
        </tr>
    </table>
<p>友達かも</p>
    <table>
        <tr>
            <td>picture</td><td>name</td><td>詳細</td><td>追加</td><td>ブロック</td>
        </tr>
        <c:forEach var="nfl" items="${newFrinedList}">
            <tr><td><img src="data:image;base64,${nfl.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${nfl.name}</td>
            <td><a href="NewFriendInfoServlet?friend_id=${nfl.user_id}">詳細</a></td>
            <td><a href="FriendAddServlet?friend_id=${nfl.user_id}">追加</a></td>
            <td><a href="NoFriendBlockServlet?friend_id=${nfl.user_id}">ブロック</a></td></tr>
        </c:forEach>
    </table>
</body>
</html>