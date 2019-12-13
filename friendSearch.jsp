<%@page pageEncoding="windows-31j"
         contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="profilePage">プロフィールに戻る</a>
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
            <td><img src="${requestScope.fb.top_picture}" height="20%" width="10%" style="border: solid;"></td>
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
            <td>picture</td><td>search_id</td><td>name</td><td>single_word</td>
        </tr>
        <c:forEach var="nfl" items="${newFrinedList}">
            <tr><td><img src="${nfl.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${nfl.search_id}</td>
            <td>${nfl.name}</td>
            <td>${nfl.single_word}</td>
            <td><a href="FriendAddServlet?friend_id=${nfl.user_id}">追加</a></td></tr>
        </c:forEach>
    </table>
</body>
</html>