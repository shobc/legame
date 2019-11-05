<%@page pageEncoding="windows-31j"
         contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>検索</h1>
    <form action="FriendSearchServlet" method="post">
        <input type="search" name="id" placeholder="IDを入力">
        <input type="submit" value="検索">
    </form>
    <table>
        <tr>
            <td>picture</td><td>search_id</td><td>name</td><td>single_word</td>
        </tr>
        <tr>
            <td><img src="${requestScope.ub.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${requestScope.ub.search_id}</td>
            <td>${requestScope.ub.name}</td>
            <td>${requestScope.ub.single_word}</td>
            <a href="FriendAddServlet?friend_id=${requestScope.ub.user_id}">追加</a>
        </tr>
    </table>
</body>
</html>