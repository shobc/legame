<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="HomePageServlet">プロフィールに戻る</a>
<table>
    <tr>
        <td>picture</td><td>name</td><td>single_word</td><td>追加</td>
    </tr>
    <tr>
        <td><img src="data:image;base64,${requestScope.fb.top_picture}" height="20%" width="10%" style="border: solid;"></td>
        <td>${requestScope.fb.name}</td>
        <td>${requestScope.fb.single_word}</td>
        <td><a href="FriendAddServlet?friend_id=${requestScope.fb.user_id}">追加</a></td>
    </tr>
</table>
</body>
</html>