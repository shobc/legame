<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�g�[�N�ꗗ</title>
    <script>

    </script>
</head>
<body>
<h1><a href="ChatPageServlet">�`���b�g�ꗗ�y�[�W�ɖ߂�</a>�g�[�N�ꗗ</h1>
<table border="1">
    <tr><td>���O</td><td>�摜</td><td>����</td><td>���e</td><td>����</td></tr>
    <c:forEach var="tl" items="${talkList}">
        <tr>
            <td>${tl.name}</td>
            <td style="width: 20%;height: 100%;"><img src="${tl.top_picture}" height="10%" width="10%" style="border: solid;"></td>
            <td>${tl.mess_time}</td>
            <td>${tl.content}</td>
            <td>${tl.read_flag}</td>
            <c:forEach var="tt" items="${tl.talk_picture}">
            <td><img src="data:image;base64,${tt.base64Image}" width="30%" height="10%"/></td>
        </c:forEach>
        </tr>
    </c:forEach>
</table>
<form action="RegisterTalkServlet" enctype="multipart/form-data" method="post">
    <input type="hidden" name="chat_id" value="${requestScope.chat_id}">
    ${inputText}
</form>
</body>
</html>