<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�A�v���ꗗ</title>
</head>
<body>
    <a href="WalletPageServlet">�E�H���b�g</a>
    <a href="#">�f��i�܂������Ȃ���j</a>
    <h1></h1>
    <h1>${title}</h1>
    <a href="AccessAppServlet?number=1">��v</a>
    <a href="AccessAppServlet?number=2">����</a>
    <a href="AccessAppServlet?number=3">����</a>
    <a href="AccessAppServlet?number=4">�o��</a>
    <a href="AccessAppServlet?number=5">�G���^��</a>
    <a href="AccessAppServlet?number=6">�X�|�[�c</a>
    <a href="AccessAppServlet?number=7">IT</a>
    <a href="AccessAppServlet?number=8">�Ȋw</a>
    <a href="AccessAppServlet?number=9">�n��</a>
    <table border="1">
        </tr>
        <c:forEach var="nl" items="${newsList}">
            <tr>
                <td>
                    <span><a href="${nl.link}" target="_blank">${nl.title}</a></span><br>
                    <span>${nl.time}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>