<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>profile</title>
</head>
<body>
<p>�ʒm${noticeCount}<a href="NewFriendListServlet">�F�B����</a></p>
<h1>profile</h1>
<p><a href="deletesession">���O�A�E�g</a></p>
<table>
    <tr><td>id</td><td>name</td><td>comment</td><td>picturePath</td><td>picture</td></tr>
    <tr><td> ${sessionScope.ub.search_id}</td><td> ${sessionScope.ub.name}</td><td> ${sessionScope.ub.single_word}</td><td> ${sessionScope.ub.top_picture}</td><td><img src="${sessionScope.ub.top_picture}" height="20%" width="10%" style="border: solid;"></td></tr>
</table>
</video>
<h1>�F�B���X�g</h1>
    <table border="1">
        <tr><td>picture</td><td>name</td><td>comment</td></tr>
        <c:forEach var="fl" items="${friendList}">
        <tr>
            <td style="width: 20%;height: 100%;"><img src="${fl.top_picture}" height="20%" width="80%" style="border: solid;"></td>
            <td>${fl.name}</td>
            <td>${fl.single_word}</td>
            <td><a href="RegisterChatServlet?friend_id=${fl.user_id}">�`���b�g����</a></td>
            <td><a href="BlockUserServlet?user_id=${fl.user_id}">�u���b�N</a></td></tr>
        </c:forEach>
    </table>
<a href="ChatPageServlet">�`���b�g���</a>
<a href="app.jsp">app</a>
<a href="TimeLineServlet">�^�C�����C��</a>
</body>
</html>
