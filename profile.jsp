<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>${ub.name}</title>
</head>
<body>
<a href="HomePageServlet">home</a>
<h1>${ub.name}さんのプロフィール</h1>
<table>
    <td style="width: 20%;height: 100%;"><img src="data:image;base64,${ub.top_picture}" height="20%" width="80%" style="border: solid;"></td>
    <td>${ub.name}</td>
    <td>${ub.single_word}</td>
    <td><a href="RegisterChatServlet?friend_id=${ub.user_id}">チャットする</a></td>
    <td><a href="BlockUserServlet?user_id=${ub.user_id}">ブロック</a></td></tr>
</table>
</body>
</html>