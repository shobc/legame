<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�`���b�g�ꗗ</title>
</head>
<body>
<h1>�`���b�g�ꗗ</h1>
<table>
    <tr><td>�g�[�N</td><td>�摜</td><td>���O</td></tr>
    <c:forEach var="cl" items="${chatList}">
        <tr><td><a href="TalkPageServlet?chat_id=${cl.chat_id}">�g�[�N</a></td><td style="width: 20%;height: 100%;"><img src="${cl.top_picture}" height="20%" width="80%" style="border: solid;"></td><td>${cl.name}</td></tr>
    </c:forEach>
</table>
<a href="profilePage">home</a>
</body>
</html>