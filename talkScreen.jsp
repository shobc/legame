<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�g�[�N�ꗗ</title>
</head>
<body>
<h1><a href="ChatPageServlet">�`���b�g�ꗗ�y�[�W�ɖ߂�</a>�g�[�N�ꗗ</h1>
<table border="1">
    <tr><td>���O</td><td>�摜</td><td>����</td><td>���e</td></tr>
    <c:forEach var="tl" items="${talkList}">
        <tr><td>${tl.name}</td>
            <td style="width: 20%;height: 100%;"><img src="${tl.top_picture}" height="20%" width="80%" style="border: solid;"></td>
        <td>${tl.mess_time}</td>
        <td>${tl.content}</td></tr>
    </c:forEach>
</table>
<form action="RegisterTalkServlet" method="post">
    <input type="hidden" name="chat_id" value="${requestScope.chat_id}">
    <input type="text" name="content"><br>
    <input type="file" name="contentPicture"><br>
    <input type="submit" value="���M"><br>
</form>
</body>
</html>