<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>profile</title>
    <script>
        function profilePage(action,id){
            $('<form/>',{action:action, method:"post"})
                .append("<input type='hidden' name='id' value='"+id+"'>")
                .appendTo($('body'))
                .submit();
        }
    </script>
</head>
<body>
<p><a href="deletesession">���O�A�E�g</a></p>
<p>�ʒm${noticeCount}<a href="NewFriendListServlet">�F�B����</a></p>
<p><a href="profilesetting">�v���t�B�[���ݒ�</a></p>
<h1>profile</h1>
<table>
    <tr><td>id</td><td>name</td><td>comment</td><td>picture</td></tr>
    <tr><td> ${sessionScope.ub.search_id}</td><td> ${sessionScope.ub.name}</td><td> ${sessionScope.ub.single_word}</td><td><a onclick="profilePage('ProfilePageServlet',${sessionScope.ub.user_id});return false;" href="#"><img src="data:image;base64,${sessionScope.ub.top_picture}" height="20%" width="10%" style="border: solid;"></a></td></tr>
</table>
</video>
<h1>�F�B���X�g</h1>
    <table border="1">
        <tr><td>picture</td><td>name</td><td>comment</td></tr>
        <c:forEach var="fl" items="${friendList}">
        <tr>
            <td style="width: 20%;height: 100%;"><a onclick="profilePage('ProfilePageServlet',${fl.user_id});return false;" href="#">�v���t�B�[����<img src="data:image;base64,${fl.top_picture}" height="20%" width="80%" style="border: solid;"></a></td>
            <td>${fl.name}</td>
            <td>${fl.single_word}</td>
        </c:forEach>
    </table>
<a href="ChatPageServlet">�`���b�g���</a>
<a href="app.jsp">app</a>
<a href="TimeLineServlet">�^�C�����C��</a>
</body>
</html>
