<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>�ʕ񂳂ꂽ�`���b�g�ꗗ</title>
</head>
<body>
<h1>�ʕ񂳂ꂽ�`���b�g�ꗗ</h1>
<p>���O�C�����̃��[�U�[${sessionScope.aub.user_name}</p>
<a href="AdminHomePageServlet">���ׂẴ^�C�����C��</a>
<a href="ReportTimeLineServlet">�ʕ񂳂ꂽ�^�C�����C��</a>
<a href="ReportChatListServlet">�ʕ񂳂ꂽ�`���b�g</a>
<a href="UserListServlet">���[�U�[�ݒ�</a>
<a href="#">���X�̐\������</a>
<a href="LogoutServlet">���O�A�E�g</a>
<table border="1">
    <tr><th>����ID</th><th>����</th></tr>
    <c:forEach var="cl" items="${chatList}">
        <tr>
            <td>${cl.name}<img src="data:image;base64,${cl.top_picture}" height="10%"></td>
            <td><a href="TalkPageServlet?chat_id=${cl.chat_id}">�g�[�N������</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>