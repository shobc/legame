<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�`���b�g�ꗗ</title>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        function sendPost(action,parameter,id){
            $('<form/>',{action:action, method:"post"})
                .append("<input type='hidden' name='"+parameter+"' value='"+id+"'>")
                .appendTo($('body'))
                .submit();
        }
    </script>
</head>
<body>
<h1>�`���b�g�ꗗ</h1>
<table>
    <tr><td>�g�[�N</td><td>�摜</td><td>���O</td><td>�폜</td></tr>
    <c:forEach var="cl" items="${chatList}">
        <tr>
            <td><a onclick="sendPost('TalkPageServlet','chat_id',${cl.chat_id})" href="#">�g�[�N</a></td>
            <td style="width: 20%;height: 100%;"><img src="data:image;base64,${cl.top_picture}" height="20%" width="80%" style="border: solid;"></td>
            <td>${cl.name}</td>
            <td>${cl.content}</td>
            <td>${cl.not_read_count}</td>
            <td><a href="DeleteChatServlet?chat_id=${cl.chat_id}">�폜</a></td>
        </tr>
    </c:forEach>
</table>
<a href="HomePageServlet">home</a>
</body>
</html>