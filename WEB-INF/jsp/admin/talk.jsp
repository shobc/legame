<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>�ʕ񂳂ꂽ�`���b�g�ɑ΂��Ẵg�[�N</title>
</head>
<body>
<h1>�ʕ񂳂ꂽ�`���b�g�ɑ΂��Ẵg�[�N</h1>
<a href="ReportChatListServlet">�߂�</a>
<table border="1">
    <tr><th>����ID</th><th>���e</th><th>����</th></tr>
    <c:forEach var="tl" items="${talkList}">
        <tr>
            <td>${tl.search_id}<img src="data:image;base64,${tl.top_picture}" height="10%"></td>
            <td>${tl.content}</td>
            <td>${tl.mess_time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>