<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>�^�C�����C��</title>
</head>
<body>
<h1>�^�C�����C��</h1>
<p>���O�C�����̃��[�U�[${sessionScope.aub.user_name}</p>
<a href="AdminHomePageServlet">���ׂẴ^�C�����C��</a>
<a href="ReportTimeLineServlet">�ʕ񂳂ꂽ�^�C�����C��</a>
<a href="ReportChatListServlet">�ʕ񂳂ꂽ�`���b�g</a>
<a href="UserListServlet">���[�U�[�ݒ�</a>
<a href="#">���X�̐\������</a>
<a href="LogoutServlet">���O�A�E�g</a>
<table border="1">
    <tr><th>���O&�ʐ^</th><th>����</th><th>���e���e</th><th>�ʐ^</th></tr>
    <c:forEach var="tll" items="${timelineList}">
        <tr>
            <td>${tll.name}<img src="data:image;base64,${tll.top_picture}" height="10%"></td>
            <td>${tll.time}</td>
            <td>${tll.timeline_sentence}</td>
            <c:forEach var="tt" items="${tll.timeline_picutre}">
                <td><img src="data:image;base64,${tt.base64Image}" width="100%" height="10%"/></td>
            </c:forEach>
            <c:if test="${not empty tll.report_count}">
                <td><span>�ʕ�${tll.report_count}</span></td>
            </c:if>
            <td><a href="DeleteTimeLineServlet?timeline_id=${tll.timeline_id}">�폜</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>