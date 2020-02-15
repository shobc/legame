<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>�o�^���[�U�[</title>
</head>
<body>
<h1>�o�^���[�U�[</h1>
<p>���O�C�����̃��[�U�[${sessionScope.aub.user_name}</p>
<a href="AdminHomePageServlet">���ׂẴ^�C�����C��</a>
<a href="ReportTimeLineServlet">�ʕ񂳂ꂽ�^�C�����C��</a>
<a href="ReportChatListServlet">�ʕ񂳂ꂽ�`���b�g</a>
<a href="UserListServlet">���[�U�[�ݒ�</a>
<a href="#">���X�̐\������</a>
<a href="LogoutServlet">���O�A�E�g</a>
<p>���[�U�[����</p>
<form action="SearchUserServlet" method="get">
    <input type="text" name="search_id">
    <input type="submit" value="����">
</form>
<table border="1">
    <tr><th>�ʐ^</th><th>����ID</th><th>����</th></tr>
    <c:forEach var="ul" items="${userList}">
        <tr>
            <td><img src="data:image;base64,${ul.top_picture}" height="10%"></td>
            <td>${ul.search_id}</td>
            <c:choose>
                <c:when test = "${ul.stop_flag=='0'}">
                    <td><a href="ReleaseUserServlet?user_id=${ul.user_id}">����</a></td>
                </c:when>
                <c:otherwise>
                    <td><a href="StopUserServlet?user_id=${ul.user_id}">��~</a></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>